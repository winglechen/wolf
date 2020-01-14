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
        Parent parent = new Parent();
        Child child = new Child();

        System.out.println("parent: " + parent.getSurName());
        System.out.println("child: " + child.getSurName());
    }

    @Data
    static class Parent {
        protected String name = "parent";
        protected String surName = "parent";
    }

    @Data
    static class Child extends Parent {
        private String surName = "child";
        {
            name = "child";
        }
    }
}
