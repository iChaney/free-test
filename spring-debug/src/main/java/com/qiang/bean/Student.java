package com.qiang.bean;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liq
 * @date 2021/9/24 17:41
 */
public class Student implements Serializable {
    Date createTime;

    public static void main(String[] args) {
        Student student = new Student();
        student.setCreateTime(new Date());
        String jsonString = JSON.toJSONString(student);
        System.out.println(jsonString);
        Student student2 = JSON.parseObject(jsonString, Student.class);
        System.out.println(student2);

    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

