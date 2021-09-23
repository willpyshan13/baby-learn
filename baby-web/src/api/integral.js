import request from '@/utils/request'

export function exchangeGoods(data) {
  return request({
    url: '/integral/exchange/goods',
    method: 'post',
    data
  })
}

export function getExchangeDetailById(id) {
  return request({
    url: '/integral/exchange/detail',
    method: 'get',
    params: { id }
  })
}

export function getIntegralList(params) {
  return request({
    url: '/integral/list',
    method: 'get',
    params
  })
}

export function deleteIntegralDetail(id) {
  return request({
    url: '/integral/deleteById',
    method: 'POST',
    params: { id }
  })
}
//
// export function addRoomCourseware(data) {
//   return request({
//     url: '/room/assigned/courseware',
//     method: 'POST',
//     data
//   })
// }
