package com.yyy.rutu.test.jvm;

public class ClassLoaderTest2 {
    public static void main(String[] args){
        // 获取 ClassLoader 的途径

        try {
            // 方式一：获取当前类的 ClassLoader
            ClassLoader classLoader = Class.forName("java.lang.String").getClassLoader();
            System.out.println(classLoader);

            // 方式二：获取当前线程上下文的 ClassLoader
            ClassLoader classLoader1 = Thread.currentThread().getContextClassLoader();
            System.out.println(classLoader1);

            // 方式三：获取系统的 ClassLoader
            ClassLoader classLoader2 = ClassLoader.getSystemClassLoader();
            System.out.println(classLoader2);

            // 方式四：获取调用者的 ClassLoader
            // DriverManager.getCallerClassLoader()

        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
