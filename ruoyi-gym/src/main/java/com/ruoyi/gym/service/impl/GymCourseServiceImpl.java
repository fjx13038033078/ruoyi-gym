package com.ruoyi.gym.service.impl;

import com.ruoyi.gym.domain.GymCourse;
import com.ruoyi.gym.mapper.GymCourseMapper;
import com.ruoyi.gym.service.GymCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 86130
 * @description 针对表【gym_course(课程表)】的数据库操作Service实现
 * @createDate 2024-02-09 23:02:51
 */
@Service
@RequiredArgsConstructor
public class GymCourseServiceImpl implements GymCourseService {

    private final GymCourseMapper gymCourseMapper;

    /**
     * 获取所有课程列表
     */
    @Override
    public List<GymCourse> listAllCourses() {
        // 使用 MyBatis Plus 的 selectList 方法查询所有课程
        return gymCourseMapper.selectList(null);
    }

    /**
     * 根据课程ID获取课程信息
     * @param courseId 课程ID
     * @return 对应课程信息，如果不存在则返回 null
     */
    @Override
    public GymCourse getCourseById(Long courseId) {
        // 使用 MyBatis Plus 的 selectById 方法根据课程ID查询课程信息
        return gymCourseMapper.selectById(courseId);
    }

    /**
     * 添加课程
     * @param course 待添加的课程信息
     * @return 添加成功返回 true，否则返回 false
     */
    @Override
    public boolean addCourse(GymCourse course) {
        // 使用 MyBatis Plus 的 insert 方法添加课程
        int rows = gymCourseMapper.insert(course);
        // 返回操作结果，如果影响的行数大于0则表示添加成功
        return rows > 0;
    }

    /**
     * 更新课程信息
     * @param course 待更新的课程信息
     * @return 更新成功返回 true，否则返回 false
     */
    @Override
    public boolean updateCourse(GymCourse course) {
        // 使用 MyBatis Plus 的 updateById 方法根据课程ID更新课程信息
        int rows = gymCourseMapper.updateById(course);
        // 返回操作结果，如果影响的行数大于0则表示更新成功
        return rows > 0;
    }

    /**
     * 删除课程
     * @param courseId 待删除课程的ID
     * @return 删除成功返回 true，否则返回 false
     */
    @Override
    public boolean deleteCourse(Long courseId) {
        // 使用 MyBatis Plus 的 deleteById 方法根据课程ID删除课程
        int rows = gymCourseMapper.deleteById(courseId);
        // 返回操作结果，如果影响的行数大于0则表示删除成功
        return rows > 0;
    }
}
