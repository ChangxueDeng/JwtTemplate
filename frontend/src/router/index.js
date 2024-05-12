import {createRouter ,createWebHistory} from "vue-router";
import {unAuthorized} from "@/net/index.js";

const routes = [
    {
        path: '/',
        name: 'welcome',
        component: () => import("@/views/WelcomePage.vue"),
        children:[
            {
                path: '',
                name: 'welcome-login',
                component: () => import("@/components/welcome/LoginPage.vue")
            },
            {
                path: '/register',
                name: 'welcome-register',
                component: () => import("@/components/welcome/RegisterPaga.vue")
            },
            {
                path: '/forget',
                name: 'welcome-forget',
                component: () => import("@/components/welcome/ForgetPage.vue"),
            }
        ]
    },
    {
        path: '/index',
        name: 'index',
        component: () => import("@/views/IndexPage.vue")
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})



router.beforeEach((to, from, next)=>{
    //判断是否登陆
    const isUnAuthorized = unAuthorized()
    if(to.name.startsWith('welcome-') && !isUnAuthorized){
        next('/index')
    }else if(to.fullPath.startsWith('/index') && isUnAuthorized){
        console.log(11)
        next('/')
    }else {
        next()
    }
})

export default router