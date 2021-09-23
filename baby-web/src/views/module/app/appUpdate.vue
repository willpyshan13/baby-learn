<template>
  <div class="app-container calendar-list-container el-tabs--border-card" style="margin: 20px; height: 90%;">
    <div class="filter-container">
      <router-link :to="'/sys/app/update/machine'">
        <el-button type="primary" size="mini">{{$t('table.machineList')}}</el-button>
      </router-link>
    </div>
    <el-row>
      <el-col :span="7">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>桌面</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="updateAppVersion('fDesktop', vDesktop)">更新</el-button>
          </div>
          <div>
            <el-form :key="1" :rules="rules" ref="fDesktop" :model="vDesktop" label-width="80px"
                     style='width:90%;margin-left:20px;' size="mini">
              <el-form-item label="版本名称" prop="versionName">
                <el-input v-model="vDesktop.versionName"></el-input>
              </el-form-item>
              <el-form-item label="版本号" prop="versionCode">
                <el-input v-model="vDesktop.versionCode"></el-input>
              </el-form-item>
              <el-form-item label="更新地址" prop="path">
                <el-input v-model="vDesktop.path"></el-input>
              </el-form-item>
              <el-form-item label="版本描述" prop="versionDesc">
                <el-input type="textarea" v-model="vDesktop.versionDesc"></el-input>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </el-col>

      <el-col :span="7" :offset="1">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>我的课程</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="updateAppVersion('fCourse', vCourse)">更新</el-button>
          </div>
          <div>
            <el-form :rules="rules" ref="fCourse" :model="vCourse" label-width="80px"
                     style='width:90%;margin-left:20px;' size="mini">
              <el-form-item label="版本名称" prop="versionName">
                <el-input v-model="vCourse.versionName"></el-input>
              </el-form-item>
              <el-form-item label="版本号" prop="versionCode">
                <el-input v-model="vCourse.versionCode"></el-input>
              </el-form-item>
              <el-form-item label="更新地址" prop="path">
                <el-input v-model="vCourse.path"></el-input>
              </el-form-item>
              <el-form-item label="版本描述" prop="versionDesc">
                <el-input type="textarea" v-model="vCourse.versionDesc"></el-input>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </el-col>

      <el-col :span="7" :offset="1">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>直播课堂</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="updateAppVersion('fLive', vLive)">更新</el-button>
          </div>
          <div>
            <el-form :rules="rules" ref="fLive" :model="vLive" label-width="80px"
                     style='width:90%;margin-left:20px;' size="mini">
              <el-form-item label="版本名称" prop="versionName">
                <el-input v-model="vLive.versionName"></el-input>
              </el-form-item>
              <el-form-item label="版本号" prop="versionCode">
                <el-input v-model="vLive.versionCode"></el-input>
              </el-form-item>
              <el-form-item label="更新地址" prop="path">
                <el-input v-model="vLive.path"></el-input>
              </el-form-item>
              <el-form-item label="版本描述" prop="versionDesc">
                <el-input type="textarea" v-model="vLive.versionDesc"></el-input>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </el-col>

    </el-row>
    <el-row>

      <el-col :span="7">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>播放器</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="updateAppVersion('fVideo', vVideo)">更新</el-button>
          </div>
          <div>
            <el-form :rules="rules" ref="fVideo" :model="vVideo" label-width="80px"
                     style='width:90%;margin-left:20px;' size="mini">
              <el-form-item label="版本名称" prop="versionName">
                <el-input v-model="vVideo.versionName"></el-input>
              </el-form-item>
              <el-form-item label="版本号" prop="versionCode">
                <el-input v-model="vVideo.versionCode"></el-input>
              </el-form-item>
              <el-form-item label="更新地址" prop="path">
                <el-input v-model="vVideo.path"></el-input>
              </el-form-item>
              <el-form-item label="版本描述" prop="versionDesc">
                <el-input type="textarea" v-model="vVideo.versionDesc"></el-input>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </el-col>

      <el-col :span="7" :offset="1">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>个人中心</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="updateAppVersion('fPerson', vPerson)">更新</el-button>
          </div>
          <div>
            <el-form :rules="rules" ref="fPerson" :model="vPerson" label-width="80px"
                     style='width:90%;margin-left:20px;' size="mini">
              <el-form-item label="版本名称" prop="versionName">
                <el-input v-model="vPerson.versionName"></el-input>
              </el-form-item>
              <el-form-item label="版本号" prop="versionCode">
                <el-input v-model="vPerson.versionCode"></el-input>
              </el-form-item>
              <el-form-item label="更新地址" prop="path">
                <el-input v-model="vPerson.path"></el-input>
              </el-form-item>
              <el-form-item label="版本描述" prop="versionDesc">
                <el-input type="textarea" v-model="vPerson.versionDesc"></el-input>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </el-col>

      <el-col :span="7" :offset="1">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>系统</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="openWhite(vSystem)"><span v-if="vSystem.white === 0">|&nbsp;开启白名单</span><span v-if="vSystem.white === 1">|&nbsp;关闭白名单</span></el-button>
            <el-button style="float: right; padding: 3px 0" type="text" @click="updateAppVersion('fSystem', vSystem)">更新&nbsp;</el-button>
          </div>
          <div>
            <el-form :rules="rules" ref="fSystem" :model="vSystem" label-width="80px"
                     style='width:90%;margin-left:20px;' size="mini">
              <el-form-item label="版本名称" prop="versionName">
                <el-input v-model="vSystem.versionName"></el-input>
              </el-form-item>
              <el-form-item label="版本号" prop="versionCode">
                <el-input v-model="vSystem.versionCode"></el-input>
              </el-form-item>
              <el-form-item label="更新地址" prop="path">
                <el-input v-model="vSystem.path"></el-input>
              </el-form-item>
              <el-form-item label="版本描述" prop="versionDesc">
                <el-input type="textarea" v-model="vSystem.versionDesc"></el-input>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </el-col>

    </el-row>
  </div>
