<template>
  <div class="posts">
    <!-- 头部 -->
    <van-nav-bar @click-left="onClickLeft" fixed placeholder>
      <template #left>
        <van-icon name="arrow-left" size="18" />
        <!-- <div class="header_center">
          <div class="img">
            <img src="https://s1.ax1x.com/2022/03/16/qpRzGT.jpg" alt="" />
          </div>
          <div class="wz">+关注</div>
        </div> -->
      </template>
      <template #right>
        <span class="iconfont icon-qita" @click="postslove()"></span>
      </template>
    </van-nav-bar>

    <!-- 内容部分 -->
    <div class="content_box" v-for="p in postsInfo" :key="p.posts_id">
      <div class="posts_title">{{ p.posts_title }}</div>
      <!-- 用户信息 -->
      <div class="user_info" @click="goMyZy(p)">
        <div class="content_list_header">
          <div class="header_left">
            <img src="https://s1.ax1x.com/2022/03/16/qpRzGT.jpg" alt="" />
          </div>
          <div class="header_right">
            <div class="header_right_top">{{ p.username }}</div>
            <div class="header_right_bottom">
              {{ p.posts_bar_name }} | {{ p.posts_time }}
            </div>
          </div>
          <div class="header_love" v-if="isLikeUser == 0" @click="addUserLike">
            + 关注
          </div>
          <div class="header_love" v-else @click="cancelLikeUser">取消关注</div>
        </div>
      </div>
      <!-- 内容部分 -->
      <div class="content_content">{{ p.posts_content }}</div>
      <!-- 图片部分 -->
      <div class="content_img">
        <img
          v-for="(i, m) in p.postsImgList"
          :key="m"
          :src="'http://localhost:8080/statics/posts_img/' + i.posts_img_path"
          alt=""
        />
      </div>
      <!-- 点赞部分 -->
      <div class="btn_love" :class="{ dz: dz_class }" @click="dz_btn">
        <span class="iconfont icon-icon"></span>
        {{ dz }}
      </div>
      <!-- 贴吧部分 -->
      <div class="posts_bar" @click="goBar(p)">
        <div class="left">
          <img
            :src="
              'http://localhost:8080/statics/posts_bar_logo/' +
              p.posts_bar_logo_path
            "
            alt=""
          />
        </div>
        <div class="center">
          <div class="center_top">{{ p.posts_bar_name }}</div>
          <div class="center_bottom">关注 2 帖子 5</div>
        </div>
        <div class="right">
          <span class="iconfont icon-xiangyoujiantou"></span>
        </div>
      </div>
      <!-- 全部回复 -->
      <div class="all_hf">
        <div class="wz">全部回复</div>
      </div>
      <!-- 评论部分 -->
      <div class="posts_com">
        <div
          class="com_list"
          v-for="(c, i) in p.postsComList"
          :key="c.posts_com_id"
        >
          <div class="list_left">
            <img
              :src="'http://localhost:8080/statics/user_avatar/' + c.avatar"
              alt=""
            />
          </div>
          <div class="list_right">
            <div class="right_top">
              <div class="top_top">
                <div class="top_left">{{ c.nickname }}</div>
                <div class="top_right" @click="comcom(c)">···</div>
              </div>
              <div class="top_bottom">
                第{{ postsInfo[0].postsComList.length - i }}楼 |
                {{ c.posts_com_time }}
              </div>
            </div>
            <div class="right_bottom">
              <div class="com_top">{{ c.posts_com_content }}</div>
              <div class="com_bottom">
                <div
                  class="com_com_list"
                  v-for="cc in c.postsComComList.slice(0, 2)"
                  :key="cc.posts_com_com_id"
                >
                  <span>{{ cc.nickname }}:</span>{{ cc.posts_com_com_content }}
                </div>
                <div
                  class="more"
                  v-if="c.postsComComList.length > 0"
                  @click="comcom(c)"
                >
                  查看全部 {{ c.postsComComList.length }} 条评论 >
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 说说你的看法 -->
    <div class="hf">
      <input type="text" placeholder="说说你的看法..." v-model="com_value" />
      <div class="fb" :class="{ fbnone: fbnone }" @click="comfb">发布</div>
    </div>
    <!-- 删除面板 -->
    <van-action-sheet v-model="show" :actions="actions" @select="onSelect" />
    <!-- 子评论面板 -->
    <van-action-sheet v-model="show1">
      <template slot="default"
        ><div class="title">{{ com_com_value.length }}条回复</div></template
      >
      <div class="content">
        <div
          class="comcom_list"
          v-for="comcom in com_com_value"
          :key="comcom.posts_com_com_id"
        >
          <span>{{ comcom.nickname }}: </span>
          {{ comcom.posts_com_com_content }}
          <span> {{ comcom.posts_com_com_time }}</span>
        </div>
      </div>
      <!-- 评论框 -->
      <div class="hf">
        <input type="text" placeholder="说说你的看法..." v-model="com_value" />
        <div class="fb" :class="{ fbnone: fbnone }" @click="comcomfb">发布</div>
      </div>
    </van-action-sheet>
    <!-- 收藏面板 -->
    <van-action-sheet
      v-model="show_love"
      :actions="actionlove"
      @select="onSelectlove"
    />
  </div>
