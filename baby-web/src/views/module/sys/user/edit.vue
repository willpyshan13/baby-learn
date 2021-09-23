<template>
  <sys-user-detail :is-edit="true" :id="id"/>
</template>

<script>
import SysUserDetail from './components/SysUserDetail'

export default {
  components: { SysUserDetail },
  data() {
    return {
      id: ''
    }
  },
  created() {
    this.id = Number(this.$route.params && this.$route.params.id)
  }
}
</script>
