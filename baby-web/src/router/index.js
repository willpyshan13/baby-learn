import Vue from 'vue'
import Router from 'vue-router'
// const _import = require('./_import_' + process.env.NODE_ENV)
// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/authredirect', component: () => import('@/views/login/authredirect'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },
  { path: '/401', component: () => import('@/views/404'), hidden: true },
  {
    path: '',
    component: Layout,
    redirect: 'dashboard',
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index'),
      name: 'dashboard',
      meta: { title: 'dashboard', icon: 'dashboard', noCache: true }
    }]
  },
  // 系统管理模块
  {
    path: '/sys',
    component: Layout,
    redirect: '/user/list',
    name: 'sysManage',
    meta: {
      title: 'sysManage',
      icon: 'user'
    },
    children: [
      { path: 'app/update', component: () => import('@/views/module/app/appUpdate'), name: 'appUpdateSetting', meta: { title: 'appUpdateSetting', icon: 'students' }},
      { path: 'app/update/machine', component: () => import('@/views/module/app/machine'), name: 'appUpdateSettingMachine', meta: { title: 'machineList', icon: 'listMachine', noCache: true }},
      // { path: 'manager', component: () => import('@/views/module/sys/manager/manager'), name: 'User', meta: { title: 'systemUser', icon: 'casual' }},
      { path: 'role', component: () => import('@/views/module/sys/role/role'), name: 'Role', meta: { title: 'roleManage', icon: 'casual' }},
      { path: 'menu', component: () => import('@/views/menu/index'), name: 'Menu', meta: { title: 'menuManage', icon: 'casual' }},
      { path: 'scheduler', component: () => import('@/views/module/scheduler/scheduler'), name: 'Scheduler', meta: { title: 'schedulerList', icon: 'casual' }},
      { path: 'logs', component: () => import('@/views/module/logs/sysLog'), name: 'SysLogs', meta: { title: 'sysLogList', icon: 'log' }},
      { path: '/wechat/mp/menu', component: () => import('@/views/module/wechat/mpMenu'), name: 'WxMpMenus', meta: { title: 'wxMpMenus', icon: 'wechat' }}
    ]
  },
  // 平台管理模块(系统管理模块改造, 当前完成系统用户管理模块)
  {
    path: '/platform',
    component: Layout,
    redirect: '/users',
    name: 'platformManage',
    meta: {
      title: 'sysManage',
      icon: 'user'
    },
    children: [
      { path: '/system/users', component: () => import('@/views/module/sys/user/index'), name: 'SystemUserList', meta: { title: 'systemUserManage', icon: 'students' }},
      { path: '/system/users/add', component: () => import('@/views/module/sys/user/add'), name: 'CreateSystemUser', meta: { title: 'createSystemUser', icon: 'stage', noCache: true }, hidden: true },
      { path: '/system/users/edit/:id(\\d+)', component: () => import('@/views/module/sys/user/edit'), name: 'UpdateSystemUser', meta: { title: 'updateSystemUser', icon: 'stage', noCache: true }, hidden: true }
      // { path: '/class/infos', component: () => import('@/views/module/class/index'), name: 'ClassInfoList', meta: { title: 'classManage', icon: 'students' }},
      // { path: '/class/infos/add', component: () => import('@/views/module/class/add'), name: 'CreateClassInfo', meta: { title: 'createClassInfo', icon: 'students' }},
      // { path: '/class/infos/edit/:id(\\d+)', component: () => import('@/views/module/class/edit'), name: 'UpdateClassInfo', meta: { title: 'updateClassInfo', icon: 'students' }}

    ]
  },
  // 销售管理
  {
    path: '/sellers',
    component: Layout,
    redirect: '/system/sellers',
    name: 'platformManage',
    meta: {
      title: 'sysManage',
      icon: 'user'
    },
    children: [
      { path: '/system/sellers', component: () => import('@/views/module/sys/seller/index'), name: 'SystemSellerList', meta: { title: 'systemSellerManage', icon: 'students' }},
      { path: '/system/sellers/add', component: () => import('@/views/module/sys/seller/add'), name: 'CreateSystemSeller', meta: { title: 'createSystemSeller', icon: 'stage', noCache: true }, hidden: true },
      { path: '/system/sellers/edit/:id(\\d+)', component: () => import('@/views/module/sys/seller/edit'), name: 'UpdateSystemSeller', meta: { title: 'updateSystemSeller', icon: 'stage', noCache: true }, hidden: true }
      // { path: '/class/infos', component: () => import('@/views/module/class/index'), name: 'ClassInfoList', meta: { title: 'classManage', icon: 'students' }},
      // { path: '/class/infos/add', component: () => import('@/views/module/class/add'), name: 'CreateClassInfo', meta: { title: 'createClassInfo', icon: 'students' }},
      // { path: '/class/infos/edit/:id(\\d+)', component: () => import('@/views/module/class/edit'), name: 'UpdateClassInfo', meta: { title: 'updateClassInfo', icon: 'students' }}

    ]
  },
  // 用户管理模块
  {
    path: '/user',
    component: Layout,
    redirect: '/user/list',
    name: 'userManage',
    meta: {
      title: 'userManage',
      icon: 'user'
    },
    children: [
      { path: 'list', component: () => import('@/views/module/person/person'), name: 'Person', meta: { title: 'studentManage', icon: 'students' }},
      { path: 'casual', component: () => import('@/views/module/casual/list'), name: 'CasualPerson', meta: { title: 'casualUserList', icon: 'casual' }},
      { path: 'teacher', component: () => import('@/views/module/teacher'), name: 'Teacher', meta: { title: 'teacherManage', icon: 'casual' }},
      { path: 'course/:id(\\d+)', component: () => import('@/views/module/person/personStage'), name: 'courseBought', meta: { title: 'boughtCourse', icon: 'course' }},
      { path: 'learn/record/:id(\\d+)', component: () => import('@/views/module/person/learningRecords'), name: 'learnRecord', meta: { title: 'learnRecord', icon: 'course' }}
    ]
  },
  // 学堂管理模块
  {
    path: '/school',
    component: Layout,
    redirect: '/school/course/stage',
    name: 'schoolManage',
    meta: {
      title: 'schoolManage',
      icon: 'user'
    },
    children:
      [
        // 课阶
        { path: 'course/stage', component: () => import('@/views/module/courseStage/courseStage'), name: 'CourseStage', meta: { title: 'stageMange', icon: 'stage' }},
        { path: 'course/stage/create', component: () => import('@/views/module/courseStage/create'), name: 'CourseStageCreate', meta: { title: 'courseStageCreate', icon: 'stage', noCache: true }, hidden: true },
        { path: 'course/stage/edit/:id(\\d+)', component: () => import('@/views/module/courseStage/edit'), name: 'CourseStageEdit', meta: { title: 'courseStageEdit', icon: 'stage', noCache: true }, hidden: true },
        // 课件
        { path: 'courseware', component: () => import('@/views/module/courseware/courseware'), name: 'Courseware', meta: { title: 'coursewareList', icon: 'courseware' }},

        // 班级相关
        { path: 'classes/:cName?', component: () => import('@/views/module/class/classes'), name: 'Classes', meta: { title: 'classManage', icon: 'class', noCache: true }},
        { path: 'classes/students/:id(\\d+)/:currentClassName(\\S+)', component: () => import('@/views/module/class/classPersonOperations'), name: 'classPersonOperations', meta: { title: 'classStudent', icon: 'class', noCache: true }},
        { path: 'classes/courses/:id(\\d+)/:currentClassName(\\S+)', component: () => import('@/views/module/class/classCourseOperations'), name: 'classCourseOperations', meta: { title: 'classCourse', icon: 'class', noCache: true }},

        // 课程相关
        { path: 'course', component: () => import('@/views/module/course/course'), name: 'Course', meta: { title: 'courseManage', icon: 'course' }},
        { path: 'course/single/choice/:id(\\d+)', component: () => import('@/views/module/course/courseMultipleChoiceGame'), name: 'playGames', meta: { title: 'playGames', icon: 'games', noCache: true }},
        { path: 'course/word/read/:id(\\d+)', component: () => import('@/views/module/course/courseVoiceGame'), name: 'speakUp', meta: { title: 'speakLoudly', icon: 'games', noCache: true }},

        // 教室相关
        { path: 'room', component: () => import('@/views/module/room/room'), name: 'Room', meta: { title: 'roomList', icon: 'room' }},
        { path: 'room/courseware/:id(\\d+)', component: () => import('@/views/module/room/roomCourseware'), name: 'roomCourseware', meta: { title: 'roomCourseware', icon: 'games', noCache: true }},
        { path: 'room/recording/:id(\\d+)', component: () => import('@/views/module/room/roomRecordings'), name: 'roomRecordings', meta: { title: 'roomRecordings', icon: 'recording', noCache: true }}

      ]
  },
  // 资源管理模块
  {
    path: '/resource',
    component: Layout,
    redirect: '/resource/video',
    name: 'resourceManage',
    meta: {
      title: 'resourceManage',
      icon: 'recourse'
    },
    children: [
      { path: 'video', component: () => import('@/views/module/video/video'), name: 'Video', meta: { title: 'videoList', icon: 'video' }},
      { path: 'cartoon', component: () => import('@/views/module/cartoon/cartoon'), name: 'Cartoon', meta: { title: 'cartoonList', icon: 'cartoon' }},
      { path: 'cartoon', name: 'cartoonList', component: () => import('@/views/module/cartoon/cartoonVideoOperations'), meta: { title: 'cartoonList', icon: 'cartoon' },
        children: [
          { path: 'content/:id(\\d+)', name: 'cartoonContent', meta: { title: 'cartoonContent', icon: 'cartoon' }}
        ]
      },
      { path: 'video/type', component: () => import('@/views/module/videoType/videoType'), name: 'videoTypeManage', meta: { title: 'videoTypeManage', icon: 'video' }},
      { path: 'audio', component: () => import('@/views/module/audio/audio'), name: 'AudioModule', meta: { title: 'audioManage', icon: 'audio' }},
      { path: 'resources', component: () => import('@/views/module/resource/list'), name: 'Resource', meta: { title: 'resourceManage', icon: 'video', noCache: true }},
      { path: 'resources/create', component: () => import('@/views/module/resource/create'), name: 'ResourceCreate', meta: { title: 'uploadResources', icon: 'resource', noCache: true }, hidden: true },
      { path: 'resources/edit/:id(\\d+)', component: () => import('@/views/module/resource/edit'), name: 'ResourceEdit', meta: { title: 'resourceEdit', icon: 'resource', noCache: true }, hidden: true }
    ]
  },
  // 游戏管理模块
  {
    path: '/games',
    component: Layout,
    redirect: '/games/play/games',
    name: 'gamesManage',
    meta: {
      title: 'gamesManage',
      icon: 'recourse'
    },
    children: [
      { path: 'play/game', component: () => import('@/views/module/game/game'), name: 'PlayGames', meta: { title: 'gameManage', icon: 'video' }},
      { path: 'speak/loudly', component: () => import('@/views/module/voice/voice'), name: 'SpeakUp', meta: { title: 'voiceManage', icon: 'cartoon' }}
    ]
  },
  // 日志管理模块
  {
    path: '/logs',
    component: Layout,
    redirect: '/logs/use/app',
    name: 'logsManager',
    meta: {
      title: 'logsManager',
      icon: 'recourse'
    },
    children: [
      { path: 'use/app', component: () => import('@/views/module/logs/useLogs'), name: 'UseLogs', meta: { title: 'useLogs', icon: 'video' }},
      { path: 'push', component: () => import('@/views/module/logs/pushLog'), name: 'PushLogs', meta: { title: 'pushLogs', icon: 'cartoon' }}
    ]
  },
  // 文章管理模块
  {
    path: '/article',
    component: Layout,
    redirect: '/article/list',
    name: 'article',
    meta: {
      title: 'article',
      icon: 'article'
    },
    children: [
      { path: 'create', component: () => import('@/views/module/article/create'), name: 'createArticle', meta: { title: 'createArticle', icon: 'edit' }},
      { path: 'edit/:id(\\d+)', component: () => import('@/views/module/article/edit'), name: 'editArticle', meta: { title: 'editArticle', noCache: true }, hidden: true },
      { path: 'list', component: () => import('@/views/module/article/list'), name: 'Article', meta: { title: 'articleList', icon: 'list' }}
    ]
  },
  // 用户积分相关
  {
    path: '/integral',
    component: Layout,
    redirect: '/integral/detail',
    name: 'integral',
    meta: {
      title: 'integral',
      icon: 'integral'
    },
    children: [
      { path: 'detail/:id(\\d+)', component: () => import('@/views/module/integral/list'), name: 'integralDetail', meta: { title: 'integralDetail', noCache: true }, hidden: true },
      { path: 'exchange/:pid(\\d+)', component: () => import('@/views/module/integral/create'), name: 'createExchangeGoods', meta: { title: 'exchangeGoods', icon: 'create' }},
      { path: 'exchange/:pid(\\d+)/:id(\\d+)', component: () => import('@/views/module/integral/edit'), name: 'editExchangeGoods', meta: { title: 'exchangeGoods', icon: 'edit' }}
    ]
  },
  // 商品管理模块
  {
    path: '/goods',
    component: Layout,
    redirect: '/goods/list',
    name: 'goodsManage',
    meta: {
      title: 'goodsManage',
      icon: 'goods'
    },
    children: [
      { path: 'create', component: () => import('@/views/module/goods/create'), name: 'createGoods', meta: { title: 'createGoods', icon: 'edit' }},
      { path: 'list', component: () => import('@/views/module/goods/list'), name: 'Goods', meta: { title: 'goodsList', icon: 'goods' }},
      { path: 'edit/:id(\\d+)', component: () => import('@/views/module/goods/edit'), name: 'editGoods', meta: { title: 'editGoods', noCache: true }, hidden: true }
    ]
  },
  // 消息推送模块
  {
    path: '/message',
    component: Layout,
    redirect: '/message/list',
    name: 'messageCenter',
    meta: {
      title: 'messageCenter',
      icon: 'message'
    },
    children: [
      { path: 'create', component: () => import('@/views/module/push/create'), name: 'pushMessage', meta: { title: 'pushMessage', icon: 'edit' }},
      { path: 'list', component: () => import('@/views/module/push/list'), name: 'Message', meta: { title: 'messageList', icon: 'goods' }},
      { path: 'edit/:id(\\d+)', component: () => import('@/views/module/push/edit'), name: 'editMessage', meta: { title: 'editMessage', noCache: true }, hidden: true }
    ]
  },
  // 版本管理模块
  {
    path: '/models',
    component: Layout,
    redirect: '/platform/models',
    name: 'versionManage',
    meta: {
      title: 'versionManage',
      icon: 'version'
    },
    children: [
      // { path: 'create', component: () => import('@/views/module/push/create'), name: 'pushMessage', meta: { title: 'pushMessage', icon: 'edit' }},
      { path: '/platform/models', component: () => import('@/views/module/machine/machineModelList'), name: 'machineModel', meta: { title: 'modelList', icon: 'model' }},
      { path: '/platform/machines', component: () => import('@/views/module/machine/machineInfoList'), name: 'machineInfo', meta: { title: 'machineList', icon: 'model' }},
      { path: '/platform/app/versions', component: () => import('@/views/module/machine/appVersionList'), name: 'appVersion', meta: { title: 'appVersionList', icon: 'model' }},
      { path: '/platform/app/info', component: () => import('@/views/module/machine/appInfoList'), name: 'appInfo', meta: { title: 'appInfoList', icon: 'model' }}
    ]
  },
  // 幼儿园管理模块
  {
    path: '/kindergarten',
    component: Layout,
    redirect: '/kindergarten/infos',
    name: 'KindergartenManage',
    meta: {
      title: 'kindergartenManage',
      icon: 'version'
    },
    children: [
      { path: '/kindergarten/infos', component: () => import('@/views/module/kindergarten/list'), name: 'KindergartenInfo', meta: { title: 'kindergartenList', icon: 'model' }},
      { path: '/kindergarten/infos/create', component: () => import('@/views/module/kindergarten/create'), name: 'KindergartenCreate', meta: { title: 'createKindergarten', icon: 'resource', noCache: true }, hidden: true },
      { path: '/kindergarten/infos/edit/:id(\\d+)', component: () => import('@/views/module/kindergarten/edit'), name: 'KindergartenEdit', meta: { title: 'updateKindergarten', icon: 'resource', noCache: true }, hidden: true }
    ]
  },
  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history',
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

