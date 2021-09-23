<template>
  <div class="app-container el-tabs--border-card" style="margin: 20px">
    <el-table :data="list" v-loading.body="listLoading"  height="650"
              element-loading-text="Loading" size="mini" border fit highlight-current-row>
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="名称" width="120" align="center">
        <template slot-scope="scope">
          {{scope.row.recordtitle}}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="160" align="center">
        <template slot-scope="scope">
          {{ scope.row.starttime | parseTime }}
          <!--{{scope.row.starttime}}-->
        </template>
      </el-table-column>
      <el-table-column label="时长" width="120" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.https_playpath_mp4">{{ scope.row.duration | durationFilterSecond }}</span>
          <span v-else>{{ scope.row.duration | durationFilter }}</span>
        </template>
      </el-table-column>
      <el-table-column label="录制件大小" width="120" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.size | fileSizeFilter }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="播放地址" width="">
        <template slot-scope="scope">
          <el-row style="margin-left: 10px;">
            <el-col :span="14" align="left">
              <span v-if="scope.row.https_playpath_mp4">{{decodeURI(scope.row.https_playpath_mp4)}}</span>
              <span v-else>{{decodeURI(scope.row.https_playpath)}}</span>
            </el-col>
            <el-col :span="2" align="left">
              <el-button v-if="scope.row.https_playpath_mp4" size="mini" round align="right" @click="openWindow(scope.row.https_playpath_mp4, '12',1280,720)">
                <span>查看</span>
              </el-button>
              <el-button v-else size="mini" round align="right" @click="openWindow(scope.row.https_playpath, '12',1280,720)">
                <span>查看</span>
              </el-button>
            </el-col>
          </el-row>
        </template>
      </el-table-column>
      <!--<el-table-column label="播放地址" width="" align="center">-->
        <!--<template slot-scope="scope">-->
          <!--<span>{{scope.row.https_playpath}}</span>-->
        <!--</template>-->
      <!--</el-table-column>-->
    </el-table>

    <div class="pagination-container">
      <el-pagination
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
  import { listRecordings } from '@/api/room'
  import { parseTime } from '@/utils/index'
  import { openWindow } from '@/utils/openWindow'
  import { fileSizeFilter, durationFilter, durationFilterSecond } from '@/utils/constant'

  export default {
    name: 'roomRecordings',
    created() {
      this.roomId = Number(this.$route.params && this.$route.params.id)
      this.getList()
    },
    data() {
      return {
        roomId: null,
        list: null, // 录制件列表
        listLoading: false, // 加载图标
        total: null, // 总页数
        listQuery: { // 查询参数
          roomId: null,
          page: 1,
          limit: 10
        }
      }
    },
    filters: {
      parseTime,
      fileSizeFilter,
      durationFilter,
      durationFilterSecond
    },
    methods: {
      openWindow,
      getList() {
        if (this.roomId != null) {
          this.listLoading = true
          this.listQuery.roomId = this.roomeId
          listRecordings(this.roomId).then((res) => {
            if (res.data.code === 0) {
              this.list = res.data.recordings
              this.listLoading = false
            } else {
              this.$message.error(res.data.msg)
            }
          })
        }
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
      }
    }
  }
</script>
