<template>
  <div class="app-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.name')" v-model="listQuery.name" size="mini"></el-input>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini"
                 @click="handleFilter">{{$t('table.search')}}
      </el-button>

      <el-button class="filter-item" type="success" icon="el-icon-delete" size="mini"
                 @click="$refs['gameSelectList'].handleAssignCourseGame()">{{$t('table.add')}}
      </el-button>

      <el-button class="filter-item" type="danger" icon="el-icon-delete" size="mini"
                 @click="handleRemoveCourseGames()">{{$t('table.remove')}}
      </el-button>

      <el-button v-if="showAddDetailButton" class="filter-item" type="success" icon="el-icon-edit" size="mini"
                 @click="$refs['gameDetailOperations'].handleGameDetailCreate()">{{$t('table.addOptions')}}
      </el-button>
    </div>

    <el-table :data="gameList" v-loading="tableLoading" height="650" ref="gameTable"
              @selection-change="handleGameSelectionChange" element-loading-text="loading"
              size="mini" border fit highlight-current-row>
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column align="center" label="序号" width="80">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="名称" width="120">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="游戏信息" width="120">
        <template slot-scope="scope">
          {{ scope.row.info }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="图片地址" width="">
        <template slot-scope="scope">
          <img class="user-avatar" v-if="scope.row.imageUrl" :src="scope.row.imageUrl+'?imageView2/1/w/40/h/40'">
        </template>
      </el-table-column>
      <el-table-column align="center" label="音频地址" width="">
        <template slot-scope="scope">
          {{ scope.row.resourceUrl }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="状态" width="80">
        <template slot-scope="scope">
          {{ [statusOptions, scope.row.status ] | keyValueFilter }}
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                     :current-page="listQuery.page" :page-sizes="[10, 20, 30, 40]"
                     :page-size="listQuery.limit" :total="total"
                     layout="total, sizes, prev, pager, next, jumper"></el-pagination>
    </div>

    <game-select-list ref="gameSelectList" :course-id="courseId" @refresh-list="fetchCourseGame"/>

  </div>
</template>
<script>
  import { getCourseGames, removeCourseGames } from '@/api/course'
  import { trueFalseOptions, statusOptions, gameTypeOptions, keyValueFilter } from '@/utils/constant'
  import GameSelectList from './gameList'

  export default {
    components: { GameSelectList },
    name: 'multipleChoiceGame',
    created() {
      this.courseId = Number(this.$route.params && this.$route.params.id)
      this.fetchCourseGame()
    },
    data() {
      return {
        courseId: null,
        expandRowKeys: [],
        gameTypeOptions: gameTypeOptions,
        trueFalseOptions: trueFalseOptions,
        statusOptions: statusOptions,
        gameSelected: [],
        gameList: [],
        gameDetailList: [],
        total: 0,
        listQuery: {
          name: null,
          page: 1,
          limit: 20,
          type: 0
        },
        tableLoading: false,
        showAddDetailButton: false,
        rightAnswerNumber: null, // 记录正确答案个数
        tempSelectionId: null
      }
    },
    filters: {
      keyValueFilter
    },
    methods: {
      fetchCourseGame() {
        if (this.courseId !== null) {
          this.listQuery.courseId = this.courseId
          this.tableLoading = true
          getCourseGames(this.listQuery).then((res) => {
            this.tableLoading = false
            if (res.data.code === 0) {
              this.gameList = res.data.courseGame.list
              this.total = res.data.courseGame.totalCount
            }
          })
        }
      },
      handleFilter() {
        this.listQuery.page = 1
        this.fetchCourseGame()
      },
      handleRemoveCourseGames() {
        if (this.gameSelected.length > 0) {
          const ids = []
          this.gameSelected.forEach(row => {
            ids.push(row.courseGameId)
          })
          this.removeCourseGames(ids)
        } else {
          this.$message.error('请选择列表项!')
        }
      },
      removeCourseGames(ids) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          removeCourseGames(ids).then((res) => {
            if (res.data.code === 0) {
              this.fetchCourseGame()
              this.$notify({
                title: '成功',
                message: '移除成功',
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
      handleGameSelectionChange(val) {
        this.gameSelected = val
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.fetchCourseGame()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.fetchCourseGame()
      }
    }
  }

</script>
