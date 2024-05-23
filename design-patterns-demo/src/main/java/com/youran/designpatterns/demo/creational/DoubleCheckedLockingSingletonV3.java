package com.youran.designpatterns.demo.creational;

import java.io.Serializable;

/**
 * @ClassName DoubleCheckedLockingSingletonV3
 * @Description 懒汉式-双重校验锁（解决了通过反射、序列化破坏单例的问题）
 * @Author YR
 */
public class DoubleCheckedLockingSingletonV3 implements Serializable {
    // volatile禁止指令重排序
    // instance = new Singleton04();可以拆解为三步：1.分配内存 2.初始化对象 3.将instance指向刚分配的内存
    // 如果不加volatile，A线程执行了1、3步，还没有执行2，B线程进行判断instance不为空，则直接返回，导致B线程拿到的是未初始化的对象
    private volatile static DoubleCheckedLockingSingletonV3 instance;


    // 防止通过反射破坏单例模式
    private DoubleCheckedLockingSingletonV3() {
        if (instance != null){
            throw new RuntimeException("The singleton pattern does not allow multiple instances");
        }
    }

    // 防止序列化破坏单例模式
    // 反序列化执行过程中会执行到ObjectInputStream#readOrdinaryObject方法，这个方法会判断对象是否包含readResolve方法，如果包含的话会直接调用这个方法获得对象实例
    private Object readResolve()  {
        return instance;
    }

    public static DoubleCheckedLockingSingletonV3 getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLockingSingletonV3.class){
                if (instance == null){
                    instance = new DoubleCheckedLockingSingletonV3();
                }
            }
        }
        return instance;
    }
}
