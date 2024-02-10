package com.ruoyi.gym.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 课程表
 * @author fanjiaxing
 * @TableName gym_course
 */

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@TableName(value ="gym_course")
public class GymCourse implements Serializable {
    /**
     * 课程ID
     */
    @TableId(type = IdType.AUTO)
    private Long courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 课程描述
     */
    private String courseDescription;

    /**
     * 课程时间
     */
    private LocalDateTime courseTime;

    /**
     * 课程地点
     */
    private String courseLocation;

    /**
     * 教练ID
     */
    private Long trainerId;

    /**
     * 教练名称
     */
    private String trainerName;

    /**
     * 课程费用
     */
    private Integer courseFee;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}