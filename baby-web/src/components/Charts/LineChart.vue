<template>
  <div :class="className" :style="{height:height,width:width}"></div>
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import { debounce } from '@/utils'

export default {
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '350px'
    },
    autoResize: {
      type: Boolean,
      default: true
    },
    chartData: {
      type: Object
    }
  },
  data() {
    return {
      chart: null
    }
  },
  mounted() {
    this.initChart()
    if (this.autoResize) {
      this.__resizeHanlder = debounce(() => {
        if (this.chart) {
          this.chart.resize()
        }
      }, 100)
      window.addEventListener('resize', this.__resizeHanlder)
    }
    // 监听侧边栏的变化
    const sidebarElm = document.getElementsByClassName('sidebar-container')[0]
    sidebarElm.addEventListener('transitionend', this.__resizeHanlder)
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    if (this.autoResize) {
      window.removeEventListener('resize', this.__resizeHanlder)
    }
    const sidebarElm = document.getElementsByClassName('sidebar-container')[0]
    sidebarElm.removeEventListener('transitionend', this.__resizeHanlder)

    this.chart.dispose()
    this.chart = null
  },
  watch: {
    chartData: {
      deep: true,
      handler(val) {
        this.setOptions(val)
      }
    }
  },
  methods: {
    setOptions({ legendData, totalViewData, indexViewData, courseDetailViewData, registeredData, xAxisData } = {}) {
      this.chart.setOption({
        xAxis: {
          data: xAxisData, // ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
          boundaryGap: false,
          axisTick: {
            show: false
          }
        },
        grid: {
          left: 10,
          right: 10,
          bottom: 20,
          top: 30,
          containLabel: true
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          },
          padding: [5, 10]
        },
        yAxis: {
          axisTick: {
            show: false
          }
        },
        legend: {
          data: legendData
        },
        series: [{
          name: '注册量', itemStyle: {
            normal: {
              color: 'rgb(137,189,27)',
              lineStyle: {
                color: 'rgb(137,189,27)',
                width: 2
              },
              areaStyle: {
                color: 'rgba(0,136,212,0.2)'
              }
            }
          },
          smooth: true,
          type: 'line',
          data: registeredData,
          animationDuration: 2800,
          animationEasing: 'cubicInOut'
        },
        {
          name: '总访问量', itemStyle: {
            normal: {
              color: 'rgb(137,189,27)',
              lineStyle: {
                color: 'rgb(137,189,27)',
                width: 2
              },
              areaStyle: {
                color: 'rgba(0,136,212,0.2)'
              }
            }
          },
          smooth: true,
          type: 'line',
          data: totalViewData,
          animationDuration: 2800,
          animationEasing: 'cubicInOut'
        },
        {
          name: '首页访问量',
          smooth: true,
          type: 'line',
          itemStyle: {
            normal: {
              color: 'rgb(0,136,212)',
              lineStyle: {
                color: 'rgb(0,136,212)',
                width: 2
              },
              areaStyle: {
                color: 'rgba(0,136,212,0.2)'
              }
            }
          },
          data: indexViewData,
          animationDuration: 2800,
          animationEasing: 'quadraticOut'
        },
        {
          name: '课程详情页访问量',
          smooth: true,
          type: 'line',
          itemStyle: {
            normal: {
              color: 'rgb(219,50,51)',
              lineStyle: {
                color: 'rgb(219,50,51)',
                width: 2
              },
              areaStyle: {
                color: '#f3f8ff'
              }
            }
          },
          data: courseDetailViewData,
          animationDuration: 2800,
          animationEasing: 'quadraticOut'
        }]
      })
    },
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')
      this.setOptions(this.chartData)
    }
  }
}
</script>
