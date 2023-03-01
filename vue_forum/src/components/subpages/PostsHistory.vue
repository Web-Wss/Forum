<template>
  <div class="postshistory">
    <!-- 头部 -->
    <van-nav-bar
      @click-left="onClickLeft"
      @click-right="onClickRight"
      title="浏览历史"
      right-text="清空"
      left-arrow
    >
      <template #left>
        <van-icon name="arrow-left" size="20" />
      </template>
    </van-nav-bar>

    <!-- 浏览历史列表 -->
    <van-cell-group>
      <van-cell
        v-for="p in posts_history_list"
        :key="p.history_id"
        center
        :label="p.posts_bar_name"
        :value="p.history_time"
        title-class="title"
        @click="goPostsDetail(p)"
      >
        <template #title>
          <span class="custom-title">{{ p.posts_title }}</span>
        </template>
      </van-cell>
    </van-cell-group>
  </div>
</template>

<script>
export default {
  name: "PostsHistory",
  data() {
    return {
      user_id: 0,
      // 浏览历史列表
      posts_history_list: [],
    };
  },
  mounted() {
    this.user_id = localStorage.getItem("user_id");
    // 获取浏览历史
    this.getPostsHistory();
  },
  methods: {
    // 去帖子详情页
    goPostsDetail(p) {
      console.log(p.posts_id);
      this.$router.push({
        name: "postsdetail",
        query: {
          posts_id: p.posts_id,
          posts_title: p.posts_title,
          posts_bar_name: p.posts_bar_name,
        },
      });
    },
    // 获取浏览历史
    async getPostsHistory() {
      await this.$http
        .post(`/posts/getpostshistory?user_id=${this.user_id}`)
        .then((res) => {
          console.log(res);
          this.posts_history_list = res.data.data;
        });
    },
    // 去my
    onClickLeft() {
      this.$router.push({
        name: "my",
      });
    },
    // 清空
    onClickRight() {
      console.log("清空");
    },
  },
};
</script>

<style scoped>
.title {
  flex: 3;
}
</style>
