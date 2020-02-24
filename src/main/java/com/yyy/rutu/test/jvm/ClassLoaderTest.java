package com.yyy.rutu.test.jvm;

public class ClassLoaderTest {

    public static void main(String[] args){
        // 获取 系统类加载器
        ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2

        // 获取上层类加载器：扩展类加载器
        ClassLoader extensionLoader = systemLoader.getParent();
        System.out.println(extensionLoader);//sun.misc.Launcher$ExtClassLoader@2cdf8d8a

        // 获取上层类加载器：无法获取引导类加载器（BootstrapClassLoader使用C、C++编写，通过Java获取不到对象）
        ClassLoader bootstrapLoader = extensionLoader.getParent();
        System.out.println(bootstrapLoader);//null

        // 对于用户自定义类，默认使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2

        // String 类使用引导类加载器进行加载
        // Java 的核心类库都是使用引导类加载器进行加载的
        // 核心类库：
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);//null

    }
}
