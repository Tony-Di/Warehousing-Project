import { createRouter, createWebHistory } from 'vue-router';
import Login from "@/components/Loginpage.vue"
import User from "@/components/Userpage.vue"
import Stockin from "@/components/Stockinpage.vue"
import Stockout from "@/components/Stockoutpage.vue"
import Manager from "@/components/ManagerPage.vue"
import Admin from "@/components/AdminPage.vue";

const routes = [
    {path:"/",redirect:"/login"},
    {path:"/login", component:Login},
    {path:"/Stockin", component:Stockin},
    {path:"/Stockout", component:Stockout},
    {path:"/Manager", component:Manager},
    {path:"/Admin", component:Admin},
    {path:"/User", component:User},
]


const router = createRouter({
    history: createWebHistory(),
    routes
});
export default  router

