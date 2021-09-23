import request from '@/utils/request'

export function removeFile(params) {
  return request({
    url: '/qiniu/remove',
    method: 'post',
    params
  })
}

export function getQiniuConfig(params) {
  return request({
    url: '/qiniu/upload/config',
    method: 'get',
    params
  })
}
//
// export function addMenu(data) {
//   return request({
//     url: '/sys/menu/add',
//     method: 'POST',
//     data
//   })
// }
//
// export function editMenu(data) {
//   return request({
//     url: '/sys/menu/update',
//     method: 'POST',
//     data
//   })
// }
//
// export function deleteMenu(id) {
//   return request({
//     url: '/sys/menu/deleteById',
//     method: 'POST',
//     params: { id }
//   })
// }
