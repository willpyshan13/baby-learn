<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input
        v-model="sysUserQuery.mixedField"
        placeholder="关键字"
        size="mini"
        class="filter-item"
        style="width: 150px;"
        @keyup.enter.native="handleSearchSysUser"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleSearchSysUser">搜索</el-button>
      <router-link :to="'/system/users/add'">
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
      v-loading="sysUserTableLoading"
      :data="sysUserList"
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
      <el-table-column label="登陆帐户" width="" align="">
        <template slot-scope="scope">
          <span>{{ scope.row.account }}</span>
        </template>
      </el-table-column>
      <el-table-column label="用户类型" width="" align="">
        <template slot-scope="scope">
          <span>{{ [userTypeDic, scope.row.userType] | dicCodeTextFilter }}</span>
        </template>
      </el-table-column>
      <el-table-column label="姓名" width="" align="">
        <template slot-scope="scope">
          <span>{{ scope.row.userName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="性别" width="" align="">
        <template slot-scope="scope">
          <span>{{ [sexDic, scope.row.sex] | dicCodeTextFilter }}</span>
        </template>
      </el-table-column>
      <el-table-column label="电话" width="" align="">
        <template slot-scope="scope">
          <span>{{ scope.row.mobilePhone }}</span>
        </template>
      </el-table-column>
      <el-table-column label="邮箱" width="" align="">
        <template slot-scope="scope">
          <span>{{ scope.row.email }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" width="" align="">
        <template slot-scope="scope">
          <span>{{ scope.row.remark }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="240" align="">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleRoleSelect(scope.row.id)">分配角色</el-button>

          <router-link :to="'/system/users/edit/' + scope.row.id">
            <el-button
              class="filter-item"
              type="primary"
              size="mini">
              <span>修改</span>
            </el-button>
          </router-link>
          <el-button type="danger" size="mini" @click="handleDeleteSysUser(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table> <!-- table data end -->
    <div class="pagination-container">
      <el-pagination
        :current-page="sysUserQuery.pageNum"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="sysUserQuery.pageSize"
        :total="sysUserTotal"
        layout="total, prev, pager, next, sizes, jumper"
        prev-text="上一页"
        next-text="下一页"
        @size-change="handleSysUserListSizeChange"
        @current-change="handleSysUserListCurrentChange"/>
    </div> <!-- pagination end -->

    <el-dialog title="分配角色" :visible.sync="roleSelectDialog">
      <el-form ref="userRoleForm" :model="userRoleObject" size="mini">
        <el-form-item label="角色:">
          <el-select clearable filterable v-model="userRoleObject.roleId" placeholder="请选择角色">
            <el-option v-for="item in roleList" :key="item.roleId" :label="item.roleName" :value="item.roleId"/>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button @click="handleConfirmRoleSelect">{{$t('table.confirm')}}</el-button>
          <el-button @click="roleSelectDialog = false">{{$t('table.cancel')}}</el-button>

        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { getSysUserList, deleteSysUser, getUserRole, updateUserRole } from '@/api/sysUser'
import { listSysRole } from '@/api/sysRole'
import { sexDic, userTypeDic } from '@/utils/constant'
import { dicCodeTextFilter } from '@/utils/index'

export default {
  filters: {
    dicCodeTextFilter
  },
  data() {
    return {
      roleSelectDialog: false,
      userRoleObject: {
        id: null,
        userId: null,
        roleId: null
      },
      roleList: [],
      userTypeDic: userTypeDic,
      sexDic: sexDic,
      sysUserList: null,
      sysUserTableLoading: true,
      sysUserTotal: 0,
      sysUserQuery: {
        mixedField: null,
        pageNum: 1,
        pageSize: 10
      }
    }
  },
  created() {
    this.fetchSysUserList()
  },
  methods: {
    handleRoleSelect(id) {
      this.userRoleObject.id = null
      this.userRoleObject.userId = null
      this.userRoleObject.roleId = null
      this.userRoleObject.userId = id
      this.roleSelectDialog = true
      this.fetchSysUserRole(id)
      this.fetchSysRoleList()
    },
    handleConfirmRoleSelect() {
      updateUserRole(this.userRoleObject).then(res => {
        res = res.data
        if (res.result === 0) {
          this.$notify({
            title: '成功',
            message: res.message,
            type: 'success',
            duration: 2000
          })
          this.roleSelectDialog = false
        } else {
          this.$message.error(res.message)
        }
      })
    },
    fetchSysUserRole(id) {
      getUserRole(id).then(res => {
        res = res.data
        if (res.result === 0) {
          Object.assign(this.userRoleObject, res.body)
          console.log(this.userRoleObject)
        } else {
          this.$message.error(res.message)
        }
      })
    },
    fetchSysRoleList() {
      listSysRole({ enablePage: false }).then(res => {
        this.roleList = res.data.sysRole.list
      })
    },
    fetchSysUserList() {
      this.sysUserTableLoading = true
      getSysUserList(this.sysUserQuery).then(res => {
        res = res.data
        if (res.result === 0) {
          this.sysUserList = res.body.list
          this.sysUserTotal = res.body.total
        } else {
          this.$message.error(res.message)
        }
        this.sysUserTableLoading = false
      })
    },
    handleSearchSysUser() {
      this.fetchSysUserList()
    },
    handleDeleteSysUser(id) {
      this.$confirm('此操作将删除数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteSysUser(id).then(res => {
          res = res.data
          if (res.result === 0) {
            this.$notify({
              title: '成功',
              message: '操作成功',
              type: 'success',
              duration: 2000
            })
            this.fetchSysUserList()
          } else {
            this.$message.error(res.message)
          }
        })
      }).catch(() => {

      })
    },
    handleSysUserListSizeChange(val) {
      this.sysUserQuery.pageSize = val
      this.fetchSysUserList()
    },
    handleSysUserListCurrentChange(val) {
      this.sysUserQuery.pageNum = val
      this.fetchSysUserList()
    }
  }
}
</script>
