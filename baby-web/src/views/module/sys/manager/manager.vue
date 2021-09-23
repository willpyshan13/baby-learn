<template>
  <div class="app-container calendar-list-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.mobileNumber')" v-model="listQuery.mobile" size="mini"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="handleCreate" type="primary" size="mini"
                 icon="el-icon-edit">{{$t('table.add')}}
      </el-button>
    </div>

    <el-table :data="list" v-loading.body="listLoading"  height="650" element-loading-text="Loading" size="mini" border fit highlight-current-row>
      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="头像" width="80" align="center">
        <template slot-scope="scope">
          <img v-if="scope.row.avatar !== ''" v-bind:src="scope.row.avatar" v-bind:width="35" height="35"/>
          <img v-if="scope.row.avatar === ''" v-bind:src="male" v-bind:width="35" height="35"/>
        </template>
      </el-table-column>
      <el-table-column label="账号" width="120" align="center">
        <template slot-scope="scope">
          {{scope.row.userName}}
        </template>
      </el-table-column>
      <el-table-column label="手机号码" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.mobile}}
        </template>
      </el-table-column>
      <el-table-column label="邮箱" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.email}}
        </template>
      </el-table-column>
      <el-table-column label="状态" width="" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{ [ statusOptions, scope.row.status ] | keyValueFilter }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="角色" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.roleName}}
        </template>
      </el-table-column>
      <!--<el-table-column label="角色" width="" align="center">-->
        <!--<template slot-scope="scope">-->
          <!--{{scope.row.roleId}}-->
        <!--</template>-->
      <!--</el-table-column>-->
      <el-table-column align="center" :label="$t('table.actions')" width="280" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="info" size="small" @click="handleUpdatePassword(scope.row)">{{$t('table.resetPassword')}}</el-button>
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


    <el-dialog width="" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form :rules="rules" ref="managerForm" :model="manager" label-width="90px"
               style='width:90%;margin-left:20px;' size="mini">

        <el-form-item label="账号" prop="userName">
          <el-input v-model="manager.userName"/>
        </el-form-item>
        <el-form-item v-if="dialogStatus==='create'" label="密码" prop="password">
          <el-input v-model="manager.password"/>
        </el-form-item>
        <el-form-item label="手机号" prop="mobile">
          <el-input v-model="manager.mobile"/>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="manager.email"/>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select clearable class="filter-item" v-model="manager.status" placeholder="请选择" value="">
            <el-option v-for="item in statusOptions" :key="item.key" :label="item.display_name"
                       :value="item.key">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="头像" prop="avatar">
          <single-image-upload :name="manager.avatar" :image="manager.avatar" v-model="manager.avatar"/>
        </el-form-item>
        <el-form-item label="角色" prop="roleId">
          <el-select clearable class="filter-item" v-model="manager.roleId" placeholder="请选择" value="">
            <el-option v-for="item in roleList" :key="item.roleId" :label="item.roleName"
                       :value="item.roleId">
            </el-option>
          </el-select>
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
  import { listSysUser, addSysUser, editSysUser, deleteSysUser, updatePassword } from '@/api/sysUser'
  import { listSysRole } from '@/api/sysRole'
  import { statusOptions, keyValueFilter, statusFilter } from '@/utils/constant'
  import male from '@/assets/header/male.png'
  import SingleImageUpload from '../../../../components/Upload/singleImageUpload'
  import { validateImage } from '@/utils/validate'

  const sysUserObject = {
    userId: null,
    userName: '',
    password: '',
    salt: '',
    email: '',
    mobile: '',
    status: 1,
    avatar: '',
    roleId: null
  }

  export default {
    name: 'User',
    components: { SingleImageUpload },
    data() {
      return {
        statusOptions: statusOptions,
        male: male,
        roleList: [],
        list: null,
        listLoading: true,
        total: null,
        listQuery: {
          userName: null,
          page: 1,
          limit: 10
        },
        manager: Object.assign({}, sysUserObject),
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '新增'
        },
        rules: {
          userName: [{ required: true, message: '账号为必填项', trigger: 'change' }],
          password: [{ required: true, message: '密码为必填项', trigger: 'change' }],
          mobileNumber: [{ required: true, message: '手机号码为必填项', trigger: 'change' }],
          avatar: [{ required: true, notNullMessage: '头像为必填项', TypeErrorMessage: '请上传正确类型的文件', trigger: 'change', validator: validateImage }]
        }
      }
    },
    filters: {
      keyValueFilter,
      statusFilter
    },
    created() {
      this.getList()
    },
    methods: {
      getList() {
        this.listLoading = true
        listSysUser(this.listQuery).then(response => {
          this.listLoading = false
          if (response.data.code === 0) {
            this.list = response.data.sysUser.list
            this.total = response.data.sysUser.totalCount
          } else {
            this.$message.error(response.data.msg)
          }
        })
      },
      getRoleList() {
        listSysRole().then((res) => {
          if (res.data.code === 0) {
            this.roleList = res.data.sysRole.list
          } else {
            this.$message.error(res.data.msg)
          }
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
        this.manager = {
          userId: null,
          userName: '',
          password: '',
          salt: '',
          email: '',
          mobile: '',
          status: 1,
          avatar: '',
          roleId: null
        }
      },
      handleCreate() {
        this.resetTemp()
        this.getRoleList()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['managerForm'].clearValidate()
        })
      },
      createData() {
        this.$refs['managerForm'].validate((valid) => {
          if (valid) {
            addSysUser(this.manager).then((res) => {
              if (res.data.code === 0) {
                this.getList()
                this.dialogFormVisible = false
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
          }
        })
      },
      handleUpdate(row) {
        this.getRoleList()
        this.manager = Object.assign({}, row) // copy obj
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['managerForm'].clearValidate()
        })
      },
      updateData() {
        this.$refs['managerForm'].validate((valid) => {
          if (valid) {
            const tempData = Object.assign({}, this.manager) // 对象拷贝
            editSysUser(tempData).then((res) => {
              if (res.data.code === 0) {
                this.getList()
                this.dialogFormVisible = false
                this.$notify({
                  title: '成功',
                  message: '更新成功',
                  type: 'success',
                  duration: 2000
                })
              } else {
                this.$message.error(res.data.msg)
              }
            })
          }
        })
      },
      handleUpdatePassword(row) {
        this.manager = Object.assign({}, row) // copy obj
        const tempData = Object.assign({}, this.manager) // 对象拷贝
        updatePassword(tempData).then((res) => {
          if (res.data.code === 0) {
            this.getList()
            this.dialogFormVisible = false
            this.$notify({
              title: '成功',
              message: '重置成功',
              type: 'success',
              duration: 2000
            })
          } else {
            this.$message.error(res.data.msg)
          }
        })
      },
      handleDelete(row) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteSysUser(row.userId).then((res) => {
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
