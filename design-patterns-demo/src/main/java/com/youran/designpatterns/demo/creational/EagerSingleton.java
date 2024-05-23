package com.youran.designpatterns.demo.creational;

/**
 * @ClassName EagerSingleton
 * @Description 饿汉式（可用）
 * @Author YR
 */
public class EagerSingleton {
    private static EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}
