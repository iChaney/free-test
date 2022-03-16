package com.qiang.classload;

import com.qiang.jvm.ClassB;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author liq
 * @date 2022/3/2 10:54
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader.toString());   // sun.misc.Launcher$AppClassLoader@18b4aac2
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader.toString());      // sun.misc.Launcher$ExtClassLoader@4f47d241
        ClassLoader bootStrapClassLoader = extClassLoader.getParent();
        System.out.println(bootStrapClassLoader);   // BootStrapClassLoader用c语言的, 在jvm内部

        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);

        ClassLoader classLoader2 = ClassB.class.getClassLoader();
        System.out.println(classLoader2);   // sun.misc.Launcher$AppClassLoader@18b4aac2

        URLClassLoader urlClassLoader = (URLClassLoader)systemClassLoader;
        URL[] urLs = urlClassLoader.getURLs();
        for (int i = 0; i <urLs.length; i++) {
            System.out.println(urLs[i]);
        }
    }
}
