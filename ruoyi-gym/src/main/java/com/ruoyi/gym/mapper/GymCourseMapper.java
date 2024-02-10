package com.ruoyi.gym.mapper;

import com.ruoyi.gym.domain.GymCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.gym.domain.GymEquipment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author fanjaixing
 * @Date 2024/2/9 23:43
 */
@Mapper
public interface GymCourseMapper {
    // 获取所有课程列表
    List<GymCourse> listAllCourses();

    // 根据课程ID获取课程信息
    GymCourse getCourseById(Long courseId);

    // 添加课程
    int addCourse(GymCourse course);

    // 更新课程信息
    int updateCourse(GymCourse course);

    // 删除课程
    int deleteCourse(Long courseId);
}
