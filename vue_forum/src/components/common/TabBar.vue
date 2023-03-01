<template>
  <div>
    <van-tabbar route v-model="active">
      <van-tabbar-item class="iconfont icon-shouye1" to="/home"
        >首页</van-tabbar-item
      >
      <van-tabbar-item class="iconfont icon-wenbenbianji" to="/enterbar"
        >进吧</van-tabbar-item
      >
      <svg class="icon" aria-hidden="true" @click="gopublish">
        <use xlink:href="#icon-jiahao"></use>
      </svg>
      <van-tabbar-item class="iconfont icon-shouye" to="/news"
        >消息</van-tabbar-item
      >
      <van-tabbar-item class="iconfont icon-31wode" to="/my"
        >我的</van-tabbar-item
      >
    </van-tabbar>
  </div>
</template>

<script>
export default {
  name: "Tabbar",
  data() {
    return {
      active: 0,
    };
  },
  methods: {
    gopublish() {
      this.$router.push({
        name: "publish",
      });
    },
  },
};
</script>

<style>
/* Font class样式 */
.van-tabbar-item {
  font-size: 13px;
}
.van-tabbar-item::before {
  font-size: 22px;
}
/* Symbol样式 */
.icon {
  width: 4em;
  height: 3em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}
</style>
