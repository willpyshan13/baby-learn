<template>
  <div>
    <el-radio-group v-model="uploadType">
      <el-radio label="file">选择文件</el-radio>
      <el-radio label="text">输入地址</el-radio>
    </el-radio-group>
    <el-input v-if="uploadType === 'text'" v-on:change="handleCommitInput" v-model="videoValue"/>
    <el-upload  v-else-if="uploadType === 'file'"
      class="upload-demo"
      ref="videoUpload"
      :action="'http://upload.qiniup.com'"
      :on-preview="handlePreview"
      :on-remove="handleVideoRemove"
      :on-success="handleVideoSuccess"
      :on-exceed="handleExceed"
      :before-upload="handleVideoBeforeUpload"
      :data="form"
      :limit="1"
      :file-list="getVideo"
      :auto-upload="false">
      <el-button slot="trigger" :size="size" type="primary">选择文件</el-button>
      <el-button style="margin-left: 10px;" :size="size" type="success" @click="videoSubmitUpload">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">您可以上传资料文件大小限制在100M以内,文件的类型如下(*.mp3;*.m4a;*.mp4)</div>
    </el-upload>
  </div>
</template>
<script>
  import { validateVideoFile } from '@/utils/validate'
  import { getQiniuConfig } from '@/api/qiniu'

  export default {
    name: 'singleVideoUpload',
    props: {
      value: String,
      video: String,
      name: String,
      size: {
        type: String,
        default: 'small'
      }
    },
    computed: {
      getVideo() {
        this.videoFileList = []
        if (typeof (this.video) !== 'undefined' && this.video !== '') {
          this.videoFileList.push({
            name: this.video,
            url: decodeURI(this.video)
          })
          this.videoValue = decodeURI(this.video)
        } else {
          this.videoValue = ''
        }
        return this.videoFileList
      }
    },
    watch: {
      // 新图片重置选择方式
      getVideo() {
        this.uploadType = 'file'
      }
    },
    data() {
      return {
        videoValue: '',
        uploadType: 'file',
        form: {},
        domain: null,
        uploadVideoStatus: false, // 图片文件上传状态
        tempUrl: '',
        dataObject: {},
        videoFileList: []
      }
    },
    methods: {
      validateVideoFile,
      handleCommitInput(val) {
        if (val !== '') {
          this.handleVideoRemove()
        }
        this.emitInput(val)
      },
      videoSubmitUpload() {
        this.$refs.videoUpload.submit()
      },
      emitInput(val) {
        this.$emit('input', val)
      },
      handlePreview(file) {
        window.open(file.url)
      },
      handleVideoSuccess(res) {
        if (res.hash !== null) {
          this.emitInput(this.domain + '/' + res.key)
          this.uploadVideoStatus = true
        } else {
          this.uploadVideoStatus = false
        }
      },
      handleVideoRemove(file) {
        this.emitInput('')
      },
      handleVideoBeforeUpload(file) {
        this.form.key = file.name
        if (!this.validateVideoFile(file.name)) {
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
