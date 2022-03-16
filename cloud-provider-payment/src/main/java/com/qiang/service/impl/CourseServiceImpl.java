package com.qiang.service.impl;

import com.qiang.mapper.CourseMapper;
import com.qiang.po.CoursePo;
import com.qiang.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liq
 * @date 2022/2/11 14:08
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    ApplicationContext applicationContext;

    @Override
    public List<CoursePo> courseList() {
        List<CoursePo> coursePos = courseMapper.selectByName(null);
        System.out.println(applicationContext.getEnvironment().getProperty("user.name"));
        System.out.println(applicationContext.getEnvironment().getProperty("user.age"));
        return coursePos;
    }
}
