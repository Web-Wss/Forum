<template>
  <div class="editnickname">
    <van-nav-bar
      title="修改昵称"
      left-arrow
      @click-left="onClickLeft"
    ></van-nav-bar>
    <van-cell-group>
      <van-field v-model="value" placeholder="改个昵称呗（3-7个字之间哦）" />
    </van-cell-group>
    <ul>
      <li style="color: red">昵称为空默认使用原用户名</li>
      <li>昵称需要包括表情，记得加哦</li>
      <li>昵称每七天可以修改一次，会员用户每天可修改一次</li>
      <li>
        昵称提交后如果未通过审核，我们会将昵称暂时重置，此时您可以修改后再次提交审核
      </li>
    </ul>
    <van-button
      style="width: 30%; margin: 20px auto; display: block"
      plain
      hairline
      type="info"
      @click="save"
      >保存</van-button
    >
  </div>
</template>

<script>
export default {
  name: "EditNickName",
  data() {
    return {
      user_id: 0,
      value: "",
    };
  },
  mounted() {
    this.user_id = localStorage.getItem("user_id");
  },
  methods: {
    // 保存按钮
    async save() {
      await this.$http
        .post(
          `/user/editusernickname?user_id=${this.user_id}&nickname=${this.value}`
        )
        .then((res) => {
          console.log(res);
          localStorage.setItem("nickname", this.value);
          this.$router.push({
            name: "information",
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    onClickLeft() {
      this.$router.push({
        name: "information",
      });
    },
  },
};
</script>

<style scoped>
.nick_name .van-nav-bar .van-icon {
  color: black;
}
ul {
  margin: 15px;
  font-size: 1px;
  padding: 10px;
  list-style: disc;
}
ul li {
  margin-top: 5px;
}
</style>
