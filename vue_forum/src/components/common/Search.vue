<template>
  <div class="search">
    <!-- 搜索位置 -->
    <van-search
      @cancel="cancel"
      show-action
      v-model="value"
      placeholder="大家都在搜索:OG掉线重连"
      @search="search"
    />

    <!-- 搜索历史 -->
    <div class="search_history" v-show="history_list">
      <div class="history_top">
        <span class="top_left"><b>搜索历史</b></span>
        <span class="top_right"
          ><span class="iconfont icon-delete"></span
        ></span>
      </div>
      <div class="history_bottom">
        <span v-for="s in his_list" :key="s.id">{{ s.content }}</span>
      </div>
    </div>

    <!-- 标签页 -->
    <van-tabs v-model="active" animated title-active-color="red">
      <van-tab title="热搜榜">
        <div class="hot_list">
          <van-cell v-for="i in 12" :key="i">
            <!-- 使用 title 插槽来自定义标题 -->
            <template #title>
              <span class="hot_px">{{ i }}</span>
              <span class="custom-title"
                >{{ i * 2 }}E {{ i ** 3 }}DG {{ i + i }} DRX</span
              >
              <span class="hot_wz">热</span>
            </template>
            <template #default>
              <van-icon class="iconfont icon-huore" color="#ee0a24" />
              <span class="hot_hot">122.5W</span>
            </template>
          </van-cell>
        </div>
      </van-tab>
      <van-tab title="热吧榜">
        <div class="hot_list">
          <van-cell v-for="i in 12" :key="i">
            <!-- 使用 title 插槽来自定义标题 -->
            <template #title>
              <span class="hot_px">{{ i }}</span>
              <span class="custom-title">EDG DRX</span>
              <span class="hot_wz">热</span>
            </template>
            <template #default>
              <van-icon name="chat-o" color="#ee0a24" />
              <span class="hot_hot">122.5W</span>
            </template>
          </van-cell>
        </div>
      </van-tab>
      <van-tab title="游戏榜">
        <div class="hot_list">
          <van-cell v-for="i in 12" :key="i">
            <!-- 使用 title 插槽来自定义标题 -->
            <template #title>
              <span class="hot_px">{{ i }}</span>
              <span class="custom-title">EDG DRX</span>
              <span class="hot_wz">热</span>
            </template>
            <template #default>
              <van-icon name="chat-o" color="#ee0a24" />
              <span class="hot_hot">122.5W</span>
            </template>
          </van-cell>
        </div>
      </van-tab>
      <van-tab disabled></van-tab>
      <van-tab disabled></van-tab>
      <van-tab disabled></van-tab>
      <van-tab disabled></van-tab>
      <van-tab disabled></van-tab>
    </van-tabs>
  </div>
</template>

<script>
import { Toast } from "vant";
export default {
  name: "Search",
  data() {
    return {
      // 搜索历史
      his_list: [
        // { id: 1, content: "我是搜索1" },
        // { id: 2, content: "我是搜索2" },
        // { id: 3, content: "我是搜索3" },
        // { id: 4, content: "我是搜索4" },
        // { id: 5, content: "我是搜索5" },
      ],
      history_list: true,
      value: "",
      active: 0,
    };
  },

  mounted() {
    console.log(this.his_list.length);
    if (this.his_list.length > 0) {
      this.history_list = true;
    } else {
      this.history_list = false;
    }
  },
  methods: {
    search() {
      if (this.value.length <= 0) {
        Toast("搜索的内容不能为空");
      } else {
        this.$router.push({
          name: "searchresult",
          params: {
            value: this.value,
          },
        });
      }
    },
    cancel() {
      console.log(123);
      this.$router.push({
        name: "enterbar",
      });
    },
  },
};
</script>

<style scoped>
.van-search {
  padding: 0 0 0 0;
  border-bottom: 1px solid #e2e2e2;
}
.van-cell {
  background-color: rgba(123, 32, 1, 0);
  padding: 10px 5px;
}
.van-search__content {
  background-color: white;
}
.van-search__action {
  color: rgb(0, 153, 255);
}

van-cell {
  background-color: blueviolet;
}

/* 搜索历史 */
.search_history {
  margin: 10px auto;
  width: 90%;
  /* background-color: #ff5757; */
}
.history_top {
  font-size: 15px;
  display: flex;
}
.history_top span:nth-child(1) {
  flex: 1;
}
.history_bottom {
  margin-top: 5px;
  /* line-height: 30px; */
}
.history_bottom span {
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;

  display: inline-block;
  max-width: 100px;
  margin: 3px;
  font-size: 12px;
  padding: 5px 10px 5px 10px;
  border-radius: 25px;
  background-color: #efefef;
}

/* 内容 */
/* 热搜榜 */
.hot_list {
  border-radius: 15px;
  margin: 5px auto;
  width: 98%;
  /* background-color: aqua; */
  background: linear-gradient(to top, #ffffff, #fff2f2);
  box-shadow: 0px 2px 3px 0px #00000021;
}
.custom-title {
  margin-right: 4px;
  vertical-align: middle;
}
.van-cell::after {
  border-bottom: 0 solid #ebedf0;
}

.hot_px {
  border-radius: 5px;
  margin-right: 5px;
  font-size: 7px;
  display: inline-block;
  width: 20px;
  height: 20px;
  line-height: 20px;
  text-align: center;
  color: white;
  background-color: #c1c1c1;
}
.van-cell:nth-child(1) .hot_px:nth-child(1) {
  background-color: red;
}
.van-cell:nth-child(2) .hot_px:nth-child(1) {
  background-color: #ff6207;
}
.van-cell:nth-child(3) .hot_px:nth-child(1) {
  background-color: #ffb52d;
}
.hot_wz {
  display: inline-block;
  width: 16px;
  height: 16px;
  line-height: 16px;
  text-align: center;
  color: white;
  background-color: #ff5757;
  border-radius: 3px;
}
.hot_hot {
  margin-left: 5px;
}
</style>
