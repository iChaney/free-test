package com.qiang.controller;

import com.qiang.po.CoursePo;
import com.qiang.pojo.RestResponse;
import com.qiang.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author liq
 * @date 2022/2/11 14:06
 */
@RestController
public class CourseController {
    @Autowired
    CourseService courseService;

    @RequestMapping("/course/list")
    public RestResponse courseList() {
        List<CoursePo> coursePos = courseService.courseList();
        return RestResponse.success(coursePos);
    }

    @GetMapping("/course/timeout")
    public RestResponse timeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return RestResponse.success();
    }
}
