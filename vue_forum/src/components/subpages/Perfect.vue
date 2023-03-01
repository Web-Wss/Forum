<template>
  <div class="perfect">
    <van-nav-bar @click-left="goregister" title="完善账号信息">
      <template #left>
        <van-icon color="#939393" name="arrow-left" size="25" />
      </template>
    </van-nav-bar>
    <!-- 标题 -->
    <div class="setting">
      <div class="big_wz">请设置用户名和密码</div>
      <div class="small_wz">使用用户名/手机号+密码可登录百度系所有产品</div>
    </div>

    <!-- 输入框 -->
    <van-form @submit="onSubmit">
      <van-field
        v-model="username"
        @blur="blur_username"
        name="username"
        label="用户名"
        placeholder="用户名"
        :rules="[{ required: true, message: '请输入用户名' }]"
      />
      <van-field
        v-model="password"
        type="password"
        name="password"
        label="密码"
        placeholder="密码"
        :rules="[{ required: true, message: '请填写密码' }]"
      />
      <div style="margin: 36px">
        <van-button round block type="info" native-type="submit"
          >提交</van-button
        >
      </div>
    </van-form>
    <!-- 登录中 -->
    <van-overlay :show="show" @click="show = false">
      <div class="wrapper" @click.stop>
        <div class="block">
          <van-loading text-color="white" color="#1989fa" size="30px" vertical
            >登录中...</van-loading
          >
        </div>
      </div>
    </van-overlay>
  </div>
</template>

<script>
export default {
  name: "Perfect",
  data() {
    return {
      // 遮罩层
      show: false,
      username: "",
      password: "",
      rules: [{ required: true, message: "该用户名已被使用" }],
    };
  },
  methods: {
    // 验证用户名是否重复
    async blur_username() {
      const res = await this.$http
        .post(`/user/register/username?username=${this.username}`)
        .then((res) => {
          if (res.data.data == "重复") {
            this.$notify("该用户名已被使用了哦，换一个试试吧~");
            this.username = "";
          }
        })
        .catch((err) => {
          // console.log(err);
        });
    },
    // 提交按钮
    async onSubmit() {
      let phone = this.$router.history.current.params.phone;
      const res = this.$http
        .post(
          `/user/register/register?username=${this.username}&password=${this.password}&phone=${phone}`
        )
        .then((res) => {
          console.log(res.data.data);
          if (res.data.data == "1") {
            this.show = true;
            setTimeout(() => {
              this.$http
                .post(
                  `/user/login?username=${this.username}&password=${this.password}`
                )
                .then((res) => {
                  // console.log(res);
                  this.$store.commit("setToken", res.data.data);
                  this.$router.push({
                    name: "my",
                  });
                });
            }, 500);
          }
        })
        .catch((err) => {
          // console.log(err);
        });
    },
    // 去注册页面
    goregister() {
      this.$router.push({
        name: "register",
      });
    },
  },
};
</script>

<style scoped>
.setting {
  padding: 30px 30px;
  height: 70px;
  /* background-color: aqua; */
}
.setting .big_wz {
  font-size: 22px;
  font-weight: 600;
}
.setting .small_wz {
  margin-top: 5px;
  font-size: 12px;
  color: #a6a6a6;
}

/* 遮罩层 */
.wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.block {
  border-radius: 20px;
  width: 120px;
  height: 120px;
  background-color: rgb(0, 0, 0);
}

.van-loading {
  margin-top: 32px;
}
</style>
