package com.qiang.classload;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author liq
 * @date 2022/3/7 18:22
 */
public class CustomClassLoader extends URLClassLoader {


    public CustomClassLoader(URL[] urls) {
        super(urls);
    }
}
