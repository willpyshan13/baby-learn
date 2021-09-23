import request from '@/utils/request'

export function addStageGroup(data) {
  return request({
    url: '/course/stage/group/create',
    method: 'POST',
    data
  })
}

export function editStageGroup(data) {
  return request({
    url: '/course/stage/group/update',
    method: 'POST',
    data
  })
}

export function deleteStageGroup(id) {
  return request({
    url: '/course/stage/group/deleteById',
    method: 'POST',
    params: { id }
  })
}

export function deleteStageGroupGoods(id) {
  return request({
    url: '/course/stage/group/goods/deleteById',
    method: 'POST',
    params: { id }
  })
}

export function getStageGroupGoods(params) {
  return request({
    url: '/course/stage/group/goods',
    method: 'get',
    params
  })
}

export function addGroupGoodsBatch(data) {
  return request({
    url: 'course/stage/group/goods/add/batch',
    method: 'POST',
    data
  })
}

export function updateGroupGoodsBatch(data) {
  return request({
    url: 'course/stage/group/goods/update',
    method: 'POST',
    data
  })
}
