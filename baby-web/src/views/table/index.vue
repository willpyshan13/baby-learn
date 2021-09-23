<template>
  <div class="app-container calendar-list-container">
     <div class="filter-container">
         <el-input style="width: 200px;" class="filter-item" :placeholder="$t('')"></el-input>
     </div>

    <el-table :data="list" v-loading.body="listLoading" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index}}
        </template>
      </el-table-column>
      <el-table-column label="标题" width="100" align="center">
        <template slot-scope="scope">
          {{scope.row.stageName}}
        </template>
      </el-table-column>
      <el-table-column label="描述" width="" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.description}}</span>
        </template>
      </el-table-column>
      <el-table-column label="阶段" width="80" align="center">
        <template slot-scope="scope">
          {{scope.row.level}}
        </template>
      </el-table-column>
      <el-table-column label="课时" width="80" align="center">
        <template slot-scope="scope">
          {{scope.row.spendTime}}
        </template>
      </el-table-column>
      <el-table-column label="课程数量" width="80" align="center">
        <template slot-scope="scope">
          {{scope.row.courseCount}}
        </template>
      </el-table-column>
      <el-table-column label="原价" width="80" align="center">
        <template slot-scope="scope">
          {{scope.row.originPrice}}
        </template>
      </el-table-column>
      <el-table-column label="优惠价" width="80" align="center">
        <template slot-scope="scope">
          {{scope.row.preferentialPrice}}
        </template>
      </el-table-column>
       <el-table-column label="已售数量" width="80" align="center">
        <template slot-scope="scope">
          {{scope.row.soldCount}}
        </template>
      </el-table-column>
      <el-table-column label="图片地址" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.imgUrl}}
        </template>
      </el-table-column>
      <el-table-column label="教师" width="110" align="center">
        <template slot-scope="scope">
          {{scope.row.teacherId}}
        </template>
      </el-table-column>
      <!-- 格式转换 -->
      <!-- <el-table-column class-name="status-col" label="Status" width="110" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{scope.row.status}}</el-tag>
        </template>
      </el-table-column> -->
    </el-table>

    <div class="pagination-container">
      <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="listQuery.page" :page-sizes="[2,4,6, 8]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>

  </div>
</template>

<script>
import { getList } from '@/api/table'

export default {
  data() {
    return {
      list: null,
      listLoading: true,
      total: null,
      listQuery: {
        page: 1,
        limit: 2
      }
    }
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getList(this.listQuery).then(response => {
        this.list = response.data.courseStageList.list
        this.total = response.data.courseStageList.totalCount
        this.listLoading = false
      })
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
