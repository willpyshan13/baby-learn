<template>
  <div>
    <el-dialog width="40%" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :before-close="handleDialogClose">
      <el-form :rules="rules" ref="coursewareDataForm" :model="courseware" label-width="90px"
               style='width:75%;margin-left:20px;' size="mini">

        <el-form-item label="课件名称" prop="name">
          <el-input v-model="courseware.name" :disabled="true"/>
        </el-form-item>

        <el-form-item label="课件类型" prop="type">
          <el-select clearable filterable class="filter-item" v-model="courseware.type" placeholder="请选择">
            <el-option v-for="item in coursewareType" :key="item.key" :label="item.display_name"
                       :value="item.key">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="课件属性" prop="dynamicPpt">
          <el-select clearable filterable class="filter-item" v-model="courseware.dynamicPpt" placeholder="请选择" :disabled="dynamicDisabled">
            <el-option v-for="item in dynamicPPT" :key="item.key" :label="item.display_name"
                       :value="item.key">
            </el-option>
          </el-select>
          <div class="el-upload__tip" style="color: #ff0000;">请在上传文件前确认课件属性</div>
        </el-form-item>

        <el-form-item label="公开文档" prop="isOpen">
          <el-switch
            v-model="courseware.isOpen"
            active-color="#13ce66"
            inactive-color="#ff4949"
            :active-value="1"
            :inactive-value="0">
          </el-switch>
        </el-form-item>
        <!--:action="'http://www.smart-dog.cn/smart-manager/courseware/upload'"-->
        <el-form-item label="课件选择" prop="resourceUrl">
          <el-upload
            class="upload-demo"
            ref="upload"
            :action="'http://www.smart-dog.cn/smart-manager/courseware/upload'"
            :on-remove="handleRemove"
            :on-change="handleFileChange"
            :on-success="handelSuccess"
            :on-exceed="handleExceed"
            :before-upload="handleBeforeUpload"
            :data="uploadData"
            :limit="1"
            :file-list="fileList"
            :auto-upload="false">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传文件</el-button>
            <div slot="tip" class="el-upload__tip">您可以上传资料文件大小限制在100M以内,文件的类型如下(*.xls;*.xlsx;*.ppt;*.pptx;*.doc;*.docx;*.txt;*.pdf;*.jpg;*.gif;*.jpeg;*.png;*.bmp;*.mp3;*.mp4)</div>
          </el-upload>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">{{$t('table.cancel')}}</el-button>
        <el-button v-if="dialogStatus==='create'" type="primary" @click="createData">{{$t('table.confirm')}}</el-button>
        <el-button v-else type="primary" @click="updateData">{{$t('table.confirm')}}</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import { openWindow } from '@/utils/openWindow'
  import { validateFileSuffix } from '@/utils/validate'
  import { addCourseware, editCourseware, deleteCourseware, deleteFile } from '@/api/courseware'
  import { dynamicPPT, coursewareType } from '@/utils/constant'
  import { roomCoursewareList } from '@/api/room'

  const coursewareObject = {
    id: null,
    name: '',
    type: 1,
    uploadType: 0,
    resourceUrl: '',
    dynamicPpt: 1,
    creator: '',
    status: 1,
    isDefault: 0,
    isOpen: 1
  }
  export default {
    name: 'coursewareOperations',
    props: {
      roomId: {
        type: Number,
        default: null
      }
    },
    computed: {
      getRoomId() {
        return this.roomId
      }
    },
    data() {
      return {
        dynamicDisabled: false,
        coursewareType: coursewareType,
        dynamicPPT: dynamicPPT,
        courseware: Object.assign({}, coursewareObject),
        uploadData: {
          dynamicPpt: 1,
          isOpen: 0
        },
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '新增'
        },
        rules: {
          name: [{ required: true, message: '请上传文件, 文件名称为课件名称', trigger: 'change' }]
        },
        fileList: [],
        existsFile: false,
        tempUrl: null // 上传文件服务器临时位置
      }
    },
    methods: {
      openWindow,
      validateFileSuffix,
      handleFileChange(file) {
        if (file != null) {
          this.existsFile = true
        } else {
          this.existsFile = false
        }
      },
      handleDialogClose(done) {
        if (this.existsFile) {
          this.$confirm('课件未上传完成, 是否要继续退出？')
            .then(() => {
              done()
            })
            .catch(_ => {})
        } else {
          done()
        }
      },
      getDynamicPPT() {
        return this.courseware.dynamicPpt
      },
      getIsOpen() {
        return this.courseware.isOpen
      },
      submitUpload() {
        this.$refs.upload.submit()
      },
      handleRemove(file, fileList) {
        this.existsFile = false
        if (file.response) {
          const params = {
            fileId: this.courseware.id,
            filePath: this.tempUrl
          }
          deleteFile(params).then((response) => {
            if (response.data.code === 0) {
              this.courseware.name = ''
              this.dynamicDisabled = false
            }
          })
        }
      },
      handelSuccess(result) {
        if (result.code === 0) {
          this.courseware.name = result.courseware.name
          this.courseware.resourceUrl = result.courseware.resourceUrl
          this.courseware.id = result.courseware.id
          this.courseware.size = result.courseware.size
          this.tempUrl = result.tempUrl

          this.dynamicDisabled = true
        } else {
          this.dynamicDisabled = false
          this.$message.error(result.msg)
        }
      },
      handleExceed(files, selectFileList) {
        this.$message.warning('当前限制选择 1 个文件，本次选择了 ' + files.length + ' 个文件，共选择了 ' + (files.length + selectFileList.length) + ' 个文件')
      },
      handleBeforeUpload(file) {
        this.uploadData.dynamicPpt = this.getDynamicPPT()
        this.uploadData.isOpen = this.getIsOpen()
        if (!this.validateFileSuffix(file.name)) {
          this.$message.error('请上传正确类型的文件!')
          return false
        } else {
          return true
        }
      },
      resetTemp() {
        this.courseware = {
          id: null,
          name: '',
          type: 1,
          uploadType: 0,
          resourceUrl: '',
          dynamicPpt: 1,
          creator: '',
          status: 1,
          isDefault: 0,
          isOpen: 1
        }
        this.fileList = []
      },
      handleCreate() {
        this.resetTemp()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['coursewareDataForm'].clearValidate()
        })
      },
      createData() {
        this.$refs['coursewareDataForm'].validate((valid) => {
          if (valid) {
            const params = {}
            params.roomId = this.getRoomId
            params.courseware = this.courseware
            addCourseware(params).then((res) => {
              if (res.data.code === 0) {
                this.$emit('refresh-list')
                this.dialogFormVisible = false
                this.$notify({
                  title: '成功',
                  message: '创建成功',
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
      handleUpdate(row) {
        this.courseware = Object.assign({}, row) // copy obj
        this.fileList = []
        this.fileList.push({
          name: this.courseware.name,
          url: this.courseware.resourceUrl
        })
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['coursewareDataForm'].clearValidate()
        })
      },
      updateData() {
        this.$refs['coursewareDataForm'].validate((valid) => {
          if (valid) {
            const tempData = Object.assign({}, this.courseware) // 对象拷贝
            editCourseware(tempData).then((res) => {
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
          }
        })
      },
      handleDelete(row) {
        roomCoursewareList({ coursewareId: row.id }).then(response => {
          const list = response.data.coursewareList.list
          if (list.length > 0) {
            const roomName = []
            list.forEach(item => {
              roomName.push(item.roomName)
            })
            this.$confirm('教室 ' + roomName.join('，') + ' 关联当前课件, 是否仍要删除？').then(() => {
              deleteCourseware(row.id).then((res) => {
                if (res.data.code === 0) {
                  this.$emit('refresh-list')
                  this.$notify({
                    title: '成功',
                    message: '删除成功',
                    type: 'success',
                    duration: 2000
                  })
                } else {
                  this.$message.error(res.data.msg)
                }
              })
            }).catch(_ => {})
          } else {
            this.$confirm('确定执行删除操作？').then(() => {
              deleteCourseware(row.id).then((res) => {
                if (res.data.code === 0) {
                  this.$emit('refresh-list')
                  this.$notify({
                    title: '成功',
                    message: '删除成功',
                    type: 'success',
                    duration: 2000
                  })
                } else {
                  this.$message.error(res.data.msg)
                }
              })
            }).catch(_ => {})
          }
        })
      }
    }
  }
</script>
