<template>
  <div>
    <van-nav-bar
      :title="title"
      @click-left="onClickLeft"
      placeholder
      fixed
      left-arrow
    />

    <div class="header_tup">
      <img
        src="https://img2.baidu.com/it/u=383380894,3933217319&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=333"
        alt=""
      />
    </div>

    <!-- user_info1 -->
    <div class="user_info1">
      <div class="left">
        <img src="https://s1.ax1x.com/2022/03/16/qpRzGT.jpg" alt="" />
      </div>
      <div class="right">
        <div class="right_top">
          <div class="yin">印</div>
          <div class="jia">+</div>
        </div>
        <div class="right_bottom">
          <div class="right_bottom_bjzl" @click="goInformation">编辑资料</div>
        </div>
      </div>
      <div class="user_info1_white"></div>
    </div>

    <div class="user_info2">
      <div class="info2_list">
        <span class="iconfont icon-icon1"> </span> {{ user_info[0].nickname }}
        <span class="iconfont icon-nan"></span>
      </div>
      <div class="info2_list">
        <span>{{ like_user_sum }} </span>粉丝 |
        <span>{{ user_like_sum }} </span>关注 |
        <span>{{ like_bar_sum }} </span>关注的吧
      </div>
      <div class="info2_list">吧龄: 0年</div>
      <div class="info2_list">简介: {{ user_info[0].user_autograph }}</div>
    </div>

    <!-- 内容部分 -->
    <div class="content">
      <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
        <van-list
          v-model="loading"
          :finished="finished"
          finished-text="没有更多了"
          @load="onLoad"
        >
          <div class="content_list" v-for="t in list" :key="t.posts_id">
            <div class="content_list_header">
              <div class="header_left">
                <img
                  :src="'http://localhost:8080/statics/user_avatar/' + t.avatar"
                  alt=""
                />
              </div>
              <div class="header_right">
                <div class="header_right_top">{{ t.nickname }}</div>
                <div class="header_right_bottom">
                  {{ t.posts_bar_name }} | {{ t.posts_time }}
                </div>
              </div>
            </div>
            <div class="title">{{ t.posts_title }}</div>
            <div class="content_list_img" @click="goPostsDetail(t)">
              <div class="img_list">
                <img
                  v-for="(i, ii) in t.postsImgList"
                  :key="ii"
                  :src="
                    'http://localhost:8080/statics/posts_img/' +
                    i.posts_img_path
                  "
                  alt=""
                />
              </div>
            </div>
            <div class="content_list_bottom">
              <div class="list_bottom_list">
                <span class="iconfont icon-shouye"></span>
                {{ t.postsComList[0] }}
              </div>
              <div
                class="list_bottom_list"
                :class="[t.postsLikeUserList == 1 ? 'postslikeuser' : '']"
                @click="userlike(t)"
              >
                <span class="iconfont icon-icon"></span>
                {{ t.postsLikeList[0] }}
              </div>
            </div>
          </div>
        </van-list>
      </van-pull-refresh>
    </div>
  </div>
</template>

