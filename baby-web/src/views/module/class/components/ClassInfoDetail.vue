<template>
  <div class="app-container">
    <el-form ref="classInfoForm" :rules="classInfoRules" :model="classInfoFormObject" label-width="95px" size="mini">
	<el-row>
	  <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
		<el-form-item label="班级名称:" prop="className">
		  <el-input v-model="classInfoFormObject.className"/>
		</el-form-item>
	  </el-col>
	</el-row>
	<el-row>
	  <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
		<el-form-item label="班级介绍:" prop="classInfo">
      <el-input v-model="classInfoFormObject.classInfo"/>
		</el-form-item>
	  </el-col>
	</el-row>
	<el-row>
	  <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
		<el-form-item label="课阶:" prop="courseStageId">
		  <el-select v-model="classInfoFormObject.courseStageId" placeholder="请选择课阶id">
			  <el-option label="选项1" :value="1"/>
		  </el-select>
		</el-form-item>
	  </el-col>
	</el-row>
	<el-row>
	  <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
		<el-form-item label="班级类型:" prop="type">
      <el-select clearable filterable class="filter-item" v-model="classInfoFormObject.type" placeholder="请选择">
        <el-option v-for="item in classType" :key="item.key" :label="item.display_name"
                   :value="item.key">
        </el-option>
      </el-select>
		</el-form-item>
	  </el-col>
	</el-row>
	<el-row>
	  <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
		<el-form-item label="班主任:" prop="headerTeacher">
		  <el-select v-model="classInfoFormObject.headerTeacher" placeholder="请选择班主任">
			  <el-option label="选项1" :value="1"/>
		  </el-select>
		</el-form-item>
	  </el-col>
	</el-row>
	<el-row>
	  <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
      <el-form-item v-if="classInfoFormObject.type === 3" label="默认一对多" prop="oneToMany">
        <el-switch
          v-model="classInfoFormObject.oneToMany"
          active-color="#13ce66"
          :active-value="true"
          :inactive-value="false">
        </el-switch>
      </el-form-item>
	  </el-col>
	</el-row>
	<el-row>
	  <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
		<el-form-item label="开课时间:" prop="autoOpenDate">
      <el-checkbox-group v-model="openWeeks">
        <el-checkbox :label="2">周一</el-checkbox>
        <el-checkbox :label="3">周二</el-checkbox>
        <el-checkbox :label="4">周三</el-checkbox>
        <el-checkbox :label="5">周四</el-checkbox>
        <el-checkbox :label="6">周五</el-checkbox>
        <el-checkbox :label="7">周六</el-checkbox>
        <el-checkbox :label="1">周日</el-checkbox>
      </el-checkbox-group>
		</el-form-item>
	  </el-col>
	</el-row>
	<el-row>
	  <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
		<el-form-item label="备注:" prop="remark">
		  <el-input v-model="classInfoFormObject.remark"/>
		</el-form-item>
	  </el-col>
	</el-row>
	<el-row>
	  <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
		<el-form-item label="幼儿园:" prop="kindergartenId">
		  <el-select v-model="classInfoFormObject.kindergartenId" placeholder="请选择幼儿园id">
			  <el-option label="选项1" :value="1"/>
		  </el-select>
		</el-form-item>
	  </el-col>
	</el-row>
	<el-row>
	  <el-col :xs="24" :sm="10" :md="8" :lg="6" :xl="4">
	    <el-form-item>
		    <el-button type="primary" @click="handleSubmitClassInfoDetail">{{ operatorText }}</el-button>
		    <el-button @click="handleBack">取消</el-button>
	    </el-form-item>
	  </el-col>
    </el-row>
    </el-form>
  </div>
</template>
<script>
import { getClassInfo, saveClassInfoDetail, updateClassInfoDetail } from '@/api/classInfo'
import { closeCurrentView } from '@/utils'
import { classType } from '@/utils/constant'

