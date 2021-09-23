<template>
  <div class="upload-container">
    <el-button icon='el-icon-upload' size="mini" :style="{background:color,borderColor:color}" @click=" dialogVisible=true" type="primary">上传图片
    </el-button>
    <el-dialog append-to-body :visible.sync="dialogVisible">
      <el-upload class="editor-slide-upload" action="http://upload.qiniup.com" :multiple="true" :file-list="fileList" :show-file-list="true"
                 list-type="picture-card" :on-remove="handleRemove" :on-success="handleSuccess" :before-upload="beforeUpload"
                 :data="form">
        <el-button size="small" type="primary">点击上传</el-button>
      </el-upload>
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="handleSubmit">确 定</el-button>
    </el-dialog>
  </div>
</template>


<script>
  // import { getToken } from 'api/qiniu'
  import { getQiniuConfig } from '@/api/qiniu'
  import { validateImgFile } from '@/utils/validate'

  export default {
    name: 'editorSlideUpload',
    props: {
      color: {
        type: String,
        default: '#1890ff'
      }
    },
    data() {
      return {
        form: {},
        domain: '',
        dialogVisible: false,
        listObj: {},
        fileList: []
      }
    },
    mounted() {
      this.getConfig()
    },
    methods: {
      getConfig() {
        getQiniuConfig({ fileType: 'image' }).then((res) => {
          if (res.data.code === 0) {
            this.form.bucketName = res.data.config.bucketName
            this.form.token = res.data.config.token
            this.domain = res.data.config.domain

            console.log(this.form)
          }
        })
      },
      validateImgFile,
      checkAllSuccess() {
        return Object.keys(this.listObj).every(item => this.listObj[item].hasSuccess)
      },
      handleSubmit() {
        const arr = Object.keys(this.listObj).map(v => this.listObj[v])
        if (!this.checkAllSuccess()) {
          this.$message('请等待所有图片上传成功 或 出现了网络问题，请刷新页面重新上传！')
          return
        }
        console.log(arr)
        this.$emit('successCBK', arr)
        this.listObj = {}
        this.fileList = []
        this.dialogVisible = false
      },
      handleSuccess(response, file) {
        const uid = file.uid
        const objKeyArr = Object.keys(this.listObj)
        for (let i = 0, len = objKeyArr.length; i < len; i++) {
          if (this.listObj[objKeyArr[i]].uid === uid) {
            this.listObj[objKeyArr[i]].url = this.domain + '/' + response.key + '?v=' + new Date().getTime()
            this.listObj[objKeyArr[i]].hasSuccess = true
            return
          }
        }
      },
      handleRemove(file) {
        const uid = file.uid
        const objKeyArr = Object.keys(this.listObj)
        for (let i = 0, len = objKeyArr.length; i < len; i++) {
          if (this.listObj[objKeyArr[i]].uid === uid) {
            delete this.listObj[objKeyArr[i]]
            return
          }
        }
      },
      beforeUpload(file) {
        this.form.key = 'article_' + file.name

        getQiniuConfig({ fileType: 'image', fileName: this.form.key }).then((res) => {
          if (res.data.code === 0) {
            this.form.bucketName = res.data.config.bucketName
            this.form.token = res.data.config.token
            this.domain = res.data.config.domain

            if (!this.validateImgFile(file.name)) {
              this.$message.error('请上传正确类型的文件!')
              return false
            } else {
              const _self = this
              const _URL = window.URL || window.webkitURL
              const fileName = file.uid
              this.listObj[fileName] = {}
              return new Promise((resolve, reject) => {
                const img = new Image()
                img.src = _URL.createObjectURL(file)
                img.onload = function() {
                  _self.listObj[fileName] = { hasSuccess: false, uid: file.uid, width: this.width, height: this.height }
                }
                resolve(true)
              })
            }
          }
        })
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .upload-container {
    .editor-slide-upload {
      margin-bottom: 20px;
    }
  }
  .editor-slide-upload {
    .el-upload--picture-card {
      width: 100%;
    }
  }
</style>
