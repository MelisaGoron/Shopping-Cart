import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate";
Vue.use(Vuex)

export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: {
    userId: null,
  },
  getters: {
    getId: (state) => {
      return state.userId;
    },
  },
  mutations: {
    setUserId(state, userId) {
      state.userId = userId;
    },
  },
  actions: {
    login(context, userId)
    {
      context.commit('setUserId', userId);
    },
    logout(context)
    {
      context.commit('setUserId', null);
    }
  },
  modules: {
  }
})
