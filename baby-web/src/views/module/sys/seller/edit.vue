<template>
  <sys-seller-detail :is-edit="true" :id="id"/>
</template>

<script>
import SysSellerDetail from './components/SysSellerDetail'

export default {
  components: { SysSellerDetail },
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
