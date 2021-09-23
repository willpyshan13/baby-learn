<template>
  <div>
    <el-radio-group v-model="uploadType">
      <el-radio label="file">选择文件</el-radio>
      <el-radio label="text">输入地址</el-radio>
    </el-radio-group>
    <el-input v-if="uploadType === 'text'" v-on:change="handleCommitInput" v-model="imageValue"/>
    <el-upload v-else-if="uploadType === 'file'"
               class="upload-demo"
               ref="imgUpload"
               :action="'http://upload.qiniup.com'"
               :on-preview="handlePreview"
               :on-remove="handleImgRemove"
               :on-success="handleImgSuccess"
               :on-exceed="handleExceed"
               :before-upload="handleImgBeforeUpload"
               :data="form"
               :limit="1"
               :file-list="getImage"
               list-type="picture"
               :auto-upload="false">
      <el-button slot="trigger" :size="size" type="primary">选取文件</el-button>
      <el-button style="margin-left: 10px;" :size="size" type="success" @click="imgSubmitUpload">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">文件的类型如下(*.jpg;*.gif;*.jpeg;*.png;*.bmp)</div>
    </el-upload>
  </div>


</template>
<script>
  import { validateImgFile } from '@/utils/validate'
  import { getQiniuConfig } from '@/api/qiniu'
  export default {
    name: 'singleImageUpload',
    props: {
      value: String,
      image: String,
      name: String,
      size: {
        type: String,
        default: 'small'
      }
    },
    computed: {
      getImage() {
        this.imageFileList = []
        if (typeof (this.image) !== 'undefined' && this.image !== '') {
          this.imageFileList.push({
            name: this.name,
            url: decodeURI(this.image)
          })
          this.imageValue = decodeURI(this.image)
        } else {
          this.imageValue = ''
        }
        return this.imageFileList
      }
    },
    watch: {
      // 新图片重置选择方式
      getImage() {
        this.uploadType = 'file'
      }
    },
    data() {
      return {
        imageValue: '',
        uploadType: 'file',
        form: {},
        domain: null,
        uploadImgStatus: false, // 图片文件上传状态
        tempUrl: '',
        dataObject: {},
        imageFileList: []
      }
    },
    methods: {
      validateImgFile,
      handleCommitInput(val) {
        if (val !== '') {
          this.handleImgRemove()
        }
        this.emitInput(val)
      },
      imgSubmitUpload() {
        this.$refs.imgUpload.submit()
      },
      emitInput(val) {
        this.$emit('input', val)
      },
      handlePreview(file) {
        window.open(file.url)
      },
      handleImgSuccess(res) {
        if (res.hash !== null) {
          this.emitInput(this.domain + '/' + res.key)
          this.uploadImgStatus = true
        } else {
          this.uploadImgStatus = false
        }
      },
      handleImgRemove(file) {
        this.emitInput('')
        if (this.imageFileList && this.imageFileList.uid !== null) {
          this.uploadImgStatus = true
        }
      },
      handleImgBeforeUpload(file) {
        this.form.key = file.name
        if (!this.validateImgFile(file.name)) {
          this.$message.error('请上传正确类型的文件!')
          return false
        } else {
          return getQiniuConfig({ fileType: file.type }).then((res) => {
            if (res.data.code === 0) {
              this.form.bucketName = res.data.config.bucketName
              this.form.token = res.data.config.token
              this.domain = res.data.config.domain
            }
          })
        }
      },
      handleExceed(files, selectFileList) {
        this.$message.warning('当前限制选择 1 个文件，本次选择了 ' + files.length + ' 个文件，共选择了 ' + (files.length + selectFileList.length) + ' 个文件')
      }
    }
  }

</script>
