package com.luo.designPattern.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib动态代理
 *
 * @author luohq
 * @date 2021-08-12
 */
public class CglibDynamicProxy {

    public static void main(String[] args) {
        //对class进行代理
        Subject subjectProxy = (Subject) Enhancer.create(
                RealSubject.class,
                new CglibMethodInterceptor()
        );
        subjectProxy.request();

        //对目标对象进行代理（适用于target对象已存在）
        Subject subject = new RealSubject();
        subjectProxy = (Subject) Enhancer.create(
                RealSubject.class,
                new CglibMethodInterceptorWithTarget(subject)
        );
        subjectProxy.request();
    }
}


/**
 * 方法拦截器 - 拦截父类方法
 */
class CglibMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib proxy request before");
        //通过代理类的方法调用父类superclass方法
        Object result = methodProxy.invokeSuper(object, args);
        System.out.println("Cglib proxy request after");
        return result;
    }
}


/**
 * 方法拦截器 - 拦截具体target对象
 */
class CglibMethodInterceptorWithTarget implements MethodInterceptor {
    //目标对象（被代理的对象)
    private Object target;

    public CglibMethodInterceptorWithTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib proxy request before");
        //调用目标对象target的method方法
        Object result = method.invoke(this.target, args);
        System.out.println("Cglib proxy request after");
        return result;
    }
}