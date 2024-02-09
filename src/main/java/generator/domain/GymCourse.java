package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 课程表
 * @TableName gym_course
 */
@TableName(value ="gym_course")
@Data
public class GymCourse implements Serializable {
    /**
     * 课程ID
     */
    @TableId(type = IdType.AUTO)
    private Long courseid;

    /**
     * 课程名称
     */
    private String coursename;

    /**
     * 课程描述
     */
    private String coursedescription;

    /**
     * 课程时间
     */
    private Date coursetime;

    /**
     * 课程地点
     */
    private String courselocation;

    /**
     * 教练ID
     */
    private Long trainerid;

    /**
     * 教练名称
     */
    private String trainername;

    /**
     * 课程费用
     */
    private Integer coursefee;

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
        GymCourse other = (GymCourse) that;
        return (this.getCourseid() == null ? other.getCourseid() == null : this.getCourseid().equals(other.getCourseid()))
            && (this.getCoursename() == null ? other.getCoursename() == null : this.getCoursename().equals(other.getCoursename()))
            && (this.getCoursedescription() == null ? other.getCoursedescription() == null : this.getCoursedescription().equals(other.getCoursedescription()))
            && (this.getCoursetime() == null ? other.getCoursetime() == null : this.getCoursetime().equals(other.getCoursetime()))
            && (this.getCourselocation() == null ? other.getCourselocation() == null : this.getCourselocation().equals(other.getCourselocation()))
            && (this.getTrainerid() == null ? other.getTrainerid() == null : this.getTrainerid().equals(other.getTrainerid()))
            && (this.getTrainername() == null ? other.getTrainername() == null : this.getTrainername().equals(other.getTrainername()))
            && (this.getCoursefee() == null ? other.getCoursefee() == null : this.getCoursefee().equals(other.getCoursefee()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCourseid() == null) ? 0 : getCourseid().hashCode());
        result = prime * result + ((getCoursename() == null) ? 0 : getCoursename().hashCode());
        result = prime * result + ((getCoursedescription() == null) ? 0 : getCoursedescription().hashCode());
        result = prime * result + ((getCoursetime() == null) ? 0 : getCoursetime().hashCode());
        result = prime * result + ((getCourselocation() == null) ? 0 : getCourselocation().hashCode());
        result = prime * result + ((getTrainerid() == null) ? 0 : getTrainerid().hashCode());
        result = prime * result + ((getTrainername() == null) ? 0 : getTrainername().hashCode());
        result = prime * result + ((getCoursefee() == null) ? 0 : getCoursefee().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", courseid=").append(courseid);
        sb.append(", coursename=").append(coursename);
        sb.append(", coursedescription=").append(coursedescription);
        sb.append(", coursetime=").append(coursetime);
        sb.append(", courselocation=").append(courselocation);
        sb.append(", trainerid=").append(trainerid);
        sb.append(", trainername=").append(trainername);
        sb.append(", coursefee=").append(coursefee);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}