package com.ruoyi.gym.service.impl;

import com.ruoyi.gym.domain.GymCourse;
import com.ruoyi.gym.mapper.GymCourseMapper;
import com.ruoyi.gym.service.GymCourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 86130
 * @description 针对表【gym_course(课程表)】的数据库操作Service实现
 * @createDate 2024-02-09 23:02:51
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GymCourseServiceImpl implements GymCourseService {

    private final GymCourseMapper gymCourseMapper;

    // 获取所有课程列表
    @Override
    public List<GymCourse> listAllCourses() {
        return gymCourseMapper.listAllCourses();
    }

    // 根据课程ID获取课程信息
    @Override
    public GymCourse getCourseById(Long courseId) {
        return gymCourseMapper.getCourseById(courseId);
    }

    // 添加课程
    @Override
    public boolean addCourse(GymCourse course) {
        int rows = gymCourseMapper.addCourse(course);
        return rows > 0;
    }

    // 更新课程信息
    @Override
    public boolean updateCourse(GymCourse course) {
        int rows = gymCourseMapper.updateCourse(course);
        return rows > 0;
    }

    // 删除课程
    @Override
    public boolean deleteCourse(Long courseId) {
        int rows = gymCourseMapper.deleteCourse(courseId);
        return rows > 0;
    }
}
