<template>
  <div class="register">
    <div class="header_bg">
      <div class="na-bg-img active"></div>
    </div>
    <van-nav-bar @click-left="gohome" title="注册">
      <template #left>
        <van-icon color="#939393" name="arrow-left" size="25" />
      </template>
    </van-nav-bar>

    <!-- 框框 -->
    <div class="content">
      <div class="content_title">欢迎注册百度账号</div>
      <!-- 手机号 -->
      <van-field
        @blur="blur"
        clearable
        maxlength="11"
        label-width="60px"
        label-align="right"
        v-model="phone"
        label="+86"
        placeholder="请输入手机号"
      />

      <div class="btn">
        <van-button
          @click="btn"
          type="primary"
          color="#5555ff"
          size="large"
          :disabled="btn_dis"
          >立即注册</van-button
        >
      </div>
      <div class="checkbox">
        <van-checkbox v-model="checked" icon-size="15px"
          >请您阅读并同意 百度用户协议 和 隐私政策 及 儿童个人信息保护声明
        </van-checkbox>
      </div>
      <div class="list">
        <span>注册百度账号,即可登录所有百度产品</span>
        <div class="icon_list">
          <div class="img_list">
            <img
              src="https://img0.baidu.com/it/u=3025921083,3634249130&fm=253&fmt=auto&app=138&f=PNG?w=200&h=200"
            />
          </div>

          <div class="img_list">
            <img
              src="https://img0.baidu.com/it/u=1997018650,4282523777&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
            />
          </div>
          <div class="img_list">
            <img
              src="https://img0.baidu.com/it/u=1008035837,3342893921&fm=253&fmt=auto&app=138&f=PNG?w=500&h=500"
            />
          </div>
          <div class="img_list">
            <img
              src="https://img0.baidu.com/it/u=965973187,3196586548&fm=253&fmt=auto&app=138&f=JPEG?w=90&h=90"
            />
          </div>
          <div class="img_list">
            <img
              src="https://dss0.bdstatic.com/-0U0bnSm1A5BphGlnYG/tam-ogel/f64816229c78178f7dcf9f6a15531b90_121_121.png"
            />
          </div>
          <div class="img_list">
            <img
              src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fgdown.baidu.com%2Fimg%2F0%2F512_512%2Fcc906478c4a47367912918385e52b5dc.png&refer=http%3A%2F%2Fgdown.baidu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1668952327&t=37ee09f858dd32a8d63b7ebe87564200"
            />
          </div>
          <div class="img_list">
            <img
              src="http://mms0.baidu.com/it/u=3828242879,3848383245&fm=253&app=138&f=PNG&fmt=auto&q=75?w=256&h=256"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    return {
      btn_dis: true,
      phone: "",
      checked: false,
    };
  },
  methods: {
    // 按钮
    async btn() {
      const res = await this.$http
        .post(`/user/register/phone?phone=${this.phone}`)
        .then((res) => {
          console.log(res);
          if (res.data.data == "重复") {
            this.$notify("该手机号已被注册!!!");
            this.phone = "";
          } else {
            this.$router.push({
              name: "perfect",
              params: { phone: this.phone },
            });
          }
        })
        .catch((err) => {
          // console.log(err);
        });
    },
    // 失焦
    async blur() {
      const res = await this.$http
        .post(`/user/register/phone?phone=${this.phone}`)
        .then((res) => {
          console.log(res);
          if (res.data.data == "重复") {
            this.$notify("该手机号已被注册!!!");
            this.phone = "";
          }
        })
        .catch((err) => {
          // console.log(err);
        });
    },
    gohome() {
      this.$router.push({
        name: "home",
      });
    },
  },
  updated() {
    // 按钮显示
    this.phone.length == 11 && this.checked == true
      ? (this.btn_dis = false)
      : (this.btn_dis = true);
  },
  // mounted() {
  //   this.$notify("该手机号已被注册!!!");
  // },
};
</script>

<style scoped>
.register {
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
  /* background: url("../assets/header.png") no-repeat 0 0; */
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
  line-height: 100px;
  width: 100%;
  height: 100px;
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

/* 复选框 */
.checkbox {
  text-align: center;
  margin: 20px auto;
  font-size: 12px;
  width: 80%;
  /* background-color: aqua; */
}

.list {
  font-size: 14px;
  text-align: center;
  color: #cbcbcb;
  margin: 50px auto;
  width: 80%;
  padding-bottom: 20px;
  /* background-color: aqua; */
}
.icon_list {
  display: flex;
  margin: 20px auto;
  /* width: 80%; */
  /* background-color: aqua; */
}
.icon_list .img_list {
  text-align: center;
  flex: 1;
}
.icon_list .img_list img {
  width: 70%;
}
</style>
