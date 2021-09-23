import request from '@/utils/request'

export function getVideoTypeList(params) {
  return request({
    url: '/videoType/list',
    method: 'get',
    params
  })
}
export function addVideoType(data) {
  return request({
    url: '/videoType/add',
    method: 'POST',
    data
  })
}
export function deleteVideoType(id) {
  return request({
    url: '/videoType/deleteById',
    method: 'POST',
    params: { id }
  })
}
export function editVideoType(data) {
  return request({
    url: 'videoType/update',
    method: 'POST',
    data
  })
}
