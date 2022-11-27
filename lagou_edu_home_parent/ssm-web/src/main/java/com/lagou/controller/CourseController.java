package com.lagou.controller;


import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     *多条件查询课程列表信息
     */
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVo courseVo){

        List<Course> courseList = courseService.findCourseByCondition(courseVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", courseList);

        return responseResult;
    }

    /**
     * 课程图片上传
     */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {

        //1.判断接收到的上传文件是否为空
        if(file.isEmpty()){
            throw  new RuntimeException();
        }

        //2.获取项目部署路径

        // D:\apache-tomcat-8.5.56\webapps\ssm-web\
        String realPath = request.getServletContext().getRealPath("/");
        // D:\apache-tomcat-8.5.56\webapps
        String substring = realPath.substring(0, realPath.indexOf("ssm-web"));



        //3.获取原文件名
        //lagou.jpg
        String originalFilename = file.getOriginalFilename();

        //4.生成新文件名
        //12421321.jpg
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //5.文件上传

        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFileName);

        // 如果目录不存在就创建目录
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录：" + filePath);
        }

        // 图片就进行了真正的上传
        file.transferTo(filePath);

        // 6. 将文件名和文件路径返回，进行响应
        Map<String, String> map = new HashMap<>();
        map.put("fileName",newFileName);

        map.put("filePath","http://localhost:8080/upload/" + newFileName);

        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);

        return responseResult;

    }


    /**
     * 新增课程及讲师信息
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {


        if (courseVo.getId() == null){
            courseService.saveCourseOrTeacher(courseVo);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增成功", null);
            return responseResult;
        }else {
            courseService.updateCourseOrTeacher(courseVo);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
            return responseResult;
        }


    }


    /**
     * 根据id查询具体的课程信息及关联的讲师信息
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){

        CourseVo courseById = courseService.findCourseById(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", courseById);

        return responseResult;

    }


    /**
     * 课程状态管理
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id,Integer status){

        courseService.updateCourseStatus(id,status);

        Map<String, Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult responseResult = new ResponseResult(true,200,"课程状态更新成功",map);
        return responseResult;
    }
}







