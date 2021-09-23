<template>
  <div class="app-container calendar-list-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.keyWord')" v-model="listQuery.mixedField" size="mini"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">
        {{$t('table.search')}}
      </el-button>
      <el-button class="filter-item" type="success" icon="el-icon-search" size="mini" @click="handleAddAppInfo">
        {{$t('table.add')}}
      </el-button>
    </div>

    <el-table :data="list" v-loading.body="listLoading" height="650" element-loading-text="Loading"
              size="mini" border fit highlight-current-row>

      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="App名称" width="240" align="center">
        <template slot-scope="scope">
          {{scope.row.name}}
        </template>
      </el-table-column>
      <el-table-column label="App编码" width="240" align="center">
        <template slot-scope="scope">
          {{scope.row.code}}
        </template>
      </el-table-column>
      <el-table-column label="备注" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.remark}}
        </template>
      </el-table-column>

      <el-table-column align="center" :label="$t('table.actions')" width="200"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" @click="handleEditAppInfo(scope.row)">{{$t('table.update')}}
          </el-button>
          <el-button size="mini" type="danger" @click="handleDeleteAppInfo(scope.row)">{{$t('table.delete')}}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="listQuery.pageIndex"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="listQuery.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>

    <el-dialog width="" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="form-container" :rules="rules" ref="appInfoForm" :model="appInfo"
               label-width="85px" style='margin-left:20px;' size="mini">
        <el-form-item label="App名称:" prop="name">
          <el-input class="filter-item" placeholder="请输入" v-model="appInfo.name"/>
        </el-form-item>
        <el-form-item label="App编码:" prop="name">
          <el-input class="filter-item" placeholder="请输入" v-model="appInfo.code"/>
        </el-form-item>
        <el-form-item label="备注:" prop="remark">
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入备注"
            v-model="appInfo.remark">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button v-if="dialogStatus==='create'" type="primary" @click="createData">{{$t('table.push')}}
          </el-button>
          <el-button v-else type="primary" @click="updateData">{{$t('table.confirm')}}</el-button>
          <el-button @click="handleCancel">{{$t('table.cancel')}}</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import { getAppInfoList, addAppInfo, updateAppInfo, deleteAppInfo } from '@/api/app'

  const appInfoObject = {
    id: null,
    name: '',
    code: '',
    remark: '',
    status: 1
  }
  export default {
    name: 'appInfo',
    data() {
      return {
        textMap: {
          create: '新增',
          update: '编辑'
        },
        dialogStatus: '',
        dialogFormVisible: false,
        appInfo: Object.assign({}, appInfoObject),
        list: null,
        listLoading: false,
        total: null,
        listQuery: {
          pageIndex: 1,
          pageSize: 10
        },
        rules: {
          name: [{ required: true, message: 'App名称为必填项', trigger: 'change' }]
        }
      }
    },
    created() {
      this.getAppInfoList()
    },
    methods: {
      getAppInfoList() {
        this.listLoading = true
        getAppInfoList(this.listQuery).then(res => {
          if (res.data.success) {
            this.list = res.data.body.list
            this.total = res.data.body.total
            this.listLoading = false
          } else {
            this.$message.error(res.data.msg)
          }
        })
      },
      handleFilter() {
        this.listQuery.pageIndex = 1
        this.getAppInfoList()
      },
      handleSizeChange(val) {
        this.listQuery.pageSize = val
        this.getAppInfoList()
      },
      handleCurrentChange(val) {
        this.listQuery.pageIndex = val
        this.getAppInfoList()
      },
      handleAddAppInfo() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.resetData()
      },
      handleEditAppInfo(row) {
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.resetData()
        this.appInfo = Object.assign({}, row)
      },
      resetData() {
        this.appInfo = {
          id: null,
          name: '',
          code: '',
          remark: '',
          status: 1
        }
        this.$nextTick(() => {
          this.$refs['appInfoForm'].clearValidate()
        })
      },
      createData() {
        this.$refs['appInfoForm'].validate((valid) => {
          if (valid) {
            addAppInfo(this.appInfo).then(res => {
              if (res.data.success) {
                this.dialogFormVisible = false
                this.getAppInfoList()
                this.$notify({
                  title: '成功',
                  message: res.data.message,
                  type: 'success',
                  duration: 2000
                })
              } else {
                this.$message.error(res.data.message)
              }
            })
          }
        })
      },
      updateData() {
        this.$refs['appInfoForm'].validate((valid) => {
          if (valid) {
            updateAppInfo(this.appInfo).then(res => {
              if (res.data.success) {
                this.dialogFormVisible = false
                this.getAppInfoList()
                this.$notify({
                  title: '成功',
                  message: res.data.message,
                  type: 'success',
                  duration: 2000
                })
              } else {
                this.$message.error(res.data.message)
              }
            })
          }
        })
      },
      handleCancel() {
        this.dialogFormVisible = false
      },
      handleDeleteAppInfo(row) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteAppInfo(row.id).then((res) => {
            if (res.data.success) {
              this.getAppInfoList()
              this.$notify({
                title: '成功',
                message: res.data.message,
                type: 'success',
                duration: 2000
              })
            } else {
              this.$message.error(res.data.msg)
            }
          })
        }).catch(() => {

        })
      }
    }
  }
</script>
