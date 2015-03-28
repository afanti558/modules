package com.lxf.fastjson;

import com.alibaba.fastjson.JSON;
import pojo.*;

import java.util.ArrayList;
import java.util.List;


public class Json {
	
	//将一个对象转换为json格式,通常为服务器端返回的数据
	public static String createJsonString(Object value){
        String alibabaJson = JSON.toJSONString(value);
        return alibabaJson;
    }
	
	public static School getBean(String jsonString, Class School) {
		School school = null;
        try {
        	school = (pojo.School) JSON.parseObject(jsonString, School);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return school;
    }
	
	public static List<School> getBeanList(String jsonString, Class School) {
        List<School> list = new ArrayList<School>();
        try {
            list = JSON.parseArray(jsonString, School);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return list;
    }

	//Test
	public static void main(String args[]){
		//组装数据
		Teacher teacher = new Teacher("林老师");
		Student s1 = new Student("01","张三");
		Student s2 = new Student("01","张三");
		List<Student> studentlist = new ArrayList<Student>();
		studentlist.add(s1);
		studentlist.add(s2);
		Collega c1 = new Collega();	
		c1.setProfessionalNo("001");	
		c1.setProfessionalName("应用数学");
		c1.setTeacher(teacher);
		c1.setStudent(studentlist);
		List<Collega> collegalist = new ArrayList<Collega>();
		collegalist.add(c1);
		School school1 = new School("0001","石化大学","丹东路西段一号",collegalist);
		
		//接受服务器端返回的请求json格式的字符串数据		
		String jsonStr = createJsonString(school1);
		System.out.println(jsonStr);
/***********************************************************************/
		
		//将json格式的字符串转换为相应的bean
		School school = getBean(jsonStr,School.class);
		List<Collega> collegaList = school.getCollega();
		System.out.println(collegaList.get(0).getTeacher());
		List<Student> studentList = collegaList.get(0).getStudent();
		System.out.println(studentList.get(0).getStudentName());
		
		
		//将json格式的字符串数组转换为相应的bean数组
//		String jsonStrArr = "["+createJsonString(school)+"]";//接受服务器端返回的请求json格式的字符串数据
//		System.out.println(getBeanList(jsonStrArr,School.class));
		
	}
}
