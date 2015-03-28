package com.lxf.httpsend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**
 * Java HTTP请求对象 发送GET/POST请求工具类
 * @author nice.man
 *
 */

public class SendHttp {

	/**
	 * 发送http请求
	 * @param url 发送请求的url地址
	 * @param pram 需要发送的参数，格式：pram=1&pram1=2
	 * @param method 发送请求的方式，参数为： GET/POST 如果为null或"",则默认为GET请求
	 * @clazz 返回类型,JSONObject表示返回json对象，String表示返回String对象(就用这两个)
	 * @return T对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T sendHttp(String url, String pram, String method,T clazz) {
		PrintWriter out = null;
        BufferedReader in = null;//字符流
        String result = "";       
        HttpURLConnection conn =null;
        try {
            URL realUrl = new URL(url);
            conn = (HttpURLConnection)realUrl.openConnection();
            //POST请求的时候必须加上以下两行设置
            conn.setDoOutput(true);//设置输出流采用字节流
            conn.setDoInput(true);//设置输入流采用字节流
            conn.setUseCaches(false); //禁用缓存
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conn.setRequestProperty("Charset", "utf-8");
            if(method!=null && !method.equals("")) {//请求方式，默认为GET
            	conn.setRequestMethod(method);
            }
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            if(pram!=null && !pram.equals("")) {
            	out.print(pram);
            }
            System.out.println("请求的完整URL--->" + out);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应,InputStreamReader是字节流通向字符流的桥梁
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{//使用finally块来关闭输出流、输入流
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
                if(conn!=null) {
                	conn.disconnect();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        if(clazz instanceof JSONObject){
        	return (T) JSON.toJSON(result);
        }else{
        	return (T) result;
        }
	}
	
	//Test
//	http://baike.baidu.com/link?url=w1alqky5u_RXElhNeGfVYa5p9zG0-4Yej4bQBfY-B228noHtQPqM_GzsohX0YlAh  百度百科  知道
	public static void main(String args []){
		String param = "url=w1alqky5u_RXElhNeGfVYa5p9zG0-4Yej4bQBfY-B228noHtQPqM_GzsohX0YlAh";
//		System.out.println(new SendHttp().sendHttp("http://zhidao.baidu.com/", null, "POST", JSONObject.class));
		System.out.println(new SendHttp().sendHttp("http://baike.baidu.com/link?"+param,null, "GET", String.class));
	}

}
