import request from '@/utils/request'

export function getKindergartenInfoList(params) {
  return request({
    url: '/kindergarten/infos',
    method: 'get',
    params
  })
}

export function getKindergartenInfo(id) {
  return request({
    url: '/kindergarten/infos/' + id,
    method: 'get'
  })
}

export function saveKindergartenInfoDetail(data) {
  return request({
    url: '/kindergarten/infos',
    method: 'post',
    data
  })
}

export function updateKindergartenInfoDetail(data) {
  return request({
    url: '/kindergarten/infos',
    method: 'put',
    data
  })
}

export function deleteKindergartenInfo(id) {
  return request({
    url: '/kindergarten/infos/' + id,
    method: 'delete'
  })
}

