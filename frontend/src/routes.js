import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

const router = new Router({
  mode: "history", // Use browser history
  routes: [
    {
      path: "/",
      name: "Home",
      component: () => import("./components/ListAppliances")
    },
    {
      path: "/appliances",
      name: "Appliances",
      component: () => import("./components/ListAppliances")
    },
    {
      path: "/appliance/:id",
      name: "Appliance Details",
      component: () => import("./components/ApplianceDetails")
    }
  ]
});

export default router;