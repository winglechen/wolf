package study.daydayup.wolf.common.util;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * study.daydayup.wolf.common.util
 *
 * @author Wingle
 * @since 2020/1/14 8:47 下午
 **/
public class CollectionUtilTest {

    @Test
    public void keys() {
    }

    @Test
    public void toMap() {
    }

    @Test
    public void sum() {
    }

    @Test
    public void groupAndSum() {
    }

    @Test
    public void group() {
    }

    @Test
    public void join() {
    }

    @Test
    public void testJoin() {
    }

    @Test
    public void toTest() {
        List<A> aList = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            A a = new A();
            a.setName("name:" + i);

            aList.add(a);
        }

        Converter converter = new Converter();
        List<B> bList = CollectionUtil.to(aList, converter::convert);

        assertEquals("convert fail", 10, bList.size());
        assertEquals("convert fail", "name:1", bList.get(1).getName());
    }


    @Data
    static class A {
        private String name;
    }

    @Data
    static class B {
        private String name;
    }

    static class Converter {
        public B convert(A a) {
            B b = new B();
            b.setName(a.getName());

            return b;
        }
    }

    //    public static void main(String[] args) {
//        List<Person> p1 = new ArrayList<>();
//        List<Person> p2 = new ArrayList<>();
//        Map<Integer, String> nameMap = new HashMap<>();
//
//        Person t1, t2;
//        for (int i=1; i<6; i++) {
//            t1 = new Person();
//            t1.setId(i);
//
//            t2 = new Person();
//            t2.setId(i);
//            t2.setName("s2_" + i);
//
//            if (i % 2 == 0) {
//                t1.setGender(0);
//                t2.setGender(0);
//            } else {
//                t1.setGender(1);
//                t2.setGender(1);
//            }
//
//            p1.add(t1);
//            p2.add(t2);
//            nameMap.put(i, "name_" + i);
//        }
//
//        List<Integer> ids = keys(p1, Person::getId);
//
//        System.out.println("persons: " + p1);
//        System.out.println("ids: " + ids);
//
//        Map<Integer, Person> map = toMap(p1, Person::getId);
//        System.out.println("map: " + map);
////
////        join(p1, Person::getId, Person::setName, nameMap);
////        System.out.println("after join persons: " + p1);
//        System.out.println("before join: " + p1);
//        join(p1, Person::getId, Person::setP, p2, Person::getId);
//        System.out.println("after join: " + p1);
//
//
//        Map<Integer, List<Person>> newMap = group(p1, Person::getGender);
//        System.out.println("gender Map: " + newMap);
//
//
//        long sum = sum(p1, Person::getId);
//        System.out.println("sum: " + sum);
//
//        Map<Integer, Long> sumMap = groupAndSum(p1, Person::getGender, Person::getId);
//        System.out.println("sumMap: " + sumMap);
//    }
//
    @Data
    static class Person {
        private int id;
        private String name;
        private int Gender;

        private Person p;
    }
}