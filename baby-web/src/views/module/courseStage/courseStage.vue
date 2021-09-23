<template>
  <div class="app-container calendar-list-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.title')" v-model="listQuery.stageName" size="mini"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}
      </el-button>

      <router-link :to="'/school/course/stage/create'">
        <el-button class="filter-item" type="primary" icon="el-icon-edit" size="mini">
          {{$t('table.add')}}
        </el-button>
      </router-link>

      <!--<el-button class="filter-item" style="margin-left: 10px;" @click="handleCreate" type="primary" size="mini"-->
                 <!--icon="el-icon-edit">{{$t('table.add')}}-->
      <!--</el-button>-->
    </div>

    <el-table :data="list" v-loading.body="listLoading"  height="650" element-loading-text="Loading" size="mini" border fit highlight-current-row>
      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="是否出售" width="100" align="center">
        <template slot-scope="scope">
          {{ [ yesNoStatusOptions, scope.row.selling ] | keyValueFilter }}
        </template>
      </el-table-column>
      <el-table-column label="标题" width="100" align="center">
        <template slot-scope="scope">
          {{scope.row.stageName}}
        </template>
      </el-table-column>
      <el-table-column label="描述" width="" align="center" show-overflow-tooltip>
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
      <el-table-column label="图片地址" width="120" align="center">
        <template slot-scope="scope">
          <img class="user-avatar" v-if="scope.row.imgUrl" :src="scope.row.imgUrl+'?imageView2/1/w/40/h/40'">
        </template>
      </el-table-column>
      <el-table-column label="教师" width="110" align="center">
        <template slot-scope="scope">
          {{scope.row.teacherName}}
        </template>
      </el-table-column>
      <el-table-column align="center" :label="$t('table.actions')" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <!--<el-button type="primary" size="mini" @click="handleUpdate(scope.row)">{{$t('table.edit')}}</el-button>-->
          <router-link :to="'/school/course/stage/edit/'+ scope.row.id">
            <el-button class="filter-item" type="primary" size="mini">
              {{$t('table.edit')}}
            </el-button>
          </router-link>

          <el-button size="mini" type="danger"
                     @click="handleDelete(scope.row)">{{$t('table.delete')}}
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
  </div>
</template>

<script>
  import { courseStageList, deleteCourseStage } from '@/api/course'
  import { yesNoStatusOptions, keyValueFilter } from '@/utils/constant'

  export default {
    name: 'CourseStage',
    data() {
      return {
        yesNoStatusOptions: yesNoStatusOptions,
        list: null,
        parentMenuList: null,
        listLoading: false,
        total: null,
        listQuery: {
          stageName: null,
          page: 1,
          limit: 10
        }
      }
    },
    filters: {
      keyValueFilter
    },
    created() {
      this.getList()
    },
    methods: {
      getList() {
        this.listLoading = true
        courseStageList(this.listQuery).then(response => {
          this.list = response.data.courseStageList.list
          this.total = response.data.courseStageList.totalCount
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
      handleDelete(row) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteCourseStage(row.id).then(() => {
            this.getList()
            this.dialogFormVisible = false
            this.$notify({
              title: '成功',
              message: '删除成功',
              type: 'success',
              duration: 2000
            })
          })
        }).catch(() => {

        })
      }
    }
  }
</script>
