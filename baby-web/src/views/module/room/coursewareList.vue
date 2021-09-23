<template>
  <div class="app-container">

    <el-dialog width="70%" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">

      <el-table :data="list" v-loading.body="listToRoomLoading"
                @selection-change="handleCoursewareSelectionChange" height="550"
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
        <el-table-column label="课件类型" width="120" align="center">
          <template slot-scope="scope">
            {{ [ coursewareType, scope.row.type ] | keyValueFilter }}
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
            {{scope.row.size}}
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

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">{{$t('table.cancel')}}</el-button>
        <el-button v-if="dialogStatus==='create'" type="primary" @click="assignCourseware">{{$t('table.confirm')}}</el-button>
      </div>
    </el-dialog>

  </div>
</template>
<script>
  import { addRoomCourseware } from '@/api/room'
  import { coursewareToRomm } from '@/api/courseware'
  import { dynamicPPT, coursewareType, keyValueFilter } from '@/utils/constant'

  export default {
    name: 'coursewareSelectList',
    props: {
      roomId: {
        type: Number,
        default: null
      }
    },
    computed: {
      getRoomId() {
        return this.roomId
      }
    },
    data() {
      return {
        coursewareType: coursewareType,
        dynamicPPT: dynamicPPT,
        list: null,
        listToRoomLoading: false,
        total: null,
        listQuery: {
          roomId: null,
          page: 1,
          limit: 10
        },
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '新增',
          assignCourseware: '关联课件'
        }
      }
    },
    filters: {
      keyValueFilter
    },
    methods: {
      handleOpenCoursewareList() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.getCoursewareListToRoom()
      },
      getCoursewareListToRoom() {
        this.listToRoomLoading = true
        this.listQuery.roomId = this.getRoomId
        coursewareToRomm(this.listQuery).then(response => {
          this.list = response.data.coursewareList.list
          this.total = response.data.coursewareList.totalCount
          this.listToRoomLoading = false
        })
      },
      assignCourseware() {
        if (this.multipleCoursewareSelection.length > 0) {
          const coursewareIds = []
          let i = 0
          this.multipleCoursewareSelection.forEach(row => {
            coursewareIds[i++] = row.id
          })
          const params = {}
          params.roomId = this.getRoomId
          params.coursewareIds = coursewareIds

          addRoomCourseware(params).then((res) => {
            if (res.data.code === 0) {
              this.$message.success('关联成功')
              this.getCoursewareListToRoom()
              this.$emit('refresh-list')
              this.dialogFormVisible = false
            } else {
              this.$message.error(res.data.msg)
            }
          })
        } else {
          this.$message.warning('请选择课件')
        }
      },
      handleCoursewareSelectionChange(val) {
        this.multipleCoursewareSelection = val
      },
      handleFilter() {
        this.listQuery.page = 1
        this.getCoursewareListToRoom()
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.getCoursewareListToRoom()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.getCoursewareListToRoom()
      }
    }
  }
</script>
