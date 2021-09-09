package com.luo.designPattern.singleton;

/**
 * 单例模式
 *
 * @author luohq
 * @date 2018/7/30
 */
public class Singleton {

    private static volatile Singleton instance;


    private Singleton(String name) {
        System.out.printf("Construct Singleton 【%s】\n", name);
    }

    /**
     * 单例模式 - 双重检查
     *
     * @return
     */
    public static Singleton getInstance() {
        if (null == instance) {
            synchronized (Singleton.class) {
                if (null == instance) {
                    instance = new Singleton("Double Check");
                }
            }
        }
        return instance;
    }


    /**
     * 单例模式 - 懒加载器
     */
    private static class SingletonContainer {
        private static Singleton instance = new Singleton("Lazy Loader");
    }

    /**
     * 单例模式 - 懒加载
     *
     * @return
     */
    public static Singleton getInstance2() {
        return SingletonContainer.instance;
    }


    public static void main(String args[]) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance2();

    }


}
