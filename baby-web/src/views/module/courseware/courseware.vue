<template>
  <div class="app-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.name')" v-model="listQuery.name" size="mini"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="$refs['coursewareOperations'].handleCreate()" type="primary" size="mini"
                 icon="el-icon-edit">{{$t('table.add')}}
      </el-button>
    </div>

    <el-table :data="list" v-loading.body="listLoading"  height="650" element-loading-text="Loading" size="mini" border fit highlight-current-row>
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
          {{ scope.row.size | fileSizeFilter }}
        </template>
      </el-table-column>
      <el-table-column align="center" :label="$t('table.actions')" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="$refs['coursewareOperations'].handleUpdate(scope.row)">{{$t('table.edit')}}</el-button>
          <el-button size="mini" type="danger"
                     @click="$refs['coursewareOperations'].handleDelete(scope.row)">{{$t('table.delete')}}
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

    <courseware-operations ref="coursewareOperations" @refresh-list="getList"/>

  </div>
</template>

<script>
import { coursewareList } from '@/api/courseware'
import { dynamicPPT, coursewareType, keyValueFilter, fileSizeFilter } from '@/utils/constant'
import CoursewareOperations from './coursewareOperations'

export default {
  name: 'Courseware',
  components: { CoursewareOperations },
  data() {
    return {
      coursewareType: coursewareType,
      dynamicPPT: dynamicPPT,
      list: null,
      parentMenuList: null,
      listLoading: true,
      total: null,
      listQuery: {
        name: null,
        page: 1,
        limit: 10
      }
    }
  },
  filters: {
    fileSizeFilter,
    keyValueFilter
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      coursewareList(this.listQuery).then(response => {
        this.list = response.data.coursewareList.list
        this.total = response.data.coursewareList.totalCount
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
    }
  }
}
</script>
