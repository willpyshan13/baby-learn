<template>
  <div class="app-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <span v-if="currentClassName !== 'undefined'" class="current-class-name">当前班级 :&nbsp;{{ currentClassName }}</span>
    </div>
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.name')" v-model="listQuery.courseName" size="mini"></el-input>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">
        {{$t('table.search')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;"
                 @click="$refs['courseSelectList'].handleAssignedCourse()" type="primary" size="mini"
                 icon="el-icon-edit">{{$t('table.assignCourses')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="handleDelete" type="danger" size="mini"
                 icon="el-icon-edit">{{$t('table.delete')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="handleUpdateOpenCourseScheduled" type="success" size="mini"
                 icon="el-icon-document">{{$t('table.openCourseSchedule')}}
      </el-button>


    </div>

    <el-table :data="courseList" v-loading.body="classCourseLoading" height="650" element-loading-text="Loading"
              size="small"
              @selection-change="handleCancelCourseSelectionChange" border fit highlight-current-row>
      <el-table-column align="center" label='ID' width="95">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="课程名称" width="150" align="center">
        <template slot-scope="scope">
          {{scope.row.courseName}}
        </template>
      </el-table-column>
      <el-table-column label="课程阶段" width="70" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.stageName}}</span>
        </template>
      </el-table-column>
      <el-table-column label="课程状态" width="70" align="center">
        <template slot-scope="scope">
          {{ [statusOptions, scope.row.status] | keyValueFilter}}
        </template>
      </el-table-column>
      <el-table-column label="课程类型" width="70" align="center">
        <template slot-scope="scope">
          {{ [courseType, scope.row.type] | keyValueFilter}}
        </template>
      </el-table-column>
      <el-table-column label="课程时长" width="70" align="center">
        <template slot-scope="scope">
          {{scope.row.duration}}
        </template>
      </el-table-column>
      <el-table-column label="课程序号" width="70" align="center">
        <template slot-scope="scope">
          {{scope.row.serialNumber}}
        </template>
      </el-table-column>
      <el-table-column label="观看权限" width="" align="center">
        <template slot-scope="scope">
          {{ [viewingAuthority, scope.row.viewingAuthority] | keyValueFilter}}
        </template>
      </el-table-column>
      <el-table-column label="内容类别" width="70" align="center" show-overflow-tooltip="">
        <template slot-scope="scope">
          {{scope.row.category}}
        </template>
      </el-table-column>
      <el-table-column label="视频地址" width="" align="center" show-overflow-tooltip>
        <template slot-scope="scope">
          {{scope.row.videoUrl}}
        </template>
      </el-table-column>
      <el-table-column label="图片地址" width="" align="center" show-overflow-tooltip="">
        <template slot-scope="scope">
          <span>{{scope.row.imageUrl}}</span>
        </template>
      </el-table-column>
      <el-table-column label="tts文本" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.ttsText}}
        </template>
      </el-table-column>
      <el-table-column label="单词" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.word}}
        </template>
      </el-table-column>
      <el-table-column label="是否开放" width="" align="center">
        <template slot-scope="scope">
          <el-button v-if="scope.row.opened === 1" size="mini" type="success"
                     @click="handleModifyOpenStatus(scope.row,0)">{{$t('table.opened')}}
          </el-button>
          <el-button v-if="scope.row.opened === 0 || !scope.row.opened" size="mini" type="danger"
                     @click="handleModifyOpenStatus(scope.row,1)">{{$t('table.closed')}}
          </el-button>
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

    <course-stage-select-list ref="courseSelectList" :height="450" :class-id="classId" @refresh-list="fetchClassCourse"/>

  </div>
