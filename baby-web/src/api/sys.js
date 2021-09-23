import request from '@/utils/request'

export function getMenuList(params) {
  return request({
    url: '/sys/menu/list',
    method: 'get',
    params
  })
}

export function menuTree(params) {
  return request({
    url: '/sys/menu/tree',
    method: 'get',
    params
  })
}

export function getParentMenu(params) {
  return request({
    url: '/sys/menu/getParentMenu',
    method: 'get',
    params
  })
}

export function addMenu(data) {
  return request({
    url: '/sys/menu/add',
    method: 'POST',
    data
  })
}

export function editMenu(data) {
  return request({
    url: '/sys/menu/update',
    method: 'POST',
    data
  })
}

export function deleteMenu(id) {
  return request({
    url: '/sys/menu/deleteById',
    method: 'POST',
    params: { id }
  })
}
