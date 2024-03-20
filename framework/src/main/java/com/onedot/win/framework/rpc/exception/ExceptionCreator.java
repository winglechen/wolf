package com.onedot.win.framework.rpc.exception;

import org.apache.dubbo.common.bytecode.ClassGenerator;
import com.onedot.win.common.lang.exception.BusinessException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * com.onedot.win.framework.rpc.exception
 *
 * @author Wingle
 * @since 2019/11/5 5:37 下午
 **/
public class ExceptionCreator {
    public static Class create(String className) {
        ClassGenerator generator = ClassGenerator.newInstance();
        generator.setClassName(className);
        generator.setSuperClass(BusinessException.class);
        generator.addConstructor(Modifier.PUBLIC, new Class<?>[]{String.class}, new Class<?>[0], "super($1);");
        generator.addDefaultConstructor();
        return null;
        //return generator.toClass();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("hello");
        Class<?> clazz = ExceptionCreator.create("DemoBizException");
        System.out.println(clazz);

        if (clazz == null) {
            return;
        }
        Constructor c = clazz.getDeclaredConstructor(new Class[]{String.class});
        c.setAccessible(true);
        BusinessException eObj = (BusinessException) c.newInstance(new Object[]{"hello"});
        System.out.println(eObj);

        System.out.println("eName: " + eObj.getClass().getName());
        System.out.println("eMsg: " + eObj.getMessage());

        try {
            throw eObj;
        } catch (BusinessException e) {
            System.out.println("catched biz exception:" + e);
        } catch (Exception e) {
            System.out.println("catched nor exception:" + e);
        }

//        BusinessException instance = (BusinessException) clazz.newInstance("I am the message of DemoBizException");
//        instance.setCode(10);
//
//        throw instance;
    }
}
