<template>
  <div class="app-container">
    <el-tabs type="border-card" v-model="activeName"
      @tab-click="handleTabClick">
      <el-tab-pane label="任务列表" name="scheduler">
        <!-- 操作按钮 -->
        <div class="filter-container">
          <el-button class="filter-item" style="margin-left: 10px;" @click="$refs['schedulerUpdate'].handleCreateJob()" type="primary" size="mini" icon="el-icon-edit">{{$t('table.add')}} </el-button>
          <el-button class="filter-item" style="margin-left: 10px;" @click="handleRefreshSchedulerList()" type="success" size="mini" icon="el-icon-refresh">{{$t('table.refresh')}} </el-button>
        </div>
        <!-- 数据表格 -->
        <el-table :data="list" v-loading.body="listLoading"  height="650" element-loading-text="Loading"
                  size="mini" border fit highlight-current-row>
          <el-table-column align="center" label='ID' width="95">
            <template slot-scope="scope">
              {{scope.$index + 1}}
            </template>
          </el-table-column>
          <el-table-column label="任务" width="" align="center">
            <template slot-scope="scope">
              {{scope.row.taskDesc}}
            </template>
          </el-table-column>
          <el-table-column label="执行周期" width="150" align="center">
            <template slot-scope="scope">
              {{scope.row.taskCron}}
            </template>
          </el-table-column>
          <el-table-column label="上次执行时间" width="140" align="center">
            <template slot-scope="scope">
              {{scope.row.previousFireTime}}
            </template>
          </el-table-column>
          <el-table-column label="下次运行时间" width="140" align="center">
            <template slot-scope="scope">
              {{scope.row.nextFireTime}}
            </template>
          </el-table-column>
          <el-table-column label="目标对象" width="111" align="center">
            <template slot-scope="scope">
              {{ [targetObject, scope.row.targetObject ] | keyValueFilter }}
            </template>
          </el-table-column>
          <el-table-column label="目标方法" width="111" align="center">
            <template slot-scope="scope">
              {{ [targetMethod, scope.row.targetMethod ] | keyValueFilter }}
            </template>
          </el-table-column>
          <el-table-column label="班级名称" width="111" align="center">
            <template slot-scope="scope">
             <span>
              <router-link :to="'/school/classes/'+ encodeURI(scope.row.className)">
                <span style="color: #03F;">{{ scope.row.className }}</span>
              </router-link>
             </span>
            </template>
          </el-table-column>
          <el-table-column label="当前课程" width="120" align="center">
            <template slot-scope="scope">
             <span>{{ scope.row.openCourseName }}</span>
            </template>
          </el-table-column>
          <el-table-column label="状态" width="80" align="center">
            <template slot-scope="scope">
              {{ [taskStatus, scope.row.status ] | keyValueFilter }}
            </template>
          </el-table-column>
          <!-- 操作按钮 -->
          <el-table-column align="center" :label="$t('table.actions')" width="370" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="mini" type="primary" @click="$refs['schedulerUpdate'].handleEditJob(scope.row)">{{$t('table.edit')}}</el-button>
              <el-button size="mini" type="info" @click="handleCloseJob(scope.row)">{{$t('table.pause')}}</el-button>
              <el-button size="mini" type="success" @click="handleOpenJob(scope.row)">{{$t('table.enable')}}</el-button>
              <el-button size="mini" type="warning" @click="handleRunJob(scope.row)">{{$t('table.run')}}</el-button>
              <el-button size="mini" type="danger" @click="handleDeleteJob(scope.row)">{{$t('table.delete')}}</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 底部分页 -->
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
      </el-tab-pane>
      <!-- 任务日志 -->
      <el-tab-pane label="任务日志" name="schedulerLogs">
        <scheduler-logs ref="schedulerLogs"/>
      </el-tab-pane>
    </el-tabs>

    <!-- 操作弹窗 -->
    <scheduler-update ref="schedulerUpdate" @refresh-list="getSchedulerList"/>
  </div>