</template>

<script>
export default {
  name: "postsdetail",
  data() {
    return {
      // 发布按钮
      fbnone: true,
      // 帖子名称
      posts_title: "",
      // 吧名称
      posts_bar_name: "",
      // 删除面板
      show: false,
      // 子评论面板
      show1: false,
      // 收藏面板
      show_love: false,
      // 根据主评论id获取子评论列表
      com_com_value: [],
      actions: [{ name: "回复楼层" }, { name: "删除" }],
      actionlove: [{ name: "收藏" }],
      // 发布你的看法
      com_value: "",
      posts_id: 0,
      // 发布子评论
      posts_com_id: 0,
      // 验证用户id
      yz_user_id: 0,
      // 帖子详细信息
      postsInfo: [],

      user_id: 0,
      user_b_id: 0,
      isLikeUser: 0,
      // 判断登录用户是否点赞了该帖子
      postsLikeUserList: 0,
      dz: "点赞",
      dz_class: true,
    };
  },
  mounted() {
    console.log(this.$route);
    this.posts_id = this.$route.query.posts_id;
    this.yz_user_id = localStorage.getItem("user_id");
    this.posts_title = this.$route.query.posts_title;
    this.posts_bar_name = this.$route.query.posts_bar_name;
    this.postsLikeUserList = this.$route.query.postsLikeUserList;
    this.getPostsDetail();
    this.postsHistory();
    // 查询登录用户是否已经关注的发帖用户
    this.user_id = localStorage.getItem("user_id");
    this.selectUserLikeUser();
  },
  updated() {
    // console.log(this.com_value);
    if (this.com_value.length > 0) {
      this.fbnone = false;
    } else {
      this.fbnone = true;
    }
    // 点赞判断
    if (this.postsLikeUserList == 0) {
      this.dz = "点赞";
      this.dz_class = false;
    } else {
      this.dz = "已点赞";
      this.dz_class = true;
    }
  },
  methods: {
    // 去Ta主页
    goMyZy(p) {
      console.log(p);
      this.$router.push({
        name: "myzy",
        query: {
          user_id: p.user_id,
          phone: p.phone,
        },
      });
    },
    // 点赞按钮
    async dz_btn() {
      if (this.postsLikeUserList == 0) {
        // 添加点赞
        console.log("添加点赞");
        await this.$http
          .get(
            `/posts/userlike?user_id=${this.user_id}&posts_id=${this.posts_id}`
          )
          .then((res) => {
            console.log(res);
            this.postsLikeUserList = 1;
            this.dz = "已点赞";
            this.dz_class = true;
          });
      } else {
        // 取消点赞
        console.log("取消点赞");
        await this.$http
          .get(
            `/posts/userlike?user_id=${this.user_id}&posts_id=${this.posts_id}`
          )
          .then((res) => {
            console.log(res);
          });
        this.postsLikeUserList = 0;
        this.dz = "点赞";
        this.dz_class = false;
      }
    },
    // 添加用户关注
    async addUserLike() {
      this.$http
        .post(
          `/user/insertuserlike?user_id=${this.user_id}&user_b_id=${this.user_b_id}`
        )
        .then((res) => {
          console.log(res);
          if (res.data.data == 1) {
            this.isLikeUser = 1;
          }
        });
    },
    // 取消用户关注
    async cancelLikeUser() {
      this.$http
        .post(
          `/user/canceluserlikeuser?user_id=${this.user_id}&user_b_id=${this.user_b_id}`
        )
        .then((res) => {
          console.log(res);
          this.isLikeUser = 0;
        });
    },
    // 查询登录用户是否已经关注的发帖用户
    async selectUserLikeUser() {
      console.log("查询用户是否被关注");
      setTimeout(() => {
        this.$http
          .post(
            `/user/selectuserlikeuser?user_id=${this.user_id}&user_b_id=${this.user_b_id}`
          )
          .then((res) => {
            console.log(res);
            this.isLikeUser = res.data.data;
          });
      }, 500);
    },
    // 收藏
    postslove() {
      console.log("收藏");
      this.show_love = true;
    },
    // 去吧页面
    goBar(p) {
      console.log(p.posts_bar_id);
      console.log(p.posts_bar_name);
      console.log(p.posts_bar_logo_path);
      this.$router.push({
        name: "bar",
        params: {
          bar_id: p.posts_bar_id,
          bar_name: p.posts_bar_name,
          bar_logo_path: p.posts_bar_logo_path,
        },
      });
    },
    // 将进入贴吧记录存入数据库
    async postsHistory() {
      console.log("存数据库");
      await this.$http
        .post(
          `/posts/postshistory?user_id=${this.yz_user_id}&posts_id=${this.posts_id}&posts_title=${this.posts_title}&posts_bar_name=${this.posts_bar_name}`
        )
        .then((res) => {
          console.log("历史记录", res);
        });
    },
    // 收藏
    async onSelectlove() {
      console.log(1);
      await this.$http
        .post(
          `/posts/postslove?posts_id=${this.posts_id}&save_user_id=${this.user_id}`
        )
        .then((res) => {
          console.log(res);
        });
      this.show_love = false;
    },
    // 删除评论
    async onSelect(item) {
      // 默认情况下点击选项时不会自动收起
      // 可以通过 close-on-click-action 属性开启自动收起
      this.show = false;
      // Toast(item.name);
      // console.log(item.name);
      if (item.name == "删除") {
        console.log(1);
        // 发送删除请求
        await this.$http
          .post(`/posts/postscomdelete?posts_com_id=${this.posts_com_id}`)
          .then((res) => {
            console.log(res);
            this.getPostsDetail();
          });
      } else if (item.name == "回复楼层") {
        console.log(2);
        // 打开回复楼层面板
        this.show1 = true;
      }
    },
    // 评论
    async comfb() {
      await this.$http
        .post(
          `/posts/postscom?posts_id=${this.posts_id}&posts_com_content=${this.com_value}&user_id=${this.yz_user_id}`
        )
        .then((res) => {
          console.log(res);
          this.com_value = "";
          this.getPostsDetail();
        });
    },
    // 子评论
    async comcomfb() {
      console.log("子评论发布");
      await this.$http
        .post(
          `/posts/postscomcom?posts_com_id=${this.posts_com_id}&posts_com_com_content=${this.com_value}&user_id=${this.yz_user_id}`
        )
        .then((res) => {
          console.log(res);
          this.com_value = "";
          this.getPostsDetail();
          this.show1 = false;
          // 获取子评论
        });
    },
    // 子评论
    comcom(c) {
      this.show = true;
      if (c.user_id == this.yz_user_id) {
        this.actions = [{ name: "回复楼层" }, { name: "删除" }];
      } else {
        this.actions = [{ name: "回复楼层" }];
        // 展示子评论面板
        // console.log(c);
        console.log(c);
      }
      // 将子评论赋值给com_com_value
      console.log("赋值posts_com_id");
      this.com_com_value = c.postsComComList;
      this.posts_com_id = c.posts_com_id;
    },
    // 查询某个帖子的详细信息
    async getPostsDetail() {
      await this.$http
        .post(
          `/posts/postsdetail?posts_id=${this.posts_id}&yz_user_id=${this.yz_user_id}`
        )
        .then((res) => {
          console.log(res);
          this.postsInfo = res.data.data;
          this.user_b_id = res.data.data[0].user_id;
        });
    },
    // 去首页
    onClickLeft() {
      this.$router.push({
        name: "home",
      });
    },
  },
};
</script>

