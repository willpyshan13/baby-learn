<template>
  <div class="app-container calendar-list-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.title')" v-model="listQuery.title" size="mini"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}</el-button>
      <el-button class="filter-item" type="danger" icon="el-icon-search" size="mini" @click="handleBatchDelete">{{$t('table.delete')}}</el-button>
    </div>

    <el-table :data="list" v-loading.body="listLoading"  height="650" element-loading-text="Loading"
              @selection-change="handleArticleSelect"
              size="mini" border fit highlight-current-row>
      <el-table-column type="selection" width="55"/>

      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="标题" width="" align="left">
        <template slot-scope="scope">
          <a :href="'../../smart/s' + scope.row.url" target="_blank">{{scope.row.title}}</a>
          <el-button size="mini" type="success" align="right" icon="document" @click='handleCopy("http://www.smart-dog.cn/smart/s" + scope.row.url,$event)'>复制链接</el-button>
        </template>
      </el-table-column>
      <el-table-column label="分类" width="120" align="center">
        <template slot-scope="scope">
          {{scope.row.codeText}}
        </template>
      </el-table-column>
      <el-table-column label="重要性" width="180" align="center">
        <template slot-scope="scope">
          <el-rate
            v-model="scope.row.importance"
            disabled
            :max='5' :colors="['#99A9BF', '#F7BA2A', '#FF9900']" :low-threshold="2"
            :high-threshold="3"
            score-template="{value}">
          </el-rate>
        </template>
      </el-table-column>
      <el-table-column label="状态" width="120" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{ [ articleStatus, scope.row.status ] | keyValueFilter }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="180" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.createTime}}</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right"  align="center" :label="$t('table.actions')" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <router-link :to="'/article/edit/'+scope.row.id">
            <el-button type="primary" size="mini">{{$t('table.edit')}}</el-button>
          </router-link>

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
  import { getArticle, deleteArticleById, deleteBatchArticle } from '@/api/article'
  import { articleStatus, keyValueFilter } from '@/utils/constant'
  import clip from '@/utils/clipboard' // use clipboard directly
  import clipboard from '@/directive/clipboard/index.js' // use clipboard by v-directive

  export default {
    name: 'Article',
    directives: {
      clipboard
    },
    data() {
      return {
        articleStatus: articleStatus,
        articleSelected: [],
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
          2: 'danger'
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
        getArticle(this.listQuery).then(response => {
          if (response.data.code === 0) {
            this.list = response.data.articleDOList.list
            this.total = response.data.articleDOList.totalCount
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
      handleDelete(row) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteArticleById(row.id, row.url).then((res) => {
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
        }).catch(() => {

        })
      },
      handleArticleSelect(v) {
        this.articleSelected = v
      },
      handleBatchDelete() {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if (this.articleSelected.length > 0) {
            const ids = []
            this.articleSelected.forEach(row => {
              ids.push(row.id)
            })
            deleteBatchArticle(ids).then((res) => {
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
      handleCopy(text, event) {
        clip(text, event)
      }
    }
  }
</script>
