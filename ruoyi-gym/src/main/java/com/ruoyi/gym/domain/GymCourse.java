package com.ruoyi.gym.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
}
