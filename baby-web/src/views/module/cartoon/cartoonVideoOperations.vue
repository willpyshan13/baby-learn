<template>
  <div class="app-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.name')" v-model="listQuery.videoUrlDesc" size="mini"></el-input>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="$refs['videoOperations'].handleCreate()" type="primary" size="mini"
                 icon="el-icon-edit">{{$t('table.add')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="handleRemove" type="danger" size="mini"
                 icon="el-icon-delete">{{$t('table.remove')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="$refs['videoSelectList'].handleAssignVideo()" type="success  " size="mini"
                 icon="el-icon-erp-link">{{$t('table.assign')}}
      </el-button>
    </div>

    <el-table :data="videoList" v-loading="tableLoading"  height="650" ref="videoTable"
              @selection-change="handleVideoSelectionChange" element-loading-text="Loading" size="mini" border fit highlight-current-row>
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{ (scope.$index + 1) + ((listQuery.page - 1) * listQuery.limit) }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="名称" width="">
        <template slot-scope="scope">
          <template v-if="scope.row.edit">
            <el-input class="edit-input" size="mini" v-model="scope.row.videoUrlDesc" style="width: 50%"/>
            <el-button class='cancel-btn' size="mini" icon="el-icon-refresh" type="warning" @click="cancelEdit(scope.row)">{{$t('table.cancel')}}</el-button>
          </template>
          <span v-else>{{ scope.row.videoUrlDesc }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="视频状态" width="150">
        <template slot-scope="scope" >
          <el-tag :type="scope.row.status | statusFilter">{{ [ videoStatus, scope.row.status ] | keyValueFilter }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="缩略图地址" width="450">
        <template slot-scope="scope">
          <el-row style="margin-left: 10px;">
            <el-col :span="20" align="left">
              <span>{{decodeURI(scope.row.videoPlayUrl)}}</span>
            </el-col>
            <el-col :span="2" align="rigth">
              <el-button size="mini" round align="right">
                <a :href="decodeURI(scope.row.videoPlayUrl)" target="_blank">
                  <span>查看</span>
                </a>
              </el-button>
            </el-col>
          </el-row>
        </template>
      </el-table-column>
      <el-table-column align="center" label="视频地址" width="480">
        <template slot-scope="scope">
          <el-row style="margin-left: 10px;">
            <el-col :span="20" align="left">
              <span>{{decodeURI(scope.row.videoWebUrl)}}</span>
            </el-col>
            <el-col :span="2" align="rigth">
              <el-button size="mini" round align="right">
                <a :href="decodeURI(scope.row.videoWebUrl)" target="_blank">
                  <span>查看</span>
                </a>
              </el-button>
            </el-col>
          </el-row>
        </template>
      </el-table-column>
      <el-table-column align="center" label="序号" width="">
        <template slot-scope="scope">
          <el-button size="mini" type="success" @click="up(scope.$index + 1, scope.row)">上移</el-button>
          <el-button  size="mini" type="danger" @click="down(scope.$index + 1, scope.row)">下移</el-button>
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

    <video-operations ref="videoOperations" :cartoon-id="videoUrlId" @refresh-list="fetchCartoonVideoData"/>

    <video-select-list  ref="videoSelectList" :cartoon-id="videoUrlId" @refresh-list="fetchCartoonVideoData"/>

  </div>
</template>
<script>
  import { getVideoList, editVideo } from '@/api/video'
  import { removeVideoBatch } from '@/api/cartoon'
  import { videoStatus, keyValueFilter } from '@/utils/constant'
  import VideoSelectList from './videoList'
  import VideoOperations from '../video/videoOperations'

  export default {
    components: {
      VideoOperations,
      VideoSelectList
    },
    name: 'cartoonVideoOperations',
    created() {
      this.videoUrlId = Number(this.$route.params && this.$route.params.id)
      this.fetchCartoonVideoData()
    },
    data() {
      return {
        videoUrlId: null,
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
        videoSelection: []
      }
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
      handleFilter() {
        this.listQuery.page = 1
        this.fetchCartoonVideoData()
      },
      fetchCartoonVideoData() {
        if (this.videoUrlId !== null) {
          this.listQuery.videoId = this.videoUrlId
          this.listQuery.orderField = 'video_url_index'
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
        }
      },
      handleRemove() { // 移除动画下视频
        if (this.videoSelection.length > 0) {
          this.$confirm('此操作将删除数据, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            const ids = []
            this.videoSelection.forEach(row => {
              ids.push(row.videoUrlId)
            })
            removeVideoBatch(ids).then((res) => {
              if (res.data.code === 0) {
                this.fetchCartoonVideoData()
                if (res.data.errorList) {
                  this.$notify({
                    title: '警告',
                    message: '删除成功, 同步删除七牛云失败',
                    duration: 4000,
                    type: 'warning'
                  })
                } else {
                  this.$notify({
                    title: '成功',
                    type: 'success',
                    message: '移除成功',
                    duration: 2000
                  })
                }
              } else {
                this.$message.error(res.data.msg)
              }
            })
          }).catch(() => {

          })
        } else {
          this.$message.error('请选择列表项!')
        }
      },
      handleVideoSelectionChange(val) { // 多选赋值
        this.videoSelection = val
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.fetchCartoonVideoData()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.fetchCartoonVideoData()
      },
      up(idx, row) {
        const index = idx + (this.listQuery.page - 1) * this.listQuery.limit

        if (idx - 2 < 0) {
          if (row.videoUrlIndex > 1) {
            // 上一页最后一行
            const query = {}
            query.videoUrlIndex = index - 1
            query.videoId = this.listQuery.videoId
            getVideoList(query).then((res) => {
              if (res.data.code === 0 && res.data.video.totalCount === 1) {
                this.downRow(index, res.data.video.list[0])
              }
            })
            this.upRow(index - 1, row)
          }
        } else {
          const preRow = this.$refs['videoTable'].data[idx - 2]
          this.downRow(index, preRow)
          this.upRow(index - 1, row)
        }
      },
      down(idx, currentRow) {
        const index = idx + (this.listQuery.page - 1) * this.listQuery.limit
        console.log(idx)
        if (idx === (this.listQuery.limit * this.listQuery.page)) {
          if (currentRow.videoUrlIndex < this.total) {
            // 下一页第一行
            const query = {}
            query.videoUrlIndex = index + 1
            query.videoId = this.listQuery.videoId
            getVideoList(query).then((res) => {
              if (res.data.code === 0 && res.data.video.totalCount === 1) {
                this.upRow(index, res.data.video.list[0])
              }
            })
            this.downRow(index + 1, currentRow)
          }
        } else {
          const nextRow = this.$refs['videoTable'].data[idx]
          this.upRow(index, nextRow)
          this.downRow(index + 1, currentRow)
        }
      },
      updateRow(idx, row) {
        const tempData = Object.assign({}, row) // 对象拷贝
        tempData.videoUrlIndex = idx
        editVideo(tempData).then(res => {
          if (res.data.code === 0) {
            this.fetchCartoonVideoData()
          }
        })
      },
      upRow(idx, o) {
        this.updateRow(idx, o)
      },
      downRow(idx, o) {
        this.updateRow(idx, o)
      }
    }
  }
</script>
