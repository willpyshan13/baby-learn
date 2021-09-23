<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input
        v-model="classInfoQuery.mixedField"
        placeholder="关键字"
        size="mini"
        class="filter-item"
        style="width: 150px;"
        @keyup.enter.native="handleSearchClassInfo"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleSearchClassInfo">搜索</el-button>
      <router-link :to="'/class/infos/add'">
        <el-button
          class="filter-item"
          type="primary"
          icon="el-icon-edit"
          size="mini">
          <span>添加</span>
        </el-button>
      </router-link>
    </div> <!-- operator button end -->
    <el-table
      v-loading="classInfoTableLoading"
      :data="classInfoList"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
      height="650"
      size="mini">
      <el-table-column align="center" label="序号" width="95">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="班级名称" width="" align="">
        <template slot-scope="scope">
          <span>{{ scope.row.className }}</span>
        </template>
      </el-table-column>
      <el-table-column label="班级介绍" width="" align="">
        <template slot-scope="scope">
          <span>{{ scope.row.classInfo }}</span>
        </template>
      </el-table-column>
      <el-table-column label="班级教师" width="" align="">
        <template slot-scope="scope">
          <span>{{ scope.row.teacherId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="课阶id" width="" align="">
        <template slot-scope="scope">
          <span>{{ scope.row.courseStageId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="班级人数" width="" align="">
        <template slot-scope="scope">
          <span>{{ scope.row.count }}</span>
        </template>
      </el-table-column>
      <el-table-column label="班主任" width="" align="">
        <template slot-scope="scope">
          <span>{{ scope.row.headerTeacher }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" width="" align="">
        <template slot-scope="scope">
          <span>{{ scope.row.remark }}</span>
        </template>
      </el-table-column>
      <el-table-column label="幼儿园id" width="" align="">
        <template slot-scope="scope">
          <span>{{ scope.row.kindergartenId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="二维码图片地址" width="" align="">
        <template slot-scope="scope">
          <span>{{ scope.row.qrCodePath }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="220" align="">
        <template slot-scope="scope">
          <el-button type="success" size="mini" @click="handleViewClassStudents(scope.row.id)">查看学生</el-button>
          <router-link :to="'/class/infos/edit/' + scope.row.id">
            <el-button
              class="filter-item"
              type="primary"
              size="mini">
              <span>修改</span>
            </el-button>
          </router-link>
          <el-button type="danger" size="mini" @click="handleDeleteClassInfo(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table> <!-- table data end -->
    <div class="pagination-container">
      <el-pagination
        :current-page="classInfoQuery.pageNum"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="classInfoQuery.pageSize"
        :total="classInfoTotal"
        layout="total, prev, pager, next, sizes, jumper"
        prev-text="上一页"
        next-text="下一页"
        @size-change="handleClassInfoListSizeChange"
        @current-change="handleClassInfoListCurrentChange"/>
    </div> <!-- pagination end -->

    <el-dialog width="60%" title="查看学生" :visible.sync="classStudentsDialogVisible" top="6vh">
      <el-tabs v-model="studentTableActiveName" @tab-click="handleStudentsTabClick" ref="studentTabs">
        <el-tab-pane label="已选学生" name="inClassStudent">
        </el-tab-pane>
        <el-tab-pane label="未选学生" name="outClassStudent">
        </el-tab-pane>
      </el-tabs>
    </el-dialog>
    <!-- student list end -->
  </div>
</template>

<script>
  import { getClassInfoList, deleteClassInfo } from '@/api/classInfo'
  import { classPerson, cancelClassStudent } from '@/api/classInfo'

  export default {
    data() {
      return {
        inClassStudentsQuery: {
          classId: null,
          mobileNumber: null,
          name: null,
          page: 1,
          limit: 10
        },
        studentTableActiveName: 'inClassStudent',
        classStudentsDialogVisible: false,
        // ------- student list end
        classInfoList: null,
        classInfoTableLoading: true,
        classInfoTotal: 0,
        classInfoQuery: {
          mixedField: null,
          pageNum: 1,
          pageSize: 10
        }
      }
    },
    created() {
      this.fetchClassInfoList()
    },
    methods: {
      fetchClassStudents(id) {
        if (id != null) {
          this.inClassStudentsQuery.classId = id
          // this.classPersonLoading = true
          classPerson(this.inClassStudentsQuery).then(response => {
            this.personList = response.data.classPersonList.list
            console.log(personList)
            // this.total = response.data.classPersonList.totalCount
            // this.classPersonLoading = false
          })
        }
      },
      handleViewClassStudents(id) {
        this.classStudentsDialogVisible = true
        this.fetchClassStudents(id)
      },
      handleStudentsTabClick() {

      },
      // ---------- student list end
      fetchClassInfoList() {
        this.classInfoTableLoading = true
        getClassInfoList(this.classInfoQuery).then(res => {
          res = res.data
          if (res.result === 0) {
            this.classInfoList = res.body.list
            this.classInfoTotal = res.body.total
          } else {
            this.$message.error(res.message)
          }
          this.classInfoTableLoading = false
        })
      },
      handleSearchClassInfo() {
        this.fetchClassInfoList()
      },
      handleDeleteClassInfo(id) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteClassInfo(id).then(res => {
            res = res.data
            if (res.result === 0) {
              this.$notify({
                title: '成功',
                message: '操作成功',
                type: 'success',
                duration: 2000
              })
              this.fetchClassInfoList()
            } else {
              this.$message.error(res.message)
            }
          })
        }).catch(() => {

        })
      },
      handleClassInfoListSizeChange(val) {
        this.classInfoQuery.pageSize = val
        this.fetchClassInfoList()
      },
      handleClassInfoListCurrentChange(val) {
        this.classInfoQuery.pageNum = val
        this.fetchClassInfoList()
      }
    }
  }
</script>
