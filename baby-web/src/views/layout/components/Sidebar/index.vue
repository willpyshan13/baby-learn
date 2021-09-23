<template>
  <scroll-bar>
    <el-menu mode="vertical" unique-opened :default-active="$route.path" :collapse="isCollapse" background-color="#304156" text-color="#fff" active-text-color="#409EFF">
      <sidebar-item :routes="routes"></sidebar-item>
    </el-menu>
  </scroll-bar>
</template>

<script>
import { mapGetters } from 'vuex'
import SidebarItem from './SidebarItem'
import ScrollBar from '@/components/ScrollBar'
import { getMenuList } from '@/api/table'

export default {
  data() {
    return {
      list: null
    }
  },
  components: { SidebarItem, ScrollBar },
  computed: {
    ...mapGetters([
      'sidebar'
    ]),
    routes() {
      // return this.$router.options.routes
      return this.list
    },
    isCollapse() {
      return !this.sidebar.opened
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      getMenuList().then(response => {
        this.list = response.data.menuList
      })
    }
  }
}
</script>
