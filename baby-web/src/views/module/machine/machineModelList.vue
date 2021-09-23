<template>
  <div class="app-container calendar-list-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.keyWord')" v-model="listQuery.mixedField" size="mini"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">
        {{$t('table.search')}}
      </el-button>
      <el-button class="filter-item" type="success" icon="el-icon-search" size="mini" @click="handleAddMachineModel">
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
      <el-table-column label="型号" width="240" align="center">
        <template slot-scope="scope">
          {{scope.row.model}}
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
          <el-button size="mini" type="primary" @click="handleEditMachineModel(scope.row)">{{$t('table.update')}}
          </el-button>
          <el-button size="mini" type="danger" @click="handleDeleteMachineModel(scope.row)">{{$t('table.delete')}}
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

    <el-dialog width="" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="form-container" :rules="rules" ref="machineModelForm" :model="machineModel"
               label-width="85px" style='margin-left:20px;' size="mini">
        <el-form-item label="型号:" prop="model">
          <el-input class="filter-item" placeholder="请输入标题" v-model="machineModel.model"/>
        </el-form-item>
        <el-form-item label="备注:" prop="remark">
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入备注"
            v-model="machineModel.remark">
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
  import { getMachineModelList, addMachineModel, updateMachineModel, deleteMachineModel } from '@/api/app'

  const machineModelObject = {
    id: null,
    model: '',
    remark: '',
    status: 1
  }
  export default {
    name: 'machineModel',
    data() {
      return {
        textMap: {
          create: '新增',
          update: '编辑'
        },
        dialogStatus: '',
        dialogFormVisible: false,
        machineModel: Object.assign({}, machineModelObject),
        list: null,
        listLoading: false,
        total: null,
        listQuery: {
          page: 1,
          limit: 10
        },
        rules: {
          model: [{ required: true, message: '型号为必填项', trigger: 'change' }]
        }
      }
    },
    created() {
      this.getModelList()
    },
    methods: {
      getModelList() {
        this.listLoading = true
        getMachineModelList(this.listQuery).then(res => {
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
        this.listQuery.page = 1
        this.getModelList()
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.getModelList()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.getModelList()
      },
      handleAddMachineModel() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.resetData()
      },
      handleEditMachineModel(row) {
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.resetData()
        this.machineModel = Object.assign({}, row)
      },
      resetData() {
        this.machineModel = {
          id: null,
          model: '',
          remark: '',
          status: 1
        }
        this.$nextTick(() => {
          this.$refs['machineModelForm'].clearValidate()
        })
      },
      createData() {
        this.$refs['machineModelForm'].validate((valid) => {
          if (valid) {
            addMachineModel(this.machineModel).then(res => {
              if (res.data.success) {
                this.dialogFormVisible = false
                this.getModelList()
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
        this.$refs['machineModelForm'].validate((valid) => {
          if (valid) {
            updateMachineModel(this.machineModel).then(res => {
              if (res.data.success) {
                this.dialogFormVisible = false
                this.getModelList()
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
      handleDeleteMachineModel(row) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteMachineModel(row.id).then((res) => {
            if (res.data.success) {
              this.getModelList()
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
