package com.raipeng.loganalysis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

class LogAnalysis extends LogAnalysisImpl {
    private static int visit = 0;

    /**
     * 模块访问量统计
     * @param logData 每条日志记录
     * @param analysiskey 模块关键词
     * @param starttime 统计开始时间
     * @param endtime 统计结束时间
     */
    @Override
	public void statistic(LogData logData, String analysiskey, String starttime, String endtime, Map<String, String> map) throws ParseException {
        String key = logData.getModuleKey();
        Date date = logData.getDatetime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        Date date2 = null;
        if (starttime == null || starttime.equals("")) {
            date1 = sdf.parse("2015-01-01 00:00:00");
        }else {
            date1 = sdf.parse(starttime);
        }
        if (endtime == null || endtime.equals("")) {
            date2 = new Date();
        }else {
            date2 = sdf.parse(endtime);
        }

        if (analysiskey.equals(key) && date.after(date1) && date.before(date2) && map.size()==0){
            visit++;
        }
        
        if (analysiskey.equals(key) && date.after(date1) && date.before(date2) && map.size()>0){
            Iterator<String> paramkeys = map.keySet().iterator();
            while(paramkeys.hasNext()){
            	String paramkey = paramkeys.next();
            	if(!logData.getParams().containsKey(paramkey) || !logData.getParams().get(paramkey).equals(map.get(paramkey))){
                    return;
                }
            }
        	visit++;
        }
    }

    @Override
    public void print(){
    	System.out.println("统计总数为:"+visit);
    }

	public int getVisit() {
		return visit;
	}


}
