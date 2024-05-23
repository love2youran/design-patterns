package com.youran.designpatterns.demo.creational;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @ClassName SingletonTest
 * @Description 单例模式测试类
 * @Author wh
 */

public class SingletonTest {


    @Test
    // 双重校验锁的破坏
    // 利用反射破坏,反破坏可参考{@link com.youran.designpatterns.builder.singleton.Singleton04V2}
    public void breakSingleton04ByReflectTest() {
        DoubleCheckedLockingSingleton instance = DoubleCheckedLockingSingleton.getInstance();
        try {
            Class<?> singleClass = Class.forName("com.youran.designpatterns.builder.singleton.DoubleCheckedLockingSingleton");
            Constructor<?> declaredConstructor = singleClass.getDeclaredConstructor(null);
            declaredConstructor.setAccessible(true);
            DoubleCheckedLockingSingleton instanceByReflect = (DoubleCheckedLockingSingleton) declaredConstructor.newInstance();
            System.out.println(instance == instanceByReflect);// false
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    // 解决反射破坏单例模式测试
    @Test
    public void resloveProblemByReflect() {
        DoubleCheckedLockingSingletonV2 instance = DoubleCheckedLockingSingletonV2.getInstance();
        try {
            Class<?> singleClass = Class.forName("com.youran.designpatterns.builder.singleton.DoubleCheckedLockingSingletonV2");
            Constructor<?> declaredConstructor = singleClass.getDeclaredConstructor(null);
            declaredConstructor.setAccessible(true);
            DoubleCheckedLockingSingletonV2 instanceByReflect = (DoubleCheckedLockingSingletonV2) declaredConstructor.newInstance();
            System.out.println(instance == instanceByReflect);// Caused by: java.lang.RuntimeException: The singleton pattern does not allow multiple instances
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    // 利用序列化破坏单例
    @Test
    public void breakSingleton04BySerialize() {
        DoubleCheckedLockingSingleton instance1 = DoubleCheckedLockingSingleton.getInstance();
        Path tempFile = null;
        try {
            // 创建临时文件，并获取其路径
            tempFile = Files.createTempFile("tempFile", ".txt");

            // 使用 try-with-resources 来自动关闭流
            try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(tempFile));
                 ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(tempFile))) {

                // 将实例写入文件
                oos.writeObject(instance1);

                // 从文件中读取实例
                DoubleCheckedLockingSingleton singleton04BySerialize = (DoubleCheckedLockingSingleton) ois.readObject();

                // 检查两个实例是否相同
                System.out.println(instance1 == singleton04BySerialize); //false

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("Error during serialization process", e);
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating temp file", e);
        } finally {
            // 删除临时文件
            if (tempFile != null) {
                try {
                    Files.deleteIfExists(tempFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 解决序列化破坏单例问题
    @Test
    public void resloveProblemBySerialize() {
        DoubleCheckedLockingSingletonV3 instance = DoubleCheckedLockingSingletonV3.getInstance();
        Path tempFile = null;
        try {
            // 创建临时文件，并获取其路径
            tempFile = Files.createTempFile("tempFile", ".txt");

            // 使用 try-with-resources 来自动关闭流
            try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(tempFile));
                 ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(tempFile))) {

                // 将实例写入文件
                oos.writeObject(instance);

                // 从文件中读取实例
                DoubleCheckedLockingSingletonV3 singleton04BySerialize = (DoubleCheckedLockingSingletonV3) ois.readObject();

                // 检查两个实例是否相同
                System.out.println(instance == singleton04BySerialize); //true

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("Error during serialization process", e);
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating temp file", e);
        } finally {
            // 删除临时文件
            if (tempFile != null) {
                try {
                    Files.deleteIfExists(tempFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testEnumSingleton() {
        EnumSingleton instance1 = EnumSingleton.getInstance();
        EnumSingleton instance2 = EnumSingleton.getInstance();
        System.out.println(instance1 == instance2);
    }
}
