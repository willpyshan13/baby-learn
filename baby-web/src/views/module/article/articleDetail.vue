<template>
    <div class="createPost-container">

      <el-form class="form-container" :rules="rules" ref="articleForm" :model="article" label-width="60px"
               style='width:90%;margin-left:20px;' size="mini">

        <div class="createPost-main-container">

          <el-row :gutter="10">
            <el-col :xs="4" :sm="3" :md="2" :lg="2" :xl="1" :offset="20">
              <el-button class="filter-item" type="success" size="small" @click="handlePublish">
                {{$t('table.publish')}}
              </el-button>
            </el-col>
            <el-col :xs="4" :sm="3" :md="2" :lg="2" :xl="1">
              <el-button class="filter-item" type="warning" size="small" @click="handleDraft">{{$t('table.draft')}}
              </el-button>
            </el-col>
          </el-row>

          <el-form-item style="margin-bottom: 40px;" label="标题:" prop="title">
            <el-input type="textarea" class="article-textarea" :rows="1" autosize placeholder="请输入标题"
                      v-model="article.title"/>
          </el-form-item>

          <el-row :gutter="10">
            <el-col :span="8">
              <el-form-item style="margin-bottom: 40px;" label="关键词:" prop="keys">
                <el-input type="textarea" class="article-textarea" :rows="1" autosize placeholder="请输入关键词"
                          v-model="article.keys"/>
                <span style="color: red; font-size: 12px;">多个关键词用" "(空格)分割</span>
              </el-form-item>
            </el-col>
            <el-col :span="8" :offset="1">
              <el-form-item style="margin-bottom: 40px;" label="作者:" prop="author">
                <el-input type="textarea" class="article-textarea" :rows="1" autosize placeholder="请输入描述"
                          v-model="article.author"/>
              </el-form-item>
            </el-col>
            <el-col :span="5" :offset="1">
              <el-form-item label="分类:" prop="type">
                <el-select clearable class="filter-item" v-model="article.type" placeholder="请选择" value="">
                  <el-option v-for="item in listArticleType" :key="item.code" :label="item.codeText"
                             :value="item.code">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="10">
            <el-col :span="8">
              <el-form-item style="margin-bottom: 40px;" label="标签:" prop="tags">
                <el-input type="textarea" class="article-textarea" :rows="1" autosize placeholder="请输入标签"
                          v-model="article.tags"/>
                <span style="color: red; font-size: 12px;">多个标签用"/"分割</span>
              </el-form-item>
            </el-col>
            <el-col :span="8" :offset="1">
              <el-form-item style="margin-bottom: 40px;" label="描述:" prop="description">
                <el-input type="textarea" class="article-textarea" :rows="1" autosize placeholder="请输入描述"
                          v-model="article.description"/>
              </el-form-item>
            </el-col>
            <el-col :span="5" :offset="1">
              <el-form-item label-width="60px" label="重要性:" class="postInfo-container-item">
                <el-rate style="margin-top:8px;" v-model="article.importance" :max='5' :colors="['#99A9BF', '#F7BA2A', '#FF9900']" :low-threshold="2"
                         :high-threshold="3">
                </el-rate>
              </el-form-item>
            </el-col>
          </el-row>

          <div class="editor-container">
            <tinymce :height=400 ref="editor" v-model="article.content"/>
          </div>
        </div>
      </el-form>
  </div>
</template>
<script>
  import { getArticleType, getArticleById, updateArticle } from '@/api/article'
  import Tinymce from '@/components/Tinymce'
  import { closeCurrentView } from '@/utils'

  const articleObject = {
    id: '',
    title: '',
    type: '',
    description: '',
    importance: 0,
    imageUrl: '',
    sendTime: '',
    author: '',
    editor: '',
    tags: '',
    keys: '',
    content: '',
    readerTimes: '',
    status: 0,
    remark: '',
    enable: '',
    codeText: ''
  }

  export default {
    name: 'articleDetail',
    components: { Tinymce },
    props: {
      isEdit: {
        type: Boolean,
        default: false
      }
    },
    created() {
      if (this.isEdit) {
        const id = this.$route.params && this.$route.params.id
        this.fetchData(id)
      } else {
        this.article = Object.assign({}, articleObject)
      }
    },
    mounted() {
      this.handleGetArticleType()
    },
    data() {
      const validateRequire = (rule, value, callback) => {
        if (value === '') {
          this.$message({
            message: rule.msg,
            type: 'error'
          })
          try {
            callback(null)
          } catch (e) {
            // ignore
          }
        } else {
          callback()
        }
      }
      return {
        article: Object.assign({}, articleObject),
        listArticleType: [],
        rules: {
          title: [{ msg: '标题为必填项', validator: validateRequire }],
          keys: [{ msg: '关键词为必填项', validator: validateRequire }],
          author: [{ msg: '作者为必填项', validator: validateRequire }],
          type: [{ msg: '请选择分类', validator: validateRequire }],
          tags: [{ msg: '标签为必填项', validator: validateRequire }],
          description: [{ msg: '描述为必填项', validator: validateRequire }],
          content: [{ required: true, msg: '文章内容为必填项', validator: validateRequire }]
        }
      }
    },
    methods: {
      fetchData(id) {
        getArticleById(id).then(res => {
          if (res.data.code === 0) {
            this.article = res.data.article
          } else {
            this.$message.error(res.data.msg)
          }
        }).catch(err => {
          console.log(err)
        })
      },
      handleGetArticleType() {
        getArticleType().then(res => {
          if (res.data.code === 0) {
            this.listArticleType = res.data.articleType.list
          } else {
            this.$message.error(res.data.msg)
          }
        })
      },
      handlePublish() {
        this.$refs['articleForm'].validate((valid) => {
          if (valid) {
            this.article.imageUrl = encodeURI(this.article.imageUrl)
            this.article.status = 1
            this.listArticleType.forEach((row) => {
              if (row.code === this.article.type) {
                this.article.codeText = row.codeText
              }
            })
            updateArticle(this.article).then((res) => {
              if (res.data.code === 0) {
                this.dialogFormVisible = false
                // 关闭当前标签页
                closeCurrentView(this.$store, this.$router, this.$route.path, '/article/list')
                this.$notify({
                  title: '成功',
                  message: '发布成功',
                  type: 'success',
                  duration: 2000
                })
              } else {
                this.$message.error(res.data.msg)
              }
            })
          }
        })
      },
      isActive(route) {
        return route.path === this.$route.path
      },
      handleDraft() {
        this.$refs['articleForm'].validate((valid) => {
          if (valid) {
            this.article.imageUrl = encodeURI(this.article.imageUrl)
            this.article.status = 2
            updateArticle(this.article).then(() => {
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '保存成功',
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      }
    }
  }
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/mixin.scss";
  .title-prompt{
    position: absolute;
    right: 0px;
    font-size: 12px;
    top:10px;
    color:#ff4949;
  }
  .createPost-container {
    position: relative;
    .createPost-main-container {
      padding: 40px 45px 20px 50px;
      .postInfo-container {
        position: relative;
        @include clearfix;
        margin-bottom: 10px;
        .postInfo-container-item {
          float: left;
        }
      }
      .editor-container {
        min-height: 500px;
        margin: 0 0 0 7px;
        .editor-upload-btn-container {
          text-align: right;
          margin-right: 10px;
          .editor-upload-btn {
            display: inline-block;
          }
        }
      }
    }
    .word-counter {
      width: 40px;
      position: absolute;
      right: -10px;
      top: 0px;
    }
  }
</style>
