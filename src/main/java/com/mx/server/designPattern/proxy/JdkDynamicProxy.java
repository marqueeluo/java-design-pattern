package com.mx.server.designPattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK - 接口级动态代理
 *
 * @author luohq
 * @date 2021-08-12
 */
public class JdkDynamicProxy {

    public static void main(String[] args) {
        //被代理的目标对象
        Subject subject = new RealSubject();
        //Proxy根据接口定义、代理回调处理器生成代理类
        Subject subjectProxy = (Subject) Proxy.newProxyInstance(
                Subject.class.getClassLoader(),
                new Class[]{Subject.class},
                new DynamicProxyHandler(subject)
        );
        //调用代理对象的方法
        subjectProxy.request();
    }
}


/**
 * 动态代理 - 调用处理类
 * 执行代理类的每个方法时，均会触发会invoke回调，
 * 可在invoke中添加控制逻辑，进而再调用被代理对象的目标方法
 */
class DynamicProxyHandler implements InvocationHandler {
    //被代理的对象
    private Object subject;

    public DynamicProxyHandler(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Dynamic proxy request before");
        //调用被代理对象的目标方法
        Object result = method.invoke(this.subject, args);
        System.out.println("Dynamic proxy request after");
        return result;
    }
}

