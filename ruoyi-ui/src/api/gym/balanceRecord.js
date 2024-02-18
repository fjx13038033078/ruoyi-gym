import request from '@/utils/request'

// 查询费用明细列表
export function listBalanceRecord(query) {
  return request({
    url: '/gym/course/balanceRecord/listAll',
    method: 'get',
    params: query
  })
}

//添加费用明细
export function addBalanceRecord(data) {
  return request({
    url: '/gym/course/balanceRecord/add',
    method: 'post',
    data: data
  })
}
