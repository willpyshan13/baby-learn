<template>
  <div class="app-container calendar-list-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.mobileNumber')" v-model="listQuery.mobile" size="mini"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">
        {{$t('table.search')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="handleCreate" type="primary" size="mini"
                 icon="el-icon-edit">{{$t('table.add')}}
      </el-button>
    </div>

    <el-table :data="list" v-loading.body="listLoading" height="650" element-loading-text="Loading" size="mini" border
              fit highlight-current-row>
      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="头像" width="80" align="center">
        <template slot-scope="scope">
          <img v-if="scope.row.logo !== ''" v-bind:src="scope.row.logo" v-bind:width="35" height="35"/>
          <img v-if="scope.row.logo === '' && scope.row.gender === 0" v-bind:src="female" v-bind:width="35"
               height="35"/>
          <img v-if="scope.row.logo === '' && scope.row.gender === 1" v-bind:src="male" v-bind:width="35" height="35"/>
        </template>
      </el-table-column>
      <el-table-column label="账号" width="120" align="center">
        <template slot-scope="scope">
          {{scope.row.account}}
        </template>
      </el-table-column>
      <el-table-column label="姓名" width="120" align="center">
        <template slot-scope="scope">
          {{scope.row.teacherName}}
        </template>
      </el-table-column>
      <el-table-column label="昵称" width="120" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.nickName}}</span>
        </template>
      </el-table-column>
      <el-table-column label="手机号码" width="120" align="center">
        <template slot-scope="scope">
          {{scope.row.mobile}}
        </template>
      </el-table-column>
      <el-table-column label="邮箱" width="180" align="center">
        <template slot-scope="scope">
          {{scope.row.email}}
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="性别" width="75" align="center">
        <template slot-scope="scope">
          {{ [ gender, scope.row.gender] | keyValueFilter}}
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="类型" width="75" align="center">
        <template slot-scope="scope">
          {{ [teacherType, scope.row.role] | keyValueFilter}}
        </template>
      </el-table-column>
      <el-table-column label="简介" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.description }}
        </template>
      </el-table-column>
      <el-table-column fixed="right" align="center" :label="$t('table.actions')" width="220"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-if="scope.row.wxMpId === '' || scope.row.wxMpId === null" @click="handleViewSubscribeCode(scope.row.teacherId)" size="mini">公众号</el-button>
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
    </div>


    <el-dialog width="" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
    <el-form :rules="rules" ref="teacherDataForm" :model="teacher" label-width="90px"
             style='width:90%;margin-left:20px;' size="mini">
      <el-row>
        <el-col :span="12">
          <el-form-item label="账号" prop="account">
            <el-input v-model="teacher.account"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="姓名" prop="teacherName">
            <el-input v-model="teacher.teacherName"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="昵称" prop="nickName">
            <el-input v-model="teacher.nickName"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="手机号码" prop="mobile">
            <el-input v-model="teacher.mobile"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="teacher.email"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="性别" prop="gender">
            <el-select clearable class="filter-item" v-model="teacher.gender" placeholder="请选择" value="">
              <el-option v-for="item in gender" :key="item.key" :label="item.display_name"
                         :value="item.key">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="类型" prop="role">
        <el-select clearable class="filter-item" v-model="teacher.role" placeholder="请选择" value="">
          <el-option v-for="item in teacherType" :key="item.key" :label="item.display_name"
                     :value="item.key">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="简介">
        <el-input type="textarea" v-model="teacher.description"/>
      </el-form-item>
      <el-form-item label="头像" prop="logo">
        <single-image-upload :name="decodeURI(teacher.logo)" :image="decodeURI(teacher.logo)" v-model="teacher.logo"/>
      </el-form-item>

      <el-form-item label="微信二维码" prop="wxQrCode">
        <single-image-upload :name="decodeURI(teacher.wxQrCode)" :image="decodeURI(teacher.wxQrCode)"
                             v-model="teacher.wxQrCode"/>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">{{$t('table.cancel')}}</el-button>
      <el-button v-if="dialogStatus==='create'" type="primary" @click="createData">{{$t('table.confirm')}}</el-button>
      <el-button v-else type="primary" @click="updateData">{{$t('table.confirm')}}</el-button>
    </div>
  </el-dialog>

  <el-dialog width="" title="查看二维码" :visible.sync="qrCodeDialog">

    <img :src="qrCodePath"/>
    <div slot="footer" class="dialog-footer">
      <el-button @click="qrCodeDialog = false">{{$t('table.cancel')}}</el-button>
    </div>
  </el-dialog>

  </div>
