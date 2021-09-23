<template>
  <div class="app-container calendar-list-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.name')" v-model="listQuery.videoUrlDesc" size="mini"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="$refs['videoOperations'].handleCreate()" type="primary" size="mini"
                 icon="el-icon-edit">{{$t('table.add')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="$refs['videoOperations'].handleDelete()" type="danger" size="mini"
                 icon="el-icon-delete">{{$t('table.delete')}}
      </el-button>
    </div>

    <el-table :data="videoList" v-loading="tableLoading"  height="650"
              @selection-change="handleVideoSelectionChange" element-loading-text="Loading" size="mini" border fit highlight-current-row>
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column align="center" width="95">
        <template slot-scope="scope">
          {{ (scope.$index + 1) + ((listQuery.page - 1) * listQuery.limit) }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="名称" width="220">
        <template slot-scope="scope">
          <template v-if="scope.row.edit">
            <el-input class="edit-input" size="mini" v-model="scope.row.videoUrlDesc" style="width: 50%"/>
            <el-button class='cancel-btn' size="mini" icon="el-icon-refresh" type="warning" @click="cancelEdit(scope.row)">{{$t('table.cancel')}}</el-button>
          </template>
          <span v-else>{{ scope.row.videoUrlDesc }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="视频状态" width="80">
        <template slot-scope="scope" >
          <el-tag :type="scope.row.status | statusFilter">{{ [ videoStatus, scope.row.status ] | keyValueFilter }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="缩略图地址" width="120">
        <template slot-scope="scope">
          <img class="user-avatar" v-if="scope.row.videoPlayUrl" :src="scope.row.videoPlayUrl+'?imageView2/1/w/40/h/40'">

          <!--<el-row style="margin-left: 10px;">-->
            <!--<el-col :span="20" align="left">-->
              <!--<span>{{decodeURI(scope.row.videoPlayUrl)}}</span>-->
            <!--</el-col>-->
            <!--<el-col :span="2" align="rigth">-->
              <!--<el-button size="mini" round align="right">-->
                <!--<a :href="decodeURI(scope.row.videoPlayUrl)" target="_blank">-->
                  <!--<span>查看</span>-->
                <!--</a>-->
              <!--</el-button>-->
            <!--</el-col>-->
          <!--</el-row>-->
        </template>
      </el-table-column>
      <el-table-column align="center" label="视频地址" width="">
        <template slot-scope="scope">
          <el-row style="margin-left: 10px;">
            <el-col :span="11" align="left">
              <span>{{decodeURI(scope.row.videoWebUrl)}}</span>
            </el-col>
            <el-col :span="2" align="left">
              <el-button size="mini" round align="right" @click="openWindow(scope.row.videoWebUrl, '12',1280,720)">
                <span>查看</span>
              </el-button>
            </el-col>
          </el-row>
        </template>
      </el-table-column>
      <el-table-column align="left" label="操作" width="180">
        <template slot-scope="scope">
          <el-button v-if="scope.row.status === 2" size="mini" type="success" @click="handleModifyStatus(scope.row,0)">{{$t('table.effective')}}
          </el-button>
          <el-button v-if="scope.row.status === 0" size="mini" type="danger" @click="handleModifyStatus(scope.row,2)">{{$t('table.ineffective')}}
          </el-button>

          <el-button v-if="scope.row.edit" type="success" @click="confirmEdit(scope.row)" size="mini" icon="el-icon-circle-check-outline">{{$t('table.confirm')}}</el-button>
          <el-button v-else type="primary" @click='scope.row.edit=!scope.row.edit' size="mini" icon="el-icon-edit">{{$t('table.edit')}}</el-button>
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

    <video-operations ref="videoOperations" :video-selection="this.videoSelection" @refresh-list="fetchData"/>

  </div>
</template>
<script>
  import { getVideoList, editVideo } from '@/api/video'
  import { videoStatus, keyValueFilter } from '@/utils/constant'
  import VideoOperations from './videoOperations'
  import { openWindow } from '@/utils/openWindow'

  export default {
    name: 'Video',
    components: {
      VideoOperations
    },
    data() {
      return {
        videoStatus: videoStatus,
        listQuery: {
          videoUrlDesc: null,
          videoId: null,
          status: null,
          page: 1,
          limit: 10
        },
        total: 0,
        videoList: [],
        tableLoading: false,
        selectDialogFormVisible: false,
        videoSelection: []
      }
    },
    created() {
      this.fetchData()
    },
    filters: {
      keyValueFilter,
      statusFilter(status) {
        const statusMap = {
          0: 'success',
          2: 'info',
          1: 'danger'
        }
        return statusMap[status]
      }
    },
    methods: {
      openWindow,
      fetchData() { // 获取视频信息
        this.tableLoading = true
        getVideoList(this.listQuery).then((response) => {
          const items = response.data.video.list
          this.videoList = items.map(v => {
            this.$set(v, 'edit', false) // https://vuejs.org/v2/guide/reactivity.html
            v.originalDesc = v.videoUrlDesc //  will be used when user click the cancel botton
            return v
          })
          // this.videoList = response.data.video.list
          this.total = response.data.video.totalCount
          this.tableLoading = false
        })
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
      confirmEdit(row) { // 确定修改信息
        row.edit = false
        row.originalDesc = row.videoUrlDesc
        const temp = Object.assign({}, row) // copy obj
        editVideo(temp).then((res) => {
          if (res.data.code === 0) {
            this.$message({
              message: '操作成功',
              type: 'success'
            })
          } else {
            this.$message({
              message: '操作失败',
              type: 'error'
            })
          }
        })
      },
      cancelEdit(row) {
        row.videoUrlDesc = row.originalDesc
        row.edit = false
      },
      handleModifyStatus(row, status) { // 修改视频状态
        row.status = status
        const temp = Object.assign({}, row) // copy obj
        editVideo(temp).then((res) => {
          if (res.data.code === 0) {
            this.$message({
              message: '操作成功',
              type: 'success'
            })
          } else {
            this.$message({
              message: '操作失败',
              type: 'error'
            })
          }
        })
      },
      handleVideoSelectionChange(val) { // 多选赋值
        this.videoSelection = val
      }
    }
  }
</script>
