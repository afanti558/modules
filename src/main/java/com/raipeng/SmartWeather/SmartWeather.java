package com.raipeng.SmartWeather;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.raipeng.hmacsha1.HMACSHA1;
import com.raipeng.httpsend.HttpClientHelper;
import com.thoughtworks.xstream.core.util.Base64Encoder;


public class SmartWeather {
	private static final String ENCODING = "UTF-8";
	private static final String APPID = "3eb15326f26e9fc8";
	private static final String PRIVATE_KEY = "16e2b5_SmartWeatherAPI_61dc81e";
	private static final String URL_HEADER="http://webapi.weather.com.cn/data/?";
	
	/**
	 * @description 公钥和私钥加密生成令牌(key)
	 * @author linxiaofan
	 * @param public_key 公钥，为不包含key在内的全部URL，这里的appid为完整的appid
	 * @param private_key 私钥，仅用于生成令牌(key)
	 * @return
	 * @throws Exception 
	 */
	public static String getKey(String public_key, String private_key) throws Exception{
		String key = "";
		byte[] key_bytes = HMACSHA1.HmacSHA1Encrypt(public_key, private_key);
		Base64Encoder base64Encoder = new Base64Encoder();
		String temp = base64Encoder.encode(key_bytes);
		key = URLEncoder.encode(temp, ENCODING); 
		return key;
	}
	
	
	public static JSONObject getWeather(String areaCode){
		JSONObject json = null;//返回json对象
		String key = null;//令牌
		String url = null;//访问url
		String public_key = null;//公钥
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmm");
        String date = dateFormat.format(new Date());
        //拼接公钥
      	public_key = URL_HEADER+"areaid="+areaCode+"&type=observe&date="+date+"&appid="+APPID;
        //公钥和私钥生成令牌key
  		try {
  			key=getKey(public_key,PRIVATE_KEY);
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		url = URL_HEADER+"areaid="+areaCode+"&type=observe&date="+date+"&appid="+APPID.substring(0, 6)+"&key="+key;
  		json = JSONObject.parseObject(HttpClientHelper.sendGet(url));
		return json;
	}
	
	public static void main(String args []){
		System.out.println(getWeather("101010100"));
	}
}
