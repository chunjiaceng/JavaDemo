import GlobalUpload from "@/components/GlobalUpload";
import VueRouter from "vue-router";
const routes = [
  // 主路由
  {
    path: "/",
    name: "GlobalUpload",
    component: GlobalUpload,
  },
];

const router = new VueRouter({
  routes,
  mode: "history",
});
export default router;
