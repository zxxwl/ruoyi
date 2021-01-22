import request from '@/utils/request'

// 查询类目列表
export function listCategory(query) {
  return request({
    url: '/merchant/category/list',
    method: 'get',
    params: query
  })
}

// 查询类目详细
export function getCategory(categoryId) {
  return request({
    url: '/merchant/category/' + categoryId,
    method: 'get'
  })
}

// 新增类目
export function addCategory(data) {
  return request({
    url: '/merchant/category',
    method: 'post',
    data: data
  })
}

// 修改类目
export function updateCategory(data) {
  return request({
    url: '/merchant/category',
    method: 'put',
    data: data
  })
}

// 删除类目
export function delCategory(categoryId) {
  return request({
    url: '/merchant/category/' + categoryId,
    method: 'delete'
  })
}

// 角色状态修改
export function changeCategoryStatus(categoryId, status) {
  const data = {
    categoryId,
    status
  }
  return request({
    url: '/merchant/category/changeStatus',
    method: 'put',
    data: data
  })
}

// 导出类目
export function exportCategory(query) {
  return request({
    url: '/merchant/category/export',
    method: 'get',
    params: query
  })
}
