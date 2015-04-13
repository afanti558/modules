package com.raipeng.loganalysis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 可分析的每条日志
 */
public class LogData {
    private String moduleKey;
    private Date datetime;
    private String ip;
    private String baseinfo;
    private String sessionid;
    private Map<String,String> params;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public String getModuleKey() {
        return moduleKey;
    }

    public void setModuleKey(String moduleKey) {
        this.moduleKey = moduleKey;
    }

    public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public Map<String,String> getParams() {
        return params;
    }

    public void setParams(Map<String,String> params) {
        this.params = params;
    }

	public String getBaseinfo() {
		return baseinfo;
	}

	public void setBaseinfo(String baseinfo) {
		this.baseinfo = baseinfo;
	}
     
	public LogData(String baseinfo){
    	if(baseinfo.matches(".+::([^:]+?)::([^:]+?)::([^:]+?.+)")){
        	try {
        		datetime = sdf.parse(baseinfo.substring(0, 19));
			} catch (Exception e) {
				e.printStackTrace();
			}
        	moduleKey = baseinfo.replaceAll(".+[-]+?(.+)::([^:]+?)::([^:]+?)::([^:]+?.+)","$1").trim();
    		ip = baseinfo.replaceAll(".+::([^:]+?)::([^:]+?)::([^:]+?.+)","$1");
        	sessionid = baseinfo.replaceAll(".+::([^:]+?)::([^:]+?)::([^:]+?.+)","$2");
        	if(sessionid.contains("."))
        		sessionid = sessionid.split("\\.")[0];
        	String ps = baseinfo.replaceAll(".+::([^:]+?)::([^:]+?)::([^:]+?.+)","$3");
        	this.params = transParams(ps);
    	}
	}
	
    public Map<String,String> transParams(String params) {
        String str = params.replaceAll("[\\[|\\]]", "");
        String[] strarr = str.split(",");
        Map<String,String> map = new HashMap<String,String>();
        for (int i = 0; i < strarr.length; i++) {
        	if(!strarr[i].contains("="))
        		continue;
            String[] temp = strarr[i].split("=");
            if(temp.length==2)
            	map.put(temp[0],temp[1]);
        }
        return map;
    }
    
}
