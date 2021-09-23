<template>
  <div class="app-container calendar-list-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item":placeholder="$t('table.menuName')" v-model="listQuery.name" size="mini"></el-input>
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" :placeholder="$t('table.menuUrl')" v-model="listQuery.url" size="mini"></el-input>
      <el-select class="filter-item" v-model="listQuery.type" @change="handleMenuChange" placeholder="请选择菜单类型" value="" size="mini" clearable>
        <el-option v-for="item in menuTypeOptions" :key="item.key" :label="item.display_name"
                   :value="item.key">
        </el-option>
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="handleCreate" type="primary" size="mini" icon="el-icon-edit">{{$t('table.add')}}</el-button>
    </div>

    <el-table :data="list" v-loading.body="listLoading"  height="650" element-loading-text="Loading" size="mini" border fit highlight-current-row>
      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="父菜单名称" width="120" align="center">
        <template slot-scope="scope">
          {{scope.row.parentName}}
        </template>
      </el-table-column>
      <el-table-column label="菜单名称" width="120" align="center">
        <template slot-scope="scope">
          {{scope.row.name}}
        </template>
      </el-table-column>
      <el-table-column label="菜单URL" width="" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.url}}</span>
        </template>
      </el-table-column>
      <el-table-column label="授权" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.perms}}
        </template>
      </el-table-column>
      <!-- 格式转换 -->
      <el-table-column class-name="status-col" label="类型" width="110" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.type | statusFilter">{{ [ menuTypeOptions, scope.row.type ] | keyValueFilter }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="菜单图标" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.icon}}
        </template>
      </el-table-column>
      <el-table-column label="排序" width="80" align="center">
        <template slot-scope="scope">
          {{scope.row.orderNum}}
        </template>
      </el-table-column>
      <el-table-column align="center" :label="$t('table.actions')" width="250" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">{{$t('table.edit')}}</el-button>
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

      <!--<el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange"-->
                     <!--:current-page="listQuery.page" :page-sizes="[10,20,30, 40]" :page-size="listQuery.limit"-->
                     <!--layout="total, sizes, prev, pager, next, jumper" :total="total">-->
      <!--</el-pagination>-->
    </div>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form :rules="rules" ref="dataForm" :model="menu" label-width="90px"
               style='width: 400px; margin-left:20px;' size="mini">

        <el-form-item label="菜单名称" prop="name">
          <el-input v-model="menu.name"/>
        </el-form-item>
        <el-form-item label="菜单类型" prop="type">
          <el-select class="filter-item" v-model="menu.type" @change="handleMenuChange" placeholder="请选择" value="" :disabled="menuTypeSelectable">
            <el-option v-for="item in menuTypeOptions" :key="item.key" :label="item.display_name"
                       :value="item.key">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item v-show="parentMenuSelectVisible" label="父级菜单" prop="parentId">
          <el-select clearable class="filter-item" v-model="menu.parentId" placeholder="请选择">
            <el-option v-for="item in parentMenuList" :key="item.menuId" :label="item.name"
                       :value="item.menuId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="菜单地址" prop="url">
          <el-input v-model="menu.url"/>
        </el-form-item>
        <el-form-item label="授权" prop="perms">
          <el-input v-model="menu.perms"/>
        </el-form-item>
        <el-form-item label="图标" prop="icon">
          <el-input v-model="menu.icon"/>
        </el-form-item>
        <el-form-item label="排序" prop="orderNum">
          <el-input v-model="menu.orderNum"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">{{$t('table.cancel')}}</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">{{$t('table.confirm')}}</el-button>
        <el-button v-else type="primary" @click="updateData">{{$t('table.confirm')}}</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { getMenuList, getParentMenu, addMenu, editMenu, deleteMenu } from '@/api/sys'
import { menuTypeOptions, keyValueFilter } from '@/utils/constant'

export default {
  name: 'Menu',
  data() {
    const validateRequire = (rule, value, callback) => {
      if ((this.menu.type !== 0) && (!value || value === '')) {
        callback(new Error('父级菜单为必填项'))
      } else {
        callback()
      }
    }
    return {
      menuTypeOptions: menuTypeOptions,
      list: null,
      parentMenuList: null,
      listLoading: true,
      total: null,
      listQuery: {
        name: null,
        page: 1,
        limit: 10
      },
      menuQuery: {
        parentId: 0
      },
      menu: {
        menuId: null,
        parentId: null,
        name: '',
        url: '',
        perms: '',
        type: 0,
        icon: '',
        orderNum: ''
      },
      parentMenuSelectVisible: false,
      menuTypeSelectable: false,
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '新增'
      },
      rules: {
        parentId: [{ required: true, trigger: 'change', validator: validateRequire }],
        name: [{ required: true, message: '菜单名称为必填项', trigger: 'change' }]
      }
    }
  },
  filters: {
    keyValueFilter,
    statusFilter(status) {
      const statusMap = {
        0: 'success',
        1: 'gray',
        2: 'danger'
      }
      return statusMap[status]
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getMenuList(this.listQuery).then(res => {
        if (res.data.code === 0) {
          this.list = res.data.menuList.list
          this.total = res.data.menuList.totalCount
          this.listLoading = false
        } else {
          this.$message.error(res.data.msg)
        }
      })
    },
    getParentMenuList() {
      getParentMenu(this.menuQuery).then(response => {
        this.parentMenuList = response.data.parentMenu
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
    handleMenuChange(value) {
      if (value === 0) {
        this.parentMenuSelectVisible = false
      } else {
        this.parentMenuSelectVisible = true
      }
    },
    resetTemp() {
      this.menuTypeSelectable = false
      this.parentMenuSelectVisible = false
      this.menu = {
        menuId: null,
        parentId: null,
        name: '',
        url: '',
        perms: '',
        type: 0,
        icon: '',
        orderNum: ''
      }
    },
    handleCreate() {
      this.resetTemp()
      this.getParentMenuList()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          addMenu(this.menu).then(() => {
            this.list.unshift(this.menu)
            this.dialogFormVisible = false
            this.getList()
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
      this.menu = Object.assign({}, row) // copy obj
      this.menuTypeSelectable = true
      if (row.type === 2) {
        this.parentMenuSelectVisible = false
      } else {
        this.parentMenuSelectVisible = true
        this.getParentMenuList()
      }
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.menu) // 对象拷贝
          editMenu(tempData).then(() => {
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
        deleteMenu(row.menuId).then(() => {
          for (const v of this.list) {
            if (v.id === this.menu.id) {
              const index = this.list.indexOf(v)
              this.list.splice(index, 1)
              break
            }
          }
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
