<template>
  <div class="app-container calendar-list-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.serialNumber')" v-model="listQuery.serialNumber" size="mini"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}</el-button>
      <el-button class="filter-item" type="danger" icon="el-icon-search" size="mini" @click="handleBatchDelete">{{$t('table.delete')}}</el-button>
      <el-button class="filter-item" type="success" icon="el-icon-edit" size="mini" @click="handleAddWhite">{{$t('table.addWhite')}}</el-button>
      <el-button class="filter-item" type="info" icon="el-icon-edit" size="mini" @click="handleAddBlack">{{$t('table.addBlack')}}</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-edit" size="mini" @click="handleNormal">{{$t('table.addNormal')}}</el-button>
    </div>

    <el-table :data="list" v-loading.body="listLoading"  height="650" element-loading-text="Loading"
              @selection-change="handleMachineSelect"
              size="mini" border fit highlight-current-row>
      <el-table-column type="selection" width="55"/>

      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="序列号" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.serialNumber}}
        </template>
      </el-table-column>
      <el-table-column label="当前版本" width="120" align="center">
        <template slot-scope="scope">
          {{scope.row.currentVersion}}
        </template>
      </el-table-column>
      <el-table-column label="状态" width="120" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.upgrade | statusFilter">{{ [ machineStatus, scope.row.upgrade ] | keyValueFilter }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="备注" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.remark}}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="180" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.createTime}}</span>
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
  import { getMachine, deleteBatchMachine, updateBatchMachine } from '@/api/app'
  import { machineStatus, keyValueFilter } from '@/utils/constant'

  export default {
    data() {
      return {
        machineStatus: machineStatus,
        machineSelect: [],
        list: null,
        listLoading: false,
        total: null,
        listQuery: {
          page: 1,
          limit: 10
        }
      }
    },
    filters: {
      keyValueFilter,
      statusFilter(status) {
        const statusMap = {
          1: 'success',
          0: 'info',
          2: 'primary'
        }
        return statusMap[status]
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList() {
        this.listLoading = true
        getMachine(this.listQuery).then(response => {
          if (response.data.code === 0) {
            this.list = response.data.machine.list
            this.total = response.data.machine.totalCount
            this.listLoading = false
          } else {
            this.$message.error(response.data.msg)
          }
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
      },
      handleMachineSelect(v) {
        this.machineSelect = v
      },
      handleBatchDelete() {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if (this.machineSelect.length > 0) {
            const ids = []
            this.machineSelect.forEach(row => {
              ids.push(row.id)
            })
            deleteBatchMachine(ids).then((res) => {
              if (res.data.code === 0) {
                this.getList()
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
          } else {
            this.$message.error('请选择列表项!')
          }
        }).catch(() => {

        })
      },
      handleAddWhite() {
        this.updateBatchUpgradeStatus(1)
      },
      handleAddBlack() {
        this.updateBatchUpgradeStatus(0)
      },
      handleNormal() {
        this.updateBatchUpgradeStatus(2)
      },
      updateBatchUpgradeStatus(upgrade) {
        if (this.machineSelect.length > 0) {
          const ids = []
          this.machineSelect.forEach(row => {
            ids.push(row.id)
          })
          const o = {}
          o.machineIds = ids
          o.upgrade = upgrade
          updateBatchMachine(o).then((res) => {
            if (res.data.code === 0) {
              this.getList()
              this.$notify({
                title: '成功',
                message: '更新成功',
                type: 'success',
                duration: 2000
              })
            } else {
              this.$message.error(res.data.msg)
            }
          })
        } else {
          this.$message.error('请选择列表项!')
        }
      }
    }
  }
</script>