export default {
  name: 'ClassInfoDetail',
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
      classType: classType,
      operatorText: '',
      openWeeks: [],
      classInfoFormObject: {
        id: null,
        className: '',
        classInfo: '',
        teacherId: null,
        openCourseId: null,
        courseStageId: null,
        type: 2,
        count: null,
        headerTeacher: null,
        personIds: '',
        oneToMany: 0,
        autoOpenDate: '',
        pushTag: '',
        remark: '',
        kindergartenId: null,
        qrCodePath: '',
        status: 1,
        createUserId: null,
        createTime: null
      },
      classInfoRules: {
        id: [{ required: true, message: '为必填项', trigger: 'change' }],
        className: [{ required: true, message: '班级名称为必填项', trigger: 'change' }],
        classInfo: [{ required: true, message: '班级介绍为必填项', trigger: 'change' }],
        teacherId: [{ required: true, message: '班级教师为必填项', trigger: 'change' }],
        openCourseId: [{ required: true, message: '当前打开课程编号为必填项', trigger: 'change' }],
        courseStageId: [{ required: true, message: '课阶id为必填项', trigger: 'change' }],
        type: [{ required: true, message: '班级类型 1:一对一 2:一对四 3:一对多为必填项', trigger: 'change' }],
        count: [{ required: true, message: '班级人数为必填项', trigger: 'change' }],
        headerTeacher: [{ required: true, message: '班主任为必填项', trigger: 'change' }],
        personIds: [{ required: true, message: '当前班级学生编号集合eg: 1,2,3,4为必填项', trigger: 'change' }],
        oneToMany: [{ required: true, message: '是否默认一对多 0:否(默认) 1:是为必填项', trigger: 'change' }],
        autoOpenDate: [{ required: true, message: '自动开课时间为必填项', trigger: 'change' }],
        pushTag: [{ required: true, message: '推送标签为必填项', trigger: 'change' }],
        remark: [{ required: true, message: '备注为必填项', trigger: 'change' }],
        kindergartenId: [{ required: true, message: '幼儿园id为必填项', trigger: 'change' }],
        qrCodePath: [{ required: true, message: '二维码图片地址为必填项', trigger: 'change' }],
        status: [{ required: true, message: '班级状态 -1:删除 0:停用 -1:删除为必填项', trigger: 'change' }],
        createUserId: [{ required: true, message: '创建人员为必填项', trigger: 'change' }],
        createTime: [{ required: true, message: '创建时间为必填项', trigger: 'change' }]
      }
    }
  },
  created() {
    if (this.isEdit) {
      this.operatorText = '更新'
      this.fetchClassInfo()
    } else {
      this.operatorText = '保存'
    }
  },
  methods: {
    fetchClassInfo() {
      getClassInfo(this.id).then(res => {
        res = res.data
        if (res.result === 0) {
          Object.assign(this.classInfoFormObject, res.body)
          if (this.classInfoFormObject.autoOpenDate != null) {
            this.openWeeks = this.classInfoFormObject.autoOpenDate.split(',').map(Number)
          }
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
      closeCurrentView(this.$store, this.$router, this.$route.path, '/class/infos')
    },
    handleSubmitClassInfoDetail() {
      if (this.isEdit) {
        this.handleUpdateClassInfoDetail()
      } else {
        this.handleSaveClassInfoDetail()
      }
    },
    handleSaveClassInfoDetail() {
      this.classInfoFormObject.autoOpenDate = this.openWeeks.join(',')
      this.$refs.classInfoForm.validate(valid => {
        if (valid) {
          saveClassInfoDetail(this.classInfoFormObject).then(res => {
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
    handleUpdateClassInfoDetail() {
      this.classInfoFormObject.autoOpenDate = this.openWeeks.join(',')
      this.$refs.classInfoForm.validate(valid => {
        if (valid) {
          updateClassInfoDetail(this.classInfoFormObject).then(res => {
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
