<template>
  <div class="app-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" :placeholder="$t('table.userName')" v-model="listQuery.userName" size="mini"></el-input>
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" :placeholder="$t('table.mobileNumber')" v-model="listQuery.mobilePhone" size="mini"></el-input>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}</el-button>
    </div>

    <el-table :data="list" v-loading.body="listLoading"  height="650"
              element-loading-text="Loading" size="mini" border fit highlight-current-row>
      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="姓名" width="" align="center">
        <template slot-scope="scope">
          {{ scope.row.userName }}
        </template>
      </el-table-column>
      <el-table-column label="电话" width="140" align="center" prop="number">
        <template slot-scope="scope">
          {{scope.row.mobilePhone }}
        </template>
      </el-table-column>
      <el-table-column label="年龄" width="" align="center">
        <template slot-scope="scope">
          {{ scope.row.age }}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="" align="center">
        <template slot-scope="scope">
          {{ scope.row.createTime }}
        </template>
      </el-table-column>
      <el-table-column v-if="true" align="center" :label="$t('table.actions')" width="340" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="danger"
                     @click="handleDeleteById(scope.row.id)">{{$t('table.delete')}}
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
  import { getCasualUserList, deleteCasualUser } from '@/api/person'

  export default {
    name: 'CasualPerson',
    created() {
      this.fetchDataList()
    },
    data() {
      return {
        list: null,
        listLoading: true,
        total: null,
        listQuery: {
          name: null,
          mobilePhone: '',
          page: 1,
          limit: 10
        }
      }
    },
    filters: {
    },
    methods: {
      fetchDataList() {
        this.listLoading = true
        getCasualUserList(this.listQuery).then(res => {
          if (res.data.code === 0) {
            this.list = res.data.casualUserList.list
            this.total = res.data.casualUserList.totalCount
          } else {
            this.$message.error(res.data.msg)
          }
          this.listLoading = false
        })
      },
      handleDeleteById(id) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteCasualUser(id).then(res => {
            if (res.data.code === 0) {
              this.fetchDataList()
              this.$notify({
                title: '成功',
                message: '删除成功',
                type: 'success',
                duration: 2000
              })
            } else {
              this.$message.error(res.data.msg)
            }
            this.listLoading = false
          })
        }).catch(() => {

        })
      },
      handleFilter() {
        this.listQuery.page = 1
        this.fetchDataList()
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.fetchDataList()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.fetchDataList()
      }
    }
  }
</script>
