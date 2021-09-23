<template>
  <el-dialog width="" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
    <el-form :rules="rules" ref="dataForm" :model="classInfo" label-width="100px"
             style='width:90%;margin-left:20px;' size="mini">
      <el-form-item label="班级名称" prop="className">
        <el-input v-model="classInfo.className"/>
      </el-form-item>
      <el-form-item label="所属幼儿园" prop="kindergartenId">
        <el-select clearable filterable class="filter-item" v-model="classInfo.kindergartenId" placeholder="请选择">
          <el-option v-for="item in kindergartenList" :key="item.id" :label="item.name"
                     :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="教师" prop="teacherId">
        <el-select clearable filterable class="filter-item" v-model="classInfo.teacherId" placeholder="请选择">
          <el-option v-for="item in teacherList" :key="item.teacherId" :label="item.teacherName"
                     :value="item.teacherId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="班主任" prop="headerTeacher">
        <el-select clearable filterable class="filter-item" v-model="classInfo.headerTeacher" placeholder="请选择">
          <el-option v-for="item in headerTeacherList" :key="item.teacherId" :label="item.teacherName"
                     :value="item.teacherId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="课阶" prop="courseStageId">
        <el-select :disabled="isEdit" clearable filterable class="filter-item" v-model="classInfo.courseStageId" placeholder="请选择">
          <el-option v-for="item in courseStageList" :key="item.id" :label="item.stageName"
                     :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="开课时间" prop="openWeeks">
        <el-checkbox-group v-model="openWeeks">
          <el-checkbox :label="2">周一</el-checkbox>
          <el-checkbox :label="3">周二</el-checkbox>
          <el-checkbox :label="4">周三</el-checkbox>
          <el-checkbox :label="5">周四</el-checkbox>
          <el-checkbox :label="6">周五</el-checkbox>
          <el-checkbox :label="7">周六</el-checkbox>
          <el-checkbox :label="1">周日</el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="班级类型" prop="type">
        <el-select clearable filterable class="filter-item" v-model="classInfo.type" placeholder="请选择">
          <el-option v-for="item in classType" :key="item.key" :label="item.display_name"
                     :value="item.key">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item v-if="classInfo.type === 3" label="默认一对多" prop="oneToMany">
        <el-switch
          v-model="classInfo.oneToMany"
          active-color="#13ce66"
          :active-value="true"
          :inactive-value="false">
        </el-switch>
      </el-form-item>
      <el-form-item label="班级介绍">
        <el-input type="textarea" v-model="classInfo.classInfo"></el-input>
      </el-form-item>
      <el-form-item label="备注">
        <el-input type="textarea" v-model="classInfo.remark"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false" size="mini">{{$t('table.cancel')}}</el-button>
      <el-button v-if="dialogStatus=='create'" type="primary" @click="createData" size="mini">{{$t('table.confirm')}}</el-button>
      <el-button v-else type="primary" @click="updateData" size="mini">{{$t('table.confirm')}}</el-button>
    </div>
  </el-dialog>

</template>
<script>
  import { addClassInfo, editClassInfo, deleteClassInfo } from '@/api/classInfo'
  import { getKindergartenInfoList } from '@/api/kindergartenInfo'
  import { teacherList } from '@/api/teacher'
  import { courseStageList } from '@/api/course'
  import { classType } from '@/utils/constant'

  const classObject = {
    id: null,
    classInfo: '',
    className: '',
    status: 1,
    count: 0,
    courseStageId: null,
    teacherId: null,
    pushTag: null,
    headerTeacher: '',
    type: 2,
    remark: '',
    oneToMany: 0,
    autoOpenDate: ''
  }

  export default {
    name: 'classOperations',
    created() {

    },
    data() {
      return {
        kindergartenList: [],
        isEdit: false,
        openWeeks: [],
        classType: classType,
        teacherList: [],
        headerTeacherList: [],
        courseStageList: [],
        classInfo: Object.assign({}, classObject),
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '新增'
        },
        rules: {
          kindergartenId: [{ required: true, message: '所属幼儿园为必选项', trigger: 'change' }],
          className: [{ required: true, message: '班级名称为必填项', trigger: 'change' }],
          teacherId: [{ required: true, message: '班级教师为必填项', trigger: 'change' }],
          courseStageId: [{ required: true, message: '班级课阶为必填项', trigger: 'change' }],
          headerTeacher: [{ required: true, message: '班主任为必填项', trigger: 'change' }],
          type: [{ required: true, message: '班级类型为必填项', trigger: 'change' }]
        }
      }
    },
    methods: {
      fetchKindergartenList() {
        getKindergartenInfoList({ enablePage: false }).then(res => {
          if (res.data.result === 0) {
            this.kindergartenList = res.data.body
          } else {
            this.$message.error(res.data.message)
          }
        })
      },
      resetTemp() {
        this.classInfo = {
          id: null,
          classInfo: '',
          className: '',
          status: 1,
          count: 0,
          courseStageId: null,
          teacherId: null,
          pushTag: null,
          headerTeacher: '',
          type: 2,
          remark: '',
          oneToMany: 0,
          autoOpenDate: ''
        }
        this.teacherList = []
        this.headerTeacherList = []
      },
      getTeacherList() {
        teacherList().then((response) => {
          response.data.teacherList.list.forEach((row) => {
            if (row.role === 0) {
              this.teacherList.push(row)
            } else if (row.role === 2) {
              this.headerTeacherList.push(row)
            }
          })
        })
      },
      getCourseStageList() {
        courseStageList().then((response) => {
          if (response.data.code === 0) {
            this.courseStageList = response.data.courseStageList.list
          } else {
            this.$message.error(response.data.msg)
          }
        })
      },
      handleCreate() {
        this.fetchKindergartenList()
        this.isEdit = false
        this.resetTemp()
        this.getTeacherList()
        this.getCourseStageList()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      createData() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.classInfo.autoOpenDate = this.openWeeks.join(',')
            addClassInfo(this.classInfo).then((res) => {
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
        this.fetchKindergartenList()
        this.isEdit = true
        this.resetTemp()
        this.getCourseStageList()
        this.getTeacherList()
        this.classInfo = Object.assign({}, row) // copy obj
        if (this.classInfo.autoOpenDate != null) {
          this.openWeeks = this.classInfo.autoOpenDate.split(',').map(Number)
        }
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      updateData() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.classInfo.autoOpenDate = this.openWeeks.join(',')
            const tempData = Object.assign({}, this.classInfo) // 对象拷贝
            editClassInfo(tempData).then((res) => {
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
          deleteClassInfo(row.id).then(() => {
            this.$emit('refresh-list')
            this.dialogFormVisible = false
            this.$notify({
              title: '成功',
              message: '删除成功',
              type: 'success',
              duration: 2000
            })
          })
        }).catch(() => {

        })
      }
    }
  }
</script>
