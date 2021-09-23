import request from '@/utils/request'

export function getPageviews(params) {
  return request({
    url: '/pageviews/chart',
    method: 'get',
    params
  })
}
export function countRegistered(params) {
  return request({
    url: '/pageviews/count/registered',
    method: 'get',
    params
  })
}
