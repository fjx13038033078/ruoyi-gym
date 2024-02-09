package com.ruoyi.gym.service.impl;

import com.ruoyi.gym.domain.GymEquipment;
import com.ruoyi.gym.mapper.GymEquipmentMapper;
import com.ruoyi.gym.service.GymEquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 86130
 * @description 针对表【gym_equipment(健身器材表)】的数据库操作Service实现
 * @createDate 2024-02-09 23:03:22
 */
@Service
@RequiredArgsConstructor
public class GymEquipmentServiceImpl implements GymEquipmentService {

    private final GymEquipmentMapper gymEquipmentMapper;

    /**
     * 获取所有设备列表
     * @return 所有设备的列表
     */
    @Override
    public List<GymEquipment> listAllEquipments() {
        return gymEquipmentMapper.selectList(null);
    }

    /**
     * 根据设备ID获取设备信息
     * @param equipmentId 设备ID
     * @return 对应设备信息，如果不存在则返回 null
     */
    @Override
    public GymEquipment getEquipmentById(Long equipmentId) {
        return gymEquipmentMapper.selectById(equipmentId);
    }

    /**
     * 添加设备
     * @param equipment 待添加的设备信息
     * @return 添加成功返回 true，否则返回 false
     */
    @Override
    public boolean addEquipment(GymEquipment equipment) {
        int rows = gymEquipmentMapper.insert(equipment);
        return rows > 0;
    }

    /**
     * 更新设备信息
     * @param equipment 待更新的设备信息
     * @return 更新成功返回 true，否则返回 false
     */
    @Override
    public boolean updateEquipment(GymEquipment equipment) {
        int rows = gymEquipmentMapper.updateById(equipment);
        return rows > 0;
    }

    /**
     * 删除设备
     * @param equipmentId 待删除设备的ID
     * @return 删除成功返回 true，否则返回 false
     */
    @Override
    public boolean deleteEquipment(Long equipmentId) {
        int rows = gymEquipmentMapper.deleteById(equipmentId);
        return rows > 0;
    }
}