</template>
<script>
  import { getAppVersion, updateAppVersion } from '../../../api/app'

  const appObject = {
    appId: null,
    appType: {
      type: Number,
      default: null
    },
    path: '',
    versionDesc: '',
    versionName: '',
    versionCode: null,
    white: 0
  }
  export default {
    data() {
      return {
        vDesktop: Object.assign({}, appObject),
        vCourse: Object.assign({}, appObject),
        vLive: Object.assign({}, appObject),
        vVideo: Object.assign({}, appObject),
        vPerson: Object.assign({}, appObject),
        vSystem: Object.assign({}, appObject),
        listLoading: false,
        rules: {
          versionName: [{ required: true, message: '版本名称为必填项', trigger: 'change' }],
          versionCode: [{ required: true, message: '版本号为必填项', trigger: 'change' }],
          path: [{ required: true, message: '更新地址为必填项', trigger: 'change' }],
          versionDesc: [{ required: true, message: '版本描述为必填项', trigger: 'change' }]
        }
      }
    },
    mounted() {
      this.getAppVersion()
    },
    methods: {
      openWhite(o) {
        o.white = o.white === 1 ? 0 : 1
        this.updateAppVersion('fSystem', o)
      },
      getAppVersion() {
        this.listLoading = true
        getAppVersion().then(res => {
          if (res.data.code === 0) {
            res.data.appVersion.list.forEach(row => {
              switch (row.appType) {
                case 0:
                  this.vDesktop = Object.assign({}, row)
                  break
                case 1:
                  this.vCourse = Object.assign({}, row)
                  break
                case 2:
                  this.vLive = Object.assign({}, row)
                  break
                case 3:
                  this.vVideo = Object.assign({}, row)
                  break
                case 4:
                  this.vPerson = Object.assign({}, row)
                  break
                case 5:
                  this.vSystem = Object.assign({}, row)
                  break
                default:
              }
            })
            this.listLoading = false
          } else {
            this.$message.error(res.data.msg)
          }
        })
      },
      updateAppVersion(form, o) {
        if (o.appId == null) {
          switch (form) {
            case 'fDesktop':
              o.appType = 0
              break
            case 'fCourse':
              o.appType = 1
              break
            case 'fLive':
              o.appType = 2
              break
            case 'fVideo':
              o.appType = 3
              break
            case 'fPerson':
              o.appType = 4
              break
            case 'fSystem':
              o.appType = 5
              break
            default:
          }
        }
        this.$refs[form].validate((valid) => {
          if (valid) {
            updateAppVersion(o).then((res) => {
              if (res.data.code === 0) {
                this.dialogFormVisible = false
                this.getAppVersion()
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
      }
    }
  }
</script>
<style>
  .el-row {
    margin-bottom: 20px;
  }
</style>
