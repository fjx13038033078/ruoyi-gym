package com.ruoyi.gym.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 课程评论表
 * @author fanjiaxing
 * @TableName gym_course_comment
 */

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@TableName(value ="gym_course_comment")
public class GymCourseComment implements Serializable {
    /**
     * 评论ID
     */
    @TableId(type = IdType.AUTO)
    private Long commentId;

    /**
     * 课程ID
     */
    private Long courseId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论时间
     */
    @TableField(value = "comment_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime commentTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

