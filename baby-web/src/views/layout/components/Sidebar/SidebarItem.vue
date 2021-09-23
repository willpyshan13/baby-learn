<template>
  <div class="menu-wrapper">
    <template v-for="item in routes">
      <router-link v-if="!item.list[0] && item.perms === 'main'" :to="item.url" :key="item.name">
        <el-menu-item :index="item.name" :class="{'submenu-title-noDropdown':true}">
          <svg-icon v-if="item.icon" :icon-class="item.icon"/>
          <span slot="title" v-if="item.name">{{item.name}}</span>
        </el-menu-item>
      </router-link>

      <el-submenu v-else :index="item.name" :key="item.name">
        <template slot="title">
          <svg-icon v-if="item.icon" :icon-class="item.icon"/>
          <span v-if="item.name">{{item.name}}</span>
        </template>

        <template v-for="child in item.list">
          <router-link :to="child.url" :key="child.name">
            <el-menu-item :index="child.url">
              <svg-icon v-if="child.icon" :icon-class="child.icon"/>
              <span v-if="child.name">{{child.name}}</span>
            </el-menu-item>
          </router-link>
        </template>

      </el-submenu>
    </template>
  </div>
</template>

<script>
  import { generateTitle } from '@/utils/i18n'
  export default {
    name: 'SidebarItem',
    props: {
      routes: {
        type: Array
      },
      isNest: {
        type: Boolean,
        default: false
      }
    },
    methods: {
      hasOneShowingChildren(children) {
        const showingChildren = children.filter(item => {
          return !item.hidden
        })
        if (showingChildren.length === 1) {
          return true
        }
        return false
      },
      generateTitle
    }
  }
</script>
