<template>
  <exchange-detail :is-edit='true'/>
</template>

<script>
  import ExchangeDetail from './exchangeDetail'

  export default {
    name: 'createExchangeForm',
    components: { ExchangeDetail }
  }

</script>
