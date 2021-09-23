<template>
  <div>
    <el-radio-group v-model="uploadType">
      <el-radio label="file">选择文件</el-radio>
      <el-radio label="text">输入地址</el-radio>
    </el-radio-group>
    <el-input v-if="uploadType === 'text'" v-on:change="handleCommitInput" v-model="audioValue"/>
    <el-upload  v-else-if="uploadType === 'file'"
                class="upload-demo"
                ref="audioUpload"
                :action="'http://upload.qiniup.com'"
                :on-preview="handlePreview"
                :on-remove="handleAudioRemove"
                :on-success="handleAudioSuccess"
                :on-exceed="handleExceed"
                :before-upload="handleAudioBeforeUpload"
                :data="form"
                :limit="1"
                :file-list="getAudio"
                :auto-upload="false">
      <el-button slot="trigger" :size="size" type="primary">选择文件</el-button>
      <el-button style="margin-left: 10px;" :size="size" type="success" @click="audioSubmitUpload">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">您可以上传资料文件大小限制在100M以内,文件的类型如下(*.mp3;*.m4a;*.wav)</div>
    </el-upload>
  </div>
</template>
<script>
  import { validateAudioFile } from '@/utils/validate'
  import { getQiniuConfig } from '@/api/qiniu'

  export default {
    name: 'SingleAudioUpload',
    props: {
      value: String,
      audio: String,
      name: String,
      size: {
        type: String,
        default: 'small'
      }
    },
    computed: {
      getAudio() {
        this.audioFileList = []
        if (typeof (this.audio) !== 'undefined' && this.audio !== '') {
          this.audioFileList.push({
            name: decodeURI(this.audio),
            url: decodeURI(this.audio)
          })
          this.audioValue = decodeURI(this.audio)
        } else {
          this.audioValue = ''
        }
        return this.audioFileList
      }
    },
    watch: {
      // 新图片重置选择方式
      getAudio() {
        this.uploadType = 'file'
      }
    },
    data() {
      return {
        audioValue: '',
        uploadType: 'file',
        form: {},
        domain: null,
        uploadAudioStatus: false, // 图片文件上传状态
        tempUrl: '',
        dataObject: {},
        audioFileList: []
      }
    },
    methods: {
      validateAudioFile,
      handleCommitInput(val) {
        if (val !== '') {
          this.handleAudioRemove()
        }
        this.emitInput(val)
      },
      audioSubmitUpload() {
        this.$refs.audioUpload.submit()
      },
      emitInput(val) {
        this.$emit('input', encodeURI(val))
      },
      handlePreview(file) {
        window.open(file.url)
      },
      handleAudioSuccess(res) {
        if (res.hash !== null) {
          this.emitInput(this.domain + '/' + res.key)
          this.uploadAudioStatus = true
        } else {
          this.uploadAudioStatus = false
        }
      },
      handleAudioRemove(file) {
        this.emitInput('')
      },
      handleAudioBeforeUpload(file) {
        this.form.key = file.name
        if (!this.validateAudioFile(file.name)) {
          this.$message.error('请上传正确的音频文件!')
          return false
        } else {
          return getQiniuConfig({ fileType: file.type }).then((res) => {
            console.log(res)
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
