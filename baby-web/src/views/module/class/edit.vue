<template>
  <class-info-detail :is-edit='true' :id="id"/>
</template>

<script>
import ClassInfoDetail from './components/ClassInfoDetail'

export default {
  name: 'UpdateClassInfoDetail',
  components: { ClassInfoDetail },
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