</template>


<script>
  import { getScheduler, openJob, runJob, deleteJob, closeJob } from '@/api/scheduler'
  import { targetObject, targetMethod, taskStatus, keyValueFilter, durationFilter } from '@/utils/constant'
  import SchedulerLogs from './schedulerLog'
  import SchedulerUpdate from './updateScheduler'

  const schedulerObject = {
    taskName: '',
    taskGroup: '',
    status: '1',
    taskDesc: '',
    targetObject: '',
    targetMethod: '',
    jobType: 'job',
    taskType: 'local',
    taskCron: ''
  }

  export default {
    name: 'Scheduler',
    components: {
      SchedulerUpdate,
      SchedulerLogs },
    data() {
      return {
        taskStatus: taskStatus,
        targetObject: targetObject,
        targetMethod: targetMethod,
        activeName: 'scheduler',
        schedulerSelection: [],
        list: null,
        listLoading: true,
        total: null,
        listQuery: {
          keyWord: null,
          page: 1,
          limit: 10
        },
        scheduler: Object.assign({}, schedulerObject)
      }
    },
    created() {
      this.getSchedulerList()
    },
    filters: {
      keyValueFilter,
      durationFilter
    },
    methods: {
      handleRefreshSchedulerList() {
        this.getSchedulerList()
      },
      getSchedulerList() {
        this.listLoading = true
        getScheduler(this.listQuery).then(res => {
          if (res.data.code === 0) {
            this.list = res.data.scheduler.list
            this.total = res.data.scheduler.totalCount
          } else {
            this.$message.error(res.data.msg)
          }
          this.listLoading = false
        })
      },
      handleCartoonSelectionChange(val) {
        this.schedulerSelection = val
      },
      handleCloseJob(row) {
        this.scheduler.taskName = row.taskName
        this.scheduler.taskGroup = row.taskGroup
        closeJob(this.scheduler).then((res) => {
          if (res.data.code === 0) {
            this.$notify({
              title: '成功',
              message: res.data.msg,
              type: 'success',
              duration: 2000
            })
            this.getSchedulerList()
          } else {
            this.$message.error(res.data.msg)
          }
        })
      },
      handleOpenJob(row) {
        this.scheduler.taskName = row.taskName
        this.scheduler.taskGroup = row.taskGroup
        openJob(this.scheduler).then((res) => {
          if (res.data.code === 0) {
            this.$notify({
              title: '成功',
              message: res.data.msg,
              type: 'success',
              duration: 2000
            })
            this.getSchedulerList()
          } else {
            this.$message.error(res.data.msg)
          }
        })
      },
      handleRunJob(row) {
        this.scheduler.taskName = row.taskName
        this.scheduler.taskGroup = row.taskGroup
        this.scheduler.id = row.id
        this.scheduler.targetObject = row.targetObject
        this.scheduler.targetMethod = row.targetMethod
        runJob(this.scheduler).then((res) => {
          if (res.data.code === 0) {
            this.$notify({
              title: '成功',
              message: res.data.msg,
              type: 'success',
              duration: 2000
            })
            this.getSchedulerList()
          } else {
            this.$message.error(res.data.msg)
          }
        })
      },
      handleDeleteJob(row) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.scheduler.taskName = row.taskName
          this.scheduler.taskGroup = row.taskGroup
          deleteJob(this.scheduler).then((res) => {
            if (res.data.code === 0) {
              this.$notify({
                title: '成功',
                message: res.data.msg,
                type: 'success',
                duration: 2000
              })
              this.getSchedulerList()
            } else {
              this.$message.error(res.data.msg)
            }
          })
        }).catch(() => {

        })
      },
      handleTabClick(tab) {
        if (tab.name === 'schedulerLogs') {
          this.$refs['schedulerLogs'].getList()
        }
      },
      handleFilter() {
        this.listQuery.page = 1
        this.getSchedulerList()
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.getSchedulerList()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.getSchedulerList()
      }
    }
  }
</script>
