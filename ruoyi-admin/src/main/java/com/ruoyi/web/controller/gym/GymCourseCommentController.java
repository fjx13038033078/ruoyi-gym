package com.ruoyi.web.controller.gym;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.gym.domain.GymCourseComment;
import com.ruoyi.gym.service.GymCourseCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 控制器实现课程评论的增删查操作
 * @author fanjiaxing
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/gym/course/comment")
public class GymCourseCommentController extends BaseController {

    private final GymCourseCommentService gymCourseCommentService;

    /**
     * 获取所有课程评论
     *
     * @return 课程评论列表
     */
    @GetMapping("/listAll")
    public TableDataInfo listAllComments() {
        startPage();
        List<GymCourseComment> commentList = gymCourseCommentService.listAllComments();
        return getDataTable(commentList);
    }

    /**
     * 根据评论ID获取评论信息
     *
     * @param commentId 评论ID
     * @return 评论信息
     */
    @GetMapping("/get")
    public AjaxResult getCommentById(@RequestParam Long commentId) {
        return success(gymCourseCommentService.getCommentById(commentId));
    }

    /**
     * 添加课程评论
     *
     * @param comment 评论信息
     * @return 是否添加成功
     */
    @PostMapping("/add")
    public AjaxResult addComment(@RequestBody GymCourseComment comment) {
        return toAjax(gymCourseCommentService.addComment(comment));
    }

    /**
     * 删除课程评论
     *
     * @param commentId 评论ID
     * @return 是否删除成功
     */
    @GetMapping("/delete")
    public AjaxResult deleteComment(@RequestParam Long commentId) {
        return toAjax(gymCourseCommentService.deleteComment(commentId));
    }
}

