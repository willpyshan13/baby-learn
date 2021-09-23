<template>
  <el-dialog width="" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" top="2vh">
    <el-form :rules="rules" ref="dataForm" :model="course" label-width="80px"
             style='margin-left:20px;' size="mini">
      <el-row :gutter="20">
        <el-col :span="11">
          <el-form-item label="课程名称" prop="courseName">
            <el-input size="mini" v-model="course.courseName"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="课程阶段" prop="stageId">
            <el-select :disabled="dialogStatus==='update'" clearable filterable class="" v-model="course.stageId"
                       placeholder="请选择" @change="handleStageSelect" style="display:block;">
              <el-option v-for="item in courseStageList" :key="item.id" :label="item.stageName"
                         :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20" type="flex">
        <el-col :span="11">
          <el-form-item label="课程单词" prop="word">
            <el-input v-model="course.word"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="课程序号" prop="serialNumber">
            <el-input type="number" v-model="course.serialNumber"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20" type="flex">
        <el-col :span="11">
          <el-form-item label="所属单元" prop="groupId">
            <el-select clearable filterable class="" v-model="course.groupId"  @change="handleStageGroupSelect" placeholder="请选择" style="display:block;">
              <el-option v-for="item in stageGroupList" :key="item.id" :label="item.name"
                         :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="所属周" prop="weeks">
            <el-select clearable filterable class="" v-model="course.weeks" placeholder="请选择" style="display:block;">
              <el-option v-for="item in courseWeekList"  :key="item.id" :label="item.name"
                         :value="item.id">
              </el-option>
            </el-select>
            <span class="el-upload__tip" style="color: red;">五节课程为一周, 请选择当前课程所属周</span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="11">
          <el-form-item label="观看权限">
            <el-radio-group v-model="course.viewingAuthority">
              <el-radio :label="0"><span>购买可看</span></el-radio>
              <el-radio :label="1"><span>登录可看</span></el-radio>
              <el-radio :label="2"><span>游客可看</span></el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item label="是否生效" prop="status">
            <el-switch
              v-model="course.status"
              active-color="#13ce66"
              :active-value="1"
              :inactive-value="0">
            </el-switch>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="课程类型">
            <el-radio-group v-model="course.type">
              <el-radio :label="0"><span>录屏</span></el-radio>
              <el-radio :label="1"><span>直播</span></el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>


      <el-row>
        <el-col :span="11">
          <el-form-item label="课程视频" prop="videoUrl">
            <single-video-upload :name="decodeURI(course.videoUrl)" :video="decodeURI(course.videoUrl)" v-model="course.videoUrl" size="mini"/>
            <!--<el-input v-model="course.videoUrl"/>-->
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="封边图片" prop="imageUrl">
            <single-image-upload :name="decodeURI(course.imageUrl)" :image="decodeURI(course.imageUrl)" v-model="course.imageUrl" size="mini"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="11">
          <el-form-item label="印章图片" prop="stampImageUrl">
            <single-image-upload :name="decodeURI(course.stampImageUrl)" :image="decodeURI(course.stampImageUrl)" v-model="course.stampImageUrl" size="mini"/>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="卡片图片" prop="cardImageUrl">
            <single-image-upload :name="decodeURI(course.cardImageUrl)" :image="decodeURI(course.cardImageUrl)" v-model="course.cardImageUrl" size="mini"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="18">
          <el-form-item label="资源文件" prop="resource">
            <single-file-upload :name="decodeURI(course.resourceUrl)" :file="decodeURI(course.resourceUrl)" v-model="course.resourceUrl" :tipStr="'命名规则: 课阶_课程_时间(年月日时).zip (文件名不能有空格, 相同资源地址相同) eg: ' + resource" size="mini"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="18">
          <el-form-item label="描述" prop="description">
            <el-input type="textarea" v-model="course.description"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item>
        <el-button v-if="dialogStatus==='create'" type="primary" @click="createData">{{$t('table.confirm')}}</el-button>
        <el-button v-else type="primary" @click="updateData">{{$t('table.update')}}</el-button>
        <el-button @click="dialogFormVisible = false">{{$t('table.cancel')}}</el-button>
      </el-form-item>

    </el-form>
  </el-dialog>
