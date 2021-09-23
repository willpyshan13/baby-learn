<template>
  <div class="app-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.searchKeys')" v-model="listQuery.searchKeys" size="mini"></el-input>
      <el-select clearable filterable class="filter-item" v-model="type" placeholder="请选择推送类型"
                 size="mini" @change="handleLogTypeSelect">
        <el-option v-for="item in logType" :key="item.key" :label="item.display_name" :value="item.key"></el-option>
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">
        {{$t('table.search')}}
      </el-button>
    </div>

    <el-table :data="list" v-loading.body="listLoading" height="650" element-loading-text="Loading" size="mini" border
              fit highlight-current-row>
      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="执行操作" width="180" align="center">
        <template slot-scope="scope">
          {{scope.row.title}}
        </template>
      </el-table-column>
      <el-table-column label="请求地址" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.requestUrl}}
        </template>
      </el-table-column>
      <el-table-column label="执行方法" width="" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.method }}</span>
        </template>
      </el-table-column>
      <el-table-column label="请求参数" width="" align="center" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <span>{{scope.row.parameters }}</span>
        </template>
      </el-table-column>
      <el-table-column label="IP地址" width="" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.clientHost }}</span>
        </template>
      </el-table-column>
      <el-table-column label="执行结果" width="" align="center" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <span>{{scope.row.result }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作人" width="" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.userName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="联系电话" width="" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.mobilePhone }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作时间" width="140" align="center">
        <template slot-scope="scope">
          {{scope.row.createTime }}
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
import { getSysLog, getApiLog } from '@/api/logs'
import { logType, keyValueFilter } from '@/utils/constant'

export default {
  name: 'SysLog',
  data() {
    return {
      logType: logType,
      list: [],
      listLoading: true,
      total: 0,
      type: 'sys',
      listQuery: {
        searchKeys: null,
        page: 1,
        limit: 10,
        orderField: 'create_time',
        order: 'desc'
      }
    }
  },
  created() {
    this.getList()
  },
  filters: {
    keyValueFilter
  },
  methods: {
    handleLogTypeSelect(val) {
      console.log(val)
      this.list = []
      this.total = 0
      this.type = val
      this.getList()
    },
    getList() {
      if (this.type !== '') {
        this.listLoading = true
        if (this.type === 'sys') {
          getSysLog(this.listQuery).then(res => {
            if (res.data.code === 0) {
              this.list = res.data.sysLog.list
              this.total = res.data.sysLog.totalCount
            } else {
              this.$message.error(res.data.msg)
            }
            this.listLoading = false
          })
        } else if (this.type === 'api') {
          getApiLog(this.listQuery).then(res => {
            if (res.data.code === 0) {
              this.list = res.data.apiLog.list
              this.total = res.data.apiLog.totalCount
            } else {
              this.$message.error(res.data.msg)
            }
            this.listLoading = false
          })
        }
      }
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
