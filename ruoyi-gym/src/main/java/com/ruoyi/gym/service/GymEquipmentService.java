package com.ruoyi.gym.service;

import com.ruoyi.gym.domain.GymEquipment;

import java.util.List;

/**
 * @author 86130
 * @description 针对表【gym_equipment(健身器材表)】的数据库操作Service
 * @createDate 2024-02-09 23:03:22
 */
public interface GymEquipmentService {
    /**
     * 获取所有设备列表
     * @return 所有设备的列表
     */
    List<GymEquipment> listAllEquipments();

    /**
     * 根据设备ID获取设备信息
     * @param equipmentId 设备ID
     * @return 对应设备信息，如果不存在则返回 null
     */
    GymEquipment getEquipmentById(Long equipmentId);

    /**
     * 添加设备
     * @param equipment 待添加的设备信息
     * @return 添加成功返回 true，否则返回 false
     */
    boolean addEquipment(GymEquipment equipment);

    /**
     * 更新设备信息
     * @param equipment 待更新的设备信息
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateEquipment(GymEquipment equipment);

    /**
     * 删除设备
     * @param equipmentId 待删除设备的ID
     * @return 删除成功返回 true，否则返回 false
     */
    boolean deleteEquipment(Long equipmentId);
}
