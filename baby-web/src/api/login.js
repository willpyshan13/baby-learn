import request from '@/utils/request'

export function login(username, password) {
  return request({
    url: '/platform/system/users/login',
    method: 'get',
    params: {
      username,
      password
    }
  })
}

export function getInfo(token) {
  return request({
    url: '/platform/system/users/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/platform/system/users/logout',
    method: 'get'
  })
}
