package com.youran.designpatterns.demo.creational;

/**
 * @ClassName SynchronizedLazySingleton
 * @Description 懒汉式-同步方法（线程安全，效率低，不推荐）
 * @Author wh
 */
public class SynchronizedLazySingleton {
    private static SynchronizedLazySingleton instance;

    private SynchronizedLazySingleton() {
    }

    public synchronized static SynchronizedLazySingleton getInstance() {
        if (instance == null) {
            instance = new SynchronizedLazySingleton();
        }
        return instance;
    }
}
