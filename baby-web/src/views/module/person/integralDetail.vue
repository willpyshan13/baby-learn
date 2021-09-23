<template>
  <el-dialog width="" :title="dialogStatus" :visible.sync="dialogFormVisible">
    <div class="app-container calendar-list-container">
      <div class="filter-container">
        <el-select clearable filterable class="filter-item" v-model="listQuery.source" placeholder="请选择积分来源" size="mini">
          <el-option v-for="item in pointsSource" :key="item.key" :label="item.display_name"
                     :value="item.key">
          </el-option>
        </el-select>
        <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}</el-button>
      </div>

      <el-table :data="list" v-loading.body="listLoading"  height="500"
                element-loading-text="Loading" size="mini" border fit highlight-current-row
                show-summary
                :summary-method="getSummaries">
        <el-table-column align="center" label='ID' width="95">
          <template slot-scope="scope">
            {{scope.$index + 1}}
          </template>
        </el-table-column>
        <el-table-column label="积分类型" width="" align="center">
          <template slot-scope="scope">
            {{ [pointsType, scope.row.type ] | keyValueFilter }}
          </template>
        </el-table-column>
        <el-table-column label="数量" width="140" align="center" prop="number">
          <template slot-scope="scope">
            {{scope.row.number}}
          </template>
        </el-table-column>
        <el-table-column label="来源" width="" align="center">
          <template slot-scope="scope">
            {{ [pointsSource, scope.row.source ] | keyValueFilter }}
          </template>
        </el-table-column>
        <el-table-column label="获得时间" width="140" align="center">
          <template slot-scope="scope">
            {{scope.row.createTime }}
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
  </el-dialog>
</template>

<script>
  import { getIntegralList } from '@/api/integral'
  import { pointsType, pointsSource, keyValueFilter, durationFilter } from '@/utils/constant'

  export default {
    name: 'integralDetail',
    props: {
      personId: {
        type: Number,
        default: null
      }
    },
    computed: {
      getPersonId() {
        return this.personId
      }
    },
    data() {
      return {
        pointsSource: pointsSource,
        pointsType: pointsType,
        list: null,
        listLoading: true,
        total: null,
        listQuery: {
          personId: null,
          type: null,
          source: null,
          startTime: null,
          endTime: null,
          page: 1,
          limit: 10
        },
        dialogFormVisible: false,
        dialogStatus: ''
      }
    },
    filters: {
      keyValueFilter,
      durationFilter
    },
    methods: {
      handleShow(personId, type) {
        this.listQuery.personId = personId
        this.listQuery.type = type
        if (type === 1) {
          this.dialogStatus = '蛋糕-积分明细'
        } else if (type === 2) {
          this.dialogStatus = '糖果-积分明细'
        }
        this.dialogFormVisible = true
        this.getIntegralList()
      },
      getIntegralList() {
        this.listLoading = true
        getIntegralList(this.listQuery).then(res => {
          if (res.data.code === 0) {
            this.list = res.data.integral.list
            this.total = res.data.integral.totalCount
          } else {
            this.$message.error(res.data.msg)
          }
          this.listLoading = false
        })
      },
      handleFilter() {
        this.listQuery.page = 1
        this.getIntegralList()
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.getIntegralList()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.getIntegralList()
      },
      getSummaries(param) {
        const { columns, data } = param
        const sums = []
        columns.forEach((column, index) => {
          if (index === 0) {
            sums[index] = '合计'
            return
          }
          const values = data.map(item => Number(item[column.property]))
          if (!values.every(value => isNaN(value))) {
            sums[index] = values.reduce((prev, curr) => {
              const value = Number(curr)
              if (!isNaN(value)) {
                return prev + curr
              } else {
                return prev
              }
            }, 0)
            sums[index] += ' 个'
          } else {
            sums[index] = 'N/A'
          }
        })

        return sums
      }
    }
  }
</script>
