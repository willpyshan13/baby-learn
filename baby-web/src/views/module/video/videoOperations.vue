<template>
  <el-dialog width="" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
    <el-form :rules="rules" ref="videoForm" :model="video" label-width="90px"
             style='width:90%;margin-left:20px;' size="small">
      <el-row>
        <el-col :span="24">
          <el-form-item label="名称" prop="videoUrlDesc">
            <el-input v-model="video.videoUrlDesc"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="状态" prop="status">
            <el-select clearable class="filter-item" v-model="video.status" placeholder="请选择" value="" style="display: block;">
              <el-option v-for="item in videoStatus" :key="item.key" :label="item.display_name"
                         :value="item.key">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="序号" prop="videoUrlIndex">
            <el-input-number v-model="video.videoUrlIndex" controls-position="right" :min="1" :max="10000"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="缩略图" prop="videoPlayUrl">
        <single-image-upload :name="video.videoPlayUrl" :image="video.videoPlayUrl" v-model="video.videoPlayUrl"/>
      </el-form-item>
      <el-form-item label="视频" prop="videoWebUrl">
        <single-video-upload :name="video.videoWebUrl" :video="video.videoWebUrl" v-model="video.videoWebUrl"/>
      </el-form-item>
      <!-- 操作按钮 -->
      <el-form-item>
        <el-button v-if="dialogStatus==='create'" type="primary" @click="createData">{{$t('table.confirm')}}</el-button>
        <el-button @click="dialogFormVisible = false">{{$t('table.cancel')}}</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
  import { addVideo, deleteVideoBatch } from '@/api/video'
  import { videoStatus } from '@/utils/constant'
  import SingleVideoUpload from '../../../components/Upload/singleVideoUpload'
  import SingleImageUpload from '../../../components/Upload/singleImageUpload'
  import { validateVideo, validateImage } from '@/utils/validate'

  const videoObject = {
    videoUrlId: null,
    status: 0,
    videoFormat: 0,
    videoId: null,
    videoPlayUrl: '',
    videoUrlDesc: '',
    videoUrlIndex: 0,
    videoWebUrl: ''
  }
  export default {
    components: {
      SingleImageUpload,
      SingleVideoUpload },
    name: 'videoOperations',
    props: {
      videoSelection: {
        type: Array,
        default: null
      },
      cartoonId: {
        type: Number,
        default: null
      }
    },
    computed: {
      getCartoonId() {
        return this.cartoonId
      }
    },
    data() {
      return {
        videoStatus: videoStatus,
        video: Object.assign({}, videoObject),
        textMap: {
          create: '新增',
          update: '编辑'
        },
        dialogStatus: '',
        dialogFormVisible: false,
        rules: {
          videoUrlDesc: [{ required: true, message: '名称为必填项', trigger: 'change' }],
          videoPlayUrl: [{ required: true, notNullMessage: '缩略图为必填项', TypeErrorMessage: '请上传正确类型的文件', trigger: 'change', validator: validateImage }],
          videoWebUrl: [{ required: true, notNullMessage: '视频为必填项', TypeErrorMessage: '请上传正确类型的文件', trigger: 'change', validator: validateVideo }]
        }
      }
    },
    methods: {
      resetForm() {
        this.video = {
          videoUrlId: null,
          status: 0,
          videoFormat: 0,
          videoId: null,
          videoPlayUrl: '',
          videoUrlDesc: '',
          videoUrlIndex: 0,
          videoWebUrl: ''
        }
      },
      handleCreate() {
        this.resetForm()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['videoForm'].clearValidate()
        })
      },
      createData() {
        this.$refs.videoForm.validate(valid => {
          if (valid) {
            this.video.videoId = this.getCartoonId
            this.video.videoWebUrl = encodeURI(this.video.videoWebUrl)
            this.video.videoPlayUrl = encodeURI(this.video.videoPlayUrl)
            addVideo(this.video).then((res) => {
              if (res.data.code === 0) {
                this.$emit('refresh-list')
                this.dialogFormVisible = false
                this.$notify({
                  title: '成功',
                  message: '新增成功',
                  type: 'success',
                  duration: 2000
                })
              } else {
                this.$message.error(res.data.msg)
              }
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      handleDelete() {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if (this.videoSelection.length > 0) {
            const video = {}
            const videos = []
            this.videoSelection.forEach(row => {
              video.id = row.videoUrlId
              video.name = row.videoUrlDesc
              video.url = row.videoWebUrl
              video.videoId = row.videoId
              videos.push(video)
            })
            deleteVideoBatch(videos).then((res) => {
              if (res.data.code === 0) {
                this.$emit('refresh-list')
                if (res.data.errorList) {
                  this.$notify({
                    title: '警告',
                    message: '删除成功, 同步删除七牛云失败',
                    duration: 4000,
                    type: 'warning'
                  })
                } else {
                  this.$notify({
                    title: '成功',
                    type: 'success',
                    message: '删除成功',
                    duration: 2000
                  })
                }
              } else {
                this.$message.error(res.data.msg)
              }
            })
          } else {
            this.$message.error('请选择列表项!')
          }
        }).catch(() => {

        })
      }
    }
  }

</script>
