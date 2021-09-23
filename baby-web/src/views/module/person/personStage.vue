<template>
  <div class="app-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input v-show="false" @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.title')" v-model="listQuery.name" size="mini"/>
      <el-button v-show="false" class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}</el-button>
      <el-button class="filter-item" type="success" icon="el-icon-edit" size="mini" @click="handleBugStage">{{$t('table.buyCourse')}}</el-button>
    </div>

    <el-table :data="list" v-loading.body="listLoading"  height="650" element-loading-text="Loading"
              size="mini" border fit highlight-current-row
              @expand-change="handleExpandChange" :row-key="handleRowKey"
              :expand-row-keys="expandRowKeys">

      <el-table-column type="expand">
        <template slot-scope="props">
          <learning-records :stage-id="tempSelectionId"/>
        </template>
      </el-table-column>


      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="标题" width="160" align="center">
        <template slot-scope="scope">
          {{scope.row.stageName}}
        </template>
      </el-table-column>
      <el-table-column label="描述" width="700" align="center" show-overflow-tooltip>
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
      <el-table-column align="center" :label="$t('table.actions')" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="danger" @click="handleDelete(scope.row)">{{$t('table.delete')}}</el-button>
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

    <el-dialog width="70%" title="购买课程" :visible.sync="dialogFormVisible">
      <el-form :rules="rules" ref="dataForm"  :model="stage" label-width="90px"
               style='width:75%;margin-left:20px;' size="mini">
        <el-form-item label="课程" prop="courseStageId">
          <el-select clearable filterable class="filter-item" v-model="stage.courseStageId" placeholder="请选择课阶">
            <el-option v-for="item in stageList" :key="item.id" :label="item.stageName"
                       :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">{{$t('table.cancel')}}</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">{{$t('table.confirm')}}</el-button>
      </div>
    </el-dialog>

  </div>
</template>
<script>
  import { courseStageList } from '@/api/course'
  import { buyCourse, deleteBuyStage } from '@/api/person'
  import { statusOptions, courseType, viewingAuthority, keyValueFilter } from '@/utils/constant'
  import LearningRecords from './learningRecords'

  export default {
    name: 'personStageList',
    components: { LearningRecords },
    props: {

    },
    created() {
      this.personId = this.$route.params && this.$route.params.id
      this.getPersonBoughtCourse()
    },
    filters: {
      keyValueFilter
    },
    data() {
      return {
        tempSelectionId: null,
        expandRowKeys: [],
        stage: {
          personId: null,
          courseStageId: null
        },
        personId: null,
        statusOptions: statusOptions,
        courseType: courseType,
        viewingAuthority: viewingAuthority,
        listLoading: false,
        list: [],
        stageList: [],
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '新增'
        },
        listQuery: {
          page: 1,
          limit: 10,
          personId: null
        },
        rules: {
          courseStageId: [{ required: true, message: '课阶为必选项', trigger: 'change' }]
        },
        total: null
      }
    },
    methods: {
      handleExpandChange(row, expandedRows) {
        // 只展开一行 判断是否至少含有一个正确答案
        if (expandedRows.length !== 0) { // 展开
          this.tempSelectionId = row.id
          this.expandRowKeys = []
          this.expandRowKeys.push(row.id)
        } else { // 关闭
          this.expandRowKeys = []
        }
      },
      handleRowKey(row) {
        return row.id
      },
      handleFilter() {
        this.listQuery.page = 1
        this.getPersonBoughtCourse()
      },
      getPersonBoughtCourse() {
        if (this.personId != null) {
          this.listQuery.notExistsPersonId = null
          this.listQuery.existsPersonId = this.personId
          this.listLoading = true
          courseStageList(this.listQuery).then((res) => {
            if (res.data.code === 0) {
              this.list = res.data.courseStageList.list
              this.total = res.data.courseStageList.totalCount
              this.listLoading = false
            } else {
              this.$message.error(res.data.msg)
            }
          })
        }
      },
      createData() {
        this.stage.personId = this.personId
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            buyCourse(this.stage).then((res) => {
              if (res.data.code === 0) {
                this.dialogFormVisible = false
                this.getPersonBoughtCourse()
                this.$notify({
                  title: '成功',
                  message: '购买成功',
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
      getCourseStageList() {
        this.listQuery.existsPersonId = null
        this.listQuery.notExistsPersonId = this.personId
        courseStageList(this.listQuery).then((response) => {
          this.stageList = response.data.courseStageList.list
        })
      },
      handleBugStage() {
        this.stage = {
          personId: null,
          courseStageId: null
        }
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.getCourseStageList()
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.getPersonBoughtCourse()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.getPersonBoughtCourse()
      },
      handleDelete(row) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteBuyStage(this.personId, row.id).then((res) => {
            if (res.data.code === 0) {
              this.getPersonBoughtCourse()
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
