package com.ruoyi.gym.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
    private BigDecimal courseFee;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
