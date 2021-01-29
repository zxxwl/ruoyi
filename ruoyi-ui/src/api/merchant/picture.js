import request from '@/utils/request'

// 查询轮播图列表
export function listMerchant(query) {
  return request({
    url: '/merchant/picture/list',
    method: 'get',
    params: query
  })
}

// 查询轮播图详细
export function getMerchant(picId) {
  return request({
    url: '/merchant/picture/' + picId,
    method: 'get'
  })
}

// 新增轮播图
export function addMerchant(data) {
  return request({
    url: '/merchant/picture',
    method: 'post',
    data: data
  })
}

// 修改轮播图
export function updateMerchant(data) {
  return request({
    url: '/merchant/picture',
    method: 'put',
    data: data
  })
}

// 删除轮播图
export function delMerchant(picId) {
  return request({
    url: '/merchant/picture/' + picId,
    method: 'delete'
  })
}

// 导出轮播图
export function exportMerchant(query) {
  return request({
    url: '/merchant/picture/export',
    method: 'get',
    params: query
  })
}
