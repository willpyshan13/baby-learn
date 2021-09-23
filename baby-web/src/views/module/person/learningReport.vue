<template>
  <div>

    <el-dialog width="" :title="title" :visible.sync="dialogFormVisible">

      <div class="block">

        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <img v-if="report.avatar !== ''" v-bind:src="report.avatar+'?imageView2/1/w/40/h/40'" style="vertical-align: middle; border-radius: 20px; margin-right: 15px;"/>
            <img v-else v-bind:src="boyPhoto" v-bind:width="40" height="40" style="vertical-align: middle; border-radius: 20px; margin-right: 15px;"/>
            <!--<img style="vertical-align: middle; border-radius: 20px; margin-right: 15px;" :src="report.avatar+'?imageView2/1/w/40/h/40'"/>-->
            <span style="color: #3a8ee6; vertical-align: middle;">用户信息</span>
          </div>
          <div class="text item">

            <el-row>
              <el-col :span="2"> <span>昵称:</span> </el-col>
              <el-col :span="4"> {{report.personName}} </el-col>

              <el-col :span="2"> <span>课程得分:</span> </el-col>
              <el-col :span="4"> {{report.totalScore}} </el-col>
            </el-row>

          </div>
        </el-card>
      </div>

      <el-card class="box-card" style="margin-top: 10px;">
        <div slot="header" class="clearfix">
          <span style="color: #3a8ee6">单词辨析</span>
        </div>
        <div class="text item">
          <el-row v-for="(reportDetail, key) in playGameReport.reportDetail" :key="key">
            <el-col :span="6">
              <span>{{reportDetail.name}}</span>
            </el-col>
            <el-col :span="12">
              <el-rate
                v-model="reportDetail.number"
                disabled
                :max="3">
              </el-rate>
            </el-col>
          </el-row>
        </div>
      </el-card>

      <el-card class="box-card" style="margin-top: 10px;">
        <div slot="header" class="clearfix">
          <span style="color: #3a8ee6">大声说</span>
        </div>
        <div class="text item">
          <el-row v-for="(reportDetail, key) in speakUpReport.reportDetail" :key="key">
            <el-col :span="6">
              <span>{{reportDetail.name}}</span>
            </el-col>
            <el-col :span="12">
              <el-rate
                v-model="reportDetail.number"
                disabled
                :max="3">
              </el-rate>
            </el-col>
          </el-row>
        </div>
      </el-card>

    </el-dialog>


  </div>
</template>
<script>
  import { getLearnReport } from '@/api/person'
  import boyPhoto from '@/assets/header/boy.png'

  export default {
    name: 'learning-report',
    data() {
      return {
        boyPhoto: boyPhoto,
        dialogFormVisible: false,
        title: '',
        query: {
          personId: null,
          courseId: null
        },
        value4: 1,
        report: {
          avatar: '',
          personId: null,
          personName: '',
          totalScore: null,
          courseName: ''
        },
        playGameReport: {
          reportDetail: [
            {
              audioUrl: '',
              id: null,
              imageUrl: '',
              name: '',
              score: null
            }
          ]
        },
        speakUpReport: {
          reportDetail: [
            {
              audioUrl: '',
              id: null,
              imageUrl: '',
              name: '',
              score: 0,
              number: 0
            }
          ]
        }
      }
    },
    methods: {
      handleCreate(personId, courseId) {
        this.query.personId = personId
        this.query.courseId = courseId
        this.fetchRecordsData()
        this.dialogFormVisible = true
      },
      fetchRecordsData() {
        this.listLoading = true
        getLearnReport(this.query).then(res => {
          if (res.data.code === 0) {
            this.playGameReport = res.data.report.playGameReport == null ? [] : res.data.report.playGameReport
            this.speakUpReport = res.data.report.speakUpReport == null ? [] : res.data.report.speakUpReport
            this.report = res.data.report
            this.title = res.data.report.courseName
            this.listLoading = false
          } else {
            this.$message.error(res.data.msg)
          }
        })
      }
    }
  }
</script>
