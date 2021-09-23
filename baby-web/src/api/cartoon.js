import request from '@/utils/request'

export function getCartoonList(params) {
  return request({
    url: '/cartoon/list',
    method: 'get',
    params
  })
}

export function addCartoon(data) {
  return request({
    url: '/cartoon/add',
    method: 'POST',
    data
  })
}

export function deleteCartoonBatch(data) {
  return request({
    url: '/cartoon/deleteBatch',
    method: 'POST',
    data
  })
}

export function editCartoon(data) {
  return request({
    url: '/cartoon/update',
    method: 'POST',
    data
  })
}

export function removeVideoBatch(data) {
  return request({
    url: '/cartoon/removeVideoBatch',
    method: 'post',
    data
  })
}

export function updateVideoId(data) {
  return request({
    url: '/cartoon/updateVideoId',
    method: 'post',
    data
  })
}
