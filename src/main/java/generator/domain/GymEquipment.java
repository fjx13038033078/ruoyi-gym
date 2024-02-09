package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 健身器材表
 * @TableName gym_equipment
 */
@TableName(value ="gym_equipment")
@Data
public class GymEquipment implements Serializable {
    /**
     * 设备ID
     */
    @TableId(type = IdType.AUTO)
    private Long equipmentid;

    /**
     * 设备名称
     */
    private String equipmentname;

    /**
     * 设备数量
     */
    private Integer equipmentquantity;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        GymEquipment other = (GymEquipment) that;
        return (this.getEquipmentid() == null ? other.getEquipmentid() == null : this.getEquipmentid().equals(other.getEquipmentid()))
            && (this.getEquipmentname() == null ? other.getEquipmentname() == null : this.getEquipmentname().equals(other.getEquipmentname()))
            && (this.getEquipmentquantity() == null ? other.getEquipmentquantity() == null : this.getEquipmentquantity().equals(other.getEquipmentquantity()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEquipmentid() == null) ? 0 : getEquipmentid().hashCode());
        result = prime * result + ((getEquipmentname() == null) ? 0 : getEquipmentname().hashCode());
        result = prime * result + ((getEquipmentquantity() == null) ? 0 : getEquipmentquantity().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", equipmentid=").append(equipmentid);
        sb.append(", equipmentname=").append(equipmentname);
        sb.append(", equipmentquantity=").append(equipmentquantity);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}