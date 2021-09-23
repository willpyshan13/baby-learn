<template>
  <div class="app-container calendar-list-container">
    <el-row>
      <el-col :span="2">
        <div class="grid-content bg-purple"></div>
      </el-col>
      <el-col :span="20">
        <!-- form -->
        <el-form class="form-container" :rules="rules" ref="messageForm" :model="message"
                 label-width="85px" style='margin-left:20px;' size="mini">
          <!-- message type -->
          <el-row :gutter="10">
            <el-col :span="6">
              <el-form-item label="消息类型:" prop="messageType">
                <el-select v-model="message.type" filterable placeholder="请选择消息类型" @change="handleMessageTypeChange">
                  <el-option v-for="item in messageTypeItems" :key="item.key" :label="item.display_name"
                             :value="item.key">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <!-- push type -->
          <el-row :gutter="10">
            <el-col :span="6">
              <el-form-item label="推送类型:" prop="objectType">
                <el-select v-model="message.objectType" filterable placeholder="请选择推送类型">
                  <el-option v-for="item in pushType" :key="item.key" :label="item.display_name"
                             :value="item.key">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <!-- push object -->
          <el-row :gutter="10">
            <el-col :span="6">
              <el-form-item label="推送对象:" prop="objectId">
                <el-input placeholder="请选择" v-model="pushObject.name" :disabled="true">
                  <el-button slot="append" class="filter-item" type="warning" icon="el-icon-search" size="small" @click="handleSelectPushObject">
                    <span v-if="this.message.objectType === 1">学生列表</span>
                    <span v-if="this.message.objectType === 2">班级列表</span>
                  </el-button>
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <!-- message title -->
          <el-row :gutter="10">
            <el-col :span="6">
              <el-form-item label="消息标题:" prop="title">
                <el-input class="filter-item" placeholder="请输入标题" v-model="message.title"/>
              </el-form-item>
            </el-col>
          </el-row>
          <!-- message content -->
          <el-row>
            <el-col :span="6">
              <el-form-item label="消息内容:" prop="content">
                <el-input
                  type="textarea"
                  :rows="2"
                  placeholder="请输入内容"
                  v-model="message.content">
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <!-- file upload -->
          <el-row>
            <el-col :span="12">
              <el-form-item v-if="this.message.type === 2" label="语音文件:" prop="data">
                <single-video-upload :name="voiceUrl" :video="voiceUrl" v-model="voiceUrl"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <!-- 操作按钮 -->
              <el-form-item>
                <el-button v-if="!isEdit" type="primary" @click="createData">{{$t('table.push')}}
                </el-button>
                <el-button v-else type="primary" @click="updateData">{{$t('table.confirm')}}</el-button>
                <el-button @click="handleCancel">{{$t('table.cancel')}}</el-button>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-col>
      <el-col :span="2">
        <div class="grid-content bg-purple"></div>
      </el-col>
    </el-row>

    <person-select-list ref="personSelectList" v-model="pushObject" :is-single="true"/>

    <class-select ref="classSelect" v-model="pushObject" :is-single="true"/>
  </div>
</template>

<script>
  import { addMessage, updateMessage, getMessageById } from '@/api/message'
  import { closeCurrentView } from '@/utils'
  import { messageType, pushType, keyValueFilter } from '@/utils/constant'
  import PersonSelectList from '../class/personList'
  import ClassSelect from '../class/classSelect'
  import SingleVideoUpload from '@/components/Upload/singleVideoUpload'

  const messageObject = {
    id: null,
    title: '',
    content: '',
    type: 1,
    data: null,
    objectType: 1,
    objectId: null,
    createTime: null
  }
  export default {
    components: {
      SingleVideoUpload,
      ClassSelect,
      PersonSelectList },
    name: 'messageDetail',
    props: {
      isEdit: {
        type: Boolean,
        default: false
      }
    },
    computed: {
      messageTypeItems: function() {
        return this.messageType.slice(0, 3)
      }
    },
    created() {
      if (this.isEdit) {
        const id = this.$route.params && this.$route.params.id
        this.fetchData(id)
      } else {
        this.message = Object.assign({}, messageObject)
      }
    },
    data() {
      return {
        messageType: messageType,
        pushType: pushType,
        voiceUrl: '',
        pushObject: {
          id: null,
          name: ''
        },
        message: {},
        rules: {
          title: [{ required: true, message: '消息标题为必填项', trigger: 'change' }],
          content: [{ required: true, message: '消息内容为必填项', trigger: 'change' }],
          objectId: [{ required: true, message: '推送对象为必填项', trigger: 'change' }],
          data: [{ required: true, message: '语音文件为必填项', trigger: 'change' }]
        }
      }
    },
    filter: {
      keyValueFilter
    },
    methods: {
      fetchData(id) {
        getMessageById(id).then((res) => {
          this.message = res.data.message
          if (this.message.type === 2) {
            this.voiceUrl = decodeURI(JSON.parse(this.message.data).voiceUrl)
          }
          this.pushObject.id = this.message.objectId
        })
      },
      createData() {
        this.message.objectId = this.pushObject.id
        const data = {}
        data.voiceUrl = encodeURI(this.voiceUrl)
        if (data.voiceUrl === '' || data.voiceUrl == null) {
          this.message.data = null
        } else {
          this.message.data = JSON.stringify(data)
        }
        this.$refs.messageForm.validate(valid => {
          if (valid) {
            addMessage(this.message).then((res) => {
              if (res.data.code === 0) {
                // 关闭当前标签页
                closeCurrentView(this.$store, this.$router, this.$route.path, '/message/list')
                this.$notify({
                  title: '成功',
                  message: '新增成功',
                  type: 'success',
                  duration: 2000
                })
              } else {
                this.$message.error(res.data.msg)
              }
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      updateData() {
        this.message.objectId = this.pushObject.id
        const data = {}
        data.voiceUrl = encodeURI(this.voiceUrl)
        if (data.voiceUrl === '' || data.voiceUrl == null) {
          this.message.data = null
        } else {
          this.message.data = JSON.stringify(data)
        }
        this.$refs['messageForm'].validate(valid => {
          if (valid) {
            updateMessage(this.message).then((res) => {
              if (res.data.code === 0) {
                // 关闭当前标签页
                closeCurrentView(this.$store, this.$router, this.$route.path, '/message/list')
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
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      handleSelectPushObject() {
        if (this.message.objectType === 1) {
          this.$refs['personSelectList'].handleAssignedStudent()
        } else if (this.message.objectType === 2) {
          this.$refs['classSelect'].handleShowList()
        }
      },
      handleCancel() {
        // 关闭当前标签页
        closeCurrentView(this.$store, this.$router, this.$route.path, '/message/list')
      },
      handleMessageTypeChange(val) {
        if (val === 2) {
          this.message.title = '语音消息'
          this.message.content = '机灵宝宝老师给你发了一条语音消息哦，快来听听吧'
        }
      }
    }
  }

</script>

<style>
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .el-select {
    display: block;
  }
  .el-date-editor.el-input, .el-date-editor.el-input__inner {
    width: 100%;
  }
</style>
