package com.youran.designpatterns.demo.creational;

import java.io.Serializable;

/**
 * @ClassName DoubleCheckedLockingSingleton
 * @Description 懒汉式-双重校验锁（推荐使用：保证了延迟加载、线程安全），但是可以使用反射和序列化进行破坏
 * @Author YR
 */
public class DoubleCheckedLockingSingleton implements Serializable {
    // volatile禁止指令重排序
    // instance = new Singleton04();可以拆解为三步：1.分配内存 2.初始化对象 3.将instance指向刚分配的内存
    // 如果不加volatile，A线程执行了1、3步，还没有执行2，B线程进行判断instance不为空，则直接返回，导致B线程拿到的是未初始化的对象
    private volatile static DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() {
    }

    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLockingSingleton.class){
                if (instance == null){
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
}
