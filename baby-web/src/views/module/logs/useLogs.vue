<template>
  <div class="app-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.userName')" v-model="listQuery.userName" size="mini"/>
      <el-select clearable filterable class="filter-item" v-model="listQuery.type" placeholder="请选择APP类型" size="mini">
        <el-option v-for="item in appType" :key="item.key" :label="item.display_name"
                   :value="item.key">
        </el-option>
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="$refs['personOperations'].handleCreate()" type="primary" size="mini"
                 icon="el-icon-edit">{{$t('table.add')}}
      </el-button>
    </div>

    <el-table :data="list" v-loading.body="listLoading"  height="650" element-loading-text="Loading" size="mini" border fit highlight-current-row>
      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="记录名称" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.name}}
        </template>
      </el-table-column>
      <el-table-column label="记录类型" width="" align="center">
        <template slot-scope="scope">
          {{ [eventType, scope.row.eventType ] | keyValueFilter }}
        </template>
      </el-table-column>
      <el-table-column label="APP类型" width="140" align="center">
        <template slot-scope="scope">
          {{ [appType, scope.row.type ] | keyValueFilter }}
        </template>
      </el-table-column>
      <el-table-column label="用户姓名" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.userName}}
        </template>
      </el-table-column>
      <el-table-column label="开始时间" width="140" align="center">
        <template slot-scope="scope">
          {{scope.row.startTime }}
        </template>
      </el-table-column>
      <el-table-column label="结束时间" width="140" align="center">
        <template slot-scope="scope">
          {{scope.row.endTime }}
        </template>
      </el-table-column>
      <el-table-column label="使用时长/点击次数" width="" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.eventType === 1">{{ scope.row.duration | durationFilter }}</span>
          <span v-if="scope.row.eventType === 2">{{ scope.row.number }} 次</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="140" align="center">
        <template slot-scope="scope">
          {{scope.row.createTime}}
        </template>
      </el-table-column>
      <el-table-column v-if="false" align="center" :label="$t('table.actions')" width="340" class-name="small-padding fixed-width">
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
import { getAppUseLogs } from '@/api/app'
import { appType, eventType, keyValueFilter, durationFilter } from '@/utils/constant'

export default {
  name: 'UseLogs',
  data() {
    return {
      appType: appType,
      eventType: eventType,
      list: null,
      listLoading: true,
      total: null,
      listQuery: {
        mobileNumber: null,
        name: null,
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
      getAppUseLogs(this.listQuery).then(res => {
        if (res.data.code === 0) {
          this.list = res.data.log.list
          this.total = res.data.log.totalCount
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
