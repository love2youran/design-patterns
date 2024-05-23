package com.youran.designpatterns.demo.creational;

/**
 * @ClassName DoubleCheckedLockingSingletonV2
 * @Description 懒汉式-双重校验锁（解决利用反射破坏单例的问题）
 * @Author YR
 */
public class DoubleCheckedLockingSingletonV2 {
    // volatile禁止指令重排序
    // instance = new Singleton04();可以拆解为三步：1.分配内存 2.初始化对象 3.将instance指向刚分配的内存
    // 如果不加volatile，A线程执行了1、3步，还没有执行2，B线程进行判断instance不为空，则直接返回，导致B线程拿到的是未初始化的对象
    private volatile static DoubleCheckedLockingSingletonV2 instance;

    private DoubleCheckedLockingSingletonV2() {
        if (instance != null){
            throw new RuntimeException("The singleton pattern does not allow multiple instances");
        }
    }

    public static DoubleCheckedLockingSingletonV2 getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLockingSingletonV2.class){
                if (instance == null){
                    instance = new DoubleCheckedLockingSingletonV2();
                }
            }
        }
        return instance;
    }
}
