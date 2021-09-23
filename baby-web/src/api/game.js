import request from '@/utils/request'

export function getGameDetail(params) {
  return request({
    url: '/game/detail',
    method: 'get',
    params
  })
}

export function addGameDetail(data) {
  return request({
    url: '/game/detail/add',
    method: 'post',
    data
  })
}

export function updateGameDetail(data) {
  return request({
    url: '/game/detail/update',
    method: 'post',
    data
  })
}

export function deleteGameDetail(id) {
  return request({
    url: '/game/detail/delete',
    method: 'post',
    params: { id }
  })
}

export function getGameList(params) {
  return request({
    url: '/game/list',
    method: 'get',
    params
  })
}

export function addGame(data) {
  return request({
    url: '/game/add',
    method: 'POST',
    data
  })
}

export function deleteGameBatch(data) {
  return request({
    url: '/game/deleteBatch',
    method: 'POST',
    data
  })
}

export function editGame(data) {
  return request({
    url: '/game/update',
    method: 'POST',
    data
  })
}
