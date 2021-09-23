<template>
  <div class="dashboard-editor-container">

    <div class="">
      <el-form label-width="80px" label-position="left" size="mini">
        <el-row>
          <el-col :span="5">
            <el-form-item label="时间段">
              <el-date-picker
                size="mini"
                v-model="dateTimeRange"
                type="daterange"
                value-format="yyyy-MM-dd"
                range-separator="至"
                start-placeholder="开始时间"
                end-placeholder="结束时间"
                :default-time="['00:00:00', '23:59:59']"
                :picker-options="dateTimeRangePickerOptions">
              </el-date-picker>
              <!--<el-date-picker
                v-model="timeRange"
                :picker-options="pickerOptions"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                :default-time="['00:00:00', '23:59:59']">
              </el-date-picker>-->
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item>
              <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}
            </el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <panel-group @handleSetLineChartData="handleSetLineChartData" :view-total="viewTotal" :registered-total="registeredTotal"/>

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <line-chart :chart-data="lineChartData"/>
    </el-row>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import LineChart from '../../components/Charts/LineChart'
import PanelGroup from './PanelGroup'
import { getPageviews, countRegistered } from '@/api/pageviews'
import { videoStatus } from '@/utils/constant'
import { parseTime } from '../../utils'

const lineChartData = {
  newVisitis: {
    totalViewData: [],
    indexViewData: [100, 120, 161, 134, 105, 160, 165],
    courseDetailViewData: [120, 82, 91, 154, 162, 140, 145],
    xAxisData: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
    legendData: ['总访问量', '首页访问量', '课程详情页访问量']
  },
  messages: {
    registeredData: [],
    xAxisData: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
    legendData: ['注册量']
  },
  purchases: {
    totalViewData: [],
    indexViewData: [80, 100, 121, 104, 105, 90, 100],
    courseDetailViewData: [120, 90, 100, 138, 142, 130, 130],
    xAxisData: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
  },
  shoppings: {
    totalViewData: [],
    indexViewData: [130, 140, 141, 142, 145, 150, 160],
    courseDetailViewData: [120, 82, 91, 154, 162, 140, 130],
    xAxisData: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
  }
}

export default {
  components: {
    PanelGroup,
    LineChart },
  name: 'dashboard',
  computed: {
    ...mapGetters([
      'name',
      'roles'
    ])
  },
  mounted() {
    const current = new Date()
    this.dateTimeRange = [parseTime(current.getTime() - 3600 * 1000 * 24 * 30, '{y}-{m}-{d}'), parseTime(current, '{y}-{m}-{d}')]
    this.handleGetPageviews({ startDay: this.dateTimeRange[0], endDay: this.dateTimeRange[1] })
    // this.handleGetPageviews({ startDay: null, endDay: null })
  },
  data() {
    return {
      dateTimeRange: null,
      videoStatus: videoStatus,
      lineChartData: lineChartData.newVisitis,
      viewTotal: null,
      registeredTotal: null,
      dateTimeRangePickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
            picker.$emit('pick', [start, end])
          }
        }]
      }
    }
  },
  methods: {
    handleFilter() {
      this.viewTotal = 0
      this.registeredTotal = 0
      this.handleGetPageviews({ startDay: this.dateTimeRange[0], endDay: this.dateTimeRange[1] })
    },
    handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type]
    },
    handleGetPageviews(params) {
      getPageviews({ startDay: params.startDay, endDay: params.endDay }).then(res => {
        lineChartData.newVisitis.xAxisData = res.data.xAxis
        lineChartData.newVisitis.totalViewData = res.data.data
        res.data.data.forEach(row => {
          this.viewTotal += row
        })
        this.handleSetLineChartData('newVisitis')
      })

      getPageviews({ type: 1, startDay: params.startDay, endDay: params.endDay }).then(res => {
        lineChartData.newVisitis.xAxisData = res.data.xAxis
        lineChartData.newVisitis.indexViewData = res.data.data
        this.handleSetLineChartData('newVisitis')
      })

      getPageviews({ type: 2, startDay: params.startDay, endDay: params.endDay }).then(res => {
        lineChartData.newVisitis.xAxisData = res.data.xAxis
        lineChartData.newVisitis.courseDetailViewData = res.data.data
        this.handleSetLineChartData('newVisitis')
      })

      countRegistered({ startDay: params.startDay, endDay: params.endDay }).then(res => {
        lineChartData.messages.xAxisData = res.data.xAxis
        lineChartData.messages.registeredData = res.data.data
        res.data.data.forEach(row => {
          this.registeredTotal += row
        })
        this.handleSetLineChartData('messages')
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px
  }
}
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
.card-panel-description {
  float: right;
  font-weight: bold;
  margin: 26px;
  margin-left: 0px;
  .card-panel-text {
    line-height: 18px;
    color: rgba(0, 0, 0, 0.45);
    font-size: 16px;
    margin-bottom: 12px;
  }
  .card-panel-num {
    font-size: 20px;
  }
}
</style>
