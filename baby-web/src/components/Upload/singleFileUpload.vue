<template>
  <div>
    <el-radio-group v-model="uploadType">
      <el-radio label="file">选择文件</el-radio>
      <el-radio label="text">输入地址</el-radio>
    </el-radio-group>
    <el-input v-if="uploadType === 'text'" v-on:change="handleCommitInput" v-model="fileValue"/>
    <el-upload v-else-if="uploadType === 'file'"
               class="upload-demo"
               ref="fileUpload"
               :action="'http://upload.qiniup.com'"
               :on-preview="handlePreview"
               :on-remove="handleFileRemove"
               :on-success="handleFileSuccess"
               :on-exceed="handleExceed"
               :before-upload="handleFileBeforeUpload"
               :data="form"
               :limit="1"
               :file-list="getFile"
               list-type="picture"
               :auto-upload="false">
      <el-button slot="trigger" :size="size" type="primary">选取文件</el-button>
      <el-button style="margin-left: 10px;" :size="size" type="success" @click="fileSubmitUpload">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">文件的类型如下( xls | xlsx | ppt | pptx | doc | docx | txt | pdf | zip )</div>
      <div v-if="tipStr !== ''" slot="tip" class="el-upload__tip" style="color: #ff0000;">{{ tipStr }}</div>
    </el-upload>
  </div>


</template>
<script>
  import { validateFile } from '@/utils/validate'
  import { getQiniuConfig } from '@/api/qiniu'
  export default {
    name: 'singleFileUpload',
    props: {
      value: String,
      file: String,
      name: String,
      tipStr: String,
      size: {
        type: String,
        default: 'small'
      }
    },
    computed: {
      getFile() {
        this.fileList = []
        if (this.file !== 'undefined' && typeof (this.file) !== 'undefined' && this.file !== '') {
          this.fileList.push({
            name: this.name,
            url: decodeURI(this.file)
          })
          this.fileValue = decodeURI(this.file)
        } else {
          this.fileValue = ''
        }
        return this.fileList
      }
    },
    watch: {
      // 新图片重置选择方式
      getFile() {
        this.uploadType = 'file'
      }
    },
    data() {
      return {
        fileValue: '',
        uploadType: 'file',
        form: {},
        domain: null,
        uploadFileStatus: false, // 图片文件上传状态
        tempUrl: '',
        dataObject: {},
        fileList: []
      }
    },
    methods: {
      validateFile,
      handleCommitInput(val) {
        if (val !== '') {
          this.handleFileRemove()
        }
        this.emitInput(val)
      },
      fileSubmitUpload() {
        this.$refs.fileUpload.submit()
      },
      emitInput(val) {
        this.$emit('input', val)
      },
      handlePreview(file) {
        window.open(file.url)
      },
      handleFileSuccess(res) {
        if (res.hash !== null) {
          this.emitInput(this.domain + '/' + res.key)
          this.uploadFileStatus = true
        } else {
          this.uploadFileStatus = false
        }
      },
      handleFileRemove(file) {
        this.emitInput('')
        if (this.fileList && this.fileList.uid !== null) {
          this.uploadFileStatus = true
        }
      },
      handleFileBeforeUpload(file) {
        if (this.name !== '') {
          this.form.key = this.name
        } else {
          this.form.key = file.name
        }
        if (!this.validateFile(file.name)) {
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
