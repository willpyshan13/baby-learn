<template>
  <div class="app-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.roomName')" v-model="listQuery.searchKeys" size="mini"></el-input>
      <el-select clearable filterable  class="filter-item" v-model="listQuery.classId" placeholder="请选择班级" @change="handleFilter" size="mini">
        <el-option v-for="item in classList" :key="item.id" :label="item.className" :value="item.id"/>
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">
        {{$t('table.search')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="$refs['roomOperations'].handleCreate()"
                 type="primary" size="mini"
                 icon="el-icon-edit">{{$t('table.add')}}
      </el-button>
    </div>

    <el-table :data="list" v-loading.body="listLoading" height="650" width="100%" element-loading-text="Loading"
              size="mini" border fit highlight-current-row
              @expand-change="handleExpandChange" :row-key="handleRowKey" :expand-row-keys="expandRowKeys" >
      <!--:default-sort = "{prop: 'createTime', order: 'descending'}"-->
      <!--@sort-change="sortTable"-->
      <el-table-column type="expand">

        <template slot-scope="scope">
          <el-form label-position="left" inline class="demo-table-expand">

            <el-form-item label="教师地址">
              <span>{{scope.row.teacherUrl}}</span>
              <el-button size="mini" type="info" icon="document" @click='handleCopy(scope.row.teacherUrl,$event)'>复制
              </el-button>
            </el-form-item>
            <el-form-item label="学员地址">
              <span>{{scope.row.confuserUrl}}</span>
            </el-form-item>
            <el-form-item label="教室类型">
              <span>{{ [roomType, scope.row.roomType] | keyValueFilter }}</span>
            </el-form-item>
            <el-form-item label="开启音视频">
              <span>{{ [ yesNoStatusOptions, scope.row.autoOpenAv ] | keyValueFilter }}</span>
            </el-form-item>
            <el-form-item label="分辨率">
              <span>{{ [ videoType, scope.row.videoType ] | keyValueFilter }}</span>
            </el-form-item>
            <el-form-item label="视频帧率">
              <span>{{ [ videoFrameRate, scope.row.videoFrameRate ] | keyValueFilter }}</span>
            </el-form-item>
            <el-form-item label="上课教师">
              <span>{{ teacherName }}</span>
            </el-form-item>
            <el-form-item label="上课班级">
              <span>{{ className }}</span>
            </el-form-item>
            <el-form-item label="创建时间">
              <span>{{ scope.row.createTime }}</span>
            </el-form-item>
          </el-form>
        </template>


      </el-table-column>
      <el-table-column align="center" label='序号' width="50">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column align="center" width="95" label="教室状态">
        <template slot-scope="scope">
          <el-tag :type="[ scope.row.startTime,scope.row.endTime ] | roomStatusIconFilter" size="mini">
            {{ [ scope.row.startTime,scope.row.endTime ] | roomStatusFilter }}
          </el-tag>
        </template>
      </el-table-column>


      <el-table-column label="教室编号" width="100" align="center">
        <template slot-scope="scope">
          {{scope.row.id}}
        </template>
      </el-table-column>
      <el-table-column label="教室名称" width="200" align="center">
        <template slot-scope="scope">
          {{scope.row.roomName}}
        </template>
      </el-table-column>
      <el-table-column label="上课时间" width="300" align="center">
        <template slot-scope="scope">
          {{ [ scope.row.startTime, scope.row.endTime ] | timeFilter }}
        </template>
      </el-table-column>
      <el-table-column label="需要密码" width="75" align="center">
        <template slot-scope="scope">
          {{ [ passwordRequired, scope.row.passwordRequired ] | keyValueFilter}}
        </template>
      </el-table-column>
      <el-table-column label="教室密码" align="center">
        <el-table-column label="老师密码" width="75" align="center" show-overflow-tooltip>
          <template slot-scope="scope">
            {{scope.row.chairmanPwd}}
          </template>
        </el-table-column>

        <el-table-column label="助教密码" width="75" align="center" show-overflow-tooltip>
          <template slot-scope="scope">
            {{scope.row.assistantPwd}}
          </template>
        </el-table-column>

        <el-table-column label="学员密码" width="75" align="center" show-overflow-tooltip>
          <template slot-scope="scope">
            {{scope.row.confuserPwd}}
          </template>
        </el-table-column>

        <el-table-column label="巡课密码" width="75" align="center" show-overflow-tooltip>
          <template slot-scope="scope">
            {{scope.row.patrolPwd}}
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column label="用户类型" width="75" align="center" show-overflow-tooltip>
        <template slot-scope="scope">
          {{ [ roomUserType, scope.row.userType ] | keyValueFilter}}
        </template>
      </el-table-column>
      <el-table-column align="center" :label="$t('table.actions')" width="400"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-if="roomStatusIconFilter(scope.row.startTime,scope.row.endTime) !== 'danger'" type="primary" size="mini" @click="$refs['roomOperations'].handleUpdate(scope.row)">
            {{$t('table.edit')}}
          </el-button>
          <!--<el-button size="small" type="success" @click="handlePushMsg(scope.row)">{{$t('table.push')}}</el-button>-->
          <router-link :to="'/school/room/courseware/'+ scope.row.id">
            <el-button class="filter-item" type="success" size="mini">{{$t('table.courseware')}}</el-button>
          </router-link>
          <router-link :to="'/school/room/recording/'+ scope.row.id">
            <el-button class="filter-item" type="info" size="mini">{{$t('table.recordings')}}</el-button>
          </router-link>
          <el-button size="mini" type="danger" @click="$refs['roomOperations'].handleDelete(scope.row)">
            {{$t('table.delete')}}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="listQuery.pageNum"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="listQuery.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>

    <room-operations ref="roomOperations" @refresh-list="getList"/>
  </div>
</template>

<script>
  import clip from '@/utils/clipboard' // use clipboard directly
  import clipboard from '@/directive/clipboard/index.js' // use clipboard by v-directive
  import { parseTime } from '@/utils/index'
  import { roomList, pushStartLearningMsg, getRoom } from '@/api/room'
  import { roomUserType, roomType, yesNoStatusOptions, videoType, passwordRequired, videoFrameRate, keyValueFilter } from '@/utils/constant'
  import RoomOperations from './roomOperations'
  import { classInfoList } from '@/api/classInfo'

  export default {
    name: 'Room',
    components: {
      RoomOperations
    },
    directives: {
      clipboard
    },
    data() {
      return {
        classList: [],
        expandRowKeys: [],
        teacherName: null,
        className: null,
        roomUserType: roomUserType,
        videoType: videoType,
        roomType: roomType,
        yesNoStatusOptions: yesNoStatusOptions,
        passwordRequired: passwordRequired,
        videoFrameRate: videoFrameRate,
        activeName: 'roomInfo',
        list: null, // 教室列表
        listLoading: false, // 加载图标
        total: null, // 总页数
        listQuery: { // 查询参数
          searchKeys: null,
          page: 1,
          limit: 10
        },
        roomId: null,
        teacherUrl: null
      }
    },
    filters: {
      keyValueFilter,
      timeFilter(time) {
        const startTime = parseTime(time[0], '{y}年{m}月{d}日 {h}:{i}')
        const endTime = parseTime(time[1], '{y}年{m}月{d}日 {h}:{i}')

        const startDate = startTime.substring(0, 11)
        const endDate = endTime.substring(0, 11)

        if (startDate === endDate) {
          return startDate + startTime.substring(11) + ' ~ ' + endTime.substring(11)
        } else {
          return startTime + ' ~ ' + endTime
        }
      },
      roomStatusFilter(time) {
        const now = Date.now()
        const b = time[0].replace(/-/g, '/')
        const e = time[1].replace(/-/g, '/')
        const bs = Date.parse(b)
        const es = Date.parse(e)
        let statusStr
        if (es < now) {
          // 已过期
          statusStr = '直播结束'
        } else if (bs > now) {
          // 未开始
          statusStr = '尚未开始'
        } else {
          // 可使用
          statusStr = '正在直播'
        }
        return statusStr
      },
      roomStatusIconFilter(time) {
        const now = Date.now()
        const b = time[0].replace(/-/g, '/')
        const e = time[1].replace(/-/g, '/')
        const bs = Date.parse(b)
        const es = Date.parse(e)
        let statusStr
        if (es < now) {
          // 已过期
          statusStr = 'danger'
        } else if (bs > now) {
          // 未开始
          statusStr = 'info'
        } else {
          // 可使用
          statusStr = 'success'
        }
        return statusStr
      }
    },
    created() {
      this.getList()
      this.getClassInfoList()
    },
    methods: {
      getClassInfoList() {
        const query = {}
        classInfoList(query).then(res => {
          if (res.data.code === 0) {
            this.classList = res.data.classInfoList.list
          } else {
            this.$message.error(res.data.msg)
          }
        })
      },
      roomStatusIconFilter(begin, end) {
        const now = Date.now()
        const b = begin.replace(/-/g, '/')
        const e = end.replace(/-/g, '/')
        const bs = Date.parse(b)
        const es = Date.parse(e)
        let statusStr
        if (es < now) {
          // 已过期
          statusStr = 'danger'
        } else if (bs > now) {
          // 未开始
          statusStr = 'info'
        } else {
          // 可使用
          statusStr = 'success'
        }
        return statusStr
      },
      handleExpandChange(row, expandedRows) {
        // 只展开一行 判断是否至少含有一个正确答案
        if (expandedRows.length !== 0) { // 展开
          this.expandRowKeys = []
          this.expandRowKeys.push(row.id)
          getRoom(Number(row.id)).then((res) => {
            if (res.data.code === 0) {
              this.teacherName = res.data.room.teacherName
              this.className = res.data.room.className
            } else {
              this.$message.error(res.data.msg)
            }
          })
        } else { // 关闭
          this.expandRowKeys = []
        }
      },
      handleRowKey(row) {
        return row.id
      },
      handleCopy(text, event) {
        clip(text, event)
      },
      getList() {
        this.listLoading = true
        // this.listQuery.orderField = 'start_time'
        // this.listQuery.order = 'desc'
        roomList(this.listQuery).then(response => {
          if (response.data.code === 0) {
            this.list = response.data.roomList.list
            this.total = response.data.roomList.total
            this.listLoading = false
          } else {
            this.$message.error(response.data.msg)
          }
        })
      },
      handlePushMsg(row) {
        pushStartLearningMsg(row.id).then(res => {
          if (res.data.code === 0) {
            if (res.data.result) {
              this.$notify({
                title: '成功',
                message: res.data.msg,
                type: 'success',
                duration: 2000
              })
            } else {
              this.$notify({
                title: '失败',
                message: res.data.msg,
                type: 'error',
                duration: 2000
              })
            }
            this.listLoading = false
          } else {
            this.$message.error(res.data.msg)
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
      handleCourseware(row) {
        this.activeName = 'roomCourseware'
        this.roomId = row.id
      },
      handleRecordings(row) {
        this.activeName = 'roomRecordings'
        this.roomId = row.id
      }
    }
  }
</script>
<style>
  .demo-table-expand .el-form-item{
    margin-right: 0;
    margin-bottom: 0;
    width: 45%;
  }
  .demo-table-expand label{
    width: 100px;
    color: #99a9bf;
  }
</style>
