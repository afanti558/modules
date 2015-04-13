package com.raipeng.fastjson;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import pojo.*;

import java.util.ArrayList;
import java.util.List;

public class Json {

    //JavaObject to JsonString
    public static String createJsonString(Object value) {
        String alibabaJson = JSON.toJSONString(value);
        return alibabaJson;
    }

    //JavaObject to JsonObject
    public static JSONObject createJsonObject(Object value) {
        JSONObject jSONObject = JSON.parseObject(JSON.toJSONString(value));
        return jSONObject;
    }

    @Test
    public void test1() {
        Teacher teacher = new Teacher("林老师");
        List<Student> studentList = new ArrayList<Student>();
        Student s1 = new Student("01", "张三");
        Student s2 = new Student("02", "李四");
        studentList.add(s1);
        studentList.add(s2);
        String classes[] = {"二年级一班", "二年级三班", "三年级"};
        teacher.setStudent(studentList);
        teacher.setClasses(classes);
        System.out.println(createJsonString(teacher));
        System.out.println(createJsonObject(teacher));
    }
/* *********************************************************************************** */

    //JsonString to JavaObject
    public <T> T getJavaObject(String jsonString, Class T) {
        T t = null;
        try {
            t = (T) JSON.parseObject(jsonString, T);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    @Test
    public void test2() {
        String jsonStr = "{\"classes\":[\"二年级一班\",\"二年级三班\",\"三年级\"],\"student\":" +
                "[{\"studentNo\":\"01\",\"studentName\":\"张三\"},{\"studentNo\":\"02\",\"studentName\":\"李四\"}],\"teacherName\":\"林老师\"}";
        Teacher teacher = getJavaObject(jsonStr, Teacher.class);
        System.out.println(teacher.toString());
    }


    //JsonString to ObjectList
    public static <T> List<T> getBeanList(String jsonString, Class T) {
        List<T> list = new ArrayList<T>();
        try {
            list = JSON.parseArray(jsonString, T);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}