<script>
export default {
  name: "Myzy",
  data() {
    return {
      // 用户信息
      user_id: 0,
      yz_user_id: 0,
      title: "我的主页",
      phone: 0,
      // 用户详细信息
      user_info: [],
      // 粉丝/关注/关注的吧
      user_like_sum: 0,
      like_user_sum: 0,
      like_bar_sum: 0,

      // 获取当前用户发布的帖子
      // 下拉刷新、上拉加载
      loading: false, //loading 设置为 true，表示处于加载状态
      finished: false,
      refreshing: false,
      list: [],
      // 分页数据
      // 当前页
      pageNum: 1,
      // 每页显示条数
      pageSize: 5,
      // 总条数
      total: 26,
      // 上一页
      prePage: 0,
      // 下一页
      nextPage: 2,
      // 是否是第一页
      isFirstPage: true,
      // 是否最后一页
      isLastPage: false,
      // 获取当前用户发布的帖子
    };
  },
  mounted() {
    // console.log(this.$route);
    this.user_id = this.$route.query.user_id;
    this.yz_user_id = localStorage.getItem("user_id");
    this.phone = this.$route.query.phone;
    this.getUserInfo();
    this.myThreeInfo();
    // 获取当前用户发布的帖子
  },
  updated() {
    if (this.user_id == localStorage.getItem("user_id")) {
      this.title = "我的主页";
    } else {
      this.title = "TA的主页";
    }
  },
  methods: {
    // 去我的
    onClickLeft() {
      this.$router.push({
        name: "my",
      });
    },
    // 去编辑资料
    goInformation() {
      this.$router.push({
        name: "information",
      });
    },
    // 获取我的四个信息
    async myThreeInfo() {
      await this.$http
        .post(`/user/myfourinfo?user_id=${this.user_id}`)
        .then((res) => {
          console.log(res);
          this.user_like_sum = res.data.data[0].user_like_sum;
          this.like_user_sum = res.data.data[0].like_user_sum;
          this.like_bar_sum = res.data.data[0].like_bar_sum;
        });
    },
    // 获取当前用户个人信息
    async getUserInfo() {
      console.log(1);
      await this.$http
        .post(`/user/info?user_id=${this.user_id}&phone=${this.phone}`)
        .then((res) => {
          console.log(res);
          this.user_info = res.data.data;
        })
        .catch((err) => {
          // console.log(err);
        });
    },

    // 获取当前用户已经发布的帖子
    // 用户点赞
    async userlike(t) {
      await this.$http
        .get(`/posts/userlike?user_id=${this.user_id}&posts_id=${t.posts_id}`)
        .then((res) => {
          console.log(res);
        });
      console.log(t);
      if (t.postsLikeUserList == 0) {
        t.postsLikeUserList = 1;
        t.postsLikeList[0] += 1;
        console.log("点赞", t.postsLikeUserList, t.postsLikeList[0]);
      } else if (t.postsLikeUserList == 1) {
        t.postsLikeUserList = 0;
        t.postsLikeList[0] -= 1;
        console.log("取消点赞", t.postsLikeUserList, t.postsLikeList[0]);
      }
    },

    // 去帖子详情
    goPostsDetail(t) {
      console.log(111);
      this.$router.push({
        name: "postsdetail",
        query: {
          posts_id: t.posts_id,
          posts_title: t.posts_title,
          posts_bar_name: t.posts_bar_name,
        },
      });
    },

    // 得到列表
    async getPostsList() {
      await this.$http
        .get(
          `/posts/homepostslist?pagenum=${this.pageNum}&pagesize=${this.pageSize}&yz_user_id=${this.yz_user_id}`
        )
        .then((res) => {
          console.log("得到列表", res);
          this.list = res.data.data.list;

          // console.log(JSON.parse(res.data.data));
          // this.list = JSON.parse(res.data.data).list;
          // // 分页相关数据赋值
          let r = res.data.data;
          this.pageNum = r.pageNum;
          this.pageSize = r.pageSize;
          this.total = r.total;
          this.prePage = r.prePage;
          this.nextPage = r.nextPage;
          this.isFirstPage = r.isFirstPage;
          this.isLastPage = r.isLastPage;
        });
    },

    // 上拉加载
    onLoad() {
      setTimeout(() => {
        // 判断有没有下一页，没有则将 finished 设置成 true
        // isLastPage表示 ： 是否是最后一页
        if (this.isLastPage) {
          this.finished = true;
        } else {
          this.finished = false;
          // 加载下一页
          this.$http
            .post(
              `/posts/homepostslist?pagenum=${this.nextPage}&pagesize=${this.pageSize}&yz_user_id=${this.yz_user_id}`
            )
            .then((res) => {
              console.log("加载下一页", res);

              // console.log(JSON.parse(res.data.data).list);
              let r = res.data.data.list;
              for (let i = 0; i < r.length; i++) {
                this.list.push(r[i]);
              }
              let rr = res.data.data;
              this.pageNum = rr.pageNum;
              this.pageSize = rr.pageSize;
              this.total = rr.total;
              this.prePage = rr.prePage;
              this.nextPage = rr.nextPage;
              this.isFirstPage = rr.isFirstPage;
              this.isLastPage = rr.isLastPage;
              // this.list.push(res.data.data.list);
            });
        }
        // 将loading设为false表示数据已经全部加载完毕
        this.loading = false;
        if (this.refreshing) {
          this.refreshing = false;
        }
      }, 1000);
    },

    // 下拉刷新
    onRefresh() {
      this.pageNum = 1;
      this.getPostsList();
      // 将 loading 设置为 true，表示处于加载状态
      this.loading = true;
      this.onLoad();
    },
    // 获取当前用户发布的帖子
  },
};
</script>

