<template>
    <goods-detail :is-edit='false'/>
</template>

<script>
  import GoodsDetail from './goodsDetail'

  export default {
    name: 'goodsCreate',
    components: { GoodsDetail }
  }

</script>
