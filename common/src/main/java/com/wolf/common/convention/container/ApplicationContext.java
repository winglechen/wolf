package com.wolf.common.convention.container;

import com.wolf.common.lang.exception.lang.ClassNotFoundException;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext implements Context {
    private final ConcurrentHashMap<String, Object> objectMap = new ConcurrentHashMap<String, Object>();

    public void register(Object o) {
        objectMap.put(o.getClass().getName(), o);
    }

    public void register(Object o, Class<?> clazz) {
        objectMap.put(clazz.getName(), o);
    }

    public <T> T getBean(Class<T> clazz) {
        return getBean(clazz, true);
    }

    public <T> T getBean(Class<T> clazz, boolean throwNotFoundException) {
        Object bean = objectMap.get(clazz.getName());

        if (bean == null) {
            return null;
        }

        if (clazz.isInstance(bean)) {
            return clazz.cast(bean);
        }

        if (!throwNotFoundException) {
            return null;
        }

        throw new ClassNotFoundException(clazz.getName());
    }

//    public static void main(String[] args) {
//        ApplicationContext context = new ApplicationContext();
//        Person p = new Person();
//
//        context.register(p, Person.class);
//
//        ObjectTree objectTree = new ObjectTree();
//        context.register(objectTree);
//
//        context.getBean(ObjectTree.class);
//    }
//
//    static class Person {
//
//    }
}
