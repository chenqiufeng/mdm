import request from '@/utils/request'

// 查询jdbc数据源配置列表
export function listDatasource(query) {
  return request({
    url: '/dataintegration/datasource/list',
    method: 'get',
    params: query
  })
}

// 查询jdbc数据源配置详细
export function getDatasource(id) {
  return request({
    url: '/dataintegration/datasource/' + id,
    method: 'get'
  })
}

// 新增jdbc数据源配置
export function addDatasource(data) {
  return request({
    url: '/dataintegration/datasource',
    method: 'post',
    data: data
  })
}

// 修改jdbc数据源配置
export function updateDatasource(data) {
  return request({
    url: '/dataintegration/datasource',
    method: 'put',
    data: data
  })
}

// 删除jdbc数据源配置
export function delDatasource(id) {
  return request({
    url: '/dataintegration/datasource/' + id,
    method: 'delete'
  })
}
