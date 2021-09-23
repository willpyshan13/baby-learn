import request from '@/utils/request'

export function getSysUser(id) {
  return request({
    url: '/platform/system/users/' + id,
    method: 'get'
  })
}

export function getSysUserList(params) {
  return request({
    url: '/platform/system/users',
    method: 'get',
    params
  })
}

export function saveSysUserDetail(data) {
  return request({
    url: '/platform/system/users',
    method: 'post',
    data
  })
}

export function updateSysUserDetail(data) {
  return request({
    url: '/platform/system/users',
    method: 'put',
    data
  })
}

export function deleteSysUser(id) {
  return request({
    url: '/platform/system/users/' + id,
    method: 'delete'
  })
}

export function getUserRole(id) {
  return request({
    url: '/platform/system/users/role/' + id,
    method: 'get'
  })
}
export function updateUserRole(data) {
  return request({
    url: '/platform/system/users/role',
    method: 'put',
    data
  })
}

// export function listSysUser(params) {
//   return request({
//     url: 'sys/user/list',
//     method: 'get',
//     params
//   })
// }
//
// export function addSysUser(data) {
//   return request({
//     url: 'sys/user/add',
//     method: 'POST',
//     data
//   })
// }
//
// export function editSysUser(data) {
//   return request({
//     url: 'sys/user/update',
//     method: 'POST',
//     data
//   })
// }
//
// export function updatePassword(data) {
//   return request({
//     url: 'sys/user/password',
//     method: 'POST',
//     data
//   })
// }
//
// export function deleteSysUser(id) {
//   return request({
//     url: 'sys/user/deleteById',
//     method: 'POST',
//     params: { id }
//   })
// }
