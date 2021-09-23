import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import user from './modules/user'
import getters from './getters'
import tagsView from './modules/tagsView'
import classInfo from './modules/classInfo'
import room from './modules/room'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    tagsView,
    user,
    classInfo,
    room
  },
  getters
})

export default store
