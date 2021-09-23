import request from '@/utils/request'

export function getCourseGames(params) {
  return request({
    url: '/course/game/list',
    method: 'get',
    params
  })
}

export function removeCourseGames(data) {
  return request({
    url: '/course/game/remove',
    method: 'post',
    data
  })
}

export function assignGame(data) {
  return request({
    url: '/course/game/assign',
    method: 'post',
    data
  })
}

export function getCourseToClass(params) {
  return request({
    url: '/course/getCourseToClass',
    method: 'get',
    params
  })
}

export function courseList(params) {
  return request({
    url: '/course/list',
    method: 'get',
    params
  })
}

export function addCourse(data) {
  return request({
    url: '/course/add',
    method: 'POST',
    data
  })
}

export function editCourse(data) {
  return request({
    url: '/course/update',
    method: 'POST',
    data
  })
}

export function deleteCourse(id) {
  return request({
    url: '/course/deleteById',
    method: 'POST',
    params: { id }
  })
}

export function courseStageList(params) {
  return request({
    url: '/course/stage/list',
    method: 'get',
    params
  })
}

export function addCourseStage(data) {
  return request({
    url: '/course/stage/add',
    method: 'POST',
    data
  })
}

export function editCourseStage(data) {
  return request({
    url: '/course/stage/update',
    method: 'POST',
    data
  })
}

export function deleteCourseStage(id) {
  return request({
    url: '/course/stage/deleteById',
    method: 'POST',
    params: { id }
  })
}

export function getCourseStageById(id) {
  return request({
    url: '/course/stage/queryById',
    method: 'GET',
    params: { id }
  })
}

export function getCourseStageGroupList(id) {
  return request({
    url: '/course/stage/group',
    method: 'GET',
    params: { id }
  })
}

export function getCourseStageGroupById(id) {
  return request({
    url: '/course/stage/group/getById',
    method: 'GET',
    params: { id }
  })
}

export function getExperienceStage() {
  return request({
    url: '/course/stage/experience',
    method: 'GET'
  })
}

export function getCourseStageUserBought(personId) {
  return request({
    url: '/course/stage/user/bought',
    method: 'GET',
    params: { personId }
  })
}

export function openPersonCourse(id) {
  return request({
    url: '/course/user/open',
    method: 'POST',
    params: { id }
  })
}

export function updateCourseGroup(data) {
  return request({
    url: '/course/stage/update/group/course',
    method: 'POST',
    data
  })
}

export function editWeekCourse(data) {
  return request({
    url: '/week/course',
    method: 'POST',
    data
  })
}

export function getListByUnitId(unitId) {
  return request({
    url: '/week/' + unitId,
    method: 'GET'
  })
}

export function createUnitWeek(data) {
  return request({
    url: '/week',
    method: 'POST',
    data
  })
}

export function updateUnitWeek(data) {
  return request({
    url: '/week',
    method: 'PUT',
    data
  })
}

export function deleteUnitWeekById(weekId) {
  return request({
    url: '/week/' + weekId,
    method: 'DELETE'
  })
}

export function getWeekResources(weekId, params) {
  return request({
    url: '/week/resources/' + weekId,
    method: 'GET',
    params: params
  })
}

export function deleteWeekResourcesPhysically(id) {
  return request({
    url: '/week/resources/' + id,
    method: 'DELETE'
  })
}

export function batchAddWeekResources(data) {
  return request({
    url: '/week/resources',
    method: 'POST',
    data
  })
}
