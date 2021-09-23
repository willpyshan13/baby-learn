<template>
  <div>
    <el-table :data="list" v-loading.body="recordsListLoading" height="469" element-loading-text="Loading" size="mini" border fit highlight-current-row>
      <el-table-column align="center" label='序号' width="92">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="课程名称" width="300" align="center">
        <template slot-scope="scope">
          {{scope.row.name}}
        </template>
      </el-table-column>
      <!-- 格式转换 -->
      <el-table-column label="开课时间" width="120" align="center">
        <template slot-scope="scope">
          {{scope.row.openDate}}
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="开放状态" width="95" align="center">
        <template slot-scope="scope">
          <el-switch :disabled="scope.row.opened === 1" @change="handleCourseOpenedChange(scope.row)"
            v-model="scope.row.opened"
            active-color="#13ce66"
            :active-value="1"
            :inactive-value="0">
          </el-switch>
          <!--{{ [yesNoStatusOptions, scope.row.opened ] | keyValueFilter }}-->
        </template>
      </el-table-column>

      <el-table-column label="签到" width="95" align="center">
        <template slot-scope="scope">
          {{ [yesNoStatusOptions, scope.row.clocked ] | keyValueFilter }}
        </template>
      </el-table-column>
      <el-table-column label="签到日期" width="120" align="center">
        <template slot-scope="scope">
          {{ scope.row.clockDate }}
        </template>
      </el-table-column>

      <el-table-column label="已学" width="95" align="center">
        <template slot-scope="scope">
          {{ [yesNoStatusOptions, scope.row.learned ] | keyValueFilter }}
        </template>
      </el-table-column>
      <el-table-column label="最后更新时间" width="240" align="center">
        <template slot-scope="scope">
          {{scope.row.updateTime}}
        </template>
      </el-table-column>
      <el-table-column align="center" :label="$t('table.actions')" width="250" class-name="small-padding">
        <template slot-scope="scope">
          <el-button v-if="scope.row.learned === 1"  type="primary" size="mini" @click="$refs['learningReport'].handleCreate(scope.row.personId, scope.row.id)">{{$t('table.learningReport')}}</el-button>
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

    <learning-report ref="learningReport"/>

  </div>
</template>
<script>
  import { openPersonCourse } from '@/api/course'
  import { getLearnRecords } from '@/api/person'
  import { yesNoStatusOptions, keyValueFilter } from '@/utils/constant'
  import LearningReport from './learningReport'

  export default {
    components: { LearningReport },
    name: 'learningRecords',
    props: {
      stageId: {
        type: Number,
        default: null
      }
    },
    watch: {
      stageId: function(val) {
        this.stageId = val
        this.fetchRecordsData()
      }
    },
    created() {
      this.personId = this.$route.params && this.$route.params.id
      this.fetchRecordsData()
    },
    filters: {
      keyValueFilter
    },
    data() {
      return {
        courseStageList: [],
        personId: null,
        yesNoStatusOptions: yesNoStatusOptions,
        list: null,
        recordsListLoading: false,
        total: null,
        listQuery: {
          stageId: 6,
          personId: null,
          page: 1,
          limit: 10
        }
      }
    },
    methods: {
      fetchRecordsData() {
        if (this.personId != null) {
          this.recordsListLoading = true
          this.listQuery.personId = this.personId
          this.listQuery.stageId = this.stageId
          getLearnRecords(this.listQuery).then(res => {
            if (res.data.code === 0) {
              this.list = res.data.learnRecords.list
              this.total = res.data.learnRecords.totalCount
              this.recordsListLoading = false
            } else {
              this.$message.error(res.data.msg)
              this.recordsListLoading = false
            }
          })
        }
      },
      handleCourseOpenedChange(row) {
        this.$confirm('此操作将打开课程且无法关闭, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          openPersonCourse(row.personCourseId).then((res) => {
            if (res.data.code === 0) {
              this.fetchRecordsData()
              this.$notify({
                type: 'success',
                message: res.data.msg
              })
            } else {
              this.$message.error(res.data.msg)
            }
          })
          this.$message({
            type: 'success',
            message: '操作成功!'
          })
        }).catch(() => {
          row.opened = 0
        })
      },

      handleStageChange(val) {
        this.listQuery.stageId = val
        this.fetchRecordsData()
      },
      handleFilter() {
        this.listQuery.page = 1
        this.fetchRecordsData()
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.fetchRecordsData()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.fetchRecordsData()
      }
    }
  }
</script>
