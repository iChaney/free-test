package com.qiang.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiang.bean.Car;
import com.qiang.config.CarProperties;
import com.qiang.mapper.CourseMapper;
import com.qiang.po.CoursePo;
import com.qiang.vo.CourseListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author liq
 * @date 2022/1/21 17:06
 */
@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    CarProperties carProperties;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/rest")
    public CourseListVO restTest() {
        return restTemplate.postForObject("http://192.168.1.94:9000/car/course/list", null, CourseListVO.class);
    }

    @PostMapping("/info")
    public Object carInfo() {
        Car car = new Car();
        car.setBrand(carProperties.getBrand());
        car.setColor(carProperties.getColor());
        car.setType(carProperties.getType());
        return car;
    }

    @PostMapping("/sql")
    public String sql() {
        Map<String, Object> map = jdbcTemplate.queryForMap("select * from shopother where shopID = '02231'");
        return JSON.toJSONString(map);
    }

    @PostMapping("/course")
    public Object course() {
        return courseMapper.selectById(1L);
    }

    @PostMapping("/course/list")
    public CourseListVO courseList() {
        PageHelper.startPage(1, 10);
        List<CoursePo> coursePoList = courseMapper.selectByName(null);
        PageInfo<CoursePo> pageInfo = new PageInfo<>(coursePoList);
        CourseListVO courseListVO = new CourseListVO();
        courseListVO.setList(coursePoList);
        courseListVO.setTotal(pageInfo.getTotal());
        return courseListVO;
    }
}
