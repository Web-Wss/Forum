<template>
  <div class="news">
    <van-tabs v-model="active" animated>
      <van-tab disabled></van-tab>
      <van-tab disabled></van-tab>
      <van-tab disabled></van-tab>
      <van-tab title="消息">
        <van-cell title="@我的">
          <template #icon>
            <span class="iconfont icon-aite"></span>
          </template>
          <!-- 使用 right-icon 插槽来自定义右侧图标 -->
          <template #right-icon>
            <van-icon name="arrow" class="search-icon" />
          </template>
        </van-cell>
        <van-cell title="点赞">
          <template #icon>
            <span class="iconfont icon-icon"></span>
          </template>
          <!-- 使用 right-icon 插槽来自定义右侧图标 -->
          <template #right-icon>
            <van-icon name="arrow" class="search-icon" />
          </template>
        </van-cell>
      </van-tab>
      <van-tab title="聊天"></van-tab>
      <van-tab title="通知"></van-tab>
      <van-tab disabled></van-tab>
      <van-tab disabled>
        <template #title>
          <van-icon size="20" class="iconfont icon-xiaoxi"
        /></template>
      </van-tab>
    </van-tabs>

    <TabBar />
  </div>
</template>

<script>
import TabBar from "../components/common/TabBar.vue";

export default {
  name: "News",
  data() {
    return {
      active: 3,
    };
  },
  components: { TabBar },
};
</script>

<style scoped>
/* icon */
.icon-xiaoxi {
  color: black;
}
.icon-aite {
  margin-right: 10px;
  text-align: center;
  border-radius: 20px;
  width: 25px;
  background-color: red;
  color: white;
}
.icon-icon {
  margin-right: 10px;
  text-align: center;
  border-radius: 20px;
  width: 25px;
  background-color: blue;
  color: white;
}
</style>
