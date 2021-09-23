import request from '@/utils/request'

export function getCasualUserList(params) {
  return request({
    url: '/casual/user/list',
    method: 'get',
    params
  })
}

export function deleteCasualUser(id) {
  return request({
    url: '/casual/user/deleteById',
    method: 'POST',
    params: { id }
  })
}

export function deleteBuyStage(personId, stageId) {
  return request({
    url: '/user/deleteBuyStage',
    method: 'post',
    params: { personId, stageId }
  })
}

export function buyCourse(data) {
  return request({
    url: '/user/buyCourse',
    method: 'post',
    data
  })
}

export function getLearnRecords(params) {
  return request({
    url: '/user/learn/records',
    method: 'get',
    params
  })
}

export function getLearnReport(params) {
  return request({
    url: '/user/learn/report',
    method: 'get',
    params
  })
}

export function getPersonToClass(params) {
  return request({
    url: '/user/getPersonToClass',
    method: 'get',
    params
  })
}

export function personList(params) {
  return request({
    url: '/user/list',
    method: 'get',
    params
  })
}

export function addPerson(data) {
  return request({
    url: '/user/add',
    method: 'POST',
    data
  })
}

export function editPerson(data) {
  return request({
    url: '/user/update',
    method: 'POST',
    data
  })
}

export function deletePerson(id) {
  return request({
    url: '/user/deleteById',
    method: 'POST',
    params: { id }
  })
}
