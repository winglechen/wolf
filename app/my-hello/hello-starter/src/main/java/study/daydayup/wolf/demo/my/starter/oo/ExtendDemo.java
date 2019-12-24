package study.daydayup.wolf.demo.my.starter.oo;

import lombok.Data;

/**
 * study.daydayup.wolf.demo.my.starter.oo
 *
 * @author Wingle
 * @since 2019/12/24 2:13 下午
 **/
public class ExtendDemo {

    public static void main(String[] args) {
        Child child = new Child();

        System.out.println(child.getName());
    }

    @Data
    static class Parent {
        protected String name = "parent";
    }

    static class Child extends Parent {
        {
            name = "child";
        }
    }
}
