package com.qiang.demo;

import com.qiang.entity.MemCardPart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liq
 * @date 2021/5/18 17:25
 */
public class JDBCdemo {
    /**
     * 0. 注册驱动 1. 获取数据库链接 2. 创建statment 3. 执行CURD 4. 封装结果集 5. 关闭链接
     *
     * @param args
     */
    public static void main(String[] args) {
        List<MemCardPart> list = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://106.14.3.93:3306/ethank_member";
            String name = "wangtianyu";
            String password = "p1R6oVW7I338LAXp";
            connection = DriverManager.getConnection(url, name, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from mem_card_part order by id desc limit 10");
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String cardPartName = resultSet.getString(2);
                list.add(new MemCardPart(id, cardPartName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        System.out.println(list);
    }
}