<style scoped>
/* 头部 */
header {
  color: white;
  position: fixed;
  top: 0;
  width: 100%;
  height: 70px;
  /* background-color: aqua; */
  display: flex;
}

header .header_list {
  text-align: center;
  line-height: 90px;
  flex: 1;
}
header .header_list:nth-child(1) {
  text-align: left;
}
header .header_list .iconfont {
  font-size: 20px;
  margin-left: 20px;
}

.header_tup {
  width: 100%;
  height: 150px;
}
.header_tup img {
  width: 100%;
  height: 190px;
}
/* user_info1 */
.user_info1 {
  position: relative;
  display: flex;
  width: 100%;
  height: 80px;
  /* background-color: violet; */
}

.user_info1 .left {
  text-align: center;
  width: 80px;
  height: 80px;
}

.user_info1 .left img {
  margin-top: 10px;
  width: 60px;
  height: 60px;
  border-radius: 50px;
}

.user_info1 .right {
  /* background-color: aqua; */
  flex: 1;
}

.user_info1 .right .right_top {
  position: relative;
  height: 20px;
  display: flex;
  /* background-color: blueviolet; */
}
.user_info1 .right .right_top .yin {
  width: 20px;
  height: 20px;
  font-size: 13px;
  text-align: center;
  line-height: 20px;
  position: absolute;
  right: 50px;
  background-color: #00000057;
  color: white;
}

.user_info1 .right .right_top .jia {
  position: absolute;
  right: 20px;
  width: 20px;
  height: 20px;
  font-size: 13px;
  text-align: center;
  line-height: 20px;
  margin-left: 5px;
  color: white;
  background-color: #00000057;
}

.user_info1 .right .right_bottom {
  position: relative;
  height: 60px;
  /* background-color: yellow; */
}

.user_info1 .right .right_bottom .right_bottom_bjzl {
  position: absolute;
  right: 10px;
  bottom: 0px;
  text-align: center;
  line-height: 30px;
  width: 80px;
  height: 30px;
  color: white;
  background-color: #218eff;
}

.user_info1_white {
  z-index: -1;
  bottom: 0;
  position: absolute;
  width: 100%;
  height: 40px;
  /* background-color: rgb(23, 171, 55); */
}

.user_info2 {
  width: 100%;
  height: 120px;
  /* background-color: #15b18a; */
  display: flex;
  justify-content: center;
  flex-direction: column;
}

.user_info2 .info2_list {
  padding: 0px 15px 0px;
  /* background-color: #4b9653; */
  margin-top: 3px;
}
.user_info2 .info2_list:nth-child(1) {
  font-size: 20px;
}

.user_info2 .info2_list:nth-child(1) span:nth-child(1) {
  color: red;
  font-size: 20px;
}
.user_info2 .info2_list:nth-child(1) span:nth-child(2) {
  font-size: 20px;
  color: #218eff;
}

.user_info2 .info2_list:nth-child(2) {
  color: #8f8f8f;
  font-size: 14px;
}
.user_info2 .info2_list:nth-child(2) span {
  color: black;
}

.user_info2 .info2_list:nth-child(3) {
  font-size: 14px;
}

