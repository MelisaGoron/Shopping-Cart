import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import LoginItem from '../views/LoginItem.vue'
import UserHome from '../views/UserHome.vue'
import AdminHome from '../views/AdminHome.vue'
import AddProduct from '../views/AddProduct.vue'
import DeleteProduct from '../views/DeleteProduct.vue'
import BuyProduct from '../views/BuyProduct.vue'
import Register from '../views/Register.vue'
import GetOrders from '../views/GetOrders.vue'

Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
  {
    path: '/',
    name: 'LoginItem',
    component: LoginItem
  },
  {
    path: '/user',
    name: 'UserHome',
    component: UserHome
  },
  {
    path: '/admin',
    name: 'AdminHome',
    component: AdminHome
  },
  {
    path: '/add_product',
    name: 'AddProduct',
    component: AddProduct
  },
  {
    path: '/delete_product',
    name: 'DeleteProduct',
    component: DeleteProduct
  },
  {
    path: '/buy_product',
    name: 'BuyProduct',
    component: BuyProduct
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/orders',
    name: 'GetOrders',
    component: GetOrders
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
