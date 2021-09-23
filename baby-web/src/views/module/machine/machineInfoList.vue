<template>
  <div class="app-container calendar-list-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.keyWord')" v-model="listQuery.mixedField" size="mini"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}</el-button>
      <el-button v-if="false" class="filter-item" type="success" icon="el-icon-search" size="mini" @click="handleAddMachineInfo">{{$t('table.add')}}</el-button>
    </div>

    <el-table :data="list" v-loading.body="listLoading" height="650" element-loading-text="Loading"
              size="mini" border fit highlight-current-row>

      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="序列号" width="240" align="center">
        <template slot-scope="scope">
          {{scope.row.serialNumber}}
        </template>
      </el-table-column>
      <el-table-column v-if="false" label="用户姓名" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.currentUserId}}
        </template>
      </el-table-column>
      <el-table-column label="用户手机号" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.mobilePhone}}
        </template>
      </el-table-column>
      <el-table-column label="当前型号" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.currentModel}}
        </template>
      </el-table-column>
      <el-table-column label="当前App编码" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.currentAppCode}}
        </template>
      </el-table-column>
      <el-table-column label="当前App版本号" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.currentVersion}}
        </template>
      </el-table-column>
      <el-table-column label="是否可升级" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.upgradeable}}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.createTime}}
        </template>
      </el-table-column>
      <el-table-column label="更新时间" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.updateTime}}
        </template>
      </el-table-column>

      <el-table-column align="center" :label="$t('table.actions')" width="200"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" @click="handleEditMachineInfo(scope.row)">{{$t('table.update')}}
          </el-button>
          <el-button size="mini" type="danger" @click="handleDeleteMachineInfo(scope.row)">{{$t('table.delete')}}
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
      <el-form class="form-container" :rules="rules" ref="machineInfoForm" :model="machineInfo"
               label-width="85px" style='margin-left:20px;' size="mini">
        <el-form-item label="序列号:" prop="serialNumber">
          <el-input disabled class="filter-item" placeholder="请输入序列号" v-model="machineInfo.serialNumber"/>
        </el-form-item>
        <el-form-item label="用户手机号:" prop="mobilePhone">
          <el-input class="filter-item" placeholder="请输入用户手机号" v-model="machineInfo.mobilePhone"/>
        </el-form-item>

        <el-form-item label="是否可升级" prop="upgradeable">
          <el-switch v-model="machineInfo.upgradeable"
                     active-color="#13ce66"
                     :active-value="1"
                     :inactive-value="0">
          </el-switch>
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
  import { getMachineInfoList, addMachineInfo, updateMachineInfo, deleteMachineInfo } from '@/api/app'

  const machineInfoObject = {
    id: null,
    serialNumber: '',
    mobilePhone: '',
    upgradeable: 1,
    status: 1
  }
  export default {
    name: 'machineInfo',
    data() {
      return {
        textMap: {
          create: '新增',
          update: '编辑'
        },
        dialogStatus: '',
        dialogFormVisible: false,
        machineInfo: Object.assign({}, machineInfoObject),
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
      this.getMachinesList()
    },
    methods: {
      getMachinesList() {
        this.listLoading = true
        getMachineInfoList(this.listQuery).then(res => {
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
        this.getMachinesList()
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.getMachinesList()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.getMachinesList()
      },
      handleAddMachineInfo() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.resetData()
      },
      handleEditMachineInfo(row) {
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.resetData()
        this.machineInfo = Object.assign({}, row)
      },
      resetData() {
        this.machineInfo = {
          id: null,
          serialNumber: '',
          mobilePhone: '',
          upgradeable: 1,
          status: 1
        }
        this.$nextTick(() => {
          this.$refs['machineInfoForm'].clearValidate()
        })
      },
      createData() {
        this.$refs['machineInfoForm'].validate((valid) => {
          if (valid) {
            addMachineInfo(this.machineInfo).then(res => {
              if (res.data.success) {
                this.dialogFormVisible = false
                this.getMachinesList()
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
        this.$refs['machineInfoForm'].validate((valid) => {
          if (valid) {
            updateMachineInfo(this.machineInfo).then(res => {
              if (res.data.success) {
                this.dialogFormVisible = false
                this.getMachinesList()
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
      handleDeleteMachineInfo(row) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteMachineInfo(row.id).then((res) => {
            if (res.data.success) {
              this.getMachinesList()
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
