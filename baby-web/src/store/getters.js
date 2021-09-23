const getters = {
  sidebar: state => state.app.sidebar,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  roles: state => state.user.roles,
  language: state => state.app.language,
  cachedViews: state => state.tagsView.cachedViews,
  classId: state => state.classInfo.id,
  currentView: state => state.tagsView.currentView
}
export default getters
