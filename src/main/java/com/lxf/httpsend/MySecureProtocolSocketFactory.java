package com.lxf.httpsend;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;

public class MySecureProtocolSocketFactory implements SecureProtocolSocketFactory {

	public MySecureProtocolSocketFactory() throws Exception{
		// 创建SSLContext对象，并使用我们指定的信任管理器初始化
		TrustManager[] tm = { new MyX509TrustManager() };
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		// 从上述SSLContext对象中得到SSLSocketFactory对象
		SSLSocketFactory ssf = sslContext.getSocketFactory();
	}
	@Override
	public Socket createSocket(String arg0, int arg1) throws IOException,
			UnknownHostException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Socket createSocket(String arg0, int arg1, InetAddress arg2, int arg3)
			throws IOException, UnknownHostException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Socket createSocket(String arg0, int arg1, InetAddress arg2,
			int arg3, HttpConnectionParams arg4) throws IOException,
			UnknownHostException, ConnectTimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Socket createSocket(Socket arg0, String arg1, int arg2, boolean arg3)
			throws IOException, UnknownHostException {
		// TODO Auto-generated method stub
		return null;
	}

}
