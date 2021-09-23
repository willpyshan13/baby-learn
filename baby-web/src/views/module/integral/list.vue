<template>
    <div class="app-container el-tabs--border-card" style="margin: 20px">
      <div class="filter-container">
        <el-select clearable filterable class="filter-item" v-model="listQuery.type" placeholder="请选择积分类型" size="mini">
          <el-option v-for="item in pointsType" :key="item.key" :label="item.display_name"
                     :value="item.key">
          </el-option>
        </el-select>
        <el-select clearable filterable class="filter-item" v-model="listQuery.source" placeholder="请选择积分来源" size="mini">
          <el-option v-for="item in pointsSource" :key="item.key" :label="item.display_name"
                     :value="item.key">
          </el-option>
        </el-select>
        <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}</el-button>
        <router-link :to="'/integral/exchange/'+ listQuery.personId">
          <el-button class="filter-item" type="primary" icon="el-icon-edit" size="mini">
            {{$t('table.add')}}
          </el-button>
        </router-link>

      </div>

      <el-table :data="list" v-loading.body="listLoading"  height="650"
                element-loading-text="Loading" size="mini" border fit highlight-current-row
                >

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
            <span v-if="scope.row.source === 9 && scope.row.number > 0">-{{scope.row.number}}</span>
            <span v-else>{{scope.row.number}}</span>
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

        <el-table-column align="center" :label="$t('table.actions')" width="340" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button v-if="false" class="filter-item" type="danger" size="mini" @click="handleDelete(scope.row.id)">
              {{$t('table.delete')}}
              <!--scope.row.source === 9-->
            </el-button>
            <router-link :to="'/integral/exchange/'+ listQuery.personId + '/' + scope.row.exchangeDetailId">
              <el-button v-if="scope.row.exchangeDetailId" class="filter-item" type="primary" size="mini">
                {{$t('table.detail')}}
              </el-button>
            </router-link>
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
  import { getIntegralList, deleteIntegralDetail } from '@/api/integral'
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
    created() {
      this.listQuery.personId = this.$route.params && this.$route.params.id
      this.getIntegralList()
    },
    data() {
      return {
        pointsSource: pointsSource,
        pointsType: pointsType,
        exchangeDetail: {},
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
        }
      }
    },
    filters: {
      keyValueFilter,
      durationFilter
    },
    methods: {
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
      handleDelete(id) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteIntegralDetail(id).then(res => {
            if (res.data.code === 0) {
              this.getIntegralList()
              this.$notify({
                title: '成功',
                message: '新增成功',
                type: 'success',
                duration: 2000
              })
            } else {
              this.$message.error(res.data.msg)
            }
          })
        }).catch(() => {

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
      }
    }
  }
</script>
