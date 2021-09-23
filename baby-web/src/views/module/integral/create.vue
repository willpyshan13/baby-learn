<template>
  <exchange-detail :is-edit='false'/>
</template>

<script>
  import ExchangeDetail from './exchangeDetail'

  export default {
    name: 'createExchangeForm',
    components: { ExchangeDetail }
  }

</script>
