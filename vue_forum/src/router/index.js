// 引入vue-router
import VueRouter from "vue-router";
// 引入store
import store from "../store";
// 引入工具:storage.js
import storage from "../utils/storage";

// 引入页面组件
import Login from "../pages/Login.vue";
import Register from "../pages/Register.vue";
import Perfect from "../components/subpages/Perfect.vue";
import My from "../pages/My.vue";
import Setting from "../components/subpages/Setting.vue";
import Information from "../components/subpages/Information.vue";
import EditNickName from "../components/subpages/EditNickName.vue";
import News from "../pages/News.vue";
import Search from "../components/common/Search.vue";
import EnterBar from "../pages/EnterBar.vue";
import Publish from "../pages/Publish.vue";
import Home from "../pages/Home.vue";
import Postsdetail from "../components/subpages/Postsdetail.vue";
import PostsHistory from "../components/subpages/PostsHistory.vue";
import UserLike from "../components/subpages/UserLike.vue";
import LikeUser from "../components/subpages/LikeUser.vue";
import SearchResult from "../components/subpages/SearchResult.vue";
import LikeBar from "../components/subpages/LikeBar.vue";
import MyPosts from "../components/subpages/MyPosts.vue";
import MySave from "../components/subpages/MySave.vue";
import Bar from "../components/subpages/Bar.vue";
import MyZy from "../components/subpages/Myzy.vue";
const router = new VueRouter({
  routes: [
    {
      path: "/",
      redirect: "login",
    },
    {
      name: "login",
      path: "/login",
      component: Login,
      meta: {
        title: "登录",
        requireAuth: false,
      },
    },
    {
      name: "register",
      path: "/register",
      component: Register,
      meta: {
        title: "注册",
        requireAuth: false,
      },
    },
    {
      name: "perfect",
      path: "/perfect",
      component: Perfect,
      meta: {
        title: "完善个人信息",
        requireAuth: false,
      },
    },
    {
      name: "my",
      path: "/my",
      component: My,
      meta: {
        title: "我的",
        requireAuth: true,
      },
    },
    {
      name: "myzy",
      path: "/myzy",
      component: MyZy,
      meta: {
        title: "我的主页",
        requireAuth: false,
      },
    },
    {
      name: "setting",
      path: "/setting",
      component: Setting,
      meta: {
        title: "设置",
        requireAuth: true,
      },
    },
    {
      name: "information",
      path: "/information",
      component: Information,
      meta: {
        title: "个人信息",
        requireAuth: true,
      },
    },
    {
      name: "editnickname",
      path: "/editnickname",
      component: EditNickName,
      meta: {
        title: "修改昵称",
        requireAuth: true,
      },
    },
    {
      name: "news",
      path: "/news",
      component: News,
      meta: {
        title: "消息",
        requireAuth: true,
      },
    },
    {
      name: "search",
      path: "/search",
      component: Search,
      meta: {
        title: "搜索",
      },
    },
    {
      name: "enterbar",
      path: "/enterbar",
      component: EnterBar,
      meta: {
        title: "进吧",
        requireAuth: false,
      },
    },
    {
      name: "publish",
      path: "/publish",
      component: Publish,
      meta: {
        title: "发布",
        requireAuth: true,
      },
    },
    {
      name: "home",
      path: "/home",
      component: Home,
      meta: {
        title: "首页",
        requireAuth: false,
      },
    },
    {
      name: "postsdetail",
      path: "/postsdetail",
      component: Postsdetail,
      meta: {
        title: "帖子详情",
        requireAuth: false,
      },
    },
    {
      name: "postshistory",
      path: "/postshistory",
      component: PostsHistory,
      meta: {
        title: "浏览历史",
        requireAuth: true,
      },
    },
    {
      name: "userlike",
      path: "/userlike",
      component: UserLike,
      meta: {
        title: "我关注的人",
        requireAuth: true,
      },
    },
    {
      name: "likeuser",
      path: "/likeuser",
      component: LikeUser,
      meta: {
        title: "我的粉丝",
        requireAuth: true,
      },
    },
    {
      name: "searchresult",
      path: "/searchresult",
      component: SearchResult,
      meta: {
        title: "搜索结果",
        requireAuth: false,
      },
    },
    {
      name: "likebar",
      path: "/likebar",
      component: LikeBar,
      meta: {
        title: "关注的吧",
        requireAuth: true,
      },
    },
    {
      name: "myposts",
      path: "/myposts",
      component: MyPosts,
      meta: {
        title: "帖子",
        requireAuth: true,
      },
    },
    {
      name: "mysave",
      path: "/mysave",
      component: MySave,
      meta: {
        title: "我的收藏",
        requireAuth: true,
      },
    },
    {
      name: "bar",
      path: "/bar",
      component: Bar,
      meta: {
        title: "贴吧页",
        requireAuth: false,
      },
    },
  ],
});

// 设置路由守卫，在进页面之前，判断有token，才进入页面，否则返回登录页面
if (storage.get("token")) {
  store.commit("setToken", storage.get("token"));
}

router.beforeEach((to, from, next) => {
  // 判断要去的路由有没有requireAuth
  if (to.matched.some((r) => r.meta.requireAuth)) {
    if (store.state.token) {
      next(); //有token，进行request请求，后台还会验证token
    } else {
      router.push({
        path: "/login",
        // 将刚刚要去的路由path作为参数，方便直接跳转
        query: { redirect: to.fullPath },
      });
    }
  } else {
    next(); //如果无需token，放行
  }
});

router.afterEach((to, from) => {
  document.title = to.meta.title;
});

export default router;
