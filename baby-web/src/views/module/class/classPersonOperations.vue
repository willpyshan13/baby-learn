<template>
  <div class="app-container el-tabs--border-card" style="margin: 20px">
    <!--{{classesId}}-->
    <div class="filter-container">
      <span v-if="currentClassName !== 'undefined'" class="current-class-name">当前班级 :&nbsp;{{ currentClassName }}</span>
    </div>
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.mobileNumber')" v-model="listQuery.mobileNumber" size="mini"></el-input>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="$refs['personSelectList'].handleAssignedStudent()" type="primary" size="mini"
                 icon="el-icon-edit">{{$t('table.assignStudent')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="handleDelete" type="danger" size="mini"
                 icon="el-icon-edit">{{$t('table.delete')}}
      </el-button>
    </div>

    <el-table :data="personList" v-loading.body="classPersonLoading"  height="650"
              @selection-change="handleCancelStudentSelectionChange" element-loading-text="Loading" size="mini" border fit highlight-current-row>
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="头像" width="120" align="center">
        <template slot-scope="scope">
          <img v-if="scope.row.photoUrl !== ''" v-bind:src="scope.row.photoUrl" v-bind:width="35" height="35"/>
          <img v-if="scope.row.photoUrl === '' && scope.row.gender === false" v-bind:src="girlPhoto" v-bind:width="35" height="35"/>
          <img v-if="scope.row.photoUrl === '' && scope.row.gender === true" v-bind:src="boyPhoto" v-bind:width="35" height="35"/>
        </template>
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
      <!-- 格式转换 -->
      <el-table-column class-name="status-col" label="性别" width="110" align="center">
        <template slot-scope="scope">
          {{scope.row.gender | personGenderFilter}}
        </template>
      </el-table-column>
      <el-table-column label="简介" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.personDesc }}
        </template>
      </el-table-column>
      <el-table-column label="积分" width="80" align="center">
        <template slot-scope="scope">
          {{scope.row.accumulatedPoints}}
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

    <person-select-list ref="personSelectList" :class-id="classId" @refresh-list="fetchClassPerson"/>

  </div>
</template>

<script>
  import { classPerson, cancelClassStudent } from '@/api/classInfo'
  import { typeOptions, personGenderFilter } from '@/utils/constant'

  import girlPhoto from '@/assets/header/girl.png'
  import boyPhoto from '@/assets/header/boy.png'
  import PersonSelectList from './personList'

export default {
    components: { PersonSelectList },
    name: 'classPersonOperations',
    created() {
      this.classId = Number(this.$route.params && this.$route.params.id)
      this.currentClassName = String(this.$route.params && this.$route.params.currentClassName)
      this.fetchClassPerson()
    },
    data() {
      return {
        currentClassName: '',
        girlPhoto: girlPhoto,
        boyPhoto: boyPhoto,
        typeOptions: typeOptions,
        classId: null,
        listQuery: {
          classId: null,
          mobileNumber: null,
          name: null,
          page: 1,
          limit: 10
        },
        total: null,
        personList: null,
        classPersonLoading: false,
        classPersonQuery: {
          page: 1,
          limit: 10,
          classId: null
        },
        multipleCancelStudentSelection: []
      }
    },
    filters: {
      personGenderFilter
    },
    methods: {
      fetchClassPerson() {
        if (this.classId != null) {
          this.listQuery.classId = this.classId
          this.classPersonLoading = true
          classPerson(this.listQuery).then(response => {
            this.personList = response.data.classPersonList.list
            this.total = response.data.classPersonList.totalCount
            this.classPersonLoading = false
          })
        }
      },
      handleDelete() {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
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
                this.fetchClassPerson()
              }
            })
          } else {
            this.$message.error('请选择学生')
          }
        }).catch(() => {

        })
      },
      handleCancelStudentSelectionChange(val) {
        this.multipleCancelStudentSelection = val
      },
      handleFilter() {
        this.listQuery.page = 1
        this.fetchClassPerson()
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.fetchClassPerson()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.fetchClassPerson()
      }
    }
  }
</script>
