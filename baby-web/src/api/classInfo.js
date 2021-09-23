import request from '@/utils/request'

// --------------------------------------

// export function getClassInfoList(params) {
//   return request({
//     url: '/class/infos',
//     method: 'get',
//     params
//   })
// }
//
// export function getClassInfo(id) {
//   return request({
//     url: '/class/infos/' + id,
//     method: 'get'
//   })
// }
//
// export function saveClassInfoDetail(data) {
//   return request({
//     url: '/class/infos',
//     method: 'post',
//     data
//   })
// }
//
// export function updateClassInfoDetail(data) {
//   return request({
//     url: '/class/infos',
//     method: 'put',
//     data
//   })
// }
//
// export function deleteClassInfo(id) {
//   return request({
//     url: '/class/infos/' + id,
//     method: 'delete'
//   })
// }

// --------------------------------------

export function generateWeekReport(data) {
  return request({
    url: '/report/week',
    method: 'POST',
    data
  })
}

export function classInfoList(params) {
  return request({
    url: '/classes/list',
    method: 'get',
    params
  })
}

export function addClassInfo(data) {
  return request({//
    url: '/classes/add',
    method: 'POST',
    data
  })
}

export function editClassInfo(data) {
  return request({
    url: '/classes/update',
    method: 'POST',
    data
  })
}

export function deleteClassInfo(id) {
  return request({
    url: '/classes/deleteById',
    method: 'POST',
    params: { id }
  })
}

export function classPerson(params) {
  return request({
    url: '/classes/getClassPerson',
    method: 'get',
    params
  })
}

export function classCourse(params) {
  return request({
    url: '/classes/getClassCourse',
    method: 'get',
    params
  })
}

export function assignedPerson(data) {
  return request({
    url: '/classes/assigned/person',
    method: 'POST',
    data
  })
}

export function assignedCourse(data) {
  return request({
    url: '/classes/assigned/course',
    method: 'POST',
    data
  })
}

export function cancelClassCourse(data) {
  return request({
    url: '/classes/cancel/course',
    method: 'POST',
    data
  })
}

export function cancelClassStudent(data) {
  return request({
    url: '/classes/cancel/student',
    method: 'POST',
    data
  })
}

export function updateClassCourseStatus(data) {
  return request({
    url: '/classes/change/status',
    method: 'post',
    data
  })
}
