import request from '@/utils/request'

export function getArticleType(params) {
  return request({
    url: '/article/type/list',
    method: 'get',
    params
  })
}

export function getArticle(params) {
  return request({
    url: '/article/list',
    method: 'get',
    params
  })
}

export function getArticleById(id) {
  return request({
    url: '/article/getById',
    method: 'get',
    params: { id }
  })
}

export function updateArticle(data) {
  return request({
    url: '/article/update',
    method: 'POST',
    data
  })
}

export function deleteArticleById(id, path) {
  return request({
    url: '/article/deleteById',
    method: 'POST',
    params: { id, path }
  })
}

export function deleteBatchArticle(data) {
  return request({
    url: '/article/deleteBatch',
    method: 'POST',
    data
  })
}
