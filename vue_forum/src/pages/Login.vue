<template>
  <div class="login">
    <!-- 头部 -->
    <div class="header_bg">
      <div class="na-bg-img active"></div>
    </div>
    <van-nav-bar @click-left="gohome" title="账号密码登录">
      <template #left>
        <van-icon color="#939393" name="arrow-left" size="25" />
      </template>
    </van-nav-bar>

    <!-- 框框 -->
    <div class="content">
      <div class="content_title">登录百度账号 精彩永相随</div>
      <!-- 手机号 -->
      <van-field
        clearable
        input-align="center"
        v-model="phone"
        placeholder="请输入手机号/用户名/邮箱"
      />
      <!-- 密码 -->
      <van-field
        :type="type"
        @click-left-icon="left_icon_click"
        input-align="center"
        label-width="60px"
        :left-icon="left_icon"
        clearable
        v-model="password"
        placeholder="请输入登录密码"
      />
      <!-- 两个span -->
      <div class="two_sapn">
        <span>更换设备登录</span>
        <span>忘记密码</span>
      </div>
      <!-- 登录按钮 -->
      <div class="btn">
        <van-button
          type="primary"
          color="#5555ff"
          size="large"
          :disabled="btn_dis"
          @click="login"
          >登录</van-button
        >
      </div>
      <div class="sms">短信验证码登录</div>
      <div class="checkbox">
        <van-checkbox v-model="checked" icon-size="15px"
          >请您阅读并同意 百度用户协议 和 隐私政策 及 儿童个人信息保护声明
        </van-checkbox>
      </div>
    </div>

    <!-- 底部 -->
    <div class="three_span">
      <span>换个账号</span> | <span @click="toregister">注册</span> |
      <span>帮助</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      // 手机号
      phone: "",
      // 密码
      password: "",
      type: "password",
      // 登录按钮是否可用
      btn_dis: true,
      // 密码icon
      left_icon: "closed-eye",
      // 复选框是否选中
      checked: false,
    };
  },
  methods: {
    // 登录按钮
    async login() {
      await this.$http
        .post(`/user/login?phone=${this.phone}&password=${this.password}`)
        .then((res) => {
          console.log(res);
          if (res.data.code == 200) {
            // 将获取到的token存入本地
            this.$store.commit("setToken", res.data.data[1]);
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
            // 跳转到我的主页
            setTimeout(() => {
              this.$router.push(
                this.$router.history.current.query.redirect || "/my"
              );
            }, 500);
          }
        })
        .catch((err) => {
          this.$notify("用户名或密码输入有误");
          this.phone = "";
          this.password = "";
        });
    },
    // 密码图标
    left_icon_click() {
      if (this.left_icon == "closed-eye") {
        this.left_icon = "eye-o";
        this.type = "text";
      } else {
        this.left_icon = "closed-eye";
        this.type = "password";
      }
    },
    // 去主页，通过路由跳转
    gohome() {
      this.$router.push({
        name: "home",
      });
    },
    // 去注册，通过路由跳转
    toregister() {
      this.$router.push({
        name: "register",
      });
    },
  },
  updated() {
    this.phone.length != 0 && this.password.length != 0 && this.checked == true
      ? (this.btn_dis = false)
      : (this.btn_dis = true);
  },
};
</script>

<style scoped>
.login {
  height: 100vh;
  background-color: rgb(244, 244, 244);
}

.na-bg-img {
  width: 100%;
  height: 28rem;
  position: absolute;
  top: 46px;
  left: 0;
  right: 0;
}

.na-bg-img.active:after {
  bottom: 0;
}

.na-bg-img.active {
  background: url("../assets/header.png") no-repeat;
  background-size: 100%;
}

.na-bg-img:after {
  content: "";
  display: inline-block;
  position: absolute;
  left: 0;
  right: 0;
  height: 23rem;
  background-image: linear-gradient(
    0deg,
    #f5f5f5 50%,
    hsla(0, 0%, 100%, 0) 99%
  );
}

/* 内容区域 */
.content {
  position: absolute;
  left: 5%;
  border-radius: 20px;
  margin: 90px auto;
  width: 90%;
  background-color: rgb(255, 255, 255);
}

.content_title {
  font-size: 20px;
  text-align: center;
  line-height: 90px;
  width: 100%;
  height: 70px;
  font-weight: 600;
  /* background-color: red; */
}

/* 手机号 */
.van-cell {
  width: 90%;
  margin: 30px auto;
  background-color: rgb(242, 242, 242);
  border-radius: 20px;
  font-size: 16px;
}

/* 按钮 */
.btn {
  margin: 0 auto;
  width: 90%;
}

.van-button {
  border-radius: 22px;
}

/* 两个span */
.two_sapn {
  color: #6e6e6e;
  margin: 10px auto;
  display: flex;
  width: 90%;
  height: 40px;
  font-size: 14px;
}

.two_sapn span:nth-child(1) {
  flex: 1;
}

/* 短信 */
.sms {
  margin-top: 10px;
  color: #6e6e6e;
  font-size: 14px;
  width: 100%;
  text-align: center;
}

/* 复选框 */
.checkbox {
  text-align: center;
  margin: 20px auto;
  font-size: 12px;
  width: 80%;
  /* background-color: aqua; */
  padding-bottom: 50px;
}

/* 底部 */
.three_span {
  color: #6e6e6e;
  text-align: center;
  width: 50%;
  font-size: 14px;
  position: fixed;
  bottom: 20px;
  left: 25%;
}
</style>
