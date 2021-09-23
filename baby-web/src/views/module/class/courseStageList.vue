<template>
  <el-dialog width="70%" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">

    <div class="filter-container">
      <el-button class="filter-item" style="margin-left: 10px;" @click="saveAssignedCourse" type="primary"
                 size="mini"
                 icon="el-icon-edit">{{$t('table.confirm')}}
      </el-button>
    </div>


    <el-table ref="courseStageTable" :data="list" v-loading.body="listLoading"  :height="height" element-loading-text="Loading"
              @selection-change="handleCourseSelectionChange"
              @select="handleSelect"
              size="mini" border fit highlight-current-row>
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="标题" width="100" align="center">
        <template slot-scope="scope">
          {{scope.row.stageName}}
        </template>
      </el-table-column>
      <el-table-column label="描述" width="" align="center" show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{scope.row.description}}</span>
        </template>
      </el-table-column>
      <el-table-column label="阶段" width="80" align="center">
        <template slot-scope="scope">
          {{scope.row.level}}
        </template>
      </el-table-column>
      <el-table-column label="课时" width="80" align="center">
        <template slot-scope="scope">
          {{scope.row.spendTime}}
        </template>
      </el-table-column>
      <el-table-column label="课程数量" width="80" align="center">
        <template slot-scope="scope">
          {{scope.row.courseCount}}
        </template>
      </el-table-column>
      <el-table-column label="原价" width="80" align="center">
        <template slot-scope="scope">
          {{scope.row.originPrice}}
        </template>
      </el-table-column>
      <el-table-column label="教师" width="110" align="center">
        <template slot-scope="scope">
          {{scope.row.teacherName}}
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
  </el-dialog>
</template>
<script>
  import { assignedCourse } from '@/api/classInfo'
  import { courseStageList } from '@/api/course'
  import { statusOptions, courseType, viewingAuthority, keyValueFilter } from '@/utils/constant'

  export default {
    name: 'courseStageSelectList',
    props: {
      classId: {
        type: Number,
        default: null
      },
      height: {
        type: Number,
        default: 650
      }
    },
    computed: {
      getClassId() {
        return this.classId
      }
    },
    filters: {
      keyValueFilter
    },
    data() {
      return {
        statusOptions: statusOptions,
        courseType: courseType,
        viewingAuthority: viewingAuthority,
        listLoading: false,
        list: [],
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '新增'
        },
        listQuery: {
          page: 1,
          limit: 10,
          classId: null
        },
        total: null,
        multipleCourseSelection: []
      }
    },
    methods: {
      getList() {
        if (this.getClassId) {
          this.listQuery.classId = this.getClassId
          this.listLoading = true

          courseStageList(this.listQuery).then((response) => {
            this.list = response.data.courseStageList.list
            this.total = response.data.courseStageList.totalCount
            this.listLoading = false
          })
        }
      },
      handleAssignedCourse() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.getList()
      },
      saveAssignedCourse() {
        if (this.multipleCourseSelection.length > 0) {
          let stageId = ''
          this.multipleCourseSelection.forEach(row => {
            stageId = row.id
          })
          const params = {}
          params.classId = this.getClassId
          params.stageId = stageId

          assignedCourse(params).then((res) => {
            if (res.data.code === 0) {
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '分配成功',
                type: 'success',
                duration: 2000
              })
              this.getList()
              this.$emit('refresh-list')
            } else {
              this.$message.error(res.data.msg)
            }
          })
        } else {
          this.$message.error('请选择课程')
        }
      },
      handleSelect(selection, row) {
        this.$refs.courseStageTable.clearSelection()
        this.$refs.courseStageTable.toggleRowSelection(row)
      },
      handleCourseSelectionChange(val) {
        this.multipleCourseSelection = val
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
