<template>
  <div class="app-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.pushObject')" v-model="listQuery.keyWord" size="mini"/>
      <el-select clearable filterable class="filter-item" v-model="listQuery.pushType" placeholder="请选择推送类型"
                 size="mini">
        <el-option v-for="item in messageType" :key="item.key" :label="item.display_name"
                   :value="item.key">
        </el-option>
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
      <el-table-column label="消息类型" width="140" align="center">
        <template slot-scope="scope">
          {{ [messageType, scope.row.pushType ] | keyValueFilter }}
        </template>
      </el-table-column>
      <el-table-column label="推送标记" width="180" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.pushAlias">{{scope.row.pushAlias}}</span>
          <span v-else> {{scope.row.pushTag}}</span>
        </template>
      </el-table-column>
      <el-table-column label="消息内容" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.pushMsg}}
        </template>
      </el-table-column>
      <el-table-column label="附加数据" width="" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.pushData">{{scope.row.pushData}}</span>
          <span v-else>无</span>
        </template>
      </el-table-column>
      <el-table-column label="推送结果" width="" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.result }}</span>
        </template>
      </el-table-column>
      <el-table-column label="推送时间" width="140" align="center">
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
import { getPushLog } from '@/api/logs'
import { messageType, keyValueFilter, durationFilter } from '@/utils/constant'

export default {
  name: 'PushLogs',
  data() {
    return {
      messageType: messageType,
      list: null,
      listLoading: true,
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
      this.listLoading = true
      getPushLog(this.listQuery).then(res => {
        if (res.data.code === 0) {
          this.list = res.data.pushLog.list
          this.total = res.data.pushLog.totalCount
        } else {
          this.$message.error(res.data.msg)
        }
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
