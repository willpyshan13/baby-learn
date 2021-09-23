import request from '@/utils/request'

export function getPushLog(params) {
  return request({
    url: '/pushLog/list',
    method: 'get',
    params
  })
}

export function getSysLog(params) {
  return request({
    url: '/logs/sys',
    method: 'get',
    params
  })
}

export function getApiLog(params) {
  return request({
    url: '/logs/api',
    method: 'get',
    params
  })
}
