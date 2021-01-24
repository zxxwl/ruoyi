import request from '@/utils/request'

// 查询商品列表
export function listInfo(query) {
  return request({
    url: '/merchant/commodity/list',
    method: 'get',
    params: query
  })
}

// 查询商品详细
export function getInfo(productId) {
  return request({
    url: '/merchant/commodity/' + productId,
    method: 'get'
  })
}

// 新增商品
export function addInfo(data) {
  return request({
    url: '/merchant/commodity',
    method: 'post',
    data: data
  })
}

// 修改商品
export function updateInfo(data) {
  return request({
    url: '/merchant/commodity',
    method: 'put',
    data: data
  })
}
// 修改商品状态
export function changeProductStatus(productId, productStatus) {
  const data = {
    productId,
    productStatus
  }
  return request({
    url: '/merchant/commodity/changeProductStatus',
    method: 'put',
    data: data
  })
}

// 删除商品
export function delInfo(productId) {
  return request({
    url: '/merchant/commodity/' + productId,
    method: 'delete'
  })
}

// 导出商品
export function exportInfo(query) {
  return request({
    url: '/merchant/commodity/export',
    method: 'get',
    params: query
  })
}
