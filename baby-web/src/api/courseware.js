import request from '@/utils/request'

export function coursewareToRomm(params) {
  return request({
    url: '/courseware/listToRoom',
    method: 'get',
    params
  })
}

export function coursewareList(params) {
  return request({
    url: '/courseware/list',
    method: 'get',
    params
  })
}

export function addCourseware(data) {
  return request({
    url: '/courseware/add',
    method: 'POST',
    data
  })
}

export function editCourseware(data) {
  return request({
    url: '/courseware/update',
    method: 'POST',
    data
  })
}

export function deleteCourseware(id) {
  return request({
    url: '/courseware/deleteById',
    method: 'POST',
    params: { id }
  })
}

export function deleteFile(params) {
  return request({
    url: '/courseware/deleteFile',
    method: 'POST',
    params
  })
}