<style scoped>
.header_center {
  margin-left: 20px;
  display: flex;
}
.header_center .img {
  border-radius: 20px;
  width: 30px;
}
.header_center .img img {
  border-radius: 20px;
  width: 100%;
}
.header_center .wz {
  margin-top: 8px;
  border-radius: 20px;
  text-align: center;
  line-height: 20px;
  width: 50px;
  height: 20px;
  background-color: #efefef;
}

/* 内容box */
.content_box {
  /* margin-bottom: 35px; */
  width: 94%;
  /* background-color: aqua; */
  margin: 5px auto 35px;
}

.content_box .posts_title {
  font-size: 18px;
}

.content_list_header {
  padding: 5px 5px 5px 2%;
  display: flex;
  /* background-color: rgb(40, 164, 26); */
  height: 40px;
}
.content_list_header .header_left {
  /* background-color: blue; */
  margin-top: 3px;
  width: 35px;
  height: 35px;
}

.content_list_header .header_left img {
  width: 35px;
  height: 35px;
  border-radius: 20px;
}

.content_list_header .header_right {
  flex: 1;
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
.content_list_header .header_love {
  margin-top: 5px;
  text-align: center;
  line-height: 25px;
  color: white;
  font-size: 12px;
  width: 70px;
  height: 25px;
  background-color: #1361d7;
}

/* 内容部分 */
.content_box .content_content {
  /* background-color: aqua; */
  margin-top: 10px;
  font-size: 15px;
}

/* 图片 */
.content_box .content_img {
  margin-top: 5px;
  width: 100%;
}
.content_box .content_img img {
  width: 100%;
}

/* 点赞 */
.content_box .btn_love {
  border-radius: 10px;
  font-size: 12px;
  text-align: center;
  border: 1px solid rgb(179, 179, 179);
  margin: 5px auto;
  width: 80%;
  height: 20px;
  line-height: 20px;
}

/* 帖吧部分 */
.content_box .posts_bar {
  margin-top: 20px;
  display: flex;
  width: 100%;
  background-color: #efefef;
}
.content_box .posts_bar .left {
  margin-right: 10px;
  width: 50px;
}
.content_box .posts_bar .left img {
  width: 50px;
}
.content_box .posts_bar .center {
  font-size: 14px;
  flex: 1;
}
.content_box .posts_bar .center .center_top {
  margin-top: 5px;
}
.content_box .posts_bar .center .center_bottom {
  font-size: 12px;
  color: #b5b5b5;
}
.content_box .posts_bar .right {
  margin-right: 5px;
  margin-top: 17px;
}

.all_hf {
  /* background-color: red; */
  height: 30px;
  line-height: 30px;
  margin-top: 5px;
  border-top: 5px solid #efefef;
  border-bottom: 1px solid #efefef;
}
.all_hf .wz {
  display: inline-block;
  border-bottom: 1px solid black;
}

/* 评论部分 */
.com_list {
  padding-top: 5px;
  margin: 0 auto;
  /* width: 94%; */
  display: flex;
  border-bottom: 1px solid #f4f4f4;
}
.com_list .list_left {
  width: 50px;
  /* background-color: aqua; */
}
.com_list .list_left img {
  border-radius: 50px;
  width: 40px;
  height: 40px;
  margin-left: 5px;
  margin-top: 5px;
}
.com_list .list_right {
  padding-left: 5px;
  flex: 1;
  /* background-color: rgb(64, 191, 35); */
}
.com_list .list_right .top_top {
  display: flex;
}
.com_list .list_right .top_top .top_left {
  flex: 1;
}
.com_list .list_right .top_bottom {
  font-size: 12px;
  color: #afafaf;
  margin-bottom: 5px;
}
.com_list .list_right .right_bottom {
  padding-bottom: 15px;
}
.com_list .list_right .right_bottom .com_bottom {
  font-size: 14px;
  margin-top: 5px;
  background-color: rgb(240, 240, 240);
}
.com_list .list_right .right_bottom .com_bottom .com_com_list span {
  color: rgb(105, 105, 255);
}
.com_list .list_right .right_bottom .com_bottom .more {
  font-size: 12px;
  color: #afafaf;
  padding-bottom: 5px;
}

/* 回复 */
.hf {
  display: flex;
  width: 100%;
  height: 35px;
  background-color: white;
  position: fixed;
  bottom: 0;
}
.hf input {
  flex: 1;
  padding-left: 10px;
  font-size: 15px;
  border-top: 1px solid #f4f4f4;
  /* width: 100%; */
  line-height: 35px;
  height: 35px;
  border-left: 0;
  border-right: 0;
  border-bottom: 0;
  background-color: white;
}
.hf input::-webkit-input-placeholder {
  color: rgb(194, 194, 194);
}
.hf .fb {
  font-size: 15px;
  color: blue;
  width: 55px;
  /* display: block; */
  line-height: 35px;
  background-color: white;
}
.fbnone {
  display: none;
}

/* 子评论 */
.title {
  width: 100%;
  text-align: center;
  padding: 10px;
  border-bottom: 1px solid rgb(199, 199, 199);
}
.content {
  font-size: 14px;
  height: 50vh;
  padding: 16px 16px 160px;
}
.content .comcom_list {
  margin-top: 5px;
}
.content span:nth-child(1) {
  color: blue;
}
.content span:nth-child(2) {
  color: #afafaf;
}

.dz {
  background-color: #ff5454;
}
</style>
