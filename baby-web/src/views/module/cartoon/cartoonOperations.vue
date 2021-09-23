<template>
  <el-dialog width="" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
    <el-form :rules="rules" ref="cartoonForm" :model="cartoon" label-width="90px"
             style='width:90%;margin-left:20px;' size="small">
      <el-row>
        <el-col :span="12">
          <el-form-item label="标题" prop="videoTitle">
            <el-input v-model="cartoon.videoTitle"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="状态" prop="status">
            <el-select clearable class="filter-item" v-model="cartoon.status" placeholder="请选择" value="" style="display: block;">
              <el-option v-for="item in videoStatus" :key="item.key" :label="item.display_name"
                         :value="item.key">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="类型" prop="typeId">
            <el-select clearable class="filter-item" v-model="cartoon.typeId" placeholder="请选择" value="" style="display: block;">
              <el-option v-for="item in videoTypeList" :key="item.typeId" :label="item.typeDesc"
                         :value="item.typeId">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="简介" prop="videoBrief">
            <el-input v-model="cartoon.videoBrief"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="预览图" prop="videoPreviewPicUrl">
        <single-image-upload :name="cartoon.videoPreviewPicUrl" :image="cartoon.videoPreviewPicUrl" v-model="cartoon.videoPreviewPicUrl"/>
      </el-form-item>
      <el-form-item label="剧集图" prop="videoWheelPicUrl">
        <single-image-upload :name="cartoon.videoWheelPicUrl" :image="cartoon.videoWheelPicUrl" v-model="cartoon.videoWheelPicUrl"/>
      </el-form-item>
      <!-- 操作按钮 -->
      <el-form-item>
        <el-button v-if="dialogStatus==='create'" type="primary" @click="createData">{{$t('table.confirm')}}
        </el-button>
        <el-button v-else type="primary" @click="updateData">{{$t('table.confirm')}}</el-button>
        <el-button @click="dialogFormVisible = false">{{$t('table.cancel')}}</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>
<script>
  import { addCartoon, deleteCartoonBatch, editCartoon } from '@/api/cartoon'
  import { getVideoTypeList } from '@/api/video'
  import { videoStatus } from '@/utils/constant'
  import SingleImageUpload from '../../../components/Upload/singleImageUpload'
  import SingleVideoUpload from '../../../components/Upload/singleVideoUpload'
  import { validateImage } from '@/utils/validate'

  const cartoonObject = {
    videoId: null,
    status: 0,
    videoActor: '',
    videoBrief: '',
    videoDesc: '',
    videoDirector: '',
    videoPreviewPicUrl: '',
    videoRegion: '',
    videoScriptWriter: '',
    videoTitle: '',
    videoWheelPicUrl: '',
    videoYear: 0,
    typeId: null,
    typeRecordId: null
  }
  export default {
    components: {
      SingleVideoUpload,
      SingleImageUpload },
    name: 'cartoonOperations',
    props: {
      cartoonSelection: {
        type: Array,
        default: []
      }
    },
    data() {
      return {
        videoStatus: videoStatus,
        cartoon: Object.assign({}, cartoonObject),
        textMap: {
          create: '新增',
          update: '编辑'
        },
        videoTypeList: [],
        dialogStatus: '',
        dialogFormVisible: false,
        rules: {
          videoTitle: [{ required: true, message: '标题为必填项', trigger: 'change' }],
          typeId: [{ required: true, message: '类型为必填项', trigger: 'change' }],
          videoPreviewPicUrl: [{ required: true, notNullMessage: '预览图为必填项', TypeErrorMessage: '请上传正确类型的文件', trigger: 'change', validator: validateImage }],
          videoWheelPicUrl: [{ required: true, notNullMessage: '剧集图为必填项', TypeErrorMessage: '请上传正确类型的文件', trigger: 'change', validator: validateImage }]
        }
      }
    },
    methods: {
      resetForm() {
        this.cartoon = {
          videoId: null,
          status: 0,
          videoActor: '',
          videoBrief: '',
          videoDesc: '',
          videoDirector: '',
          videoPreviewPicUrl: '',
          videoRegion: '',
          videoScriptWriter: '',
          videoTitle: '',
          videoWheelPicUrl: '',
          videoYear: 0,
          typeId: null,
          typeRecordId: null
        }
      },
      getVideoTypeList() {
        const params = {
          typeParent: 1
        }
        getVideoTypeList(params).then((res) => {
          this.videoTypeList = res.data.videoType.list
        })
      },
      handleCreate() {
        this.resetForm()
        this.getVideoTypeList()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['cartoonForm'].clearValidate()
        })
      },
      createData() {
        this.$refs.cartoonForm.validate(valid => {
          if (valid) {
            addCartoon(this.cartoon).then((res) => {
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
      handleUpdate(row) {
        this.getVideoTypeList()
        this.cartoon = Object.assign({}, row) // copy obj
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['cartoonForm'].clearValidate()
        })
      },
      updateData() {
        this.$refs['cartoonForm'].validate(valid => {
          if (valid) {
            editCartoon(this.cartoon).then((res) => {
              if (res.data.code === 0) {
                this.$emit('refresh-list')
                this.dialogFormVisible = false
                this.$notify({
                  title: '成功',
                  message: '更新成功',
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
          if (this.cartoonSelection.length > 0) {
            const ids = []
            this.cartoonSelection.forEach(row => {
              ids.push(row.videoId)
            })
            deleteCartoonBatch(ids).then((res) => {
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
