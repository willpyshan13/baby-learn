import request from '@/utils/request'

export function getListOutWeekSelected(weekId, params) {
  return request({
    url: '/resources/out/week/' + weekId,
    method: 'GET',
    params: params
  })
}

export function getList(params) {
  return request({
    url: '/resources',
    method: 'GET',
    params: params
  })
}

export function createResource(data) {
  return request({
    url: '/resources',
    method: 'POST',
    data
  })
}
export function updateResource(data) {
  return request({
    url: '/resources',
    method: 'PUT',
    data
  })
}

export function getResourceById(id) {
  return request({
    url: '/resources/' + id,
    method: 'GET'
  })
}

export function deleteResourceById(id) {
  return request({
    url: '/resources/' + id,
    method: 'DELETE'
  })
}
