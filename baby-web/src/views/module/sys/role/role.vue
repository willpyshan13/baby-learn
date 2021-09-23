<template>
    <div>
    <el-row>
      <el-col :span="12">
        <div class="app-container calendar-list-container el-tabs--border-card" style="margin: 20px">

        <div class="filter-container">
          <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                    :placeholder="$t('table.mobileNumber')" v-model="listQuery.roleName" size="mini"/>
          <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}
          </el-button>
          <el-button class="filter-item" style="margin-left: 10px;" @click="handleCreate" type="primary" size="mini"
                     icon="el-icon-edit">{{$t('table.add')}}
          </el-button>
        </div>

        <el-table :data="list" v-loading.body="listLoading"  height="650"
                  @row-click="handleCellClick"
                  element-loading-text="Loading" size="mini" border fit highlight-current-row>
          <el-table-column align="center" label='ID' width="95">
            <template slot-scope="scope">
              {{scope.$index + 1}}
            </template>
          </el-table-column>
          <el-table-column label="角色名称" width="120" align="center">
            <template slot-scope="scope">
              {{scope.row.roleName}}
            </template>
          </el-table-column>
          <el-table-column label="备注" width="" align="center">
            <template slot-scope="scope">
              {{scope.row.remark}}
            </template>
          </el-table-column>
          <el-table-column align="center" :label="$t('table.actions')" width="280" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button type="primary" size="small" @click="handleUpdate(scope.row)">{{$t('table.edit')}}</el-button>
              <el-button size="mini" type="danger" @click="handleDelete(scope.row)">{{$t('table.delete')}}</el-button>
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
      </el-col>
      <el-col :span="12">
        <el-form label-width="80px">
        <div class="app-container calendar-list-container el-tabs--border-card" style="margin: 20px; height: 800px;">
          <div class="filter-container">
            <span>权限列表</span>
          </div>
          <!-- -->
          <el-tree ref="permissionTree" :data="menuTree" show-checkbox accordion node-key="id" :default-checked-keys="listPermission"/>

          <div style="position:absolute;bottom:40px;">
            <el-button type="primary" @click="handleUpdateRolePermission">{{$t('table.update')}}</el-button>
          </div>

        </div>
        </el-form>
      </el-col>
    </el-row>



    <el-dialog width="30%" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">

      <el-form :rules="rules" ref="roleForm" :model="sysRole" label-width="90px"
               style='width:75%;margin-left:20px;' size="mini">

        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="sysRole.roleName"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="sysRole.remark"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">{{$t('table.cancel')}}</el-button>
        <el-button v-if="dialogStatus==='create'" type="primary" @click="createData">{{$t('table.confirm')}}</el-button>
        <el-button v-else type="primary" @click="updateData">{{$t('table.confirm')}}</el-button>
      </div>
    </el-dialog>
    </div>
</template>

<script>
  import { listSysRole, addSysRole, editSysRole, deleteSysRole, updateRolePermission } from '@/api/sysRole'
  import { menuTree } from '@/api/sys'

  const sysRoleObject = {
    roleId: null,
    roleName: '',
    remark: ''
  }

  export default {
    name: 'Role',
    data() {
      return {
        menuTree: [],
        listPermission: [],
        list: null,
        listLoading: true,
        total: null,
        listQuery: {
          userName: null,
          page: 1,
          limit: 10
        },
        sysRole: Object.assign({}, sysRoleObject),
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '新增'
        },
        roleId: null,
        rules: {
          roleName: [{ required: true, message: '角色名称为必填项', trigger: 'change' }]
        }
      }
    },
    created() {
      this.getList()
      this.getMenuTree()
    },
    methods: {
      handleCellClick(row, column, cell, event) {
        this.getMenuTree(row.roleId)
        this.sysRole.roleId = row.roleId
      },
      getMenuTree(id) {
        menuTree({ roleId: id }).then(response => {
          this.menuTree = response.data.menu
          if (id) {
            this.listPermission = response.data.permission
            this.listPermission.forEach(key => {
              this.$refs['permissionTree'].setCurrentKey(key)
            })
          }
        })
      },
      handleUpdateRolePermission() {
        const operations = this.$refs['permissionTree'].getCheckedKeys()
        const menus = this.$refs['permissionTree'].getHalfCheckedKeys()
        const menuIds = operations.concat(menus)
        const params = {
          roleId: this.sysRole.roleId,
          menuIds: menuIds
        }
        updateRolePermission(params).then((res) => {
          if (res.data.code === 0) {
            this.$notify({
              title: '成功',
              message: '创建成功',
              type: 'success',
              duration: 2000
            })
          } else {
            this.$message.error(res.data.msg)
          }
        })
      },
      getList() {
        this.listLoading = true
        listSysRole(this.listQuery).then(response => {
          this.list = response.data.sysRole.list
          this.total = response.data.sysRole.totalCount
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
      resetTemp() {
        this.sysRole = {
          roleId: null,
          roleName: '',
          remark: ''
        }
      },
      handleCreate() {
        this.resetTemp()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['roleForm'].clearValidate()
        })
      },
      createData() {
        this.$refs['roleForm'].validate((valid) => {
          if (valid) {
            addSysRole(this.sysRole).then(() => {
              this.getList()
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '创建成功',
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      },
      handleUpdate(row) {
        this.sysRole = Object.assign({}, row) // copy obj
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['roleForm'].clearValidate()
        })
      },
      updateData() {
        this.$refs['roleForm'].validate((valid) => {
          if (valid) {
            const tempData = Object.assign({}, this.sysRole) // 对象拷贝
            editSysRole(tempData).then(() => {
              this.getList()
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '更新成功',
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      },
      handleDelete(row) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteSysRole(row.roleId).then((res) => {
            if (res.data.code === 0) {
              this.getList()
              this.$notify({
                title: '成功',
                message: '删除成功',
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
