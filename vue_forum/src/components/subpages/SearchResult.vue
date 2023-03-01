<template>
  <div class="searchresult">
    <!-- 搜索位置 -->
    <van-search
      @cancel="cancel"
      show-action
      v-model="value"
      placeholder="大家都在搜索:OG掉线重连"
      @search="search"
    />

    <!-- 五个span -->
    <div class="span_list">
      <span>综合</span>
      <span>帖</span>
      <span>吧</span>
      <span>人</span>
      <span>直播</span>
    </div>

    <!-- 相关吧 -->
    <div class="bar_list">
      <div class="title">相关吧</div>
      <div class="list">
        <!-- 一个吧 -->
        <div
          class="list_list"
          @click="goBar(b)"
          v-for="b in bar_list"
          :key="b.posts_bar_id"
        >
          <div class="list_list_left">
            <img
              :src="
                'http://localhost:8080/statics/posts_bar_logo/' +
                b.posts_bar_logo_path
              "
              alt=""
            />
          </div>
          <div class="list_list_center">
            <div class="center_top">{{ b.posts_bar_name }}</div>
            <div class="center_bottom">
              关注 {{ b.searchBarLikeSum }} 帖子 {{ b.searchPostsSum }}
            </div>
          </div>
          <div class="list_list_right">
            <button v-if="b.userLike != 1">关注</button>
            <button v-else>已关注</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SearchResult",
  data() {
    return {
      // 搜索到的吧列表
      bar_list: [],
      value: "",
      user_id: 0,
    };
  },
  mounted() {
    this.user_id = localStorage.getItem("user_id");
    this.value = this.$route.params.value;
    this.search();
  },
  methods: {
    // 去bar
    goBar(b) {
      console.log(b);
      this.$router.push({
        name: "bar",
        params: {
          bar_id: b.posts_bar_id,
          bar_name: b.posts_bar_name,
          bar_logo_path: b.posts_bar_logo_path,
        },
      });
    },
    // 执行搜索
    async search() {
      console.log(this.value);
      await this.$http
        .post(
          `/posts/searchpostsbar?posts_bar_name=${this.value}&user_id=${this.user_id}`
        )
        .then((res) => {
          console.log(res);
          this.bar_list = res.data.data;
        });
    },
    cancel() {
      console.log(123);
      this.$router.push({
        name: "enterbar",
      });
    },
  },
};
</script>

<style scoped>
.searchresult {
  background-color: #f9f9f9;
}
/* 搜索框 */
.van-search {
  padding: 0 0 0 0;
  border-bottom: 1px solid #e2e2e2;
}
.van-cell {
  background-color: rgba(123, 32, 1, 0);
  padding: 10px 5px;
}
.van-search__content {
  background-color: white;
}
.van-search__action {
  color: rgb(0, 153, 255);
}

van-cell {
  background-color: blueviolet;
}

/* 五个span */
.span_list {
  padding-left: 10px;
  display: flex;
  font-size: 15px;
  /* width: 100%; */
  height: 30px;
  line-height: 30px;
  /* background-color: aqua; */
}
.span_list span {
  margin-right: 5px;
  text-align: center;
  display: block;
  width: 30px;
  /* background-color: antiquewhite; */
}
.span_list span:nth-child(3) {
  width: 15px;
  border-bottom: 3px solid rgb(57, 92, 231);
}

/* 吧列表 */
.bar_list {
  margin-top: 10px;
  margin: 5px auto;
  width: 96%;
  height: 500px;
  border-radius: 10px;
  background-color: white;
}
.bar_list .title {
  /* width: 100%; */
  font-size: 14px;
  padding-left: 5px;
  line-height: 30px;
  height: 30px;
  /* background-color: aqua; */
  font-weight: 600;
}

/* 搜索结果列表 */
.bar_list .list_list {
  display: flex;
  width: 100%;
  /* background-color: aqua; */
}
.bar_list .list_list .list_list_left {
  text-align: center;
  margin-top: 4px;
  width: 50px;
  height: 50px;
}
.bar_list .list_list .list_list_left img {
  border-radius: 10px;
  width: 46px;
  height: 46px;
}
.bar_list .list_list .list_list_center {
  flex: 1;
  margin-left: 10px;
}
.bar_list .list_list .list_list_center .center_top {
  margin-top: 7px;
  font-size: 16px;
}
.bar_list .list_list .list_list_center .center_bottom {
  color: #9a9a9a;
  font-size: 12px;
}
.bar_list .list_list .list_list_right {
  text-align: center;
  margin-top: 15px;
  font-size: 14px;
  width: 70px;
}
.bar_list .list_list .list_list_right button {
  /* width: 40px; */
  border-radius: 7px;
  color: rgb(98, 79, 194);
  border: 0;
  background-color: rgb(244, 242, 255);
}
</style>
