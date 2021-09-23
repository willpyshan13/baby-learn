<template>
  <div class="app-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.name')" v-model="listQuery.className" size="mini"></el-input>
      <el-select clearable filterable v-model="listQuery.kindergartenId" placeholder="请选择幼儿园" size="mini" @change="handleFilter" class="filter-item">
        <el-option v-for="item in kindergartenList" :key="item.id" :label="item.name" :value="item.id"/>
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="$refs['classOperations'].handleCreate()" type="primary" size="mini" icon="el-icon-edit">{{$t('table.add')}}</el-button>
    </div>

    <el-table :data="list" v-loading.body="listLoading" height="650" element-loading-text="Loading" size="small" border
              fit highlight-current-row>
      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="班级名称" width="180" align="center">
        <template slot-scope="scope">
          {{scope.row.className}}
        </template>
      </el-table-column>
      <el-table-column label="教师" width="110" align="center">
        <template slot-scope="scope">
          {{scope.row.teacherName}}
        </template>
      </el-table-column>
      <el-table-column label="班主任" width="110" align="center">
        <template slot-scope="scope">
          {{scope.row.headerTeacherName}}
        </template>
      </el-table-column>
      <el-table-column label="课程阶段" width="110" align="center">
        <template slot-scope="scope">
          {{scope.row.stageName}}
        </template>
      </el-table-column>
      <el-table-column label="班级人数" width="80" align="center">
        <template slot-scope="scope">
          {{scope.row.count}}
        </template>
      </el-table-column>
      <el-table-column label="班级介绍" width="" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.classInfo}}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" width="" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.remark}}</span>
        </template>
      </el-table-column>
      <el-table-column label="二维码" width="" align="center">
        <template slot-scope="scope">
          <a href="javascript:void(0)" @click="downloadImage(scope.row.className, scope.row.qrCodePath)">
            <img :src='scope.row.qrCodePath' class="img-circle" width="35px" style="" />
          </a>
        </template>
      </el-table-column>
      <el-table-column align="center" :label="$t('table.actions')" width="330"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="$refs['classOperations'].handleUpdate(scope.row)">
            {{$t('table.edit')}}
          </el-button>
          <!--<router-link :to="'/school/classes/students/'+ scope.row.id + '/' + encodeURI(scope.row.className)">-->
            <el-button class="filter-item" type="success" size="mini" @click="handleViewClassStudents(scope.row.id, scope.row.className)">{{$t('table.classStudent')}}</el-button>
          <!--</router-link>-->
          <router-link v-if="false" :to="'/school/classes/courses/'+ scope.row.id + '/' + encodeURI(scope.row.className)">
            <el-button class="filter-item" type="info" size="mini">{{$t('table.classCourse')}}</el-button>
          </router-link>
          <el-button size="mini" type="danger" @click="$refs['classOperations'].handleDelete(scope.row)">
            {{$t('table.delete')}}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="listQuery.page"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="listQuery.limit"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>

    <class-operations ref="classOperations" @refresh-list="getList"/>

    <el-dialog width="60%" :title="className" :visible.sync="classStudentsDialogVisible" top="6vh" @close="handleClassStudentsDialogClosed">
      <el-tabs v-model="studentTableActiveName" @tab-click="handleStudentsTabClick" ref="studentTabs">
        <el-tab-pane label="已选学生" name="inClassStudent">
          <div class="filter-container">
            <el-input @keyup.enter.native="handleInClassStudentsFilter" style="width: 200px;" class="filter-item"
                      :placeholder="$t('table.mobileNumber')" v-model="inClassStudentsQuery.mobileNumber" size="mini"></el-input>
            <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleInClassStudentsFilter">{{$t('table.search')}}</el-button>
            <el-button class="filter-item" style="margin-left: 10px;" @click="handleRemoveClassStudent" type="danger" size="mini" icon="el-icon-edit">{{$t('table.delete')}}
            </el-button>
          </div>
          <el-table :data="inClassStudentList" v-loading.body="classPersonLoading"  height="500"
                    element-loading-text="Loading" size="mini" border fit highlight-current-row
                    @selection-change="handleCancelStudentSelectionChange">
            <el-table-column
              type="selection"
              width="55">
            </el-table-column>
            <el-table-column label="姓名" width="120" align="center">
              <template slot-scope="scope">
                {{scope.row.userName}}
              </template>
            </el-table-column>
            <el-table-column label="昵称" width="" align="center">
              <template slot-scope="scope">
                <span>{{scope.row.nickName}}</span>
              </template>
            </el-table-column>
            <el-table-column label="手机号码" width="" align="center">
              <template slot-scope="scope">
                {{scope.row.mobileNumber}}
              </template>
            </el-table-column>
            <el-table-column label="简介" width="" align="center">
              <template slot-scope="scope">
                {{scope.row.personDesc }}
              </template>
            </el-table-column>
          </el-table>
          <div class="pagination-container">
            <el-pagination
              @size-change="handleInClassStudentsSizeChange"
              @current-change="handleInClassStudentsCurrentChange"
              :current-page="inClassStudentsQuery.page"
              :page-sizes="[10, 20, 30, 40]"
              :page-size="inClassStudentsQuery.limit"
              layout="total, sizes, prev, pager, next, jumper"
              :total="inClassStudentTotal">
            </el-pagination>
          </div>
        </el-tab-pane>
        <el-tab-pane label="未选学生" name="outClassStudent">
          <div class="filter-container">
            <el-input @keyup.enter.native="handleStudentFilter" style="width: 200px;" class="filter-item" :placeholder="$t('table.searchKeys')" v-model="outClassStudentsQuery.searchKeys" size="mini"/>
            <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleStudentFilter">{{$t('table.search')}}</el-button>
            <el-button class="filter-item" style="margin-left: 10px;" @click="handleConfirmStudentSelect" type="primary" size="mini" icon="el-icon-edit">{{$t('table.confirm')}}</el-button>
          </div>
          <el-table :data="studentList" v-loading.body="classPersonLoading"  height="500"
                    element-loading-text="Loading" size="mini" border fit highlight-current-row
                    @selection-change="handleStudentSelectionChange">
            <el-table-column
              type="selection"
              width="55">
            </el-table-column>
            <el-table-column label="姓名" width="120" align="center">
              <template slot-scope="scope">
                {{scope.row.userName}}
              </template>
            </el-table-column>
            <el-table-column label="昵称" width="" align="center">
              <template slot-scope="scope">
                <span>{{scope.row.nickName}}</span>
              </template>
            </el-table-column>
            <el-table-column label="手机号码" width="" align="center">
              <template slot-scope="scope">
                {{scope.row.mobileNumber}}
              </template>
            </el-table-column>
            <el-table-column label="简介" width="" align="center">
              <template slot-scope="scope">
                {{scope.row.personDesc }}
              </template>
            </el-table-column>
          </el-table>
          <div class="pagination-container">
            <el-pagination
              @size-change="handleStudentSizeChange"
              @current-change="handleStudentCurrentChange"
              :current-page="outClassStudentsQuery.page"
              :page-sizes="[10, 20, 30, 40]"
              :page-size="outClassStudentsQuery.limit"
              layout="total, sizes, prev, pager, next, jumper"
              :total="studentTotal">
            </el-pagination>
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>
    <!-- student list end -->

  </div>
