<template>
  <div class="app-container">
    <el-dialog width="60%" title="选择游戏" :visible.sync="dialogFormVisible">
      <div class="filter-container">
        <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                  :placeholder="$t('table.title')" v-model="listQuery.name" size="mini"/>
        <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini"
                   @click="handleFilter">{{$t('table.search')}}
        </el-button>
        <el-button class="filter-item" type="success" icon="el-icon-edit" size="mini"
                   @click="handleSave">{{$t('table.confirm')}}
        </el-button>
      </div>

      <el-table :data="gameList" v-loading="tableLoading" height="500" ref="gameListTable"
                @selection-change="handleGameSelectionChange" element-loading-text="loading"
                size="mini" border fit highlight-current-row>
        <el-table-column type="selection" width="55"/>
        <el-table-column align="center" label="序号" width="80">
          <template slot-scope="scope">
            {{ scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column align="center" label="名称" width="200">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </template>
        </el-table-column><!--
        <el-table-column v-show="false" align="center" label="游戏信息" width="120">
          <template slot-scope="scope">
            {{ scope.row.info }}
          </template>
        </el-table-column>-->
        <el-table-column align="center" label="游戏图片" width="80">
          <template slot-scope="scope">
            <img class="user-avatar" v-if="scope.row.imageUrl" :src="scope.row.imageUrl+'?imageView2/1/w/40/h/40'">
          </template>
        </el-table-column>
        <el-table-column v-show="false" align="center" label="音频" width="">
          <template slot-scope="scope">
            {{ decodeURI(scope.row.resourceUrl) }}
          </template>
        </el-table-column><!--
        <el-table-column v-show="false" align="center" label="状态" width="80">
          <template slot-scope="scope">
            {{ [statusOptions, scope.row.status ] | keyValueFilter }}
          </template>
        </el-table-column>-->
        <!--<el-table-column align="center" label="类型" width="80">
          <template slot-scope="scope">
            {{ [gameTypeOptions, scope.row.type ] | keyValueFilter }}
          </template>
        </el-table-column>-->
      </el-table>

      <div class="pagination-container">
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                       :current-page="listQuery.page" :page-sizes="[10, 20, 30, 40]"
                       :page-size="listQuery.limit" :total="total"
                       layout="total, sizes, prev, pager, next, jumper"/>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import { getGameList } from '@/api/game'
  import { assignGame } from '@/api/course'
  import { trueFalseOptions, statusOptions, gameTypeOptions, keyValueFilter } from '@/utils/constant'

  export default {
    name: 'gameSelectList',
    props: {
      courseId: { // 传入 courseId 则当前为子组件 部分功能需要隐藏
        type: Number,
        default: null
      },
      type: {
        type: Number,
        default: 0
      }
    },
    computed: {
      getCourseId() {
        return this.courseId
      },
      getType() {
        return this.type
      }
    },
    data() {
      return {
        dialogFormVisible: false,
        expandRowKeys: [],
        gameTypeOptions: gameTypeOptions,
        trueFalseOptions: trueFalseOptions,
        statusOptions: statusOptions,
        gameSelected: [],
        gameList: [],
        total: 0,
        listQuery: {
          name: null,
          page: 1,
          limit: 20,
          courseId: null
        },
        tableLoading: false
      }
    },
    filters: {
      keyValueFilter
    },
    methods: {
      fetchData() {
        this.tableLoading = true
        this.listQuery.courseId = this.getCourseId
        this.listQuery.type = this.getType
        getGameList(this.listQuery).then((res) => {
          this.tableLoading = false
          if (res.data.code === 0) {
            this.gameList = res.data.game.list
            this.total = res.data.game.totalCount
          }
        })
      },
      handleAssignCourseGame() {
        this.fetchData()
        this.dialogFormVisible = true
      },
      handleFilter() {
        this.listQuery.page = 1
        this.fetchData()
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.fetchData()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.fetchData()
      },
      handleSave() {
        if (this.gameSelected.length > 0) {
          const params = {}
          const ids = []
          this.gameSelected.forEach(row => {
            ids.push(row.id)
          })
          params.courseId = this.getCourseId
          params.gameIds = ids
          assignGame(params).then((res) => {
            if (res.data.code === 0) {
              this.fetchData()
              this.$emit('refresh-list')
              this.$notify({
                title: '成功',
                type: 'success',
                message: '操作成功',
                duration: 2000
              })
              this.dialogFormVisible = false
            } else {
              this.$message.error(res.data.msg)
            }
          })
        } else {
          this.$message.error('请选择列表项!')
        }
      },
      handleGameSelectionChange(val) {
        this.gameSelected = val
      }
    }
  }

</script>
