import request from '@/utils/request'

export function pushStartLearningMsg(roomId) {
  return request({
    url: '/room/push/startLearning',
    method: 'post',
    params: { roomId }
  })
}

export function listRecordings(roomId) {
  return request({
    url: '/room/recordings',
    method: 'get',
    params: { roomId }
  })
}

export function roomCoursewareList(params) {
  return request({
    url: '/room/courseware',
    method: 'get',
    params
  })
}

export function addRoomCourseware(data) {
  return request({
    url: '/room/assigned/courseware',
    method: 'POST',
    data
  })
}

export function deleteRoomCourseware(data) {
  return request({
    url: '/room/cancel/courseware',
    method: 'POST',
    data
  })
}

export function roomList(params) {
  return request({
    url: '/room/list',
    method: 'get',
    params
  })
}

export function getRoom(id) {
  return request({
    url: '/room/detail',
    method: 'get',
    params: { id }
  })
}

export function addRoom(data) {
  return request({
    url: '/room/add',
    method: 'POST',
    data
  })
}

export function editRoom(data) {
  return request({
    url: '/room/update',
    method: 'POST',
    data
  })
}

export function deleteRoom(id) {
  return request({
    url: '/room/deleteById',
    method: 'POST',
    params: { id }
  })
}
