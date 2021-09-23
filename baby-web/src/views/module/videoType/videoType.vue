<template>
  <div class="app-container calendar-list-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.name')" v-model="listQuery.typeDesc" size="mini"/>
      <el-select clearable class="filter-item" v-model="listQuery.typeParent" placeholder="请选择类型" value="" size="mini">
        <el-option v-for="item in parentTypeList" :key="item.typeId" :label="item.typeDesc"
                   :value="item.typeId">
        </el-option>
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}</el-button>
      <el-button class="filter-item" type="primary" style="margin-left: 10px;"size="mini" icon="el-icon-edit" @click="handleCreate">{{$t('table.add')}}</el-button>

    </div>
    <el-table :data="videoTypeList" v-loading="tableLoading" height="650"
              stripe style="width: 100%"  border fit highlight-current-row>
      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="所属类型" align="center" width="">
        <template slot-scope="scope">
          <span>{{scope.row.parentName}}</span>
        </template>
      </el-table-column>
      <el-table-column label="分类" align="center" width="">
      <template slot-scope="scope">
        <span>{{scope.row.typeName}}</span>
      </template>
      </el-table-column>
      <el-table-column label="名称" align="center" width="">
        <template slot-scope="scope">
          <template v-if="scope.row.edit">
            <el-input class="edit-input" size="mini" v-model="scope.row.typeDesc" style="width: 50%"/>
            <el-button class='cancel-btn' size="mini" icon="el-icon-refresh" type="warning" @click="cancelEdit(scope.row)">{{$t('table.cancel')}}</el-button>
          </template>
          <span v-else>{{scope.row.typeDesc}}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="280">
        <template slot-scope="scope">
          <el-button type="primary" @click="handleUpdate(scope.row)" size="mini" icon="el-icon-edit">{{$t('table.edit')}}</el-button>
          <el-button class="filter-item" type="danger" style="margin-left: 10px;" size="mini" icon="el-icon-delete" @click="handleDelete(scope.row)">{{$t('table.delete')}}</el-button>
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
    <el-dialog width="40%" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form :rules="rules" ref="dataForm" :model="videoType" label-width="90px" style='width: 90%; margin-left:20px;' size="mini">
        <el-form-item label="所属类型" prop="status">
          <el-select clearable class="filter-item" v-model="videoType.typeParent" placeholder="请选择" value="" style="display: block;">
            <el-option v-for="item in parentTypeList" :key="item.typeId" :label="item.typeDesc"
                       :value="item.typeId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="分类" prop="typeName">
          <el-input v-model="videoType.typeName"/>
        </el-form-item>
        <el-form-item label="描述" prop="typeDesc">
          <el-input v-model="videoType.typeDesc"/>
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
  import { getVideoTypeList, editVideoType, addVideoType, deleteVideoType } from '@/api/videotype'

  const videoTypeObject = {
    id: null,
    typeName: '',
    typeDesc: '',
    typeParent: null
  }
  export default {
    data() {
      return {
        listQuery: {
          page: 1,
          limit: 10,
          typeParent: null
        },
        videoType: Object.assign({}, videoTypeObject),
        parentTypeList: [],
        total: 0,
        videoTypeList: [],
        tableLoading: false,
        videoTypeSelection: [],
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '新增'
        },
        rules: {
          typeDesc: [{ required: true, message: '分类描述为必填项', trigger: 'change' }],
          typeName: [{ required: true, message: '分类名称为必填项', trigger: 'change' }]
        }
      }
    },
    mounted() {
      this.fetchData()
      this.getParentVideoType()
    },
    methods: {
      getParentVideoType() {
        getVideoTypeList({ typeParent: 0 }).then((res) => {
          if (res.data.code === 0) {
            this.parentTypeList = res.data.videoType.list
          } else {
            this.$message.error(res.data.msg)
          }
        })
      },
      fetchData() {
        this.tableLoading = true
        getVideoTypeList(this.listQuery).then((response) => {
          if (response.data.code === 0) {
            this.videoTypeList = response.data.videoType.list
            this.total = response.data.videoType.totalCount
            this.tableLoading = false
          } else {
            this.$message.error(response.data.msg)
          }
        })
      },
      resetTemp() {
        this.videoType = {
          id: null,
          typeName: '',
          typeDesc: '',
          typeParent: null
        }
      },
      handleCreate() {
        this.resetTemp()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      handleUpdate(row) {
        this.videoType = Object.assign({}, row) // copy obj
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      updateData() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            const tempData = Object.assign({}, this.videoType) // 对象拷贝
            editVideoType(tempData).then((res) => {
              if (res.data.code === 0) {
                this.fetchData()
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
      createData() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            addVideoType(this.videoType).then(() => {
              this.dialogFormVisible = false
              this.fetchData()
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
      handleDelete(row) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteVideoType(row.typeId).then((res) => {
            this.dialogFormVisible = false
            this.fetchData()
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

<style scoped>

</style>
