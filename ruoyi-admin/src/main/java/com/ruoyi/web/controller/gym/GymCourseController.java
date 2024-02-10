package com.ruoyi.web.controller.gym;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.gym.domain.GymCourse;
import com.ruoyi.gym.service.GymCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * @Author fanjaixing
 * @Date 2024/2/10 12:05
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/gym/course")
public class GymCourseController extends BaseController {

    private final GymCourseService gymCourseService;

    /**
     * 获取所有课程列表
     * @return 所有课程的列表
     */
    @GetMapping("/queryAll")
    public TableDataInfo listAllCourses() {
        startPage();
        List<GymCourse> gymCourseList = gymCourseService.listAllCourses();
        return getDataTable(gymCourseList);
    }

    /**
     * 根据课程ID获取课程信息
     * @param courseId 课程ID
     * @return 对应课程信息
     */
    @GetMapping("/getDetail")
    public GymCourse getCourseById(@RequestParam Long courseId) {
        // TODO: 实现根据课程ID获取课程信息的业务逻辑
        return null;
    }

    /**
     * 添加课程
     * @param course 待添加的课程信息
     * @return 添加成功返回 true，否则返回 false
     */
    @PostMapping("/add")
    public boolean addCourse(@RequestBody GymCourse course) {
        // TODO: 实现添加课程的业务逻辑
        return false;
    }

    /**
     * 更新课程信息
     * @param courseId 待更新的课程ID
     * @param course 待更新的课程信息
     * @return 更新成功返回 true，否则返回 false
     */
    @PostMapping("/update")
    public boolean updateCourse(@RequestParam Long courseId, @RequestBody GymCourse course) {
        // TODO: 实现更新课程信息的业务逻辑
        return false;
    }

    /**
     * 删除课程
     * @param courseId 待删除课程的ID
     * @return 删除成功返回 true，否则返回 false
     */
        @GetMapping("/delete")
    public boolean deleteCourse(@RequestParam Long courseId) {
        // TODO: 实现删除课程的业务逻辑
        return false;
    }
}
