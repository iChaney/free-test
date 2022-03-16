package com.qiang.design.adapter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

/**
 * 适配器模式
 *
 * @author liq
 * @date 2021/6/15 17:00
 */
public class AdapterTest {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("C:\\Users\\admin\\Desktop\\test.log");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        Stream<String> lines = bufferedReader.lines();
        lines.forEach(s -> System.out.println(s));
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
    }
}
