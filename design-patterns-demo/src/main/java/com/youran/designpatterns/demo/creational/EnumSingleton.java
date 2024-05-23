package com.youran.designpatterns.demo.creational;

/**
 * @ClassName EnumSingleton
 * @Description 枚举单例（最简单且最安全）
 * @Author YR
 */
public enum EnumSingleton {
    SINGLETON_05;

    public static EnumSingleton getInstance() {
        return SINGLETON_05;
    }
    public void doSomething() {
        System.out.println("do something");
    }
}

