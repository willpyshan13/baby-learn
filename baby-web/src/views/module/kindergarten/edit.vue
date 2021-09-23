<template>
  <kindergarten-info-detail :is-edit='true' :id="id"/>
</template>

<script>
import KindergartenInfoDetail from './KindergartenDetail'

export default {
  name: 'UpdateKindergartenDetail',
  components: { KindergartenInfoDetail },
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
