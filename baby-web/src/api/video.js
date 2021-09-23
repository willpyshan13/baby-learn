import request from '@/utils/request'

export function getVideoTypeList(params) {
  return request({
    url: '/videoType/list',
    method: 'get',
    params
  })
}

export function getVideoList(params) {
  return request({
    url: '/video/list',
    method: 'get',
    params
  })
}

export function addVideo(data) {
  return request({
    url: '/video/add',
    method: 'POST',
    data
  })
}

export function deleteVideoBatch(data) {
  return request({
    url: '/video/deleteBatch',
    method: 'POST',
    data
  })
}

export function editVideo(data) {
  return request({
    url: '/video/update',
    method: 'POST',
    data
  })
}
