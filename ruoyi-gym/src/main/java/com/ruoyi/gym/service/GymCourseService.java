package com.ruoyi.gym.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.gym.domain.GymCourse;

import java.util.List;

/**
 * @author 86130
 * @description 针对表【gym_course(课程表)】的数据库操作Service
 * @createDate 2024-02-09 23:02:51
 */
public interface GymCourseService {

    public List<GymCourse> listAllCourses();

    public GymCourse getCourseById(Long courseId);

    public boolean addCourse(GymCourse course);

    public boolean updateCourse(GymCourse course);

    public boolean deleteCourse(Long courseId);
}
