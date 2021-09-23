<template>
  <div class="app-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.title')" v-model="listQuery.videoTitle" size="mini"></el-input>
      <el-select clearable filterable class="filter-item" v-model="listQuery.typeId" placeholder="请选择类型" size="mini">
        <el-option v-for="item in videoTypeList" :key="item.typeId" :label="item.typeDesc"
                   :value="item.typeId">
        </el-option>
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">
        {{$t('table.search')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="$refs['cartoonOperations'].handleCreate()"
                 type="primary" size="mini"
                 icon="el-icon-edit">{{$t('table.add')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="$refs['cartoonOperations'].handleDelete()"
                 type="danger" size="mini"
                 icon="el-icon-delete">{{$t('table.delete')}}
      </el-button>
    </div>

    <el-table :data="cartoonList" v-loading.body="tableLoading" height="650"
              @selection-change="handleCartoonSelectionChange" element-loading-text="Loading" size="mini" border fit
              highlight-current-row>
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>

      <el-table-column align="center" label="标题" width="">
        <template slot-scope="scope">
          {{scope.row.videoTitle}}
        </template>
      </el-table-column>

      <el-table-column align="center" label="预览图" width="120">
        <template slot-scope="scope">
          <img class="user-avatar" v-if="scope.row.videoPreviewPicUrl"
               :src="scope.row.videoPreviewPicUrl+'?imageView2/1/w/40/h/40'">
        </template>
      </el-table-column>

      <el-table-column align="center" label="轮播图" width="120">
        <template slot-scope="scope">
          <img class="user-avatar" v-if="scope.row.videoWheelPicUrl"
               :src="scope.row.videoWheelPicUrl+'?imageView2/1/w/40/h/40'">
        </template>
      </el-table-column>

      <el-table-column align="center" label="简介" width="">
        <template slot-scope="scope">
          {{scope.row.videoBrief}}
        </template>
      </el-table-column>

      <el-table-column align="center" label="状态" width="80">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{ [ videoStatus, scope.row.status ] | keyValueFilter }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="类型" width="120">
        <template slot-scope="scope">
          <span>{{scope.row.typeDesc}}</span>
        </template>
      </el-table-column>

      <el-table-column align="left" label="操作" width="240">
        <template slot-scope="scope">
          <el-button v-if="scope.row.status === 2" size="mini" type="success" @click="handleModifyStatus(scope.row,0)">
            {{$t('table.effective')}}
          </el-button>
          <el-button v-if="scope.row.status === 0" size="mini" type="danger" @click="handleModifyStatus(scope.row,2)">
            {{$t('table.ineffective')}}
          </el-button>
          <el-button type="primary" size="small" @click="$refs['cartoonOperations'].handleUpdate(scope.row)">
            {{$t('table.edit')}}
          </el-button>
          <router-link :to="'/resource/cartoon/content/'+ scope.row.videoId">
            <el-button class="filter-item" type="info" size="small">{{$t('table.cartoonContent')}}</el-button>
          </router-link>
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

    <cartoon-operations ref="cartoonOperations" :cartoon-selection="cartoonSelection" @refresh-list="fetchData"/>

  </div>
</template>
<script>
  import { getCartoonList, editCartoon } from '@/api/cartoon'
  import { getVideoTypeList } from '@/api/video'
  import { videoStatus, keyValueFilter } from '@/utils/constant'
  import CartoonOperations from './cartoonOperations'
  import CartoonVideoOperations from './cartoonVideoOperations'

  export default {
    name: 'Cartoon',
    components: {
      CartoonVideoOperations,
      CartoonOperations
    },
    data() {
      return {
        cartoonId: null,
        activeName: 'cartoonList',
        videoStatus: videoStatus,
        listQuery: {
          videoBrief: null,
          videoYear: 0,
          page: 1,
          limit: 10
        },
        total: 0,
        cartoonList: [],
        videoTypeList: [],
        tableLoading: false,
        cartoonSelection: []
      }
    },
    created() {
      this.fetchData()
      this.getVideoTypeList()
    },
    filters: {
      keyValueFilter,
      statusFilter(status) {
        const statusMap = {
          0: 'success',
          2: 'info',
          1: 'danger'
        }
        return statusMap[status]
      }
    },
    methods: {
      getVideoTypeList() {
        const params = {
          typeParent: 1
        }
        getVideoTypeList(params).then((res) => {
          this.videoTypeList = res.data.videoType.list
        })
      },
      fetchData() {
        this.tableLoading = true
        getCartoonList(this.listQuery).then((response) => {
          this.cartoonList = response.data.video.list
          this.total = response.data.video.totalCount
          this.tableLoading = false
        })
      },
      handleFilter() {
        this.listQuery.page = 1
        this.fetchData()
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.fetchData()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.fetchData()
      },
      handleModifyStatus(row, status) {
        row.status = status
        const temp = Object.assign({}, row) // copy obj
        editCartoon(temp).then((res) => {
          if (res.data.code === 0) {
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
      handleCartoonSelectionChange(val) {
        this.cartoonSelection = val
      },
      handleCartoonVideo(row) {
        this.cartoonId = row.videoId
        this.activeName = 'videoList'
      }
    }
  }
</script>
