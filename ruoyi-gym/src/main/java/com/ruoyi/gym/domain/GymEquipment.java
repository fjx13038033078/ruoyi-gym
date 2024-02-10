package com.ruoyi.gym.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 健身器材表
 * @author fanjiaxing
 * @TableName gym_equipment
 */
@TableName(value ="gym_equipment")
@Data
public class GymEquipment implements Serializable {
    /**
     * 设备ID
     */
    @TableId(type = IdType.AUTO)
    private Long equipmentId;

    /**
     * 设备名称
     */
    private String equipmentName;

    /**
     * 设备数量
     */
    private Integer equipmentQuantity;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
