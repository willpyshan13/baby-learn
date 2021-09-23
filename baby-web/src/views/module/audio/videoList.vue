<template>
  <el-dialog width="60%" title="选择视频"  :visible.sync="selectDialogFormVisible">
    <div>
      <div class="filter-container">
        <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                  :placeholder="$t('table.name')" v-model="listQuery.videoUrlDesc" size="mini"/>
        <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}
        </el-button>
        <el-button class="filter-item" style="margin-left: 10px;" @click="handleSave" type="primary" size="mini"
                   icon="el-icon-edit">{{$t('table.confirm')}}
        </el-button>
      </div>

      <el-table :data="videoList" v-loading="tableLoading"  height="500"
                @selection-change="handleVideoSelectionChange" element-loading-text="Loading" size="mini" border fit highlight-current-row>
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column align="center" label='ID' width="95">
          <template slot-scope="scope">
            {{scope.$index + 1}}
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
        <el-table-column align="center" label="视频地址" width="450">
          <template slot-scope="scope">
            <el-row style="margin-left: 10px;">
              <el-col :span="20" align="left">
                <span>{{decodeURI(scope.row.videoWebUrl)}}</span>
              </el-col>
              <el-col :span="2" align="rigth">
                <el-button size="mini" round align="right">
                  <a :href="decodeURI(scope.row.videoWebUrl)" target="_blank">
                    <span>查看{{cartoonId}}</span>
                  </a>
                </el-button>
              </el-col>
            </el-row>
          </template>
        </el-table-column>
        <el-table-column align="center" label="格式" width="80">
          <template slot-scope="scope">
            {{scope.row.videoFormat}}
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
  </el-dialog>
</template>
<script>
  import { getVideoList } from '@/api/video'
  import { updateVideoId } from '@/api/cartoon'

  import { videoStatus, keyValueFilter } from '@/utils/constant'

  const videoObject = {
    videoUrlId: null,
    status: 0,
    videoFormat: 0,
    videoId: null,
    videoPlayUrl: '',
    videoUrlDesc: '',
    videoUrlIndex: 0,
    videoWebUrl: ''
  }
  export default {
    name: 'videoSelectList',
    props: {
      cartoonId: {
        type: Number,
        default: null
      }
    },
    data() {
      return {
        videoStatus: videoStatus,
        video: Object.assign({}, videoObject),
        listQuery: {
          videoUrlDesc: null,
          emptyVideoId: true,
          videoId: null,
          status: null,
          page: 1,
          limit: 10
        },
        total: 0,
        videoList: [],
        tableLoading: false,
        videoSelection: [],
        selectDialogFormVisible: false
      }
    },
    created() {
      // this.fetchData()
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
      fetchData() {
        this.tableLoading = true
        getVideoList(this.listQuery).then((res) => {
          if (res.data.code === 0) {
            this.videoList = res.data.video.list
            this.total = res.data.video.totalCount
            this.tableLoading = false
          } else {
            this.$message.error(res.data.msg)
          }
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
      resetForm() {
        this.video = {
          videoUrlId: null,
          status: 0,
          videoFormat: 0,
          videoId: null,
          videoPlayUrl: '',
          videoUrlDesc: '',
          videoUrlIndex: 0,
          videoWebUrl: ''
        }
      },
      handleAssignVideo() { // 指定视频
        this.fetchData()
        this.selectDialogFormVisible = true
      },
      handleVideoSelectionChange(val) {
        this.videoSelection = val
      },
      handleSave() {
        if (this.videoSelection.length > 0) {
          const params = {}
          const ids = []
          this.videoSelection.forEach(row => {
            ids.push(row.videoUrlId)
          })
          params.id = this.cartoonId
          params.videoUrlIds = ids
          updateVideoId(params).then((res) => {
            if (res.data.code === 0) {
              this.fetchData()
              this.$emit('refresh-list')
              this.$notify({
                title: '成功',
                type: 'success',
                message: '操作成功',
                duration: 2000
              })
            } else {
              this.$message.error(res.data.msg)
            }
          })
        } else {
          this.$message.error('请选择列表项!')
        }
      }
    }
  }

</script>