</template>

<script>
  import { classInfoList, classPerson, cancelClassStudent, assignedPerson } from '@/api/classInfo'
  import { getKindergartenInfoList } from '@/api/kindergartenInfo'
  import { getPersonToClass } from '@/api/person'
  import { gender, statusOptions, courseType, keyValueFilter } from '@/utils/constant'

  import girlPhoto from '@/assets/header/girl.png'
  import boyPhoto from '@/assets/header/boy.png'
  import ClassOperations from './classOperations'

  export default {
    name: 'Classes',
    components: {
      ClassOperations
    },
    data() {
      return {
        kindergartenList: [],
        className: '',
        studentList: [],
        studentTotal: 0,
        outClassStudentsQuery: {
          classId: null,
          searchKeys: '',
          page: 1,
          limit: 10
        },
        multipleCancelStudentSelection: [],
        multipleStudentSelection: [],
        classId: null,
        classPersonLoading: false,
        inClassStudentList: [],
        inClassStudentTotal: 0,
        inClassStudentsQuery: {
          classId: null,
          mobileNumber: null,
          name: null,
          page: 1,
          limit: 10
        },
        studentTableActiveName: 'inClassStudent',
        classStudentsDialogVisible: false,
        // ------- student list end
        gender: gender,
        statusOptions: statusOptions,
        courseType: courseType,
        girlPhoto: girlPhoto,
        boyPhoto: boyPhoto,
        list: null,
        listLoading: true,
        total: null,
        listQuery: {
          kindergartenId: null,
          className: '',
          page: 1,
          limit: 10
        },
        classCourseList: null
      }
    },
    filters: {
      keyValueFilter
    },
    created() {
      this.fetchKindergartenList()
      const className = String(this.$route.params && this.$route.params.cName)
      if (className != null && className.trim() !== '' && className !== 'undefined') {
        this.listQuery.className = className
        this.getList()
      } else {
        this.getList()
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
      handleViewClassStudents(id, className) {
        this.classId = id
        this.className = className
        this.studentTableActiveName = 'inClassStudent'
        this.classStudentsDialogVisible = true
        this.fetchClassStudents()
      },
      handleClassStudentsDialogClosed() {
        this.studentList = []
        this.studentTotal = 0
        this.inClassStudentList = []
        this.inClassStudentTotal = 0
      },
      handleStudentsTabClick() {
        const tabName = event.target.getAttribute('id')
        if (tabName === 'tab-inClassStudent') {
          this.inClassStudentsQuery.mobileNumber = ''
          this.inClassStudentsQuery.type = null
          this.inClassStudentsQuery.pageNum = 1
          this.inClassStudentsQuery.pageSize = 10
          this.fetchClassStudents()
        } else if (tabName === 'tab-outClassStudent') {
          this.outClassStudentsQuery.searchKeys = ''
          this.outClassStudentsQuery.classId = null
          this.outClassStudentsQuery.page = 1
          this.outClassStudentsQuery.limit = 10
          this.fetchStudents()
        }
      },
      handleConfirmStudentSelect() {
        if (this.multipleStudentSelection.length > 0) {
          const studentIds = []
          let i = 0
          this.multipleStudentSelection.forEach(row => {
            studentIds[i++] = row.personId
          })
          const params = {}
          params.classId = this.classId
          params.studentIds = studentIds

          assignedPerson(params).then((res) => {
            if (res.data.code === 0) {
              this.$notify({
                title: '成功',
                message: '分配成功',
                type: 'success',
                duration: 2000
              })
              this.fetchClassStudents()
              this.studentTableActiveName = 'inClassStudent'
            } else {
              this.$message.error(res.data.msg)
            }
          })
        } else {
          this.$message.error('请选择学生!')
        }
      },
      handleStudentSelectionChange(val) {
        this.multipleStudentSelection = val
      },
      fetchStudents() {
        this.outClassStudentsQuery.classId = this.classId
        this.classPersonLoading = true
        getPersonToClass(this.outClassStudentsQuery).then((res) => {
          if (res.data.code === 0) {
            this.studentList = res.data.personToClassList.list
            this.studentTotal = res.data.personToClassList.totalCount
          } else {
            this.$message.error('获取学生信息失败')
          }
          this.classPersonLoading = false
        })
      },
      handleStudentFilter() {
        this.outClassStudentsQuery.page = 1
        this.fetchStudents()
      },
      handleStudentSizeChange(val) {
        this.outClassStudentsQuery.limit = val
        this.fetchStudents()
      },
      handleStudentCurrentChange(val) {
        this.outClassStudentsQuery.page = val
        this.fetchStudents()
      },
      handleRemoveClassStudent() {
        if (this.multipleCancelStudentSelection.length > 0) {
          const studentIds = []
          let i = 0
          this.multipleCancelStudentSelection.forEach(row => {
            studentIds[i++] = row.personId
          })
          const params = {}
          params.classId = this.classId
          params.studentIds = studentIds
          cancelClassStudent(params).then((response) => {
            if (response.data.code === 0) {
              this.$notify({
                title: '成功',
                message: '删除成功',
                type: 'success',
                duration: 2000
              })
              this.fetchClassStudents()
              // this.studentTableActiveName = 'inClassName'
            }
          })
        } else {
          this.$message.error('请选择学生')
        }
      },
      handleCancelStudentSelectionChange(val) {
        this.multipleCancelStudentSelection = val
      },
      fetchClassStudents() {
        if (this.classId != null) {
          this.inClassStudentsQuery.classId = this.classId
          this.classPersonLoading = true
          classPerson(this.inClassStudentsQuery).then(res => {
            if (res.data.code === 0) {
              this.inClassStudentList = res.data.classPersonList.list
              this.inClassStudentTotal = res.data.classPersonList.totalCount
            } else {
              this.$message.error('获取班级学生信息失败')
            }
            this.classPersonLoading = false
          })
        }
      },
      handleInClassStudentsFilter() {
        this.inClassStudentsQuery.page = 1
        this.fetchClassStudents()
      },
      handleInClassStudentsSizeChange(val) {
        this.inClassStudentsQuery.limit = val
        this.fetchClassStudents()
      },
      handleInClassStudentsCurrentChange(val) {
        this.inClassStudentsQuery.page = val
        this.fetchClassStudents()
      },
      // ---------- student list end
      downloadImage(name, src) {
        const canvas = document.createElement('canvas')
        const img = document.createElement('img')
        img.onload = function(e) {
          canvas.width = img.width
          canvas.height = img.height
          const context = canvas.getContext('2d')
          context.drawImage(img, 0, 0, img.width, img.height)
          canvas.getContext('2d').drawImage(img, 0, 0, img.width, img.height)
          canvas.toBlob((blob) => {
            const link = document.createElement('a')
            link.href = window.URL.createObjectURL(blob)
            link.download = name
            link.click()
          }, 'image/jpeg')
        }
        img.setAttribute('crossOrigin', 'Anonymous')
        img.src = src
      },
      getList() {
        this.listLoading = true
        classInfoList(this.listQuery).then(response => {
          this.list = response.data.classInfoList.list
          this.total = response.data.classInfoList.totalCount
          this.listLoading = false
        })
      },
      handleFilter() {
        this.listQuery.page = 1
        this.getList()
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.getList()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.getList()
      }
    }
  }
</script>
