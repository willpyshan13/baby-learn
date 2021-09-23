<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input
        v-model="kindergartenInfoQuery.mixedField"
        placeholder="关键字"
        size="mini"
        class="filter-item"
        style="width: 150px;"
        @keyup.enter.native="handleSearchKindergartenInfo"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleSearchKindergartenInfo">搜索</el-button>
      <router-link :to="'/kindergarten/infos/create'">
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
      v-loading="kindergartenInfoTableLoading"
      :data="kindergartenInfoList"
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
      <el-table-column label="幼儿园名称" width="140" align="">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="幼儿园编号" width="120" align="">
        <template slot-scope="scope">
          <span>{{ scope.row.code }}</span>
        </template>
      </el-table-column>
      <el-table-column label="园长姓名" width="90" align="">
        <template slot-scope="scope">
          <span>{{ scope.row.director }}</span>
        </template>
      </el-table-column>
      <el-table-column label="联系电话" width="100" align="">
        <template slot-scope="scope">
          <span>{{ scope.row.mobilePhone }}</span>
        </template>
      </el-table-column>
      <el-table-column label="所在地区" width="" align="">
        <template slot-scope="scope">
          <span>{{ [areaJsonInfo, scope.row.areaCode ] | areaCodeFilter }}</span>
        </template>
      </el-table-column>
      <el-table-column label="详细地址" width="200" align="">
        <template slot-scope="scope">
          <span>{{ scope.row.address }}</span>
        </template>
      </el-table-column>
      <el-table-column label="销售人员" width="90" align="">
        <template slot-scope="scope">
          <span>{{ scope.row.salespersonName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150" align="">
        <template slot-scope="scope">
          <router-link :to="'/kindergarten/infos/edit/' + scope.row.id">
            <el-button
              class="filter-item"
              type="primary"
              size="mini">
              <span>修改</span>
            </el-button>
          </router-link>
          <el-button type="danger" size="mini" @click="handleDeleteKindergartenInfo(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table> <!-- table data end -->
    <div class="pagination-container">
      <el-pagination
        :current-page="kindergartenInfoQuery.pageNum"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="kindergartenInfoQuery.pageSize"
        :total="kindergartenInfoTotal"
        layout="total, prev, pager, next, sizes, jumper"
        prev-text="上一页"
        next-text="下一页"
        @size-change="handleKindergartenInfoListSizeChange"
        @current-change="handleKindergartenInfoListCurrentChange"/>
    </div> <!-- pagination end -->
  </div>
</template>

<script>
import { getKindergartenInfoList, deleteKindergartenInfo } from '@/api/kindergartenInfo'
import axios from 'axios'

export default {
  filters: {
    areaCodeFilter([array, value]) {
      let areaText = ''

      const pro = value.substring(0, 2) + '0000'
      const city = value.substring(0, 4) + '00'
      const block = value

      const proText = array[pro] ? array[pro] : ''
      const cityText = array[city] ? array[city] : ''
      const blockText = array[block] ? array[block] : ''

      areaText = areaText + proText
      if (proText !== cityText) {
        areaText = areaText + cityText
      }
      areaText = areaText + blockText

      return areaText
    }
  },
  data() {
    return {
      province: [],
      city: [],
      block: [],
      areaJsonInfo: [],
      kindergartenInfoList: null,
      kindergartenInfoTableLoading: true,
      kindergartenInfoTotal: 0,
      kindergartenInfoQuery: {
        mixedField: null,
        pageNum: 1,
        pageSize: 10
      }
    }
  },
  created() {
    this.fetchKindergartenInfoList()
    this.fetchAreaJsonInfo()
  },
  methods: {
    fetchAreaJsonInfo() {
      const that = this
      axios.get('../static/json/map.json').then(function(response) {
        if (response.status === 200) {
          that.areaJsonInfo = response.data
        }
      })
    },
    fetchKindergartenInfoList() {
      this.kindergartenInfoTableLoading = true
      getKindergartenInfoList(this.kindergartenInfoQuery).then(res => {
        res = res.data
        if (res.result === 0) {
          this.kindergartenInfoList = res.body.list
          this.kindergartenInfoTotal = res.body.total
        } else {
          this.$message.error(res)
        }
        this.kindergartenInfoTableLoading = false
      })
    },
    handleSearchKindergartenInfo() {
      this.fetchKindergartenInfoList()
    },
    handleDeleteKindergartenInfo(id) {
      this.$confirm('此操作将删除数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteKindergartenInfo(id).then(res => {
          res = res.data
          if (res.result === 0) {
            this.$notify({
              title: '成功',
              message: '操作成功',
              type: 'success',
              duration: 2000
            })
            this.fetchKindergartenInfoList()
          } else {
            this.$message.error(res.message)
          }
        })
      }).catch(() => {

      })
    },
    handleKindergartenInfoListSizeChange(val) {
      this.kindergartenInfoQuery.pageSize = val
      this.fetchKindergartenInfoList()
    },
    handleKindergartenInfoListCurrentChange(val) {
      this.kindergartenInfoQuery.pageNum = val
      this.fetchKindergartenInfoList()
    }
  }
}
</script>
