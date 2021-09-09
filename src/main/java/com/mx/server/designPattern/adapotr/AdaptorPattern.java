package com.mx.server.designPattern.adapotr;

/**
 * 适配器模式
 *
 * @author luohq
 * @date 2021-08-10
 */
public class AdaptorPattern {

    public static void main(String[] args) {
        //被适配者
        Adaptee adaptee = new Adaptee();

        //对象适配器
        Target target = new AdaptorObj(adaptee);
        target.request();

        //类适配器
        target = new AdaptorClass();
        target.request();

        //接口适配器
        AdapteeInterface adapteeInterface = new AdaptorAbs() {
            @Override
            public void method1() {
                System.out.println("AdaptorAbs method1");
            }
        };
        adapteeInterface.method1();
    }
}

/**
 * 目标
 */
interface Target {
    void request();
}

/**
 * 被适配者
 */
class Adaptee {
    void specialRequest() {
        System.out.println("Adaptee do");
    }
}


/**
 * ===============================================================
 * 对象适配器 - 通过组合被适配者，实现目标接口的形式
 * ===============================================================
 */

/**
 * 适配器 - 对象适配器模式 - 组合adaptee
 */
class AdaptorObj implements Target {
    private Adaptee adaptee;

    public AdaptorObj(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        System.out.println("AdaptorObj do");
        this.adaptee.specialRequest();
    }
}
/**
 * ===============================================================
 * 类适配器 - 通过继承被适配者，实现目标接口的形式
 * 单继承有限制，不够灵活，且会将adaptee方法也暴露在适配器adaptor中来
 * ===============================================================
 */

/**
 * 适配器 - 类适配器模式 - 继承adaptee
 */
class AdaptorClass extends Adaptee implements Target {

    @Override
    public void request() {
        System.out.println("AdaptorClass do");
        super.specialRequest();
    }
}


/**
 * ===============================================================
 * 适配器抽象类（空实现被适配接口中的所有方法)
 * 接口适配器模式中被适配的对象是一个接口，
 * 在我们不需要全部实现被适配接口中提供的方法时，可以设计一个抽象类先实现该接口，并空实现接口中的所有方法。
 * 这样继承了该抽象类的子类就可以选择性的重写其中的方法，以达到想要的效果
 * ===============================================================
 */

/**
 * 被适配的接口
 */
interface AdapteeInterface {
    void method1();

    void method2();

    void method3();
}

/**
 * 适配器抽象类（空实现被适配接口中的所有方法)
 */
abstract class AdaptorAbs implements AdapteeInterface {
    @Override
    public void method1() {

    }

    @Override
    public void method2() {

    }

    @Override
    public void method3() {

    }
}