.user_info2 .info2_list:nth-child(4) {
  font-size: 14px;
  color: #8f8f8f;
}

/* 内容列表 */
/* .nr_list {
  width: 100%;
  border-top: 1px solid white;
  background-color: #f3f3f3;
}
.nr_list .list_list {
  padding-top: 10px;
  display: flex;
  background-color: white;
  margin-top: 8px;
}
.nr_list .list_list .list_list_left {
  display: flex;
  flex-direction: column;
  width: 60px;
}
.nr_list .list_list .list_list_left span:nth-child(1) {
  text-align: center;
  font-size: 22px;
}
.nr_list .list_list .list_list_left span:nth-child(2) {
  color: #b3b3b3;
  font-size: 12px;
  text-align: center;
}

.nr_list .list_list .list_list_right {
  flex: 1;
  flex-direction: column;
}
.nr_list .list_list .list_list_right .list_list_right_top {
  height: 30px;
  line-height: 30px;
  display: flex;
}
.nr_list .list_list .list_list_right .list_list_right_top .top_left {
  color: #8f8f8f;
  font-size: 12px;
  flex: 1;
}
.nr_list .list_list .list_list_right .list_list_right_top .top_right {
  width: 30px;
}
.nr_list .list_list .list_list_right .list_list_right_top .top_right span {
  font-size: 22px;
  color: #b3b3b3;
}

.nr_list .list_list .list_list_right .list_list_right_center {
  line-height: 30px;
}

.nr_list .list_list .list_list_right .list_list_right_center .img_list {
  display: flex;
  width: 100%;
}
.nr_list .list_list .list_list_right .list_list_right_center .img_list img {
  width: 50px;
  margin-right: 10px;
  flex: 1;
}

.nr_list .list_list .list_list_right .list_list_right_bottom {
  height: 40px;
  line-height: 40px;
  display: flex;
}
.nr_list .list_list .list_list_right .list_list_right_bottom .bottom_list {
  flex: 1;
  text-align: center;
} */

/* 内容部分 */
.content {
  /* margin-top: 46px; */
  margin-bottom: 50px;
}
.content .content_list {
  background-color: white;
  margin-top: 10px;
}

.content .content_list .title {
  width: 96%;
  margin: 0 auto;
}
.content .content_list .content_list_header {
  padding: 5px 5px 5px 2%;
  display: flex;
  /* background-color: aqua; */
  height: 40px;
}
.content_list_header .header_left {
  /* background-color: blue; */

  width: 35px;
  height: 35px;
}

.content_list_header .header_left img {
  width: 35px;
  height: 35px;
  border-radius: 20px;
}

.content_list_header .header_right {
  margin-left: 5px;
  margin-top: 5px;
  /* background-color: blue; */
}
.content_list_header .header_right .header_right_top {
  width: 100%;
  font-size: 15px;
  line-height: 20px;
  height: 20px;
  /* background-color: violet; */
}
.content_list_header .header_right .header_right_bottom {
  font-size: 8px;
  line-height: 15px;
  width: 100%;
  height: 15px;
  /* background-color: tomato; */
}

.content_list_bottom {
  font-size: 12px;
  /* background-color: violet; */
  height: 30px;
  line-height: 30px;
  display: flex;
}
.content_list_bottom .list_bottom_list {
  text-align: center;
  flex: 1;
}

img:nth-last-child(1):first-child,
img:nth-last-child(1):first-child {
  margin: 0 auto;
  margin-left: 2%;
  margin-top: 5px;
  width: 96%;
}

img:nth-last-child(2):first-child,
img:nth-last-child(2):first-child ~ img {
  width: 31%;
  object-fit: cover;
  height: 15vh;
  margin-left: 2%;
  margin-top: 5px;
}

img:nth-last-child(3):first-child,
img:nth-last-child(3):first-child ~ img {
  width: 31%;
  object-fit: cover;
  height: 15vh;
  margin-left: 2%;
  margin-top: 5px;
}

.postslikeuser {
  color: red;
}
</style>
