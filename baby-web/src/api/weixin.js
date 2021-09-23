import request from '@/utils/request'

export function getMpButtons() {
  return request({
    url: '/wechat/menu/get',
    method: 'get'
  })
}

export function createMpMenu(data) {
  return request({
    url: '/wechat/menu/mp/create',
    method: 'post',
    data
  })
}
