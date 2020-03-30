package study.daydayup.wolf.demo.my.starter.base.oo;

import lombok.Data;

/**
 * study.daydayup.wolf.demo.my.starter.base.oo
 *
 * @author Wingle
 * @since 2019/12/25 8:26 下午
 **/
public class GenDemo {

    public static void main(String[] args) {
        Order o = new Order();

        Child c = new Child(o);
        System.out.println(c.show());
    }

    @Data
    static class Parent<T> {
        protected T model;

        protected void init(T model) {
            this.model = model;
        }
    }

    @Data
    static class Child extends Parent<Order> {
        private String name;

        public Child(Order o) {
            init(o);
        }

        public String show() {
            return this.model.getName();
        }
    }

    @Data
    static class Order {
        private String name = "order";
    }
}
