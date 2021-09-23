import request from '@/utils/request'

export function getWxSubscribeCode(id) {
  return request({
    url: '/teacher/subscribe/code/' + id,
    method: 'get'
  })
}

export function teacherList(params) {
  return request({
    url: '/teacher/list',
    method: 'get',
    params
  })
}

export function addTeacher(data) {
  return request({
    url: '/teacher/add',
    method: 'POST',
    data
  })
}

export function editTeacher(data) {
  return request({
    url: '/teacher/update',
    method: 'POST',
    data
  })
}

export function deleteTeacher(id) {
  return request({
    url: '/teacher/deleteById',
    method: 'POST',
    params: { id }
  })
}