</template>
<script>
  import { classCourse, cancelClassCourse, updateClassCourseStatus, generateWeekReport } from '@/api/classInfo'
  import { statusOptions, courseType, viewingAuthority, keyValueFilter } from '@/utils/constant'
  import CourseStageSelectList from './courseStageList'
  import { updateOpenCourseScheduled } from '@/api/scheduler'

  export default {
    components: { CourseStageSelectList },
    name: 'classCourseOperations',
    created() {
      this.classId = Number(this.$route.params && this.$route.params.id)
      this.currentClassName = String(this.$route.params && this.$route.params.currentClassName)
      this.fetchClassCourse()
    },
    data() {
      return {
        currentClassName: '',
        statusOptions: statusOptions,
        courseType: courseType,
        viewingAuthority: viewingAuthority,
        classId: null,
        listQuery: {
          classId: null,
          courseName: null,
          page: 1,
          limit: 10
        },
        total: null,
        courseList: null,
        classCourseLoading: false,
        multipleCancelCourseSelection: []
      }
    },
    filters: {
      keyValueFilter,
      openedFilter(type) {
        const openMap = {
          1: '开放',
          0: '关闭'
        }
        return openMap[type]
      }
    },
    methods: {
      fetchClassCourse() {
        if (this.classId != null) {
          this.listQuery.classId = this.classId
          this.classCourseLoading = true
          classCourse(this.listQuery).then(res => {
            if (res.data.code === 0) {
              this.courseList = res.data.classCourseList.list
              this.total = res.data.classCourseList.totalCount
              this.classCourseLoading = false
            } else {
              this.$message.error(res.data.msg)
            }
          })
        }
      },
      handleDelete() {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if (this.multipleCancelCourseSelection.length > 0) {
            const classCourseIds = []
            let i = 0
            this.multipleCancelCourseSelection.forEach(row => {
              classCourseIds[i++] = row.classCourseId
            })
            const params = {}
            params.classCourseIds = classCourseIds

            cancelClassCourse(params).then((response) => {
              if (response.data.code === 0) {
                this.$notify({
                  title: '成功',
                  message: '删除成功',
                  type: 'success',
                  duration: 2000
                })
                this.fetchClassCourse()
              }
            })
          } else {
            this.$message.error('请选择课程')
          }
        }).catch(() => {

        })
      },
      handleFilter() {
        this.listQuery.page = 1
        this.fetchClassCourse()
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.fetchClassCourse()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.fetchClassCourse()
      },
      handleCancelCourseSelectionChange(val) {
        this.multipleCancelCourseSelection = val
      },
      handleModifyOpenStatus(row, status) {
        const temp = {
          id: row.classCourseId,
          classId: this.classId,
          status: status
        }
        updateClassCourseStatus(temp).then((res) => {
          if (res.data.code === 0) {
            row.opened = status
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
      handleUpdateOpenCourseScheduled() {
        updateOpenCourseScheduled(this.classId).then((res) => {
          if (res.data.code === 0) {
            this.$notify({
              title: '成功',
              message: res.data.msg,
              type: 'success',
              duration: 2000
            })
          } else {
            this.$message.error(res.data.mesg)
          }
        })
      },
      handleGenerateWeekReport() {
        let s = this.multipleCancelCourseSelection
        let i = 0
        const courseIds = []

        if (s.length !== 5) {
          this.$message.error('请选择五天的课程')
          return
        }

        // 希尔排序 根据课程序列号
        this.shellSortSerialNumber(s)

        s = this.multipleCancelCourseSelection

        // 判断课程是否连续
        let serialNumCnt = s[0].serialNumber - 1
        this.multipleCancelCourseSelection.forEach((row) => {
          serialNumCnt += 1
          courseIds[i++] = row.id
        })

        if (serialNumCnt !== s[s.length - 1].serialNumber) {
          this.$message.error('请选择连续课程')
          return
        }
        // 判断课程是否同一周
        const n = s[0].courseName.substring(s[0].courseName.length - 2)
        let error = false
        for (let i = 0; i < 5; i++) {
          if (i < 4 && s[i].courseName.indexOf(n) < 0) {
            error = true
          }
        }
        if (error) {
          this.$message.error('请选择同一周的课程')
          return
        }

        const params = {}
        params.courseIds = courseIds
        params.classId = this.classId

        generateWeekReport(params).then((response) => {
          if (response.data.code === 0) {
            this.$notify({
              title: '成功',
              message: '生成成功',
              type: 'success',
              duration: 2000
            })
            this.fetchClassCourse()
          }
        })
        console.log(this.multipleCancelCourseSelection)
      },
      shellSortSerialNumber(arr) {
        const len = arr.length
        let gap = Math.ceil(len / 2)
        let i, k, j, temp
        while (gap > 0) {
          for (k = 0; k < gap; k++) {
            const tagArr = []
            tagArr.push(arr[k])
            for (i = k + gap; i < len; i = i + gap) {
              temp = arr[i]
              tagArr.push(temp)
              for (j = i - gap; j > -1; j = j - gap) {
                if (arr[j].serialNumber > temp.serialNumber) {
                  arr[j + gap] = arr[j]
                } else {
                  break
                }
              }
              arr[j + gap] = temp
            }
          }
          gap = parseInt(gap / 2)
        }
        this.multipleCancelCourseSelection = arr
      }
    }
  }
</script>
