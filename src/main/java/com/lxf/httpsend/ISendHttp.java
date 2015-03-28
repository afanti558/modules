package com.lxf.httpsend;

/**
 * java.net 原生态的请求模拟
 * Java HTTP请求对象 发送GET/POST请求工具类
 * @author nice.man
 *
 */
public interface ISendHttp {
	/**
	 * 发送http请求
	 * @param url 发送请求的url地址
	 * @param pram 需要发送的参数，格式：pram=1&pram1=2
	 * @param method 发送请求的方式，参数为： GET/POST 如果为null或"",则默认为GET请求
	 * @clazz 返回类型,JSONObject表示返回json对象，String表示返回String对象(就用这两个)
	 * @return T对象
	 */
	public <T> T sendHttp(String url , String pram, String method, T clazz);
	
	
}