</template>

<script>
import { teacherList, addTeacher, editTeacher, deleteTeacher, getWxSubscribeCode } from '@/api/teacher'
import { gender, teacherType, keyValueFilter } from '@/utils/constant'

import female from '@/assets/header/female.png'
import male from '@/assets/header/male.png'
import SingleImageUpload from '../../components/Upload/singleImageUpload'
import { validateImage } from '@/utils/validate'

export default {
  name: 'Teacher',
  components: { SingleImageUpload },
  data() {
    return {
      qrCodeDialog: false,
      qrCodePath: '',
      female: female,
      male: male,
      teacherType: teacherType,
      gender: gender,
      list: null,
      parentMenuList: null,
      listLoading: true,
      total: null,
      listQuery: {
        name: null,
        page: 1,
        limit: 10
      },
      activeName: 'teacherList',
      teacherQuery: {
        parentId: 0
      },
      teacher: {
        teacherId: null,
        account: '',
        nickName: '',
        firstName: '',
        password: '',
        mobile: '',
        email: '',
        description: '',
        gender: 0,
        role: 0,
        logo: '',
        teacherName: '',
        teacherPassword: '',
        wxQrCode: ''
      },
      parentMenuSelectVisible: true,
      teacherTypeSelectable: false,
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '新增'
      },
      rules: {
        account: [{ required: true, message: '账号不能为空', trigger: 'change' }],
        firstName: [{ required: true, message: '姓名不能为空', trigger: 'change' }],
        nickName: [{ required: true, message: '昵称不能为空', trigger: 'change' }],
        mobile: [{ required: true, message: '手机号码不能为空', trigger: 'change' }],
        logo: [{
          required: true,
          notNullMessage: '头像为必填项',
          TypeErrorMessage: '请上传正确类型的文件',
          trigger: 'change',
          validator: validateImage
        }]

      }
    }
  },
  filters: {
    keyValueFilter
  },
  created() {
    this.getList()
  },
  methods: {
    handleViewSubscribeCode(id) {
      this.qrCodeDialog = true
      this.fetchWxSubscribeCode(id)
    },
    fetchWxSubscribeCode(id) {
      getWxSubscribeCode(id).then(res => {
        console.log(res.data.body.ticket)
        this.qrCodePath = 'https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=' + res.data.body.ticket
        console.log(this.qrCodePath)
      })
    },
    getList() {
      this.listLoading = true
      teacherList(this.listQuery).then(response => {
        if (response.data.code === 0) {
          this.list = response.data.teacherList.list
          this.total = response.data.teacherList.totalCount
          this.listLoading = false
        } else {
          this.$message.error(response.data.msg)
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
      this.teacher = {
        teacherId: null,
        account: '',
        nickName: '',
        firstName: '',
        password: '',
        mobile: '',
        email: '',
        description: '',
        gender: 0,
        role: 0,
        logo: '',
        teacherName: '',
        teacherPassword: '',
        wxQrCode: ''
      }
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['teacherDataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['teacherDataForm'].validate((valid) => {
        if (valid) {
          this.teacher.logo = encodeURI(this.teacher.logo)
          this.teacher.wxQrCode = encodeURI(this.teacher.wxQrCode)
          addTeacher(this.teacher).then(() => {
            // this.list.unshift(this.teacher)
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
      this.teacher = Object.assign({}, row) // copy obj
      this.teacherTypeSelectable = true
      this.parentMenuSelectVisible = row.type !== 2
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['teacherDataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['teacherDataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.teacher) // 对象拷贝
          tempData.logo = encodeURI(this.teacher.logo)
          tempData.wxQrCode = encodeURI(this.teacher.wxQrCode)
          editTeacher(tempData).then(() => {
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
        deleteTeacher(row.teacherId).then((res) => {
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
