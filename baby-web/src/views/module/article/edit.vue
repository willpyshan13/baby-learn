<template>
  <article-detail :is-edit='true'/>
</template>

<script>
  import ArticleDetail from './articleDetail'

  export default {
    name: 'editForm',
    components: { ArticleDetail }
  }
</script>
