package com.lxf.httpsend;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;

/**
 * Http访问操作类
 * @author Administrator
 *
 */
public class HttpClientHelper {
	 // http请求对象 
	private static final HttpClient HTTPCLIENT;
	 // 请求超时毫秒数 
	private static final int TIMEOUT = 10000;
	 // 编码
	private static final String CHARSET = "utf-8";
	 //代理信息
	private static final boolean ISPROXY = false;
	private static final String PROXYIP = "192.168.19.9";
	private static final int PROXYPORT = 80;
	
	static {
		// 初始化http请求对象
		HTTPCLIENT = new HttpClient();
		// 设置请求超时毫秒数
		HttpConnectionManagerParams httpConnectionManagerParams = new HttpConnectionManagerParams();
		httpConnectionManagerParams.setConnectionTimeout(TIMEOUT);
		HttpConnectionManager httpConnectionManager = new SimpleHttpConnectionManager();
		httpConnectionManager.setParams(httpConnectionManagerParams);
		HTTPCLIENT.setHttpConnectionManager(httpConnectionManager);
		// 设置代理服务器
		if (ISPROXY) {
			HTTPCLIENT.getHostConfiguration().setProxy(PROXYIP, PROXYPORT);
		}
		//https协议
		Protocol myhttps;
		try {
			myhttps = new Protocol("https", new MySecureProtocolSocketFactory (), 443);
			Protocol.registerProtocol("https ", myhttps);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]){
		System.out.println(sendGet("http://www.baidu.com/",true));
	}
	/**
	 * 发送Get请求  
	 * @param url
	 * @return
	 */
	public static String sendGet(String url) {
		return sendGet(url, false);
	}
	
	/**
	 * 发送http	GET请求
	 * author linxiaofan
	 * @param url	请求url地址
	 * @param isJson	
	 * @return
	 */
	public static String sendGet(String url, boolean isJson) {
		String result = null;
		GetMethod getMethod = new GetMethod(url);
		//设置成了默认的恢复策略，在发生异常时候将自动重试3次，在这里你也可以设置成自定义的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		// 设置请求编码
		getMethod.getParams().setHttpElementCharset(CHARSET);
		getMethod.getParams().setContentCharset(CHARSET);
		getMethod.getParams().setCredentialCharset(CHARSET);
		//***************
		if (isJson) {
			getMethod.addRequestHeader("Accept", "application/json");
		}
		try {
			//执行getMethod
			int status = HTTPCLIENT.executeMethod(getMethod);
			if (status != HttpStatus.SC_OK) {
				System.out.println("getMethod failed: " + getMethod.getStatusLine());
			}
			result = new String(getMethod.getResponseBodyAsString().getBytes(CHARSET));
		} catch (HttpException e) {
			e.printStackTrace();
			System.out.println("http get请求异常:" + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("http get IO流异常" + e.getMessage());
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		return result;
	}
	
	/**
	 * 发送post请求
	 * @param url
	 * @param params
	 * @return
	 */
	public static String sendPost(String url, Map<String, String> params) {
		return sendPost(url, params, false);
	}
	
	/**
	 * 发送Post请求
	 * @param url url地址
	 * @param params 请求参数
	 * @return
	 */
	public static String sendPost(String url, Map<String, String> params, boolean isJson) {
		
		String result = null;
		PostMethod postMethod = new PostMethod(url);
		// 默认恢复策略
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		// 设置请求编码
		postMethod.getParams().setHttpElementCharset(CHARSET);
		postMethod.getParams().setContentCharset(CHARSET);
		postMethod.getParams().setCredentialCharset(CHARSET);
		if (isJson) postMethod.addRequestHeader("Accept", "application/json");
		if (MapUtils.isNotEmpty(params)) {//设参数
			Set<String> keys = params.keySet();
			for (String key : keys) {
				postMethod.addParameter(key, params.get(key));
			}
		}
		//第二种设参数
//		NameValuePair[] data = { new NameValuePair("id", "youUserName"),new NameValuePair("passwd", "yourPwd") };
//		postMethod.setRequestBody(data);
		try {
			int status = HTTPCLIENT.executeMethod(postMethod);
			result = new String(postMethod.getResponseBodyAsString().getBytes(CHARSET));
			if (status != HttpStatus.SC_OK) {
				System.out.println("postMethod failed: " + postMethod.getStatusLine());
				return null;
			}
			// HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发
			// 301或者302
			if (status == HttpStatus.SC_MOVED_PERMANENTLY || 
					status == HttpStatus.SC_MOVED_TEMPORARILY) {
			    // 从头中取出转向的地址
			    Header locationHeader = postMethod.getResponseHeader("location");
			    String location = null;
			    if (locationHeader != null) {
			       location = locationHeader.getValue();
			       System.out.println("The page was redirected to:" + location);
			    } else {
			       System.out.println("Location field value is null.");
			    }
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();// 释放连接
		}
		return result;
	}


	public static String sendPost(String url, List<HttpParam> params, boolean isJson) {
		String result = null;
		PostMethod postMethod = new PostMethod(url);
		// 默认恢复策略
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		// 设置请求编码
		postMethod.getParams().setHttpElementCharset(CHARSET);
		postMethod.getParams().setContentCharset(CHARSET);
		postMethod.getParams().setCredentialCharset(CHARSET);
		if (isJson)
			postMethod.addRequestHeader("Accept", "application/json");
		if (CollectionUtils.isNotEmpty(params)) {
			for (HttpParam param : params) {
				postMethod.addParameter(param.getKey(), param.getValue());
			}
		}
		try {
			int status = HTTPCLIENT.executeMethod(postMethod);
			result = postMethod.getResponseBodyAsString();
			if (status != HttpStatus.SC_OK) {
				System.out.println("http请求失败:" + status);
				return result;
			}
		} catch (HttpException e) {
			e.printStackTrace();
			System.out.println("http post请求异常:" + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("http post IO流异常" + e.getMessage());
		} finally {
			// 释放连接
			postMethod.releaseConnection();
		}
		return result;
	}
	
	
	/**
	 * http请求参数
	 */
	public static class HttpParam {
		private String key;
		private String value;
		public HttpParam() {
		}
		public HttpParam(String key, String value) {
			this.key = key;
			this.value = value;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
}
