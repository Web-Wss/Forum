<template>
  <div class="user_like">
    <!-- 头部 -->
    <van-nav-bar fixed placeholder @click-left="gomy" title="我关注的人">
      <template #left>
        <van-icon color="#939393" name="arrow-left" size="25" />
      </template>
    </van-nav-bar>

    <!-- 全部关注几人 -->
    <div class="likesum">全部关注{{ like_list.length }}人</div>
    <!-- 关注列表 -->
    <div class="like_list">
      <div class="list_list" v-for="l in like_list" :key="l.users_like_id">
        <div class="list_left">
          <img
            :src="
              'http://localhost:8080/statics/user_avatar/' +
              l.userList[0].avatar
            "
            alt=""
          />
        </div>
        <div class="list_center">
          <div class="center_top">{{ l.userList[0].nickname }}</div>
          <div class="center_bottom">{{ l.userList[0].user_autograph }}</div>
        </div>
        <div class="list_right" @click="cancellike(l)">
          <button v-if="l.isMutual == 1 && l.isMutuals == 1">互相关注</button>
          <button v-else>已关注</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserLike",
  data() {
    return {
      user_id: 0,
      like_list: [],
    };
  },
  mounted() {
    this.user_id = localStorage.getItem("user_id");
    this.getUserLike();
  },
  methods: {
    // 取消关注
    async cancellike(l) {
      this.$http
        .post(
          `/user/canceluserlikeuser?user_id=${l.user_id}&user_b_id=${l.user_b_id}`
        )
        .then((res) => {
          console.log(res);
          this.getUserLike();
        });
    },
    // 获取用户关注
    async getUserLike() {
      await this.$http
        .post(`/user/userlikeuser?user_id=${this.user_id}`)
        .then((res) => {
          console.log(res);
          this.like_list = res.data.data;
        });
    },
    gomy() {
      this.$router.push({
        name: "my",
      });
    },
  },
};
</script>

<style scoped>
.user_like .likesum {
  padding-left: 10px;
  /* width: 100%; */
  height: 30px;
  line-height: 30px;
  /* padding: 10px; */
  /* background-color: red; */
  font-size: 12px;
  color: rgb(174, 174, 174);
}
/* 关注列表 */
.user_like .like_list {
  width: 100%;
  /* background-color: rgb(120, 166, 118); */
}
.user_like .like_list .list_list {
  border-bottom: 1px solid rgb(243, 243, 243);
  display: flex;
  width: 100%;
  height: 60px;
  /* background-color: aqua; */
}
.user_like .like_list .list_list .list_left {
  width: 60px;
  position: relative;
}
.user_like .like_list .list_list .list_left img {
  width: 40px;
  height: 40px;
  position: absolute;
  left: 10px;
  top: 10px;
  border-radius: 50px;
}
.user_like .like_list .list_list .list_center {
  /* background-color: bisque; */
  flex: 1;
}
.user_like .like_list .list_list .list_center .center_top {
  margin-top: 10px;
}
.user_like .like_list .list_list .list_center .center_bottom {
  font-size: 12px;
  color: rgb(167, 167, 167);
}
.user_like .like_list .list_list .list_right {
  margin: 15px auto;
  width: 80px;
  height: 60px;
}
.user_like .like_list .list_list .list_right button {
  background-color: white;
  color: rgb(152, 152, 152);
  border: 1px solid rgb(233, 233, 233);
  font-size: 12px;
  width: 70px;
}
</style>
