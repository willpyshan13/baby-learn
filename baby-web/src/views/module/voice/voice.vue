<template>
  <div class="app-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.name')" v-model="listQuery.name" size="mini"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini"
                 @click="handleFilter">{{$t('table.search')}}
      </el-button>
      <el-button class="filter-item" type="success" icon="el-icon-edit" size="mini"
                 @click="$refs['voiceOperation'].handleCreate()">{{$t('table.add')}}
      </el-button>

      <el-button class="filter-item" type="danger" icon="el-icon-delete" size="mini"
                 @click="$refs['voiceOperation'].handleDelete()">{{$t('table.delete')}}
      </el-button>
    </div>

    <el-table :data="gameList" v-loading="tableLoading" height="650" ref="gameTable"
              @selection-change="handleGameSelectionChange" element-loading-text="loading"
              size="mini" border fit highlight-current-row>

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
      <el-table-column align="center" label="图片地址" width="">
        <template slot-scope="scope">
          <img class="user-avatar" v-if="scope.row.imageUrl" :src="scope.row.imageUrl+'?imageView2/1/w/40/h/40'">
        </template>
      </el-table-column>
      <el-table-column align="center" label="音频地址" width="">
        <template slot-scope="scope">
          {{ decodeURI(scope.row.resourceUrl) }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="状态" width="80">
        <template slot-scope="scope">
          {{ [statusOptions, scope.row.status ] | keyValueFilter }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="120">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="$refs['voiceOperation'].handleUpdate(scope.row)">{{$t('table.edit')}}</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                     :current-page="listQuery.page" :page-sizes="[10, 20, 30, 40]"
                     :page-size="listQuery.limit" :total="total"
                     layout="total, sizes, prev, pager, next, jumper"/>
    </div>

    <voice-operations ref="voiceOperation" @refresh-list="fetchGameData" :select-options="this.gameSelected"/>

  </div>
</template>
<script>
  import { getGameList } from '@/api/game'
  import { trueFalseOptions, statusOptions, gameTypeOptions, keyValueFilter } from '@/utils/constant'
  import VoiceOperations from './voiceOperation'

  export default {
    name: 'SpeakUp',
    components: {
      VoiceOperations
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
          type: 1
        },
        tableLoading: false,
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
        this.tableLoading = true
        getGameList(this.listQuery).then((res) => {
          this.tableLoading = false
          if (res.data.code === 0) {
            this.gameList = res.data.game.list
            this.total = res.data.game.totalCount
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
