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
        //设置评论人ID为当前登录用户的ID
        comment.setUserId(SecurityUtils.getUserId());
        // 设置评论人姓名为当前登录用户姓名
        comment.setUserName(SecurityUtils.getUsername());
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
        Long userId = SecurityUtils.getUserId(); // 获取当前登录用户的ID
        // 根据评论ID查询评论信息
        GymCourseComment comment = gymCourseCommentMapper.getCommentById(commentId);
        if (comment != null && userId.equals(comment.getUserId())) { // 检查当前用户是否是评论的作者
            int rows = gymCourseCommentMapper.deleteComment(commentId); // 执行删除操作
            return rows > 0;
        } else {
            // 当前用户不是评论的作者，不能删除评论
            throw new RuntimeException("仅可以删除自己的评论");
        }
    }
}
