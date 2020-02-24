package com.yyy.rutu.test.jvm;

import sun.security.ec.CurveDB;

import java.net.URL;
import java.security.Provider;

public class ClassLoaderTest1 {
    public static void main(String[] args){
        System.out.println("****** 启动类加载器 *******");
        // 获取 BootstrapClassLoader 能够加载的 api 的路径
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for(URL url : urls){
            System.out.println(url.toExternalForm());
        }
        // 从上面的路径中所以选择一个类，来查看其类加载器是什么：是引导类加载器
        ClassLoader classLoader = Provider.class.getClassLoader();
        System.out.println(classLoader);// null


        System.out.println("****** 扩展类加载器 *******");
        String extDirs = System.getProperty("java.ext.dirs");
        for (String path : extDirs.split(";")){
            System.out.println(path);
        }
        // 从上面的路径中所以选择一个类，来查看其类加载器是什么：是扩展类加载器
        ClassLoader classLoader1 = CurveDB.class.getClassLoader();
        System.out.println(classLoader1);// sun.misc.Launcher$ExtClassLoader@5a2e4553

    }
}
