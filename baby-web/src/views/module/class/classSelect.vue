<template>
  <el-dialog width="70%" title="班级选择" :visible.sync="dialogFormVisible">

    <div class="app-container calendar-list-container">
      <div class="filter-container">
        <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                  :placeholder="$t('table.name')" v-model="listQuery.className" size="mini"/>
        <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">
          {{$t('table.search')}}
        </el-button>
        <el-button class="filter-item" style="margin-left: 10px;" @click="confirm" type="primary"
                   size="mini"
                   icon="el-icon-edit">{{$t('table.confirm')}}
        </el-button>
      </div>

      <el-table :data="list" v-loading.body="listLoading" height="500" element-loading-text="Loading"
                size="small" border fit highlight-current-row
                @selection-change="handleMultipleSelect"
      >
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column align="center" label='ID' width="95">
          <template slot-scope="scope">
            {{scope.$index + 1}}
          </template>
        </el-table-column>
        <el-table-column label="班级名称" width="120" align="center">
          <template slot-scope="scope">
            {{scope.row.className}}
          </template>
        </el-table-column>
        <el-table-column label="班级介绍" width="" align="center">
          <template slot-scope="scope">
            <span>{{scope.row.classInfo}}</span>
          </template>
        </el-table-column>
        <el-table-column label="教师" width="110" align="center">
          <template slot-scope="scope">
            {{scope.row.teacherName}}
          </template>
        </el-table-column>
        <el-table-column label="课程阶段" width="110" align="center">
          <template slot-scope="scope">
            {{scope.row.stageName}}
          </template>
        </el-table-column>
        <el-table-column label="班级人数" width="80" align="center">
          <template slot-scope="scope">
            {{scope.row.count}}
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
  import { classInfoList } from '@/api/classInfo'

  export default {
    name: 'classSelect',
    props: {
      isSingle: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        multipleSelected: [],
        dialogFormVisible: false,
        list: null,
        listLoading: true,
        total: null,
        listQuery: {
          className: null,
          page: 1,
          limit: 10
        }
      }
    },
    methods: {
      getList() {
        this.listLoading = true
        classInfoList(this.listQuery).then(response => {
          this.list = response.data.classInfoList.list
          this.total = response.data.classInfoList.totalCount
          this.listLoading = false
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
      handleShowList() {
        this.dialogFormVisible = true
        this.getList()
      },
      handleHiddenList() {
        this.dialogFormVisible = false
      },
      handleMultipleSelect(val) {
        this.multipleSelected = val
      },
      singleSelect() {
        if (this.multipleSelected.length !== 1) {
          this.$message.error('请选择一条数据!')
          return false
        } else {
          const o = {}
          o.id = this.multipleSelected[0].id
          o.name = this.multipleSelected[0].className
          this.$emit('input', o)
          this.dialogFormVisible = false
        }
      },
      confirm() {
        if (this.isSingle) {
          this.singleSelect()
        }
      }
    }
  }
</script>
