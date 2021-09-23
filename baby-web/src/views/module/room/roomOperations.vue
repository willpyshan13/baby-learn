<template>
  <el-dialog width="50%" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
    <el-form :rules="rules" ref="dataForm" :model="room" label-width="90px" v-loading="dialogLoading"
             style='width:75%;margin-left:20px;' size="mini">
      <el-form-item label="教室名称" prop="roomName">
        <el-input v-model="room.roomName"/>
      </el-form-item>
      <el-form-item label="教室类型" prop="roomType">
        <el-radio-group v-model="room.roomType" @change="handleRoomTypeChange">
          <el-radio :label="1"><span>一对一</span></el-radio>
          <el-radio :label="2"><span>一对四</span></el-radio>
          <el-radio :label="3"><span>一对多</span></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="开课时间" prop="dateTimeRange">
        <el-date-picker
          size="mini"
          v-model="dateTimeRange"
          type="datetimerange"
          value-format="yyyy-MM-dd HH:mm:ss"
          range-separator="至"
          start-placeholder="开始时间"
          end-placeholder="结束时间"
          :picker-options="dateTimeRangePickerOptions">
        </el-date-picker>
      </el-form-item>
      <el-row>
        <el-col :span="8">
          <el-form-item label="需要密码" prop="passwordRequired">
            <el-switch
              v-model="room.passwordRequired"
              active-color="#13ce66"
              @change="handlePasswordRequiredChange"
              :active-value="1"
              :inactive-value="0">
            </el-switch>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="开启音视频" prop="autoOpenAv">
            <el-switch
              v-model="room.autoOpenAv"
              active-color="#13ce66"
              :active-value="1"
              :inactive-value="0">
            </el-switch>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label=".264编码" prop="vcodec">
            <el-switch
              v-model="room.vcodec"
              active-color="#13ce66"
              :active-value="2"
              :inactive-value="0">
            </el-switch>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>

        <el-col :span="12">
          <el-form-item label="上课教师" prop="teacherId">
            <el-select clearable filterable class="filter-item" v-model="room.teacherId" placeholder="请选择">
              <el-option v-for="item in teacherList" :key="item.teacherId" :label="item.teacherName"
                         :value="item.teacherId">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="教师密码" prop="chairmanPwd">
            <el-input v-model="room.chairmanPwd"/>
          </el-form-item>
        </el-col>

      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="助教密码" prop="assistantPwd">
            <el-input v-model="room.assistantPwd"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="巡课密码" prop="patrolPwd">
            <el-input v-model="room.patrolPwd"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col v-show="confuserPwdRequired" :span="12">
          <el-form-item label="学员密码" prop="confuserPwd">
            <el-input v-model="room.confuserPwd"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="视频分辨率" prop="videoType">
            <el-select filterable class="" v-model="room.videoType" placeholder="请选择" style="display:block;">
              <el-option v-for="item in videoType" :key="item.key" :label="item.display_name"
                         :value="item.key">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="视频帧率" prop="videoFrameRate">
            <el-select filterable class="" v-model="room.videoFrameRate" placeholder="请选择" style="display:block;">
              <el-option v-for="item in videoFrameRate" :key="item.key" :label="item.display_name"
                         :value="item.key">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="推送用户" prop="classId">
            <el-col :span="18">
              <el-row>
                <el-radio-group v-model="room.userType" @change="handleRoomUserTypeChange">
                  <el-radio v-for="item in roomUserType" :key="item.key" :label="item.key">{{ item.display_name }}</el-radio>
                </el-radio-group>
              </el-row>
            </el-col>
            <el-col :span="6">
              <el-row>
                <el-select v-if="room.userType === 2" clearable filterable multiple class="" v-model="classIdList"
                           placeholder="请选择" style="display: block" @change="handleClassSelectChange">
                  <el-option v-for="item in classList" :key="item.id" :label="item.className"
                             :value="item.id">
                  </el-option>
                </el-select>
              </el-row>
            </el-col>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="教室图标" prop="logoUrl">
        <single-image-upload :name="room.logoUrl" :image="room.logoUrl" v-model="room.logoUrl"/>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">{{$t('table.cancel')}}</el-button>
      <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">{{$t('table.confirm')}}</el-button>
      <el-button v-else type="primary" @click="updateData">{{$t('table.confirm')}}</el-button>
    </div>
  </el-dialog>
