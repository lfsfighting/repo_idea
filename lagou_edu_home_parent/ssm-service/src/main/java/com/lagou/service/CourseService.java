package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    /**
     * 多条件查询课程信息
     */
    public List<Course> findCourseByCondition(CourseVo courseVo);

    /**
     * 添加课程及讲师信息
     */
    public void saveCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException;

    /**
     * 根据id查询课程信息
     */
    public CourseVo findCourseById(Integer id);


    /**
     * 修改课程信息
     */
    public void updateCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException;


    /**
     * 课程状态管理
     */
    public void updateCourseStatus(int courseId,int status);

}
