<template>
  <div class="app-container calendar-list-container">
    <el-row>
      <el-col :span="20">
        <el-form class="form-container" :rules="rules" ref="resourceForm" :model="resource"
                 label-width="85px" style='margin-left:20px;' size="mini">
          <el-row :gutter="10">
            <el-col :span="6">
              <el-form-item label="资源名称:" prop="name">
                <el-input class="filter-item" placeholder="请输入标题" v-model="resource.name"/>
              </el-form-item>
            </el-col>
          </el-row> <!-- resource name end -->
          <el-row :gutter="10">
            <el-col :span="6">
              <el-form-item label="资源类型:" prop="type">
                <el-select v-model="resource.type" filterable placeholder="请选择资源类型" @change="handleResourceTypeChange">
                  <el-option v-for="item in resourceType" :key="item.key" :label="item.display_name"
                             :value="item.key">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row> <!-- resource type end -->
          <el-row :gutter="10">
            <el-col :span="12">
              <el-form-item label="资源文件:" prop="url">
                <single-image-upload v-if="resource.type === Number(3)" :name="decodeURI(resource.url)" :image="decodeURI(resource.url)" v-model="resource.url"/>
                <single-video-upload v-if="resource.type === Number(1)" :name="decodeURI(resource.url)" :video="decodeURI(resource.url)" v-model="resource.url"/>
                <single-audio-upload v-if="resource.type === Number(2)" :name="resource.url" :audio="resource.url" v-model="resource.url"/>
                <single-file-upload v-if="resource.type === Number(4)" :name="decodeURI(resource.url)" :file="decodeURI(resource.url)" v-model="resource.url"/>
              </el-form-item>
            </el-col>
          </el-row> <!-- resource url end -->
          <el-row>
            <el-col :span="8">
              <el-form-item>
                <el-button v-if="!isEdit" type="primary" @click="createData">{{$t('table.add')}}
                </el-button>
                <el-button v-else type="primary" @click="updateData">{{$t('table.confirm')}}</el-button>
                <el-button @click="handleCancel">{{$t('table.cancel')}}</el-button>
              </el-form-item>
            </el-col>
          </el-row> <!-- operation end -->
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import { createResource, updateResource, getResourceById } from '@/api/resource'
  import { closeCurrentView } from '@/utils'
  import { resourceType, keyValueFilter } from '@/utils/constant'
  import SingleImageUpload from '@/components/Upload/singleImageUpload'
  import SingleFileUpload from '@/components/Upload/singleFileUpload'
  import SingleVideoUpload from '@/components/Upload/singleVideoUpload'
  import SingleAudioUpload from '@/components/Upload/singleAudioUpload'

  const resourceObject = {
    id: null,
    name: '',
    url: '',
    type: null,
    status: 1
  }
  export default {
    name: 'resourceDetail',
    components: { SingleAudioUpload, SingleVideoUpload, SingleFileUpload, SingleImageUpload },
    props: {
      isEdit: {
        type: Boolean,
        default: false
      }
    },
    created() {
      if (this.isEdit) {
        const id = this.$route.params && this.$route.params.id
        this.fetchData(id)
      } else {
        this.resource = Object.assign({}, resourceObject)
      }
    },
    data() {
      return {
        resource: Object.assign({}, resourceObject),
        resourceType: resourceType,
        rules: {
          name: [{ required: true, message: '资源名称为必填项', trigger: 'change' }],
          type: [{ required: true, message: '资源类型为必填项', trigger: 'change' }],
          url: [{ required: true, message: '资源文件为必填项', trigger: 'change' }]
        }
      }
    },
    filter: {
      keyValueFilter
    },
    methods: {
      fetchData(id) {
        getResourceById(id).then((res) => {
          if (res.data.success) {
            this.resource = res.data.body
          } else {
            this.$meesage.error(res.data.message)
          }
        })
      },
      createData() {
        this.resource.url = encodeURI(this.resource.url)
        this.$refs.resourceForm.validate(valid => {
          if (valid) {
            createResource(this.resource).then(res => {
              if (res.data.success) {
                closeCurrentView(this.$store, this.$router, this.$route.path, '/resource/resources')
                this.$notify({
                  title: '成功',
                  message: res.data.message,
                  type: 'success',
                  duration: 2000
                })
              } else {
                this.$message.error(res.data.message)
              }
            })
          }
        })
      },
      updateData() {
        this.$refs.resourceForm.validate(valid => {
          if (valid) {
            updateResource(this.resource).then(res => {
              if (res.data.success) {
                closeCurrentView(this.$store, this.$router, this.$route.path, '/resource/resources')
                this.$notify({
                  title: '成功',
                  message: res.data.message,
                  type: 'success',
                  duration: 2000
                })
              } else {
                this.$message.error(res.data.message)
              }
            })
          }
        })
      },
      handleResourceTypeChange(val) {
        this.resource.url = ''
      },
      handleCancel() {
        // 关闭当前标签页
        closeCurrentView(this.$store, this.$router, this.$route.path, '/resource/resources')
      }
    }
  }
</script>
