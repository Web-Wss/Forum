<template>
  <div class="publish">
    <!-- 头部 -->
    <van-nav-bar
      @click-left="onClickLeft"
      @click-right="onClickRight"
      title="发布帖子"
      right-text="发布"
      left-arrow
    >
      <template #left>
        <van-icon name="cross" size="20" />
      </template>
    </van-nav-bar>

    <!-- 选择吧 -->
    <van-cell title="选择吧" icon="aim" @click="showpopup">
      <!-- 使用 right-icon 插槽来自定义右侧图标 -->
      <template #right-icon>
        <van-icon name="arrow" class="arrow-icon" />
      </template>
      <template #title
        ><div class="change">选择吧</div>
        <div class="changebar">{{ changebar }}</div>
      </template>
    </van-cell>

    <van-action-sheet
      title="选择吧"
      v-model="show"
      position="bottom"
      round
      :style="{ height: '80%' }"
    >
      <van-cell
        @click="barid(p)"
        v-for="(p, i) in posts_bar_list"
        :key="p.posts_bar_id"
      >
        <!-- 使用 right-icon 插槽来自定义右侧图标 -->
        <template #title>
          <div class="list">
            <div class="bar_logo">
              <img
                :src="
                  'http://localhost:8080/statics/posts_bar_logo/' +
                  p.posts_bar_logo_path
                "
                alt=""
              />
            </div>
            <div class="bar_title">{{ p.posts_bar_name }}</div>
          </div>
        </template>
      </van-cell>
    </van-action-sheet>

    <!-- 添加标题 -->
    <b>
      <van-field
        v-model="titlevalue"
        rows="1"
        autosize
        type="textarea"
        placeholder="加个标题呦~"
      />
    </b>

    <!-- 发布的内容 -->
    <van-field
      v-model="contentvalue"
      rows="3"
      autosize
      type="textarea"
      placeholder="来吧,尽情发挥吧..."
    />

    <!-- 上传图片 -->
    <van-uploader
      upload-text="限3张(每张<=10M)"
      :max-count="3"
      :after-read="afterRead"
      preview-size="120"
      v-model="fileList"
      multiple
    />

    <!-- 遮罩层 -->
    <van-overlay :show="zzc">
      <div class="wrapper" @click.stop>
        <div class="block">
          <van-loading color="#0094ff" size="60" vertical
            >发布中...</van-loading
          >
        </div>
      </div>
    </van-overlay>
  </div>
</template>

<script>
import { Uploader } from "vant";
import upLoadImgPublish from "../utils/upLoadImgPublish";
export default {
  name: "Publish",
  data() {
    return {
      // 遮罩层
      zzc: false,
      // 选择合适的...
      changebar: "选择合适的吧会有更多的赞哦~",
      // 弹出层
      show: false,
      // 内容
      contentvalue: "",
      bar_id: 0,
      // 标题
      titlevalue: "",
      // 吧表
      posts_bar_list: [],
      fileList: [
        // { url: "https://img01.yzcdn.cn/vant/leaf.jpg" },
        // // Uploader 根据文件后缀来判断是否为图片文件
        // // 如果图片 URL 中不包含类型信息，可以添加 isImage 标记来声明
        // { url: "https://cloud-image", isImage: true },
      ],
    };
  },
  mounted() {
    this.postsbarall();
    this.changebar = this.$route.params.changebar;
    this.bar_id = this.$route.params.bar_id;
  },
  methods: {
    // 获取吧表数据
    async postsbarall() {
      const res = await this.$http
        .get(`/posts/postsbarall`)
        .then((res) => {
          console.log(res);
          this.posts_bar_list = res.data.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 选择吧
    showpopup() {
      this.show = true;
    },
    barid(p) {
      // console.log(p);
      this.changebar = p.posts_bar_name;
      this.bar_id = p.posts_bar_id;
      this.show = false;
      // this.bar_id = barid;
    },

    onClickLeft() {
      this.$router.push({
        name: "home",
      });
    },
    async onClickRight() {
      console.log(this.fileList);
      await upLoadImgPublish(
        this.fileList,
        this.bar_id,
        this.titlevalue,
        this.contentvalue
      );
      this.zzc = true;
      setTimeout(() => {
        this.$router.push({
          name: "home",
        });
      }, 1000);
    },
    afterRead(file) {
      // 此时可以自行将文件上传至服务器
      // this.fileList = file;
      // console.log(file.file);
    },
  },
};
</script>

<style scoped>
.custom-title {
  margin-right: 4px;
  vertical-align: middle;
}

.arrow-icon {
  font-size: 16px;
  line-height: inherit;
}

/* 选择吧 */
.change {
  display: inline-block;
}
.changebar {
  padding: 0 10px 0;
  margin-left: 10px;
  display: inline-block;
  border-radius: 20px;
  background-color: #efefef78;
  color: #bbbbbb;
  font-size: 10px;
}

.list {
  display: flex;
  width: 100%;
  /* background-color: aqua; */
}
.list .bar_logo {
  margin-right: 10px;
  text-align: center;
  width: 40px;
  height: 40px;
  margin-top: 5px;
}
.list .bar_logo img {
  border-radius: 10px;
  width: 35px;
  height: 35px;
}
.list .bar_title {
  flex: 1;
  line-height: 40px;
}

/* 遮罩层*/
.wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.block {
  border-radius: 20px;
  width: 100px;
  height: 100px;
  background-color: rgb(135, 41, 41);
}
</style>
