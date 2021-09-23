import request from '@/utils/request'

export function getGoodsList(params) {
  return request({
    url: '/goods/list',
    method: 'get',
    params
  })
}

export function getGoodsById(id) {
  return request({
    url: '/goods/queryById',
    method: 'get',
    params: { id }
  })
}

export function addGoods(data) {
  return request({
    url: '/goods/add',
    method: 'post',
    data
  })
}

export function updateGoods(data) {
  return request({
    url: '/goods/update',
    method: 'post',
    data
  })
}

export function deleteGoodsById(id) {
  return request({
    url: '/goods/deleteById',
    method: 'post',
    params: { id }
  })
}
