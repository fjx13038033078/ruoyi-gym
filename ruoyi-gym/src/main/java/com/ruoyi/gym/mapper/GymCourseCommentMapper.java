package com.ruoyi.gym.mapper;

import com.ruoyi.gym.domain.GymCourseComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GymCourseCommentMapper {

    // 获取所有课程评论列表
    List<GymCourseComment> listCommentsById(Long courseId);

    // 根据评论ID获取评论信息
    GymCourseComment getCommentById(Long commentId);

    // 添加课程评论
    int addComment(GymCourseComment comment);

    // 删除课程评论
    int deleteComment(Long commentId);
}
