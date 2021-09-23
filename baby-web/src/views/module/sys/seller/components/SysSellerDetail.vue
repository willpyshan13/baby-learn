<template>
  <div class="app-container">
    <el-form ref="sysUserForm" :rules="rules" :model="sysUserFormObject" label-width="95px" size="mini">
      <el-row>
        <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
          <el-form-item label="登陆帐户:" prop="account">
            <el-input v-model="sysUserFormObject.account"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row v-if="!isEdit">
        <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
          <el-form-item label="密码:" prop="password">
            <el-input v-model="sysUserFormObject.password"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
          <el-form-item label="用户类型:" prop="userType">
            <el-select disabled v-model="sysUserFormObject.userType" placeholder="请选择用户类型">
              <el-option
                v-for="item in userTypeDit"
                :value="Number(item.code)"
                :label="item.codeText"
                :key="Number(item.code)"/>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
          <el-form-item label="姓名:" prop="userName">
            <el-input v-model="sysUserFormObject.userName"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
          <el-form-item label="性别:" prop="sex">
            <el-select v-model="sysUserFormObject.sex" placeholder="请选择性别">
              <el-option
                v-for="item in sexDic"
                :key="Number(item.code)"
                :label="item.codeText"
                :value="Number(item.code)"/>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
          <el-form-item label="电话:" prop="mobilePhone">
            <el-input v-model="sysUserFormObject.mobilePhone"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
          <el-form-item label="邮箱:" prop="email">
            <el-input v-model="sysUserFormObject.email"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
          <el-form-item>
            <el-button type="primary" @click="handleSubmitSysUserDetail">{{ operatorText }}</el-button>
            <el-button @click="handleBack">取消</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>
<script>
  import { getSysUser, saveSysUserDetail, updateSysUserDetail } from '@/api/sysUser'
  import { sexDic, userTypeDic } from '@/utils/constant'
  import { closeCurrentView } from '@/utils'

  export default {
    name: 'SysSellerDetail',
    props: {
      isEdit: {
        type: Boolean,
        default: false
      },
      id: {
        type: Number,
        default: null
      }
    },
    data() {
      return {
        userTypeDit: userTypeDic,
        sexDic: sexDic,
        operatorText: '',
        sysUserFormObject: {
          id: null,
          account: '',
          password: '',
          userType: 2,
          userName: '',
          namePinyin: '',
          sex: 0,
          avatar: '',
          mobilePhone: '',
          email: '',
          idCard: '',
          weiXin: '',
          weiBo: '',
          qq: '',
          birthday: '',
          deptId: null,
          position: '',
          address: '',
          staffNo: '',
          status: 1,
          remark: '',
          createUserId: null,
          createTime: null
        },
        rules: {
          id: [{ required: true, message: 'id为必填项', trigger: 'change' }],
          account: [{ required: true, message: '登陆帐户为必填项', trigger: 'change' }],
          password: [{ required: true, message: '密码为必填项', trigger: 'change' }],
          userType: [{ required: true, message: '用户类型为必填项', trigger: 'change' }],
          userName: [{ required: true, message: '姓名为必填项', trigger: 'change' }],
          namePinyin: [{ required: false, message: '姓名拼音为必填项', trigger: 'change' }],
          sex: [{ required: true, message: '性别为必填项', trigger: 'change' }],
          avatar: [{ required: false, message: '头像为必填项', trigger: 'change' }],
          mobilePhone: [{ required: true, message: '电话为必填项', trigger: 'change' }],
          email: [{ required: false, message: '邮箱为必填项', trigger: 'change' }],
          idCard: [{ required: true, message: '身份证号码为必填项', trigger: 'change' }],
          weiXin: [{ required: true, message: '微信为必填项', trigger: 'change' }],
          weiBo: [{ required: true, message: '微博为必填项', trigger: 'change' }],
          qq: [{ required: true, message: 'QQ为必填项', trigger: 'change' }],
          birthday: [{ required: true, message: '出生日期为必填项', trigger: 'change' }],
          deptId: [{ required: true, message: '部门编号为必填项', trigger: 'change' }],
          position: [{ required: true, message: '职位为必填项', trigger: 'change' }],
          address: [{ required: true, message: '详细地址为必填项', trigger: 'change' }],
          staffNo: [{ required: true, message: '工号为必填项', trigger: 'change' }],
          status: [{ required: true, message: '状态为必填项', trigger: 'change' }],
          remark: [{ required: true, message: '备注为必填项', trigger: 'change' }],
          createUserId: [{ required: true, message: '创建人员为必填项', trigger: 'change' }],
          createTime: [{ required: true, message: '创建时间为必填项', trigger: 'change' }]
        }
      }
    },
    created() {
      if (this.isEdit) {
        this.operatorText = '更新'
        this.fetchSysUser()
      } else {
        this.operatorText = '保存'
      }
    },
    methods: {
      fetchSysUser() {
        getSysUser(this.id).then(res => {
          res = res.data
          if (res.result === 0) {
            Object.assign(this.sysUserFormObject, res.body)
          } else {
            this.$message.error(res.message)
          }
        })
      },
      handleSuccess(message) {
        this.$notify({
          title: '成功',
          message: message,
          type: 'success',
          duration: 2000
        })
      },
      handleBack() {
        closeCurrentView(this.$store, this.$router, this.$route.path, '/system/sellers')
      },
      handleSubmitSysUserDetail() {
        if (this.isEdit) {
          this.handleUpdateSysUserDetail()
        } else {
          this.handleSaveSysUserDetail()
        }
      },
      handleSaveSysUserDetail() {
        this.$refs.sysUserForm.validate(valid => {
          if (valid) {
            saveSysUserDetail(this.sysUserFormObject).then(res => {
              res = res.data
              if (res.result === 0) {
                this.handleSuccess(res.message)
                this.handleBack()
              } else {
                this.$message.error(res.message)
              }
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      handleUpdateSysUserDetail() {
        this.$refs.sysUserForm.validate(valid => {
          if (valid) {
            updateSysUserDetail(this.sysUserFormObject).then(res => {
              res = res.data
              if (res.result === 0) {
                this.handleSuccess(res.message)
                this.handleBack()
              } else {
                this.$message.error(res.message)
              }
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      }
    }
  }
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
  .app-container {
    .el-form {
      .el-select {
        display: block;
      }
    }
  }
</style>
