import { createRouter, createWebHistory } from 'vue-router';
import Login from "@/components/Loginpage.vue"
import User from "@/components/Userpage.vue"

const routes = [
    {path:"/",redirect:"/login"},
    {path:"/login", component:Login},
    {path:"/userpage/:id", component:User},

]


const router = createRouter({
    history: createWebHistory(),
    routes
});
export default  router

