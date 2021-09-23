import request from '@/utils/request'

export function updateOpenCourseScheduled(classId) {
  return request({
    url: '/scheduled/open/course',
    method: 'POST',
    params: { classId }
  })
}

export function getScheduler(params) {
  return request({
    url: '/scheduled/list',
    method: 'get',
    params
  })
}

export function getSchedulerLogs(params) {
  return request({
    url: '/scheduled/logs/list',
    method: 'get',
    params
  })
}

export function addJob(data) {
  return request({
    url: '/scheduled/add',
    method: 'post',
    data
  })
}

export function openJob(data) {
  return request({
    url: '/scheduled/open',
    method: 'post',
    data
  })
}

export function runJob(data) {
  return request({
    url: '/scheduled/run',
    method: 'post',
    data
  })
}

export function closeJob(data) {
  return request({
    url: '/scheduled/close',
    method: 'post',
    data
  })
}

export function deleteJob(data) {
  return request({
    url: '/scheduled/delete',
    method: 'post',
    data
  })
}

