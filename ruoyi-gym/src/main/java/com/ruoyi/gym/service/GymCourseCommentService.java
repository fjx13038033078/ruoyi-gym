package com.ruoyi.gym.service;

import com.ruoyi.gym.domain.GymCourseComment;

import java.util.List;
/**
 * @author 86130
 * @description 针对表【gym_course_comment(课程评论表)】的数据库操作 Service
 * @createDate 2024-02-09 23:02:51
 */
public interface GymCourseCommentService {

    /**
     * 获取所有课程评论
     *
     * @return 课程评论列表
     */
    List<GymCourseComment> listAllComments();

    /**
     * 根据评论ID获取评论信息
     *
     * @param commentId 评论ID
     * @return 评论信息
     */
    GymCourseComment getCommentById(Long commentId);

    /**
     * 添加课程评论
     *
     * @param comment 评论信息
     * @return 是否添加成功
     */
    boolean addComment(GymCourseComment comment);

    /**
     * 删除课程评论
     *
     * @param commentId 评论ID
     * @return 是否删除成功
     */
    boolean deleteComment(Long commentId);
}

