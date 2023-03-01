<template>
  <div class="mysave">
    <!-- 头部 -->
    <van-nav-bar
      title="我的收藏"
      right-text="编辑"
      left-arrow
      @click-left="onClickLeft"
      @click-right="onClickRight"
    />

    <!-- 收藏列表 -->
    <div class="save_list">
      <div
        class="list_list"
        @click="goDetail(s)"
        v-for="s in save_list"
        :key="s.posts_save_id"
      >
        <div class="list_top">
          <div class="top_left">
            <div>
              <img src="https://s1.ax1x.com/2022/03/16/qpRzGT.jpg" alt="" />
            </div>
            <div class="nickname">{{ s.posts[0].users[0].nickname }}</div>
          </div>
          <div class="top_right"><button>+关注</button></div>
        </div>
        <div class="list_bottom">
          <div class="bottom_left">
            <img
              :src="
                'http://localhost:8080/statics/posts_img/' +
                s.postsImgList[0].posts_img_path
              "
              alt=""
            />
          </div>
          <div class="bottom_right">{{ s.posts[0].posts_title }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "MySave",
  data() {
    return {
      user_id: 0,
      // 收藏列表
      save_list: [],
    };
  },
  mounted() {
    this.user_id = localStorage.getItem("user_id");
    // 获取收藏列表
    this.getSaveList();
  },
  methods: {
    // 去帖子详情
    goDetail(s) {
      this.$router.push({
        name: "postsdetail",
        query: {
          posts_id: s.posts_id,
          posts_title: s.posts_title,
          posts_bar_name: s.posts_bar_name,
        },
      });
    },
    onClickLeft() {
      this.$router.push({
        name: "my",
      });
    },
    onClickRight() {},
    async getSaveList() {
      await this.$http
        .post(`/user/usersaveposts?save_user_id=${this.user_id}`)
        .then((res) => {
          console.log(res);
          this.save_list = res.data.data;
        });
    },
  },
};
</script>

<style scoped>
/* 收藏列表 */
.save_list {
  width: 100%;
}
.save_list .list_list {
  padding: 5px 10px 5px;
  height: 100px;
  border-bottom: 5px solid rgb(247, 247, 247);
}
.save_list .list_list .list_top {
  display: flex;
  width: 100%;
  height: 40px;
  /* background-color: beige; */
}
.save_list .list_list .list_top .top_left {
  display: flex;
  flex: 1;
}
.save_list .list_list .list_top .top_left img {
  border-radius: 50px;
  width: 40px;
}
.save_list .list_list .list_top .top_left .nickname {
  margin-left: 10px;
  line-height: 40px;
}
.save_list .list_list .list_top .top_right {
  margin-top: 7px;
  width: 60px;
}
.save_list .list_list .list_top .top_right button {
  font-size: 14px;
  background-color: white;
  color: rgb(53, 176, 218);
  border: 1px solid rgb(53, 176, 218);
}
.save_list .list_list .list_bottom {
  display: flex;
}
.save_list .list_list .list_bottom img {
  width: 50px;
  height: 50px;
}
.save_list .list_list .list_bottom .bottom_right {
  margin-left: 10px;
}
</style>
