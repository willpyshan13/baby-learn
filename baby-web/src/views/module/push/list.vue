<template>
  <div class="app-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <!--<el-select clearable filterable class="filter-item" v-model="listQuery.type" placeholder="请选择积分类型" size="mini">-->
        <!--<el-option v-for="item in pointsType" :key="item.key" :label="item.display_name"-->
                   <!--:value="item.key">-->
        <!--</el-option>-->
      <!--</el-select>-->
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.name')" v-model="listQuery.name" size="mini"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}</el-button>
      <router-link :to="'/message/create'">
        <el-button class="filter-item" type="primary" icon="el-icon-edit" size="mini">
          {{$t('table.add')}}
        </el-button>
      </router-link>

    </div>

    <el-table :data="list" v-loading.body="listLoading"  height="650"
              element-loading-text="Loading" size="mini" border fit highlight-current-row>
      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <!--<el-table-column label="商品图片" width="120" align="center">-->
        <!--<template slot-scope="scope">-->
          <!--<img v-if="scope.row.imageUrl !== ''" v-bind:src="scope.row.imageUrl + '?v=' + (new Date()).valueOf()" v-bind:width="35" height="35"/>-->
        <!--</template>-->
      <!--</el-table-column>-->
      <el-table-column label="消息类型" width="" align="center">
        <template slot-scope="scope">
          {{ [ messageType, scope.row.type ] | keyValueFilter }}
        </template>
      </el-table-column>
      <el-table-column label="消息标题" width="140" align="center" prop="number">
        <template slot-scope="scope">
          {{scope.row.title }}
        </template>
      </el-table-column>
      <el-table-column label="消息内容" width="" align="center">
        <template slot-scope="scope">
          {{ scope.row.content }}
        </template>
      </el-table-column>
      <el-table-column label="推送数据" width="" align="center">
        <template slot-scope="scope">
          {{ scope.row.data }}
        </template>
      </el-table-column>
      <el-table-column label="推送类型" width="" align="center">
        <template slot-scope="scope">
          {{ [ pushType, scope.row.objectType ] | keyValueFilter }}
        </template>
      </el-table-column>
      <el-table-column label="接收对象" width="" align="center">
        <template slot-scope="scope">
          {{ scope.row.objectId }}
        </template>
      </el-table-column>
      <el-table-column label="推送时间" width="" align="center">
        <template slot-scope="scope">
          {{ scope.row.createTime }}
        </template>
      </el-table-column>
      <el-table-column v-if="true" align="center" :label="$t('table.actions')" width="340" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <router-link :to="'/message/edit/'+ scope.row.id">
            <el-button class="filter-item" type="primary" size="mini">
              {{$t('table.edit')}}
            </el-button>
          </router-link>

          <el-button size="mini" type="danger"
                     @click="handleDeleteById(scope.row.id)">{{$t('table.delete')}}
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
  import { getMessageList, deleteMessageById } from '@/api/message'
  import { messageType, pushType, keyValueFilter } from '@/utils/constant'

  export default {
    name: 'Message',
    created() {
      this.fetchDataList()
    },
    data() {
      return {
        messageType: messageType,
        pushType: pushType,
        list: null,
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
      keyValueFilter
      // durationFilter
    },
    methods: {
      fetchDataList() {
        this.listLoading = true
        getMessageList(this.listQuery).then(res => {
          if (res.data.code === 0) {
            this.list = res.data.messageList.list
            this.total = res.data.messageList.totalCount
          } else {
            this.$message.error(res.data.msg)
          }
          this.listLoading = false
        })
      },
      handleDeleteById(id) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteMessageById(id).then(res => {
            if (res.data.code === 0) {
              this.fetchDataList()
              this.$notify({
                title: '成功',
                message: '删除成功',
                type: 'success',
                duration: 2000
              })
            } else {
              this.$message.error(res.data.msg)
            }
            this.listLoading = false
          })
        }).catch(() => {

        })
      },
      handleFilter() {
        this.listQuery.page = 1
        this.fetchDataList()
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.fetchDataList()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.fetchDataList()
      }
    }
  }
</script>
