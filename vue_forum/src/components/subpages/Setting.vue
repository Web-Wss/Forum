<template>
  <div class="setting">
    <van-nav-bar @click-left="gomy" title="设置">
      <template #left>
        <van-icon color="#939393" name="arrow-left" size="25" />
      </template>
    </van-nav-bar>
    <div class="list_box">
      <div style="padding: 10px 0 10px">
        <van-cell class="list" size="large" title="账号安全" is-link />
      </div>
      <div style="padding: 10px 0 10px">
        <van-cell size="large" title="个人资料" to="Information" is-link>
          <slot>
            <img style="width: 20px" :src="avatar_path" alt="" />
          </slot>
        </van-cell>
        <van-cell class="list" size="large" title="账号管理" is-link />
      </div>
      <div style="padding: 10px 0 10px">
        <van-cell
          class="list"
          size="large"
          title="浏览设置"
          value="图片显示、字号等"
          is-link
        />
        <van-cell class="list" size="large" title="消息提醒" is-link />
        <van-cell class="list" size="large" title="隐私设置" is-link />
        <van-cell class="list" size="large" title="辅助功能" is-link />
      </div>
      <van-cell
        class="list"
        size="large"
        title="版本信息"
        value="9.1.0.0"
        is-link
      />
      <van-cell class="list" size="large" title="意见反馈" is-link />
      <van-button @click="logout" type="primary" size="large" color="white"
        ><span style="color: #67a6f2">退出</span></van-button
      >
    </div>
  </div>
</template>

<script>
export default {
  name: "Setting",
  data() {
    return {
      // 用户头像
      avatar_path: "",
    };
  },
  mounted() {
    this.avatar_path =
      "http://localhost:8080/statics/user_avatar/" +
      localStorage.getItem("avatar");
  },
  methods: {
    // 退出登录
    logout() {
      localStorage.clear();
      this.$router.push({
        name: "login",
      });
    },
    // 点击左箭头，返回到设置页
    gomy() {
      this.$router.push({
        name: "my",
      });
    },
  },
};
</script>

<style scoped>
.list_box {
  width: 100%;
  background-color: #f2f3f5;
}
</style>
