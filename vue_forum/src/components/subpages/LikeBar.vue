<template>
  <div class="likebar">
    <!-- 头部 -->
    <van-nav-bar title="我关注的吧" left-arrow @click-left="onClickLeft">
      <template #right>
        <van-icon name="cluster-o" size="18" />
        <span @click="changewz">{{ right_title }}</span>
      </template>
    </van-nav-bar>

    <!-- 关注的吧列表 -->
    <div class="bar_list">
      <div
        class="list_list"
        @click="goBar(l)"
        v-for="l in like_bar_list"
        :key="l.posts_bar_like_id"
      >
        <div class="list_left">
          <img
            :src="
              'http://localhost:8080/statics/posts_bar_logo/' +
              l.posts_bar_logo_path
            "
            alt=""
          />
        </div>
        <div class="list_center">
          <div class="center_top">{{ l.posts_bar_name }}</div>
          <div class="center_bottom">经验 7/15</div>
        </div>
        <div class="list_right">
          <button v-if="deleteshow" @click="deletelike()">删除</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "LikeBar",
  data() {
    return {
      // 编辑、完成
      right_title: "编辑",
      deleteshow: false,
      like_bar_list: [],
      user_id: 0,
    };
  },
  mounted() {
    this.user_id = localStorage.getItem("user_id");
    // 获取当前用户关注的吧
    this.UserLikeBar();
  },
  methods: {
    // 删除按钮
    deletelike() {
      console.log("删除");
    },
    // 改变文字：编辑、完成
    changewz() {
      if (this.right_title == "编辑") {
        this.right_title = "完成";
        this.deleteshow = true;
      } else {
        this.right_title = "编辑";
        this.deleteshow = false;
      }
    },

    // 去bar
    goBar(l) {
      console.log(l);
      this.$router.push({
        name: "bar",
        params: {
          bar_id: l.posts_bar_id,
          bar_name: l.posts_bar_name,
          bar_logo_path: l.posts_bar_logo_path,
        },
      });
    },
    // 回到我的
    onClickLeft() {
      this.$router.push({
        name: "my",
      });
    },
    async UserLikeBar() {
      await this.$http
        .post(`/user/userlikebar?user_id=${this.user_id}`)
        .then((res) => {
          console.log(res);
          this.like_bar_list = res.data.data;
        });
    },
  },
};
</script>

<style scoped>
.van-icon {
  margin-right: 10px;
}

/* 吧列表 */
.likebar .bar_list {
}

.likebar .bar_list .list_list {
  display: flex;
  /* background-color: aqua; */
  width: 100%;
  height: 60px;
  border-bottom: 1px solid rgb(232, 232, 232);
}
.likebar .bar_list .list_list .list_left {
  /* background-color: bisque; */
  text-align: center;
  margin-top: 5px;
  width: 60px;
}
.likebar .bar_list .list_list .list_left img {
  width: 50px;
  height: 50px;
}
.likebar .bar_list .list_list .list_center {
  margin-top: 10px;
  margin-left: 10px;
  flex: 1;
}
.likebar .bar_list .list_list .list_center .center_bottom {
  color: rgb(184, 184, 184);
  font-size: 12px;
}
.likebar .bar_list .list_list .list_right {
  width: 60px;
  /* line-height: 60px; */
  /* background-color: red; */
}
.likebar .bar_list .list_list .list_right button {
  padding: 5px 8px 5px;
  font-size: 12px;
  color: white;
  background-color: rgb(248, 82, 82);
  border: 0;
  margin-top: 15px;
}
</style>
