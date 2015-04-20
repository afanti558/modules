package com.raipeng.anotation;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestCase {
    public void getFruitInfo(String clazz) throws ClassNotFoundException {
        Class<?> cls = Class.forName(clazz);
        Field[] fields = cls.getDeclaredFields();
        Method[] methods = cls.getMethods();

        for (Field field:fields) {
            if (field.isAnnotationPresent(FruitColor.class)) {
                FruitColor color = field.getAnnotation(FruitColor.class);
                System.out.println("Fruit Color:"+color.fruitColor());
            }
        }
        for (Method method:methods) {
            if (method.isAnnotationPresent(Perfact.class)) {
                Perfact perfact = method.getAnnotation(Perfact.class);
                if ("好的".equals(perfact.perfact())) {
                    System.out.println("这个苹果是" + perfact.perfact());
                }else {
                    System.out.println("这个苹果是" + perfact.perfact());
                }

            }
        }

    }

    @Test
    public void test() {
        try {
            new TestCase().getFruitInfo("com.raipeng.anotation.Apple");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
