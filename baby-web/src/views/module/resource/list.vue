<template>
  <div class="app-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.name')" v-model="resourceQuery.name" size="mini"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}</el-button>
      <router-link :to="'/resource/resources/create'">
        <el-button class="filter-item" type="primary" icon="el-icon-edit" size="mini">
          {{$t('table.uploadResources')}}
        </el-button>
      </router-link>
    </div>

    <el-table :data="resourceList" v-loading.body="tableLoading"  height="650"
              element-loading-text="Loading" size="mini" border fit highlight-current-row>
      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="资源名称" width="180" align="center" prop="number">
        <template slot-scope="scope">
          {{scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="资源类型" width="95" align="center">
        <template slot-scope="scope">
          {{ [ resourceType, scope.row.type ] | keyValueFilter }}
        </template>
      </el-table-column>
      <el-table-column label="资源地址" width="" align="center">
        <template slot-scope="scope">
          {{ decodeURI(scope.row.url) }}
        </template>
      </el-table-column>
      <el-table-column v-if="true" align="center" :label="$t('table.actions')" width="340" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <router-link :to="'/resource/resources/edit/'+ scope.row.id">
            <el-button class="filter-item" type="primary" size="mini">
              {{$t('table.edit')}}
            </el-button>
          </router-link>
          <el-button size="mini" type="danger" @click="handleDeleteById(scope.row.id)">{{$t('table.delete')}}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="resourceQuery.pageNum"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="resourceQuery.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="resourceTotal">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import { getList, deleteResourceById } from '@/api/resource'
  import { resourceType, keyValueFilter } from '@/utils/constant'

  export default {
    name: 'Resource',
    created() {
      this.fetchResourceDataList()
    },
    data() {
      return {
        resourceType: resourceType,
        resourceList: null,
        resourceTotal: 0,
        tableLoading: true,
        resourceQuery: {
          name: null,
          type: null,
          pageNum: 1,
          pageSize: 10
        }
      }
    },
    filters: {
      keyValueFilter
    },
    methods: {
      fetchResourceDataList() {
        this.tableLoading = true
        getList(this.resourceQuery).then(res => {
          if (res.data.success) {
            this.resourceList = res.data.body.list
            this.resourceTotal = res.data.body.total
          } else {
            this.$message.error(res.data.message)
          }
          this.tableLoading = false
        })
      },
      handleDeleteById(id) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteResourceById(id).then(res => {
            if (res.data.success) {
              this.$notify({
                title: '成功',
                message: res.data.message,
                type: 'success',
                duration: 2000
              })
              this.fetchResourceDataList()
            } else {
              this.$message.error(res.data.msg)
            }
          })
        }).catch(() => {

        })
      },
      handleFilter() {
        this.resourceQuery.pageNum = 1
        this.fetchResourceDataList()
      },
      handleSizeChange(val) {
        this.resourceQuery.pageSize = val
        this.fetchResourceDataList()
      },
      handleCurrentChange(val) {
        this.resourceQuery.pageNum = val
        this.fetchResourceDataList()
      }
    }
  }
</script>
