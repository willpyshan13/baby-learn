<template>
  <div class="app-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.name')" v-model="listQuery.name" size="mini"></el-input>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="$refs['coursewareOperations'].handleCreate()" type="primary" size="mini"
                 icon="el-icon-edit">{{$t('table.add')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="$refs['coursewareSelectList'].handleOpenCoursewareList()" type="primary" size="mini"
                 icon="el-icon-edit">{{$t('table.assignCourseware')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="handleCoursewareBatchDelete" type="danger" size="mini"
                 icon="el-icon-edit">{{$t('table.delete')}}
      </el-button>
    </div>

    <el-table :data="list" v-loading.body="listLoading"  height="610"
              @selection-change="handleCoursewareCancelSelectionChange"
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
      <el-table-column label="课件编号" width="120" align="center">
        <template slot-scope="scope">
          {{scope.row.id}}
        </template>
      </el-table-column>
      <el-table-column label="课件名称" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.name}}
        </template>
      </el-table-column>
      <el-table-column
        prop="tag"
        label="课件类型"
        width="100"
        :filters="[{ text: '视频', value: 1 }, { text: '音频', value: 2 }, { text: '文档', value: 3 }]"
        :filter-method="filterTag"
        filter-placement="bottom-end">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.type === 1 ? 'primary' : 'success'"
            disable-transitions>{{ [ coursewareType, scope.row.type ] | keyValueFilter }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="课件地址" width="" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.resourceUrl}}</span>
        </template>
      </el-table-column>
      <el-table-column label="课件属性" width="120" align="center">
        <template slot-scope="scope">
          {{ [ dynamicPPT, scope.row.dynamicPpt ] | keyValueFilter }}
        </template>
      </el-table-column>
      <el-table-column label="文件大小" width="120" align="center">
        <template slot-scope="scope">
          {{scope.row.size | fileSizeFilter }}
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

    <courseware-operations ref="coursewareOperations" :room-id="roomId" @refresh-list="getList"/>

    <courseware-select-list ref="coursewareSelectList" :room-id="roomId" @refresh-list="getList"/>
  </div>
</template>

<script>
import { roomCoursewareList, deleteRoomCourseware } from '@/api/room'

import { dynamicPPT, coursewareType, keyValueFilter, fileSizeFilter } from '@/utils/constant'
import CoursewareSelectList from './coursewareList'
import CoursewareOperations from '../courseware/coursewareOperations'

export default {
  components: {
    CoursewareOperations,
    CoursewareSelectList },
  name: 'roomCourseware',
  created() {
    this.roomId = Number(this.$route.params && this.$route.params.id)
    this.getList()
  },
  data() {
    return {
      roomId: null,
      coursewareType: coursewareType,
      dynamicPPT: dynamicPPT,
      list: null,
      listLoading: false,
      total: null,
      listQuery: {
        name: null,
        page: 1,
        limit: 10
      },
      multipleCoursewareCancelSelection: []
    }
  },
  filters: {
    keyValueFilter,
    fileSizeFilter
  },
  methods: {
    filterTag(value, row) {
      return row.type === value
    },
    getList() {
      if (this.roomId != null) {
        this.listLoading = true
        this.listQuery.roomId = this.roomId
        roomCoursewareList(this.listQuery).then(res => {
          if (res.data.code === 0) {
            this.list = res.data.coursewareList.list
            this.total = res.data.coursewareList.totalCount
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
    },
    handleCoursewareBatchDelete() {
      this.$confirm('此操作将删除数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (this.multipleCoursewareCancelSelection.length > 0) {
          const coursewareIds = []
          const roomCoursewareIds = []
          let i = 0
          this.multipleCoursewareCancelSelection.forEach(row => {
            coursewareIds[i++] = row.id
            roomCoursewareIds[i++] = row.roomCoursewareId
          })
          const params = {}
          params.roomId = this.roomId
          params.roomCoursewareIds = roomCoursewareIds
          params.coursewareIds = coursewareIds

          deleteRoomCourseware(params).then(() => {
            this.$notify({
              title: '成功',
              message: '删除成功',
              type: 'success',
              duration: 2000
            })
            this.getList()
          })
        } else {
          this.$message.warning('请选择课件')
        }
      }).catch(() => {

      })
    },
    handleCoursewareCancelSelectionChange(val) {
      this.multipleCoursewareCancelSelection = val
    }
  }
}
</script>
