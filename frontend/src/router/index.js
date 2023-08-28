import { createRouter, createWebHistory } from "vue-router";
import NotFoundView from "../views/NotFoundView.vue";
import HomeView from "../views/HomeView.vue";
import AboutView from "../views/AboutView.vue";
import LoginView from "../views/LoginView.vue";
import RegisterView from "../views/RegisterView.vue";
import DashboardView from "../views/DashboardView.vue";
import ProjectView from "../views/ProjectView.vue";
import NewProjectView from "../views/NewProjectView.vue";
import SettingsProjectView from "../views/SettingsProjectView.vue";

const requireAuth = (to, from, next) => {
  localStorage.getItem("logged_in") === "true"
    ? next()
    : next({ name: "login" });
};

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/about",
    name: "about",
    component: AboutView,
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
  },
  {
    path: "/dashboard",
    name: "dashboard",
    component: DashboardView,
    beforeEnter: requireAuth,
  },
  {
    path: "/project/:projectId",
    name: "project",
    props: true,
    component: ProjectView,
    beforeEnter: requireAuth,
  },
  {
    path: "/project/:projectId/settings",
    name: "settingsproject",
    props: true,
    component: SettingsProjectView,
    beforeEnter: requireAuth,
  },
  {
    path: "/project/new",
    name: "newproject",
    component: NewProjectView,
    beforeEnter: requireAuth,
  },
  {
    path: "/:catchAll(.*)",
    name: "notfound",
    component: NotFoundView,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
