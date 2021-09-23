<template>
  <div>
  <!-- 弹窗 -->
  <el-dialog width="50%" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
    <!-- 表单 -->
    <el-form :rules="rules" ref="schedulerForm" :model="scheduler" label-width="90px"
             style='width:75%;margin-left:20px;' size="mini">

      <el-form-item label="任务描述" prop="taskDesc">
        <el-input v-model="scheduler.taskDesc"/>
      </el-form-item>
      <el-form-item v-if="false" label="执行类型" prop="taskDesc">
        <el-input v-model="scheduler.taskDesc"/>
      </el-form-item>

      <el-form-item v-if="false" label="执行器类型" prop="taskDesc">
        <el-input v-model="scheduler.taskDesc"/>
      </el-form-item>

      <el-form-item label="目标对象" prop="targetObject">

        <el-select filterable class="" v-model="scheduler.targetObject" placeholder="请选择" style="display:block;">
          <el-option v-for="item in targetObject" :key="item.key" :label="item.display_name"
                     :value="item.key">
          </el-option>
        </el-select>

      </el-form-item>
      <el-form-item label="目标方法" prop="targetMethod">

        <el-select filterable class="" v-model="scheduler.targetMethod" placeholder="请选择" style="display:block;">
          <el-option v-for="item in targetMethod" :key="item.key" :label="item.display_name"
                     :value="item.key">
          </el-option>
        </el-select>

      </el-form-item>

      <el-form-item label="推送对象:" prop="id">
        <el-input placeholder="请选择" v-model="classObject.name" :disabled="true">
          <el-button slot="append" class="filter-item" type="warning" icon="el-icon-search" size="small" @click="handleSelectPushObject">
            <span v-if="this.scheduler.targetMethod === 'openClassCourseJob'">班级列表</span>
            <!--<span v-if="this.message.objectType === 2">班级列表</span>-->
          </el-button>
        </el-input>
      </el-form-item>

      <el-form-item label="任务周期" prop="taskCron">
        <el-radio-group v-model="runType">
          <el-radio label="1">Cron 表达式</el-radio>
          <el-radio label="2">指定时间</el-radio>
        </el-radio-group>

        <el-input v-if="runType === '1'" v-model="scheduler.taskCron"/>

        <el-date-picker v-if="runType === '2'"
                        v-model="scheduler.runTime"
                        type="datetime"
                        format="yyyy-MM-dd HH:mm:ss"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        @change="handleDateChange"
                        placeholder="选择日期时间">
        </el-date-picker>

      </el-form-item>


      <el-form-item v-if="runType === 1"  prop="taskCron">
      </el-form-item>


      <!-- 操作按钮 -->
      <el-form-item>
        <el-button v-if="dialogStatus==='create'" type="primary" @click="updateJob">{{$t('table.confirm')}} </el-button>
        <el-button v-else type="primary" @click="updateJob">{{$t('table.confirm')}}</el-button>
        <el-button @click="dialogFormVisible = false">{{$t('table.cancel')}}</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

  <class-select ref="classSelect" v-model="classObject" :is-single="true"/>

  </div>

</template>

<script>
  import { addJob } from '@/api/scheduler'
  import { formatCron } from '../../../utils'
  import { targetObject, targetMethod } from '@/utils/constant'
  import ClassSelect from '../class/classSelect'

  const schedulerObject = {
    taskName: '',
    taskGroup: '',
    status: '1',
    taskDesc: '',
    targetObject: '',
    targetMethod: '',
    jobType: 'job',
    taskType: 'local',
    taskCron: '',
    runTime: null,
    id: null
  }

  export default {
    name: 'schedulerUpdate',
    components: { ClassSelect },
    data() {
      return {
        classObject: {
          id: null,
          name: ''
        },
        targetObject: targetObject,
        targetMethod: targetMethod,
        runType: '1',
        scheduler: Object.assign({}, schedulerObject),
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '新增'
        },
        rules: {
          targetObject: [{
            required: true,
            message: '目标对象为必填项',
            trigger: 'change'
          }],
          targetMethod: [{
            required: true,
            message: '目标方法为必填项',
            trigger: 'change'
          }],
          taskDesc: [{
            required: true,
            message: '任务描述为必填项',
            trigger: 'change'
          }],
          taskCron: [{
            required: true,
            message: '任务周期为必填项',
            trigger: 'change'
          }]
        }
      }
    },
    methods: {
      handleSelectPushObject() {
        if (this.scheduler.targetObject === 'openClassCourseJob') {
          this.$refs['classSelect'].handleShowList()
        }
      },
      handleCreateJob() {
        this.resetTemp()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['schedulerForm'].clearValidate()
        })
      },
      updateJob() {
        this.scheduler.id = this.classObject.id
        this.$refs['schedulerForm'].validate((valid) => {
          if (valid) {
            addJob(this.scheduler).then((res) => {
              if (res.data.code === 0) {
                this.$emit('refresh-list')
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
      handleEditJob(row) {
        console.log(row)
      },
      handleDateChange(value) {
        this.scheduler.taskCron = formatCron(value)
      },
      resetTemp() {
        this.classObject = {
          id: null,
          name: ''
        }
        this.scheduler = {
          taskName: '',
          taskGroup: '',
          status: '1',
          taskDesc: '',
          targetObject: '',
          targetMethod: '',
          jobType: 'job',
          taskType: 'local',
          taskCron: '',
          runTime: null
        }
      }
    }
  }
</script>
