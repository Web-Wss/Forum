<template>
  <div class="like_user">
    <!-- 头部 -->
    <van-nav-bar fixed placeholder @click-left="gomy" title="我的粉丝">
      <template #left>
        <van-icon color="#939393" name="arrow-left" size="25" />
      </template>
    </van-nav-bar>
    <!-- 全部关注几人 -->
    <div class="likesum">全部粉丝{{ like_list.length }}人</div>
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
        <div class="list_right">
          <button
            v-if="l.isMutual == 1 && l.isMutuals == 1"
            @click="cancelLike(l)"
          >
            互相关注
          </button>
          <button v-else @click="like(l)">关注</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "LikeUser",
  data() {
    return {
      user_id: 0,
      like_list: [],
    };
  },
  mounted() {
    this.user_id = localStorage.getItem("user_id");
    // 获取我的粉丝
    this.getLikeUser();
  },
  methods: {
    // 取消关注
    cancelLike(l) {
      console.log(l);
      this.$http
        .post(
          `/user/canceluserlikeuser?user_id=${l.user_b_id}&user_b_id=${l.user_id}`
        )
        .then((res) => {
          console.log(res);
          this.getLikeUser();
        });
    },
    // 关注
    like(l) {
      console.log(l);
      this.$http
        .post(
          `/user/insertuserlike?user_id=${l.user_b_id}&user_b_id=${l.user_id}`
        )
        .then((res) => {
          console.log(res);
          this.getLikeUser();
        });
    },

    // 获取我的粉丝
    async getLikeUser() {
      await this.$http
        .post(`/user/likeuser?user_id=${this.user_id}`)
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
.like_user .likesum {
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
.like_user .like_list {
  width: 100%;
  /* background-color: rgb(120, 166, 118); */
}
.like_user .like_list .list_list {
  border-bottom: 1px solid rgb(243, 243, 243);
  display: flex;
  width: 100%;
  height: 60px;
  /* background-color: aqua; */
}
.like_user .like_list .list_list .list_left {
  width: 60px;
  position: relative;
}
.like_user .like_list .list_list .list_left img {
  width: 40px;
  height: 40px;
  position: absolute;
  left: 10px;
  top: 10px;
  border-radius: 50px;
}
.like_user .like_list .list_list .list_center {
  /* background-color: bisque; */
  flex: 1;
}
.like_user .like_list .list_list .list_center .center_top {
  margin-top: 10px;
}
.like_user .like_list .list_list .list_center .center_bottom {
  font-size: 12px;
  color: rgb(167, 167, 167);
}
.like_user .like_list .list_list .list_right {
  margin: 15px auto;
  width: 80px;
  height: 60px;
}
.like_user .like_list .list_list .list_right button {
  background-color: white;
  color: rgb(152, 152, 152);
  border: 1px solid rgb(233, 233, 233);
  font-size: 12px;
  width: 70px;
}
</style>