</template>
<script>
  import { addRoom, editRoom, deleteRoom } from '@/api/room'
  import { teacherList } from '@/api/teacher'
  import { classInfoList } from '@/api/classInfo'
  import { roomUserType, roomType, videoType, videoFrameRate } from '@/utils/constant'
  import { validateImage } from '@/utils/validate'
  import SingleImageUpload from '../../../components/Upload/singleImageUpload'

  const roomObject = { // 教室对象
    id: null,
    roomName: '',
    roomType: 3,
    startTime: null,
    endTime: null,
    chairmanPwd: '',
    assistantPwd: '',
    confuserPwd: '',
    patrolPwd: '',
    passwordRequired: 0,
    autoOpenAv: 1,
    videoType: 3,
    confuserUrl: '',
    teacherUrl: '',
    videoFrameRate: 20,
    confuserNum: 0,
    classId: '',
    roomId: null,
    logoUrl: '',
    vcodec: 2,
    userType: 2,
    teacherId: null
  }
  export default {
    components: { SingleImageUpload },
    name: 'roomOperations',
    data() {
      const validateRequire = (rule, value, callback) => {
        if (this.room.passwordRequired === 1 && (!value || value === '')) {
          callback(new Error('学员密码为必填项'))
        } else {
          callback()
        }
      }
      const dateTimeRangeValidator = (rule, value, callback) => {
        if (this.dateTimeRange == null) {
          callback(new Error('开课时间为必填项'))
        } else {
          callback()
        }
      }
      const classIdValidator = (role, value, callback) => {
        if (this.room.userType === 2) {
          if (this.room.classId == null || this.room.classId.trim() === '') {
            callback(new Error('请选择班级'))
          } else {
            callback()
          }
        } else {
          callback()
        }
      }
      return {
        teacherList: [],
        dialogLoading: false,
        classList: [],
        classIdList: [],
        roomUserType: roomUserType,
        videoType: videoType,
        roomType: roomType,
        videoFrameRate: videoFrameRate,
        room: Object.assign({}, roomObject),
        dateTimeRange: null,
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '新增'
        },
        confuserPwdRequired: false,
        rules: {
          chairmanPwd: [{ required: true, message: '教师密码为必填项', trigger: 'change' }],
          dateTimeRange: [{ required: true, trigger: 'change', validator: dateTimeRangeValidator }],
          roomName: [{ required: true, message: '教室名称为必填项', trigger: 'change' }],
          patrolPwd: [{ required: true, message: '巡课密码为必填项', trigger: 'change' }],
          assistantPwd: [{ required: true, message: '助教密码为必填项', trigger: 'change' }],
          confuserPwd: [{ required: true, trigger: 'change', validator: validateRequire }],
          logoUrl: [{ required: false, notNullMessage: '教室图标为必填项', TypeErrorMessage: '请上传正确类型的文件', trigger: 'change', validator: validateImage }],
          classId: [{ required: false, trigger: 'change', validator: classIdValidator }],
          teacherId: [{ required: true, message: '上课教师为必选项', trigger: 'change' }]
        },
        dateTimeRangePickerOptions: {
          shortcuts: [{
            text: '今晚',
            onClick(picker) {
              const date = new Date()
              const start = new Date(date.getFullYear(), date.getMonth(), date.getDate(), 19, 0, 0)
              const end = new Date(date.getFullYear(), date.getMonth(), date.getDate(), 21, 0, 0)
              picker.$emit('pick', [start, end])
            }
          }, {
            text: '明晚',
            onClick(picker) {
              const date = new Date()
              date.setTime(date.getTime() + 24 * 60 * 60 * 1000)
              const start = new Date(date.getFullYear(), date.getMonth(), date.getDate(), 19, 0, 0)
              const end = new Date(date.getFullYear(), date.getMonth(), date.getDate(), 21, 0, 0)
              picker.$emit('pick', [start, end])
            }
          }],
          disabledDate: (time) => {
            return time.getTime() <= new Date()
          }
        }
      }
    },
    methods: {
      getTeacherList() {
        teacherList().then((response) => {
          response.data.teacherList.list.forEach((row) => {
            if (row.role === 0) {
              this.teacherList.push(row)
            }
          })
        })
      },
      handleRoomUserTypeChange(val) {
        if (val === 2) {
          this.classList = []
          this.room.classId = null
          this.getClassInfoList()
        } else {
          this.classList = []
        }
      },
      getClassInfoList() {
        const query = {}
        classInfoList(query).then(res => {
          if (res.data.code === 0) {
            this.classList = res.data.classInfoList.list
          } else {
            this.$message.error(res.data.msg)
          }
        })
      },
      handleRoomTypeChange(val) {
        if (val === 3) {
          this.room.autoOpenAv = 0
        } else {
          this.room.autoOpenAv = 1
        }
      },
      handleCreate() {
        this.resetTemp()
        this.getTeacherList()
        this.getClassInfoList()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      createData() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.dialogLoading = true
            const roomBo = {}
            this.room.startTime = this.dateTimeRange[0]
            this.room.endTime = this.dateTimeRange[1]
            // this.room.classId = this.classIdList.join(',')
            roomBo.room = this.room
            addRoom(roomBo).then((res) => {
              if (res.data.code !== 0) {
                this.dialogLoading = false
                this.$notify({
                  title: '错误',
                  message: res.data.msg,
                  type: 'error',
                  duration: 2000
                })
              } else {
                this.dialogLoading = false
                this.$emit('refresh-list')
                this.dialogFormVisible = false
                this.$notify({
                  title: '成功',
                  message: res.data.msg,
                  type: 'success',
                  duration: 2000
                })
              }
            })
          }
        })
      },
      handleUpdate(row) {
        this.resetTemp()
        this.room = Object.assign({}, row) // copy obj
        if (this.room.classId !== '' && this.room.classId != null) {
          this.room.classId.split(',').forEach((n) => {
            this.classIdList.push(Number(n))
          })
        }
        this.getTeacherList()
        this.getClassInfoList()
        this.dateTimeRange = [row.startTime, row.endTime]
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      updateData() {
        // this.room.classId = this.classIdList.join(',')
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.dialogLoading = true
            this.room.startTime = this.dateTimeRange[0]
            this.room.endTime = this.dateTimeRange[1]
            const tempData = Object.assign({}, this.room) // 对象拷贝
            const roomBo = {}
            roomBo.room = tempData
            editRoom(roomBo).then((res) => {
              if (res.data.code === 0) {
                this.dialogLoading = false
                this.$emit('refresh-list')
                this.dialogFormVisible = false
                this.$notify({
                  title: '成功',
                  message: res.data.msg,
                  type: 'success',
                  duration: 2000
                })
              } else {
                this.dialogLoading = false
                this.$message.error(res.data.msg)
              }
            })
          }
        })
      },
      handleDelete(row) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteRoom(row.id).then((res) => {
            if (res.data.code === 0) {
              this.$emit('refresh-list')
              this.dialogFormVisible = false
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
        }).catch(() => {

        })
      },
      handleClassSelectChange(val) {
        this.room.classId = val.join(',')
      },
      resetTemp() {
        this.dateTimeRange = []
        this.teacherList = []
        this.classIdList = []
        this.room = {
          id: null,
          roomName: '',
          roomType: 2,
          startTime: null,
          endTime: null,
          chairmanPwd: '',
          assistantPwd: '',
          confuserPwd: '',
          patrolPwd: '',
          passwordRequired: 0,
          autoOpenAv: 1,
          videoType: 3,
          confuserUrl: '',
          teacherUrl: '',
          videoFrameRate: 20,
          confuserNum: 0,
          classId: '',
          logoUrl: '',
          vcodec: 2,
          userType: 2,
          teacherId: null
        }
      },
      handlePasswordRequiredChange(val) {
        this.confuserPwdRequired = val === 1
      }
    }
  }
</script>
