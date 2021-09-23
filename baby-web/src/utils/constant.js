// person

export const typeOptions = [
  { key: false, display_name: '女' },
  { key: true, display_name: '男' }
]

// arr to obj ,such as { CN : "China", US : "USA" }
const typeKeyValue = typeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.display_name
  return acc
}, {})

export function personGenderFilter(type) {
  return typeKeyValue[type]
}

export function statusFilter(status) {
  const statusMap = {
    0: 'success',
    2: 'info',
    1: 'danger'
  }
  return statusMap[status]
}

export const sexDic = [
  { code: 0, codeText: '未知' },
  { code: 1, codeText: '男' },
  { code: 2, codeText: '女' }
]

export const userTypeDic = [
  { code: 1, codeText: '管理员' },
  { code: 2, codeText: '销售员' }
]

export const appVersionStatus = [
  { key: 0, display_name: '暂停使用' },
  { key: 1, display_name: '正式发布' },
  { key: 2, display_name: '等待发布' }
]

export const wechatMenuButtonType = [
  // { key: 'click', display_name: '点击事件' },
  { key: 'view', display_name: '页面跳转' },
  { key: 'miniprogram', display_name: '小程序' }
]

export const resourceType = [
  { key: 1, display_name: '视频' },
  { key: 2, display_name: '音频' },
  { key: 3, display_name: '图片' },
  { key: 4, display_name: '文件' }
]

export const weekResourceType = [
  { key: 1, display_name: '歌曲' },
  { key: 2, display_name: '故事' }
]

export const targetMethod = [
  // { key: 'openClassCourse', display_name: '班级课程开放' },
  // { key: 'classBeginMessage', display_name: '课程开始通知' },
  // { key: 'generateRecording', display_name: '关联录制件' },

  { key: 'generateYesterdayRecording', display_name: '生成昨日教室录制件' },
  // { key: 'generateByRoomId', display_name: '生成教室录制件' },
  { key: 'liveBroadcastNextDay', display_name: '次日直播通知' },
  // { key: 'openExperiencePersonCourse', display_name: '体验用户课程打开' },
  { key: 'openPersonCourse', display_name: '用户课程打开' }
]

export const targetObject = [
  // { key: 'openClassCourseJob', display_name: '班级课程调度' },
  // { key: 'messageJob', display_name: '消息推送调度' },
  // { key: 'recordingGeneratorJob', display_name: '录制件生成调度' },
  { key: 'pushJob', display_name: '推送任务' },
  { key: 'courseJob', display_name: '课程任务' },
  { key: 'recordingJob', display_name: '录制件任务' }
]

export const roomUserType = [
  { key: -1, display_name: '所有用户' },
  { key: 0, display_name: '体验用户' },
  { key: 1, display_name: '正常用户' },
  { key: 2, display_name: '班级用户' }
]

export const classType = [
  { key: 1, display_name: '一对一' },
  { key: 2, display_name: '一对四' },
  { key: 3, display_name: '一对多' }
]

export const logType = [
  { key: 'sys', display_name: '后台日志' },
  { key: 'api', display_name: '接口日志' }
]

export const messageType = [
  { key: 1, display_name: '普通消息' },
  { key: 2, display_name: '语音消息' },
  { key: 3, display_name: '通知消息' },
  { key: 4, display_name: '上课开始' },
  { key: 5, display_name: '强制登出' },
  { key: 6, display_name: '更新标签' }
]

export const pushType = [
  { key: 1, display_name: '个体推送' },
  { key: 2, display_name: '班级推送' }
]

export const expressStatus = [
  { key: 0, display_name: '未发货' },
  { key: 1, display_name: '已发货' },
  { key: 2, display_name: '已签收' }
]

export const machineStatus = [
  { key: 0, display_name: '黑名单' },
  { key: 1, display_name: '白名单' },
  { key: 2, display_name: '正 常' }
]

export const articleStatus = [
  { key: 0, display_name: '已删除' },
  { key: 1, display_name: '已发布' },
  { key: 2, display_name: '草 稿' }
]

export const menuTypeOptions = [
  { key: 0, display_name: '目录' },
  { key: 1, display_name: '菜单' },
  { key: 2, display_name: '按钮' }
]

export const gender = [
  { key: 0, display_name: '女' },
  { key: 1, display_name: '男' }
]
export const teacherType = [
  { key: 0, display_name: '教师' },
  { key: 1, display_name: '助教' },
  { key: 2, display_name: '班主任' }
]

export const statusOptions = [
  { key: 1, display_name: '生效' },
  { key: 0, display_name: '失效' }
]

export const courseType = [
  { key: 0, display_name: '录屏' },
  { key: 1, display_name: '直播' }
]

export const viewingAuthority = [
  { key: 0, display_name: '购买可看' },
  { key: 1, display_name: '登录可看' },
  { key: 2, display_name: '游客可看' }
]

export const gameTypeOptions = [
  { key: 0, display_name: '文字' },
  { key: 1, display_name: '图片' },
  { key: 2, display_name: '视频' }
]

export const roomType = [
  { key: 1, display_name: '一对一' },
  { key: 2, display_name: '一对四' },
  { key: 3, display_name: '一对多' }
]

