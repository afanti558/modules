package com.lxf.xml;

import pojo.*;

import com.thoughtworks.xstream.XStream;


/**
 * bean 和 xml相互转换
 * @description
 * @author 
 * @time 2014-9-11下午1:38:37
 */
public class XStreams {
	/**
	 * 将bean转换为xml
	 * @return void
	 * @description 
	 * @author linxiaofan
	 * @time 2014-9-11 下午3:24:30
	 */
	public static void beanToxml(){
		XStream xstream = new XStream();
//		XStreams xstream = new XStreams(new DomDriver()); // does not require XPP3 library
		//不带格式
//		XStream xstream = new XStream(new StaxDriver());// does not require XPP3 library starting with Java 6
		User user = new User(3,"小张");
		xstream.alias("user", User.class);
		String xml = xstream.toXML(user);
		System.out.println(xml);
	}
	
	public static void xmlToBean(){
		XStream xstream = new XStream();
		xstream.alias("user", User.class);
		String xml = "<user><id>3</id><name>小张</name></user>";
		User user = (User) xstream.fromXML(xml);
		System.out.println(user);
		
	}
	//测试
	public static void main(String args []){
		System.out.println("beanTomxl:");
		beanToxml();
		System.out.println("*************************");
		System.out.println("xmlTobean:");
		xmlToBean();
	}
}
