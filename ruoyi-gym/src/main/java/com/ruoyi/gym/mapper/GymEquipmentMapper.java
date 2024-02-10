package com.ruoyi.gym.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.gym.domain.GymEquipment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author fanjiaxing
 * @description 针对表【gym_equipment(健身器材表)】的数据库操作Mapper
 * @createDate 2024-02-09 23:03:22
 * @Entity generator.domain.GymEquipment
 */
@Mapper
public interface GymEquipmentMapper {
    // 获取所有设备列表
    List<GymEquipment> listAllEquipments();

    // 根据设备ID获取设备信息
    GymEquipment getEquipmentById(Long equipmentId);

    // 添加设备
    int addEquipment(GymEquipment equipment);

    // 更新设备信息
    int updateEquipment(GymEquipment equipment);

    // 删除设备
    int deleteEquipment(Long equipmentId);

}