export const passwordRequired = [
  { key: 0, display_name: '否' },
  { key: 1, display_name: '是' }
]

export const yesNoStatusOptions = [
  { key: 0, display_name: '否' },
  { key: 1, display_name: '是' }
]

export const trueFalseOptions = [
  { key: false, display_name: '否' },
  { key: true, display_name: '是' }
]

export const videoType = [
  // { key: 0, display_name: '176*144' },
  // { key: 1, display_name: '320*240' },
  { key: 2, display_name: '640*480' },
  { key: 3, display_name: '1280*720' },
  { key: 4, display_name: '1920*1080' }
]

export const videoFrameRate = [
  { key: 10, display_name: '10' },
  { key: 15, display_name: '15' },
  { key: 20, display_name: '20' },
  { key: 25, display_name: '25' },
  { key: 30, display_name: '30' }
]

export const coursewareType = [
  { key: 1, display_name: '视频' },
  { key: 2, display_name: '音频' },
  { key: 3, display_name: '文档' }
]

export const dynamicPPT = [
  { key: 0, display_name: '非动态 PPT' },
  { key: 1, display_name: '动态 PPT' }
]

export const videoStatus = [
  { key: 0, display_name: '生效' },
  { key: 1, display_name: '已删除' },
  { key: 2, display_name: '失效' }
]

export const appType = [
  { key: 1, display_name: '我的课程' },
  { key: 2, display_name: '播放器' },
  { key: 3, display_name: '一起玩-爱奇艺' },
  { key: 4, display_name: '一起玩-小企鹅' },
  { key: 5, display_name: '跳舞' },
  { key: 6, display_name: '音乐' },
  { key: 7, display_name: '故事' },
  { key: 8, display_name: '悬浮框' },
  { key: 9, display_name: '语音点击' },
  { key: 10, display_name: '长颈鹿点击' }
]

export const taskStatus = [
  { key: 'NORMAL', display_name: '正常' },
  { key: 'PAUSED', display_name: '暂停' },
  { key: 'COMPLETE', display_name: '完成' },
  { key: 'ERROR', display_name: '异常' },
  { key: 'BLOCKED', display_name: '堵塞' }
]

export const taskLogStatus = [
  { key: 'I', display_name: '执行中' },
  { key: 'S', display_name: '成功' },
  { key: 'E', display_name: '异常' }
]

export const eventType = [
  { key: 1, display_name: '停留事件' },
  { key: 2, display_name: '点击事件' }
]

export const pointsSource = [
  { key: 1, display_name: '玩游戏-初始' },
  { key: 2, display_name: '玩游戏-补加' },
  { key: 3, display_name: '玩游戏-复习' },
  { key: 4, display_name: '大声说-初始' },
  { key: 5, display_name: '大声说-补加' },
  { key: 6, display_name: '大声说-复习' },
  { key: 7, display_name: '签到' },
  { key: 8, display_name: '观看视频' },
  { key: 9, display_name: '兑换商品' }
]
export const pointsType = [
  // { key: 1, display_name: '蛋糕' },
  { key: 2, display_name: '糖果' }
]

export function keyValueFilter([array, value]) {
  const temp = array.reduce((acc, cur) => {
    acc[cur.key] = cur.display_name
    return acc
  }, {})
  return temp[value]
}

export function fileSizeFilter(value) {
  if (value == null || value === '') {
    return '0 Bytes'
  }
  const unitArr = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB']
  let index = 0

  let quotient = parseFloat(value)
  while (quotient > 1024) {
    index += 1
    quotient = quotient / 1024
  }

  let tempNumber
  if (quotient >= 0) {
    tempNumber = parseInt((quotient * Math.pow(10, 2) + 0.5)) / Math.pow(10, 2)
    return tempNumber + ' ' + unitArr[index]
  } else {
    const numberRound1 = -quotient
    tempNumber = parseInt((numberRound1 * Math.pow(10, 2) + 0.5)) / Math.pow(10, 2)
    return -tempNumber + ' ' + unitArr[index]
  }
}

export function durationFilter(time) {
  const hours = parseInt((time % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60))
  const minutes = parseInt((time % (1000 * 60 * 60)) / (1000 * 60))
  const seconds = parseInt((time % (1000 * 60)) / 1000)

  let str = ''
  if (hours && hours !== 0) {
    str += hours + '小时'
  }
  if (minutes && minutes !== 0) {
    str += minutes + '分'
  }
  if (seconds && seconds !== 0) {
    str += parseInt(seconds) + '秒'
  }

  return str
}

export function durationFilterSecond(time) {
  const hours = parseInt((time % (60 * 60 * 24)) / (60 * 60))
  const minutes = parseInt((time % (60 * 60)) / (60))
  const seconds = parseInt((time % (60)))

  let str = ''
  if (hours && hours !== 0) {
    str += hours + '小时'
  }
  if (minutes && minutes !== 0) {
    str += minutes + '分'
  }
  if (seconds && seconds !== 0) {
    str += parseInt(seconds) + '秒'
  }

  return str
}
