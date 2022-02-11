package com.qiang.service.impl;

import com.qiang.mapper.CourseMapper;
import com.qiang.po.CoursePo;
import com.qiang.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<CoursePo> courseList() {
        return courseMapper.selectByName(null);
    }
}