</template>
<script>
  import { addCourse, editCourse, deleteCourse, courseStageList, getCourseStageGroupList, getCourseStageGroupById,
    getListByUnitId } from '@/api/course'
  import SingleImageUpload from '../../../components/Upload/singleImageUpload'
  import SingleVideoUpload from '../../../components/Upload/singleVideoUpload'
  import { validateVideo, validateImage } from '@/utils/validate'
  import SingleFileUpload from '../../../components/Upload/singleFileUpload'
  import { parseTime } from '@/utils/index'

  const courseObject = {
    id: null,
    courseName: '',
    description: '',
    stageId: null,
    teacherId: null,
    status: 1,
    type: 0,
    startTime: new Date(),
    endTime: new Date(),
    duration: 0,
    serialNumber: 0,
    viewingAuthority: 0,
    category: '',
    videoUrl: '',
    imageUrl: '',
    ttsText: '',
    word: '',
    resourceUrl: '',
    weeks: null,
    groupId: null,
    stampImageUrl: '',
    cardImageUrl: ''
  }

  export default {
    components: {
      SingleFileUpload,
      SingleVideoUpload,
      SingleImageUpload },
    name: 'courseOperations',
    data() {
      return {
        courseWeekList: [],
        courseStageList: [],
        stageGroupWeek: 0,
        stageGroupList: [],
        course: Object.assign({}, courseObject),
        dialogFormVisible: false,
        dialogStatus: '',
        resourceName: '',
        textMap: {
          update: '编辑',
          create: '新增'
        },
        rules: {
          courseName: [{ required: true, message: '课程名称为必填项', trigger: 'change' }],
          stageId: [{ required: true, message: '课程阶段为必选项', trigger: 'change' }],
          word: [{ required: true, message: '课程单词为必选项', trigger: 'change' }],
          groupId: [{ required: true, message: '课程所属单元为必填项', trigger: 'change' }],
          weeks: [{ required: true, message: '课程所属周为必填项', trigger: 'change' }],
          ttsText: [{ required: true, message: '课程TTS为必选项', trigger: 'change' }],
          serialNumber: [{ required: true, message: '课程序号为必选项', trigger: 'change' }],
          videoUrl: [{ required: true, notNullMessage: '视频为必填项', TypeErrorMessage: '请上传正确类型的文件', trigger: 'change', validator: validateVideo }],
          imageUrl: [{ required: true, notNullMessage: '图片为必填项', TypeErrorMessage: '请上传正确类型的文件', trigger: 'change', validator: validateImage }],
          stampImageUrl: [{ required: true, notNullMessage: '印章图片为必填项', TypeErrorMessage: '请上传正确类型的文件', trigger: 'change', validator: validateImage }],
          cardImageUrl: [{ required: true, notNullMessage: '卡片图片为必填项', TypeErrorMessage: '请上传正确类型的文件', trigger: 'change', validator: validateImage }]
        }
      }
    },
    created() {
      this.resource = 'L1_E1_' + parseTime(new Date(), '{y}{m}{d}{h}') + '.zip'
    },
    methods: {
      parseTime,
      getCourseUnitWeekListByUnitId(unitId) {
        getListByUnitId(unitId).then((res) => {
          if (res.data.success) {
            this.courseWeekList = res.data.body.list
          } else {
            this.$message.error(res.data.message)
          }
        })
      },
      handleStageSelect(stageId) {
        this.course.groupId = null
        this.course.weeks = null
        this.fetchStageGroupList(stageId)
      },
      handleStageGroupSelect(groupId) {
        this.getCourseUnitWeekListByUnitId(groupId)
      },
      getCourseStageList() {
        courseStageList().then((res) => {
          if (res.data.code === 0) {
            this.courseStageList = res.data.courseStageList.list
          } else {
            this.$message.error(res.data.msg)
          }
        })
      },
      fetchStageGroupList(stageId) {
        getCourseStageGroupList(stageId).then((res) => {
          if (res.data.code === 0) {
            this.stageGroupList = res.data.groupList
          } else {
            this.$message.error(res.data.msg)
          }
        })
      },
      fetchStageGroup(groupId) {
        getCourseStageGroupById(groupId).then((res) => {
          if (res.data.code === 0) {
            this.stageGroupWeek = res.data.stageGroup.week
          } else {
            this.$message.error(res.data.msg)
          }
        })
      },
      resetTemp() {
        this.course = {
          id: null,
          courseName: '',
          description: '',
          stageId: null,
          teacherId: null,
          status: 1,
          type: 0,
          startTime: new Date(),
          endTime: new Date(),
          duration: 0,
          serialNumber: 0,
          viewingAuthority: 0,
          category: '',
          videoUrl: '',
          imageUrl: '',
          ttsText: '',
          word: '',
          resourceUrl: '',
          weeks: null,
          groupId: null,
          stampImageUrl: '',
          cardImageUrl: ''
        }

        this.stageGroupList = []
        this.stageGroupWeek = 0
      },
      handleCreate() {
        this.resetTemp()
        this.getCourseStageList()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      createData() {
        this.ttsText = this.word
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.course.videoUrl = encodeURI(this.course.videoUrl)
            this.course.imageUrl = encodeURI(this.course.imageUrl)
            this.course.resourceUrl = encodeURI(this.course.resourceUrl)
            this.course.stampImageUrl = encodeURI(this.course.stampImageUrl)
            this.course.cardImageUrl = encodeURI(this.course.cardImageUrl)
            addCourse(this.course).then((res) => {
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
        this.getCourseStageList()
        this.fetchStageGroupList(row.stageId)
        this.getCourseUnitWeekListByUnitId(row.groupId)

        this.course = Object.assign({}, row) // copy obj
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      updateData() {
        this.ttsText = this.word
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            // 对象拷贝
            const tempData = Object.assign({}, this.course)
            // url 编码
            tempData.videoUrl = encodeURI(decodeURI(tempData.videoUrl))
            tempData.imageUrl = encodeURI(decodeURI(tempData.imageUrl))
            tempData.resourceUrl = encodeURI(decodeURI(tempData.resourceUrl))
            tempData.stampImageUrl = encodeURI(decodeURI(tempData.stampImageUrl))
            tempData.cardImageUrl = encodeURI(decodeURI(tempData.cardImageUrl))
            editCourse(tempData).then((res) => {
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
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteCourse(row.id).then((res) => {
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
      }
    }
  }

</script>
