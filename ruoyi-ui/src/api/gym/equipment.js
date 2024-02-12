import request from '@/utils/request'

// 查询设备列表
export function listEquipments(query) {
  return request({
    url: '/gym/equipment/queryAll',
    method: 'get',
    params: query
  })
}

// 查询设备详细信息
export function getEquipment(equipmentId) {
  return request({
    url: '/gym/equipment/getDetail',
    method: 'get',
    params: { equipmentId }
  })
}

// 添加设备
export function addEquipment(data) {
  return request({
    url: '/gym/equipment/add',
    method: 'post',
    data: data
  })
}

// 更新设备信息
export function updateEquipment(data) {
  return request({
    url: '/gym/equipment/update',
    method: 'post',
    data: data
  })
}

// 删除设备
export function deleteEquipment(equipmentId) {
  return request({
    url: '/gym/equipment/delete',
    method: 'get',
    params: { equipmentId }
  })
}
