<template>
  <goods-detail :is-edit='true'/>
</template>

<script>
  import GoodsDetail from './goodsDetail'

  export default {
    name: 'goodsEdit',
    components: { GoodsDetail }
  }
</script>
