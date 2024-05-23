package com.youran.designpatterns.demo.creational;

/**
 * @ClassName LazySingleton
 * @Description 懒汉式（线程不安全，不可用）
 * @Author YR
 */
public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
