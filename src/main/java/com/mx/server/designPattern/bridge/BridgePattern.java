package com.mx.server.designPattern.bridge;

/**
 * 桥接模式
 *
 * @author luohq
 * @date 2021-09-08
 */
public class BridgePattern {
    public static void main(String[] args) {
        //初始定义：抽象维度1 +　实现维度1
        Abstraction abstraction = new ConcreteAbstraction1();
        Implementor implementor = new ConcreteImplementor1();

        //抽象维度1 +　实现维度1
        abstraction.setImplementor(implementor);
        abstraction.operation();

        //抽象维度1 +　实现维度2
        implementor = new ConcreteImplementor2();
        abstraction.setImplementor(implementor);
        abstraction.operation();

        //抽象维度2 +　实现维度2
        abstraction = new ConcreteAbstraction2();
        abstraction.setImplementor(implementor);
        abstraction.operation();
    }
}

/**
 * 抽象 - 抽象定义
 */
abstract class Abstraction {
    /**
     * 抽象关联实现
     */
    protected Implementor implementor;

    /**
     * 抽象的变化维度
     */
    abstract void operation();

    public void setImplementor(Implementor implementor) {
        this.implementor = implementor;
    }
}

/**
 * 抽象 - 抽象实现类1
 */
class ConcreteAbstraction1 extends Abstraction {
    @Override
    void operation() {
        System.out.println("abstract op1");
        super.implementor.operationImpl();
    }
}

/**
 * 抽象 - 抽象实现类2
 */
class ConcreteAbstraction2 extends Abstraction {
    @Override
    void operation() {
        System.out.println("abstract op2");
        super.implementor.operationImpl();
    }
}

/**
 * 实现 - 接口类
 */
interface Implementor {
    /**
     * 实现的变化维度
     */
    void operationImpl();
}

/**
 * 实现 - 具体实现类1
 */
class ConcreteImplementor1 implements Implementor {
    @Override
    public void operationImpl() {
        System.out.println("implementor op1");
    }
}

/**
 * 实现 - 具体实现类2
 */
class ConcreteImplementor2 implements Implementor {
    @Override
    public void operationImpl() {
        System.out.println("implementor op2");
    }
}
