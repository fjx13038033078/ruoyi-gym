package com.ruoyi.gym.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.gym.domain.GymCourseComment;
import com.ruoyi.gym.mapper.GymCourseCommentMapper;
import com.ruoyi.gym.service.GymCourseCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 针对表【gym_course_comment(课程评论表)】的数据库操作 Service 实现
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GymCourseCommentServiceImpl implements GymCourseCommentService {

    private final GymCourseCommentMapper gymCourseCommentMapper;

    /**
     * 获取所有课程评论
     *
     * @return 课程评论列表
     */
    @Override
    public List<GymCourseComment> listAllComments() {
        return gymCourseCommentMapper.listAllComments();
    }

    /**
     * 根据评论ID获取评论信息
     *
     * @param commentId 评论ID
     * @return 评论信息
     */
    @Override
    public GymCourseComment getCommentById(Long commentId) {
        return gymCourseCommentMapper.getCommentById(commentId);
    }

    /**
     * 添加课程评论
     *
     * @param comment 评论信息
     * @return 是否添加成功
     */
    @Override
    public boolean addComment(GymCourseComment comment) {
        comment.setUserId(SecurityUtils.getUserId());
        // 设置评论时间为当前时间
        comment.setCommentTime(LocalDateTime.now());
        int rows = gymCourseCommentMapper.addComment(comment);
        return rows > 0;
    }

    /**
     * 删除课程评论
     *
     * @param commentId 评论ID
     * @return 是否删除成功
     */
    @Override
    public boolean deleteComment(Long commentId) {
        int rows = gymCourseCommentMapper.deleteComment(commentId);
        return rows > 0;
    }
}
