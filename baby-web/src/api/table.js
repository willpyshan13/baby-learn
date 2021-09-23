import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/course/stage/list',
    method: 'get',
    params
  })
}
export function getMenuList() {
  return request({
    url: '/sys/menu/nav',
    method: 'get'
  })
}
