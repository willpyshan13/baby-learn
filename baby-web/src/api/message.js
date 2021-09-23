import request from '@/utils/request'

export function getMessageList(params) {
  return request({
    url: '/message/list',
    method: 'get',
    params
  })
}

export function getMessageById(id) {
  return request({
    url: '/message/queryById',
    method: 'get',
    params: { id }
  })
}

export function addMessage(data) {
  return request({
    url: '/message/add',
    method: 'post',
    data
  })
}

export function updateMessage(data) {
  return request({
    url: '/message/update',
    method: 'post',
    data
  })
}

export function deleteMessageById(id) {
  return request({
    url: '/message/deleteById',
    method: 'post',
    params: { id }
  })
}
