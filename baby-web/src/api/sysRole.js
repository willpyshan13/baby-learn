import request from '@/utils/request'

export function updateRolePermission(data) {
  return request({
    url: 'sys/role/permission',
    method: 'POST',
    data
  })
}

export function listSysRole(params) {
  return request({
    url: 'sys/role/list',
    method: 'get',
    params
  })
}

export function addSysRole(data) {
  return request({
    url: 'sys/role/add',
    method: 'POST',
    data
  })
}

export function editSysRole(data) {
  return request({
    url: 'sys/role/update',
    method: 'POST',
    data
  })
}

export function deleteSysRole(id) {
  return request({
    url: 'sys/role/deleteById',
    method: 'POST',
    params: { id }
  })
}
