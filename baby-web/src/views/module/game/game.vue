<template>
  <div class="app-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.name')" v-model="listQuery.name" size="mini"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini"
                 @click="handleFilter">{{$t('table.search')}}
      </el-button>
      <el-button class="filter-item" type="success" icon="el-icon-edit" size="mini"
                 @click="$refs['gameOperations'].handleCreate()">{{$t('table.add')}}
      </el-button>

      <el-button class="filter-item" type="danger" icon="el-icon-delete" size="mini"
                 @click="$refs['gameOperations'].handleDelete()">{{$t('table.delete')}}
      </el-button>

      <el-button v-if="showAddDetailButton" class="filter-item" type="success" icon="el-icon-edit" size="mini"
                 @click="$refs['gameDetailOperations'].handleGameDetailCreate()">{{$t('table.addOptions')}}
      </el-button>
    </div>

    <el-table :data="gameList" v-loading="tableLoading" height="650" ref="gameTable"
              @selection-change="handleGameSelectionChange" element-loading-text="loading"
              @expand-change="handleExpandChange" :row-key="handleRowKey"
              :expand-row-keys="expandRowKeys"
              size="mini" border fit highlight-current-row>
      <el-table-column type="expand">
        <!-- 展开行 游戏详情 -->
        <template slot-scope="props">
          <el-table :data="gameDetailList" ref="gameDetailTable" width="60%" border fit highlight-current-row>
            <el-table-column align="center" label="序号" width="80">
              <template slot-scope="prop">
                <span>{{ prop.$index + 1 }}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" label="标题" width="80">
              <template slot-scope="prop">
                <span>{{prop.row.word}}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" label="是否正确答案" width="120">
              <template slot-scope="prop">
                <span>{{ [ trueFalseOptions, prop.row.rightAnswer ] | keyValueFilter }}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" label="播报内容" width="">
              <template slot-scope="prop">
                <span>{{prop.row.ttsText}}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" label="图片地址" width="">
              <template slot-scope="prop">
                <img class="user-avatar" v-if="prop.row.imageUrl" :src="prop.row.imageUrl+'?imageView2/1/w/40/h/40'">
              </template>
            </el-table-column>

            <el-table-column align="center" label="操作" width="240">
              <template slot-scope="prop">
                <el-button type="primary" size="mini" @click="$refs['gameDetailOperations'].handleGameDetailUpdate(prop.row)">{{$t('table.edit')}}
                </el-button>
                <el-button type="danger" size="mini" @click="$refs['gameDetailOperations'].handleGameDetailDelete(prop.row)">{{$t('table.remove')}}
                </el-button>
              </template>
            </el-table-column>

          </el-table>
        </template>
      </el-table-column>

      <el-table-column type="selection" width="55"/>
      <el-table-column align="center" label="序号" width="80">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="名称" width="180">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="游戏信息" width="180">
        <template slot-scope="scope">
          {{ scope.row.info }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="图片" width="120">
        <template slot-scope="scope">
          <img class="user-avatar" v-if="scope.row.imageUrl" :src="scope.row.imageUrl+'?imageView2/1/w/40/h/40'">
        </template>
      </el-table-column>
      <el-table-column align="center" label="音频地址" width="">
        <template slot-scope="scope">
          <span>{{decodeURI(scope.row.resourceUrl)}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="状态" width="80">
        <template slot-scope="scope">
          {{ [statusOptions, scope.row.status ] | keyValueFilter }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="120">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="$refs['gameOperations'].handleUpdate(scope.row)">{{$t('table.edit')}}</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                     :current-page="listQuery.page" :page-sizes="[10, 20, 30, 40]"
                     :page-size="listQuery.limit" :total="total"
                     layout="total, sizes, prev, pager, next, jumper"/>
    </div>

    <game-operations ref="gameOperations" @refresh-list="fetchGameData" :select-options="this.gameSelected"/>

    <game-detail-operations ref="gameDetailOperations" :game-id="this.expandRowKeys[0]" @refresh-list="fetchGameDetailData"/>

  </div>
</template>
<script>
  import { getGameList, getGameDetail } from '@/api/game'
  import { trueFalseOptions, statusOptions, gameTypeOptions, keyValueFilter } from '@/utils/constant'
  import GameOperations from './gameOperations'
  import GameDetailOperations from './gameDetailOperations'

export default {
    name: 'PlayGames',
    components: {
      GameDetailOperations,
      GameOperations
    },
    data() {
      return {
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
    created() {
      this.fetchGameData()
    },
    filters: {
      keyValueFilter
    },
    methods: {
      fetchGameData() {
        this.rightAnswerNumber = null
        this.tableLoading = true
        getGameList(this.listQuery).then((res) => {
          this.tableLoading = false
          if (res.data.code === 0) {
            this.gameList = res.data.game.list
            this.total = res.data.game.totalCount
          }
        })
      },
      fetchGameDetailData() {
        // 执行查询
        const params = {
          gameId: this.expandRowKeys[0]
        }
        getGameDetail(params).then((res) => {
          if (res.data.code === 0) {
            this.gameDetailList = res.data.detail
            this.rightAnswerNumber = 0
            this.gameDetailList.forEach(row => {
              if (row.rightAnswer) {
                this.rightAnswerNumber += 1
              }
            })
          } else {
            this.$message.error('获取详情失败')
          }
        })
      },
      handleFilter() {
        this.listQuery.page = 1
        this.fetchGameData()
      },
      handleGameSelectionChange(val) {
        this.gameSelected = val
      },
      handleExpandChange(row, expandedRows) {
        // 只展开一行 判断是否至少含有一个正确答案
        if (expandedRows.length !== 0) { // 展开
          if (this.rightAnswerNumber == null || this.rightAnswerNumber === 1) {
            this.tempSelectionId = row.id
            this.expandRowKeys = []
            this.expandRowKeys.push(row.id)
            this.fetchGameDetailData()
            this.showAddDetailButton = true
          } else {
            if (this.rightAnswerNumber > 1) {
              this.$message.error('只能有一个正确答案')
              this.expandRowKeys = []
              this.expandRowKeys.push(this.tempSelectionId)
            } else if (this.rightAnswerNumber < 1) {
              this.$message.error('请添加一个正确答案')
              this.expandRowKeys = []
              this.expandRowKeys.push(this.tempSelectionId)
            } else {
              this.rightAnswerNumber = null
            }
          }
        } else { // 关闭
          if (this.rightAnswerNumber > 1) {
            this.$message.error('只能有一个正确答案')
            this.expandRowKeys = []
            this.expandRowKeys.push(this.tempSelectionId)
          } else if (this.rightAnswerNumber < 1) {
            this.$message.error('请添加一个正确答案')
            this.expandRowKeys = []
            this.expandRowKeys.push(this.tempSelectionId)
          } else {
            this.showAddDetailButton = false
            this.rightAnswerNumber = null
          }
        }
      },
      handleRowKey(row) {
        return row.id
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.fetchGameData()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.fetchGameData()
      }
    }
  }

</script>
