<template>
  <el-dialog width="70%" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">

    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" :placeholder="$t('table.searchKeys')" v-model="classPersonQuery.searchKeys" size="mini"/>
      <!--<el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" :placeholder="$t('table.nickName')" v-model="classPersonQuery.nickName" size="mini"/>-->
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="confirm" type="primary"
                 size="mini"
                 icon="el-icon-edit">{{$t('table.confirm')}}
      </el-button>
    </div>

    <el-table :data="personToClassList" v-loading.body="personToClassLoading"  height="455"
              @selection-change="handlePersonSelectionChange"
              element-loading-text="Loading" size="mini" border fit highlight-current-row>
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
      <!-- 格式转换 -->
      <el-table-column class-name="status-col" label="性别" width="110" align="center">
        <template slot-scope="scope">
          {{scope.row.gender | personGenderFilter}}
        </template>
      </el-table-column>
      <el-table-column label="昵称" width="120" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.nickName}}</span>
        </template>
      </el-table-column>
      <el-table-column label="手机号码" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.mobileNumber}}
        </template>
      </el-table-column>
      <el-table-column label="所在班级" width="" align="center">
        <template slot-scope="scope">
          <span v-for="v in scope.row.className.split('、')">
            <router-link :to="'/school/classes/'+ encodeURI(v)">
              {{v}}
            </router-link>
          </span>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="classPersonQuery.page"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="classPersonQuery.limit"
        layout="total, sizes, prev, pager, next, jumper"
        :total="classPersonTotal">
      </el-pagination>
    </div>
  </el-dialog>
</template>
<script>
  import { assignedPerson } from '@/api/classInfo'
  import { typeOptions, personGenderFilter } from '@/utils/constant'
  import { getPersonToClass } from '@/api/person'
  import girlPhoto from '@/assets/header/girl.png'
  import boyPhoto from '@/assets/header/boy.png'

  export default {
    name: 'personSelectList',
    props: {
      classId: {
        type: Number,
        default: null
      },
      isSingle: {
        type: Boolean,
        default: false
      }
    },
    computed: {
      getClassId() {
        return this.classId
      }
    },
    filters: {
      personGenderFilter
    },
    data() {
      return {
        girlPhoto: girlPhoto,
        boyPhoto: boyPhoto,
        typeOptions: typeOptions,
        multipleStudentSelection: [],
        personToClassList: [],
        personToClassLoading: false,
        classPersonTotal: null,
        classPersonQuery: {
          searchKeys: null,
          page: 1,
          limit: 10,
          classId: null
        },
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '新增'
        }
      }
    },
    methods: {
      handleAssignedStudent() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.getPersonToClassList()
      },
      getPersonToClassList() {
        this.classPersonQuery.classId = this.getClassId
        this.personToClassLoading = true
        getPersonToClass(this.classPersonQuery).then((response) => {
          this.personToClassList = response.data.personToClassList.list
          this.classPersonTotal = response.data.personToClassList.totalCount
          this.personToClassLoading = false
        })
      },
      saveAssignedPerson() {
        if (this.multipleStudentSelection.length > 0) {
          const studentIds = []
          let i = 0
          this.multipleStudentSelection.forEach(row => {
            studentIds[i++] = row.personId
          })
          const params = {}
          params.classId = this.getClassId
          params.studentIds = studentIds

          assignedPerson(params).then((res) => {
            if (res.data.code === 0) {
              this.$notify({
                title: '成功',
                message: '分配成功',
                type: 'success',
                duration: 2000
              })
              this.dialogFormVisible = false
              this.getPersonToClassList()
              this.$emit('refresh-list')
            } else {
              this.$message.error(res.data.msg)
            }
          })
        } else {
          this.$message.error('请选择学生!')
        }
      },
      singleSelect() {
        if (this.multipleStudentSelection.length !== 1) {
          this.$message.error('请选择一条数据!')
          return false
        } else {
          const o = {}
          o.id = this.multipleStudentSelection[0].personId
          o.name = this.multipleStudentSelection[0].userName
          this.$emit('input', o)
          this.dialogFormVisible = false
        }
      },
      confirm() {
        if (this.isSingle) {
          this.singleSelect()
        } else {
          this.saveAssignedPerson()
        }
      },
      handleFilter() {
        this.classPersonQuery.page = 1
        this.getPersonToClassList()
      },
      handlePersonSelectionChange(val) {
        this.multipleStudentSelection = val
      },
      handleSizeChange(val) {
        this.classPersonQuery.limit = val
        this.getPersonToClassList()
      },
      handleCurrentChange(val) {
        this.classPersonQuery.page = val
        this.getPersonToClassList()
      }
    }
  }

</script>
