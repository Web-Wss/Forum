<template>
  <div class="bar">
    <!-- 头部 -->
    <van-nav-bar left-arrow @click-left="onClickLeft" fixed placeholder>
      <template #right>
        <van-icon name="search" size="18" />
        <van-icon name="volume-o" size="18" />
      </template>
    </van-nav-bar>
    <!-- 吧信息 -->
    <div class="bar_info">
      <div class="info_left">
        <img
          :src="'http://localhost:8080/statics/posts_bar_logo/' + bar_logo_path"
          alt=""
        />
      </div>
      <div class="info_center">
        <div class="bar_title">{{ bar_name }}</div>
        <div class="bottom">关注 66 帖子 12</div>
      </div>
      <div class="info_right"><button>+关注</button></div>
    </div>
    <!-- 当前贴吧的帖子列表 -->
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
                  {{ t.posts_time }}
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
    <!-- 发布 -->
    <div class="push">
      <svg class="icon" aria-hidden="true" @click="gopublish">
        <use xlink:href="#icon-jiahao"></use>
      </svg>
    </div>
  </div>
</template>

<script>
export default {
  name: "Bar",
  data() {
    return {
      // 吧信息
      bar_id: 0,
      bar_name: 0,
      bar_logo_path: 0,
      user_id: 0,
      yz_user_id: 0,
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
    };
  },

  mounted() {
    console.log(this.$route);
    localStorage.setItem(
      "bar_id",
      this.$route.params.bar_id == undefined
        ? localStorage.getItem("bar_id")
        : this.$route.params.bar_id
    );
    localStorage.setItem(
      "bar_name",
      this.$route.params.bar_name == undefined
        ? localStorage.getItem("bar_name")
        : this.$route.params.bar_name
    );
    localStorage.setItem(
      "bar_logo_path",
      this.$route.params.bar_logo_path == undefined
        ? localStorage.getItem("bar_logo_path")
        : this.$route.params.bar_logo_path
    );
    this.bar_id = localStorage.getItem("bar_id");
    this.bar_name = localStorage.getItem("bar_name");
    this.bar_logo_path = localStorage.getItem("bar_logo_path");
    this.user_id = localStorage.getItem("user_id");
    this.yz_user_id = localStorage.getItem("user_id");
    this.getPostsList();
  },

  methods: {
    // 返回
    onClickLeft() {
      this.$router.push({
        name: "home",
      });
    },
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
          `/posts/barpostslist?pagenum=${this.pageNum}&pagesize=${this.pageSize}&bar_id=${this.bar_id}&yz_user_id=${this.yz_user_id}`
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
              `/posts/barpostslist?pagenum=${this.nextPage}&pagesize=${this.pageSize}&bar_id=${this.bar_id}&yz_user_id=${this.yz_user_id}`
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
    // 发布按钮
    gopublish() {
      console.log("发布");
      this.$router.push({
        name: "publish",
        params: {
          changebar: this.bar_name,
          bar_id: this.bar_id,
        },
      });
    },
  },
};
</script>

<style scoped>
/* 吧信息 */
.bar_info {
  padding: 0 5px 0;
  display: flex;
  /* width: 96%; */
  height: 60px;
  /* background-color: aqua; */
  border-bottom: 5px solid rgb(239, 239, 239);
}
.bar_info .info_left {
  padding-top: 5px;
  width: 50px;
}
.bar_info .info_left img {
  width: 50px;
  height: 50px;
}
.bar_info .info_center {
  flex: 1;
  padding-left: 10px;
  padding-top: 5px;
}
.bar_info .info_center .bottom {
  font-size: 12px;
  margin-top: 5px;
  color: rgb(176, 176, 176);
}
.bar_info .info_right {
  width: 60px;
}
.bar_info .info_right button {
  margin-top: 15px;
  border: 0;
  font-size: 14px;
  color: white;
  background-color: rgb(54, 131, 239);
}

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

/* 发布按钮 */
.push {
  width: 100%;
  position: fixed;
  bottom: 0;
  left: calc(50% - 32px);
}
</style>
