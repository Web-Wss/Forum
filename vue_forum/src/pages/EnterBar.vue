<template>
  <div class="enterbar">
    <van-nav-bar title="进吧">
      <template #right>
        <van-icon class="iconfont icon-qiandao" size="25" />
      </template>
    </van-nav-bar>
    <!-- 搜索位置 -->
    <van-search
      readonly
      @focus="goSearch"
      v-model="value"
      placeholder="大家都在搜索:OG掉线重连"
    />
    <!-- 我关注的吧 -->
    <div class="my_attention">
      <div class="title">我关注的吧</div>
      <van-row>
        <van-cell-group>
          <van-col span="12" v-for="i in userlikebar" :key="i.posts_bar_id"
            ><van-cell :title="i.posts_bar_name">
              <slot
                ><svg class="icon" aria-hidden="true">
                  <use xlink:href="#icon-zuanshi"></use></svg></slot
            ></van-cell>
          </van-col>
        </van-cell-group>
      </van-row>
    </div>
    <!-- 探索更多有趣内容 -->
    <van-button @click="goSearch" color="white" plain size="large"
      >+ 探索更多有趣内容</van-button
    >
    <TabBar />
  </div>
</template>

<script>
import TabBar from "../components/common/TabBar.vue";
export default {
  name: "EnterBar",
  components: { TabBar },
  data() {
    return {
      value: "",
      user_id: 0,
      userlikebar: [],
    };
  },
  mounted() {
    this.user_id = localStorage.getItem("user_id");
    // 获取用户关注的吧
    this.$http.post(`/user/userlikebar?user_id=${this.user_id}`).then((res) => {
      this.userlikebar = res.data.data;
    });
  },
  methods: {
    // 去搜索组件
    goSearch() {
      this.$router.push({
        name: "search",
      });
    },
  },
};
</script>

<style scoped>
.van-nav-bar__right .icon-qiandao {
  color: black;
}
.icon {
  width: 3em;
  height: 1em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}
.van-search {
  padding: 0;
}
/* 我关注的吧 */
.enterbar .my_attention {
  padding: 7px;
}
.enterbar .my_attention .title {
  margin-left: 5px;
  width: 100%;
  height: 30px;
  font-size: 14px;
  color: #bdbdbd;
}

.van-cell {
  padding: 10px 5px;
}

.van-button__content {
  font-size: 14px;
  color: #0076ff;
}
</style>
