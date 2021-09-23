<template>
  <div class="app-container">
    <el-form ref="kindergartenInfoForm" :rules="rules" :model="kindergartenInfoFormObject" label-width="100px" size="mini">
      <el-row>
        <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
          <el-form-item label="幼儿园名称:" prop="name">
            <el-input v-model="kindergartenInfoFormObject.name"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
          <el-form-item label="幼儿园编号:" prop="code">
            <el-input v-model="kindergartenInfoFormObject.code"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
          <el-form-item label="联系电话:" prop="mobilePhone">
            <el-input v-model="kindergartenInfoFormObject.mobilePhone"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
          <el-form-item label="园长姓名:" prop="director">
            <el-input v-model="kindergartenInfoFormObject.director"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="24" :md="21" :lg="17" :xl="10">
          <el-form-item label="所在地区:" prop="areaCode">
            <v-distpicker ref="vdis" @province="selectProvince" @city="selectCity" @area="selectArea" :province="province" :city="city" :area="area"></v-distpicker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
          <el-form-item label="详细地址:" prop="address">
            <el-input v-model="kindergartenInfoFormObject.address"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
          <el-form-item label="销售人员:" prop="salesperson">
            <el-select v-model="kindergartenInfoFormObject.salesperson" placeholder="请选择销售人员">
              <el-option v-for="item in sysUserList" :key="item.id" :label="item.userName" :value="item.id"/>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
          <el-form-item>
            <el-button type="primary" @click="handleSubmitKindergartenInfoDetail">{{ operatorText }}</el-button>
            <el-button @click="handleBack">取消</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>
<script>
import { getKindergartenInfo, saveKindergartenInfoDetail, updateKindergartenInfoDetail } from '@/api/kindergartenInfo'
import { getSysUserList } from '@/api/sysUser'
import { closeCurrentView } from '@/utils'
import CitySelect from '@/components/CitySelect/citySelect'
import VDistpicker from '@/components/DistPicker/Distpicker'

export default {
  name: 'KindergartenInfoDetail',
  components: { CitySelect, VDistpicker },
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
      province: null,
      city: null,
      area: null,
      areaCode: {},
      operatorText: '',
      sysUserList: [],
      kindergartenInfoFormObject: {
        id: null,
        name: '',
        code: '',
        director: '',
        mobilePhone: '',
        areaCode: null,
        address: '',
        salesperson: null,
        status: 1,
        createUserId: null,
        createTime: null
      },
      rules: {
        name: [{ required: true, message: '幼儿园名称为必填项', trigger: 'change' }],
        code: [{ required: true, message: '幼儿园编号为必填项', trigger: 'change' }],
        director: [{ required: false, message: '园长姓名为必填项', trigger: 'change' }],
        mobilePhone: [{ required: true, message: '联系电话为必填项', trigger: 'change' }],
        areaCode: [{ required: false, message: '地区编码为必填项', trigger: 'change' }],
        address: [{ required: false, message: '详细地址为必填项', trigger: 'change' }],
        salesperson: [{ required: false, message: '销售人员为必填项', trigger: 'change' }]
      }
    }
  },
  created() {
    this.fetchSysUserList()
    if (this.isEdit) {
      this.operatorText = '更新'
      this.fetchKindergartenInfo()
    } else {
      this.operatorText = '保存'
    }
  },
  methods: {
    selectProvince(val) {
      this.kindergartenInfoFormObject.areaCode = ''
    },
    selectCity(val) {
      this.kindergartenInfoFormObject.areaCode = ''
      this.$refs.kindergartenInfoForm.validate(() => {})
    },
    selectArea(val) {
      if (val.code) {
        this.kindergartenInfoFormObject.areaCode = val.code
        this.$refs.kindergartenInfoForm.validate(() => {})
      }
    },
    fetchSysUserList() {
      getSysUserList({ enablePage: false, userType: 2 }).then(res => {
        res = res.data
        if (res.result === 0) {
          this.sysUserList = res.body
        } else {
          this.$message.error(res.message)
        }
      })
    },
    fetchKindergartenInfo() {
      getKindergartenInfo(this.id).then(res => {
        res = res.data
        if (res.result === 0) {
          Object.assign(this.kindergartenInfoFormObject, res.body)
          this.province = this.kindergartenInfoFormObject.areaCode.substring(0, 2) + '0000'
          this.city = this.kindergartenInfoFormObject.areaCode.substring(0, 4) + '00'
          this.area = this.kindergartenInfoFormObject.areaCode + ''
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
      closeCurrentView(this.$store, this.$router, this.$route.path, '/kindergarten/infos')
    },
    handleSubmitKindergartenInfoDetail() {
      if (this.isEdit) {
        this.handleUpdateKindergartenInfoDetail()
      } else {
        this.handleSaveKindergartenInfoDetail()
      }
    },
    handleSaveKindergartenInfoDetail() {
      this.$refs.kindergartenInfoForm.validate(valid => {
        if (valid) {
          saveKindergartenInfoDetail(this.kindergartenInfoFormObject).then(res => {
            res = res.data
            if (res.result === 0) {
              this.handleSuccess(res.message)
              this.handleBack()
            } else {
              this.$message.error(res.message)
            }
          })
        } else {
          return false
        }
      })
    },
    handleUpdateKindergartenInfoDetail() {
      this.$refs.kindergartenInfoForm.validate(valid => {
        if (valid) {
          updateKindergartenInfoDetail(this.kindergartenInfoFormObject).then(res => {
            res = res.data
            if (res.result === 0) {
              this.handleSuccess(res.message)
              this.handleBack()
            } else {
              this.$message.error(res.message)
            }
          })
        } else {
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
