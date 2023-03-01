<template>
  <div class="my">
    <van-nav-bar @click-right="setting" title="我的">
      <template #right>
        <van-icon class="iconfont icon-shezhi" size="25" />
      </template>
    </van-nav-bar>
    <!-- 个人主页 -->

    <!-- 用户信息 -->
    <div class="user_info" @click="myZy">
      <div class="user_left">
        <img :src="avatar_path" alt="" />
      </div>
      <div class="user_center">
        <div class="center_top">
          <span class="iconfont icon-icon"></span>
          <span>{{ nickname }}</span>
          <span class="iconfont icon-xingbienan"></span>
        </div>
        <div class="center_bottom">{{ user_autograph }}</div>
      </div>
      <div class="user_right">
        <span class="iconfont icon-xiangyoujiantou"></span>
      </div>
    </div>

    <!-- 四个模块 -->
    <div v-cloak class="four_mk">
      <div class="mk_list" v-for="m in mkList" :key="m.id" @click="gomk(m.id)">
        <span>{{ m.sum }}</span>
        <span>{{ m.title }}</span>
      </div>
    </div>

    <!-- 三个选项 -->
    <van-cell-group>
      <van-cell is-link to="mysave">
        <!-- 使用 right-icon 插槽来自定义右侧图标 -->
        <template #icon>
          <span class="iconfont icon-shoucang"></span>
        </template>
        <template #title>
          <span class="custom-title">我的收藏</span>
        </template>
      </van-cell>
      <van-cell is-link to="postshistory">
        <!-- 使用 right-icon 插槽来自定义右侧图标 -->
        <template #icon>
          <span class="iconfont icon-jilu"></span>
        </template>
        <template #title>
          <span class="custom-title">浏览历史</span>
        </template>
      </van-cell>
      <van-cell is-link>
        <!-- 使用 right-icon 插槽来自定义右侧图标 -->
        <template #icon>
          <span class="iconfont icon-24gl-headphones"></span>
        </template>
        <template #title>
          <span class="custom-title">服务中心</span>
        </template>
      </van-cell>
    </van-cell-group>

    <TabBar />
  </div>
</template>

<script>
import TabBar from "../components/common/TabBar.vue";

export default {
  name: "My",
  components: { TabBar },
  data() {
    return {
      user_id: 0,
      phone: 0,
      // 昵称
      nickname: "",
      // 头像
      avatar_path: "",
      // 签名
      user_autograph: "",
      mkList: [
        { id: 1, sum: 0, title: "关注" },
        { id: 2, sum: 0, title: "粉丝" },
        { id: 3, sum: 0, title: "关注的吧" },
        { id: 4, sum: 0, title: "帖子" },
      ],
    };
  },
  mounted() {
    this.user_id = localStorage.getItem("user_id");
    this.phone = localStorage.getItem("phone");
    this.nickname = localStorage.getItem("nickname");
    this.avatar_path =
      "http://localhost:8080/statics/user_avatar/" +
      localStorage.getItem("avatar");
    this.user_autograph = localStorage.getItem("user_autograph");
    this.getUserInfo();
    // 获取四个信息
    this.myFourInfo();
  },
  methods: {
    // 获取我的四个信息
    async myFourInfo() {
      await this.$http
        .post(`/user/myfourinfo?user_id=${this.user_id}`)
        .then((res) => {
          console.log(res);
          this.mkList[0].sum = res.data.data[0].user_like_sum;
          this.mkList[1].sum = res.data.data[0].like_user_sum;
          this.mkList[2].sum = res.data.data[0].like_bar_sum;
          this.mkList[3].sum = res.data.data[0].user_posts_sum;
        });
    },
    // 获取用户信息
    async getUserInfo() {
      console.log(1);
      await this.$http
        .post(`/user/info?user_id=${this.user_id}&phone=${this.phone}`)
        .then((res) => {
          console.log(res);
          // 将用户信息存储到本地
          localStorage.setItem("avatar", res.data.data[0].avatar);
          localStorage.setItem("nickname", res.data.data[0].nickname);
          localStorage.setItem("phone", res.data.data[0].phone);
          localStorage.setItem(
            "user_autograph",
            res.data.data[0].user_autograph
          );
          localStorage.setItem("user_id", res.data.data[0].user_id);
          localStorage.setItem("user_sex", res.data.data[0].user_sex);
          localStorage.setItem("user_time", res.data.data[0].user_time);
          localStorage.setItem("username", res.data.data[0].username);
        })
        .catch((err) => {
          // console.log(err);
        });
    },
    // 四个模块
    gomk(id) {
      console.log(id);
      if (id == 1) {
        this.$router.push({
          name: "userlike",
        });
      } else if (id == 2) {
        this.$router.push({
          name: "likeuser",
        });
      } else if (id == 3) {
        this.$router.push({
          name: "likebar",
        });
      } else if (id == 4) {
        this.$router.push({
          name: "myzy",
          query: {
            user_id: this.user_id,
            phone: localStorage.getItem("phone"),
          },
        });
      }
    },
    myZy() {
      console.log("去我的主页");
      this.$router.push({
        name: "myzy",
        query: {
          user_id: this.user_id,
          phone: localStorage.getItem("phone"),
        },
      });
    },
    // 去设置页面
    setting() {
      this.$router.push({
        name: "setting",
      });
    },
  },
};
</script>

<style scoped>
.van-nav-bar__right .icon-shezhi {
  margin-right: 0px;
  color: black;
}
/* 用户信息部分 */
.user_info {
  display: flex;
  width: 100%;
  height: 60px;
}
.user_info .user_left {
  text-align: center;
  width: 60px;
  height: 60px;
}
.user_info .user_left img {
  width: 50px;
  height: 50px;
  border-radius: 25px;
}
.user_info .user_center {
  padding-left: 10px;
  padding-top: 5px;
  height: 60px;
  flex: 1;
}
.user_info .user_center .center_top {
  width: 100%;
  height: 25px;
}
.user_info .user_center .center_top span:nth-child(1) {
  color: #5c5c5c;
  font-size: 20px;
}
.user_info .user_center .center_top span:nth-child(3) {
  color: blue;
}
.user_info .user_center .center_bottom {
  width: 100%;
  height: 25px;
  color: #808080;
  font-size: 12px;
}
.user_info .user_right {
  /* width: 20px; */
  height: 60px;
  line-height: 60px;
}

/* 四个模块 */
.four_mk {
  border-top: 1px solid #f3f3f3;
  width: 100%;
  height: 60px;
  display: flex;
  border-bottom: 8px solid #f3f3f3;
}

.four_mk .mk_list {
  height: 60px;
  text-align: center;
  flex: 1;
  display: flex;
  justify-content: center;
  flex-direction: column;
}
.four_mk .mk_list span {
  font-size: 16px;
}
.four_mk .mk_list span:nth-child(2) {
  font-size: 12px;
  color: #818181;
}

/* 三个选项 */
.custom-title {
  font-size: 16px;
}

.iconfont {
  font-size: 18px;
  margin-right: 15px;
}
</style>
