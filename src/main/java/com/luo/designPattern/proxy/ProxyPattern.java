package com.luo.designPattern.proxy;

/**
 * 代理模式
 *
 * @author luohq
 * @date 2021-08-12
 */
public class ProxyPattern {

    public static void main(String[] args) {
        Subject realSubject = new RealSubject();
        Subject subject = new Proxy(realSubject);
        subject.request();
    }

}


/**
 * ===============================================================
 * 静态代理 - 被代理的接口在编码阶段就已经确认了
 * 如下示例代理仅能对Subject进行代理
 * ===============================================================
 */
/**
 * 目标接口
 */
interface Subject {
    void request();
}

/**
 * 具体实现（被代理类）
 */
class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("RealSubject request");
    }
}

/**
 * 代理类（实现目标接口Subject，组合具体实现类RealSubject）
 */
class Proxy implements Subject {
    private Subject subject;

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        System.out.println("proxy request before");
        this.subject.request();
        System.out.println("proxy request after");
    }
}
