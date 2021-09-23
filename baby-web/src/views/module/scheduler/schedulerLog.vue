<template>
  <div>
    <div v-if="false" class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.pushObject')" v-model="listQuery.keyWord" size="mini"/>
      <el-select clearable filterable class="filter-item" v-model="listQuery.status" placeholder="请选择推送类型" size="mini">
        <el-option v-for="item in taskLogStatus" :key="item.key" :label="item.display_name"
                   :value="item.key">
        </el-option>
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">
        {{$t('table.search')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="$refs['personOperations'].handleCreate()"
                 type="primary" size="mini"
                 icon="el-icon-edit">{{$t('table.add')}}
      </el-button>
    </div>

    <el-table :data="list" v-loading.body="schedulerLogsLoading" height="650" element-loading-text="Loading" size="mini" border
              fit highlight-current-row>
      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="任务对象" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.groupName}}
        </template>
      </el-table-column>
      <el-table-column label="任务方法" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.taskName}}
        </template>
      </el-table-column>
      <el-table-column label="开始时间" width="160" align="center">
        <template slot-scope="scope">
          {{scope.row.startTime}}
        </template>
      </el-table-column>
      <el-table-column label="结束时间" width="180" align="center">
        <template slot-scope="scope">
          {{scope.row.endTime}}
        </template>
      </el-table-column>
      <el-table-column label="服务器" width="" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.serverHost">{{scope.row.serverHost}}</span>
          <span v-else>{{scope.row.serverDuid}}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" width="180" align="center">
        <template slot-scope="scope">
          {{ [taskLogStatus, scope.row.status ] | keyValueFilter }}
        </template>
      </el-table-column>
      <el-table-column label="对象id" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.targetId}}
        </template>
      </el-table-column>

      <el-table-column v-if="false" align="center" :label="$t('table.actions')" width="340"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="danger"
                     @click="handleDelete(scope.row)">{{$t('table.delete')}}
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
  </div>

</template>

<script>
  import { getSchedulerLogs } from '@/api/scheduler'
  import { taskLogStatus, keyValueFilter, durationFilter } from '@/utils/constant'

  export default {
    name: 'schedulerLogs',
    data() {
      return {
        taskLogStatus: taskLogStatus,
        list: null,
        schedulerLogsLoading: false,
        total: null,
        listQuery: {
          keyWord: null,
          page: 1,
          limit: 10
        }
      }
    },
    created() {
      this.getList()
    },
    filters: {
      keyValueFilter,
      durationFilter
    },
    methods: {
      getList() {
        this.schedulerLogsLoading = true
        getSchedulerLogs(this.listQuery).then(res => {
          if (res.data.code === 0) {
            this.list = res.data.schedulerLogs.list
            this.total = res.data.schedulerLogs.totalCount
          } else {
            this.$message.error(res.data.msg)
          }
          this.schedulerLogsLoading = false
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
