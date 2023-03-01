<template>
  <div class="information">
    <!-- 头部 -->
    <van-nav-bar
      @click-left="onClickLeft"
      @click-right="onClickRight"
      title="我的资料"
      right-text="保存"
      left-arrow
    >
      <template #left>
        <van-icon name="cross" size="20" />
      </template>
    </van-nav-bar>
    <!-- 头像 -->
    <!-- <div class="header_img">
      <van-image
        round
        width="8rem"
        height="8rem"
        src="https://img01.yzcdn.cn/vant/cat.jpeg"
      />
    </div> -->
    <!-- 上传图片 -->
    <div class="upload">
      <van-uploader
        ref="uploadImg"
        :preview-full-image="false"
        :deletable="false"
        :max-count="1"
        :after-read="afterRead"
        preview-size="120"
        v-model="fileList"
        multiple
      >
        <template>
          <div class="header_img">
            <van-image width="8rem" height="8rem" :src="header_img" />
          </div>
        </template>
      </van-uploader>
    </div>

    <!-- 输入框 -->
    <van-cell-group>
      <van-field label="用户名" v-model="username" disabled />
      <van-field
        label="昵称"
        @click="editnickname"
        v-model="nickname"
        readonly
        is-link
      />
      <van-field label="性别" @click="showsex" :value="sex" readonly />
      <van-field
        @focus="qmfocus"
        @blur="qmblur"
        v-model="message"
        rows="1"
        autosize
        label="个性签名"
        type="textarea"
        maxlength="50"
        placeholder="签名是一种态度,我想我可以更酷..."
        :show-word-limit="showwordlimit"
      />
    </van-cell-group>
    <!-- 性别弹出框 -->
    <van-action-sheet v-model="show" :actions="actions" @select="onSelect" />
  </div>
</template>

<script>
import upLoaderImg from "../../utils/upLoadImg.js";
export default {
  name: "Information",
  data() {
    return {
      // 上传图片
      fileList: [
        // { url: "http://localhost:8080/statics/user_avatar/1_zfb.jpg" },
        // // Uploader 根据文件后缀来判断是否为图片文件
        // // 如果图片 URL 中不包含类型信息，可以添加 isImage 标记来声明
        // { url: "https://cloud-image", isImage: true },
      ],
      // 性别是否显示
      show: false,
      actions: [{ name: "男" }, { name: "女" }],
      // 用户名
      username: "",
      user_id: 0,
      phone: 0,
      // 用户头像
      header_img: "",
      // 昵称
      nickname: "",
      sex: "",
      // 签名
      message: "",
      // 控制显示字数
      showwordlimit: false,
    };
  },
  mounted() {
    this.username = localStorage.getItem("username");
    this.nickname = localStorage.getItem("nickname");
    this.sex = localStorage.getItem("user_sex");
    this.message = localStorage.getItem("user_autograph");
    this.header_img =
      "http://localhost:8080/statics/user_avatar/" +
      localStorage.getItem("avatar");
    this.user_id = localStorage.getItem("user_id");
    this.phone = localStorage.getItem("phone");
    this.getUserInfo();
  },
  methods: {
    // 获取用户信息
    async getUserInfo() {
      console.log(1);
      const res = await this.$http
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
    // 上传图片
    async afterRead(file) {
      // 此时可以自行将文件上传至服务器
      console.log(file);
      let uploadImg = await upLoaderImg(file.file);
      console.log("上传之后");
      this.getUserInfo();
    },
    // 修改昵称
    editnickname() {
      this.$router.push({
        name: "editnickname",
      });
    },
    // 性别选择
    showsex() {
      this.show = true;
    },
    onSelect(item) {
      // 默认情况下点击选项时不会自动收起
      // 可以通过 close-on-click-action 属性开启自动收起
      this.show = false;
      // console.log(item.name);
      this.sex = item.name;
    },

    // 个性签名方法
    qmfocus() {
      this.showwordlimit = true;
    },
    qmblur() {
      this.showwordlimit = false;
    },
    // 点击导航左侧按钮
    onClickLeft() {
      this.$router.push({
        name: "setting",
      });
    },
    async onClickRight() {
      console.log("保存");
      // 保存用户信息
      const res = await this.$http
        .post(
          `/user/edituserinfo?user_id=${this.user_id}&user_sex=${this.sex}&user_autograph=${this.message}`
        )
        .then((res) => {
          console.log(res);
          this.getUserInfo();
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style scoped>
.van-nav-bar .van-icon {
  color: black;
}
/* 头像 */
.upload {
  padding: 50px 0;
  text-align: center;
  /* background-color: aqua; */
}
</style>
