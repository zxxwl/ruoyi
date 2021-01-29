import request from '@/utils/request'

// 查询评论信息列表
export function listBuyer(query) {
  return request({
    url: '/buyer/comment/list',
    method: 'get',
    params: query
  })
}

// 查询评论信息详细
export function getBuyer(commentId) {
  return request({
    url: '/buyer/comment/' + commentId,
    method: 'get'
  })
}

// 导出评论信息
export function exportBuyer(query) {
  return request({
    url: '/buyer/comment/export',
    method: 'get',
    params: query
  })
}
