package com.raipeng.loganalysis;

import org.junit.Test;

import java.io.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class Log {
    private LogAnalysisImpl logAnalysis = null;
    private LogData logData;

    @Test
    public void test() throws IOException, ParseException {
        File f = readFile("mp.2015-04-10.log");
        Map<String,String> map = new HashMap<String,String>();
        map.put("options", "coco");
        map.put("industryId", "d2fc1c67fb57495b916ee8ad4526e426");
        statistics(f, "shopping", "2010-04-10 14:00:00", "2015-04-10 14:00:00",map);

    }

    /**
     * @param file 读取的文件
     * @param modulekey 模块关键词
     * @param begintime 开始统计日期，如果为null或""表示从最初日期
     * @param endtime 开始统计日期，如果为null或""表示到统计时间
     */
    public void statistics(File file,String modulekey,String begintime,String endtime,Map<String,String> map) throws IOException, ParseException {
        InputStream in = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(in, "UTF-8");
        BufferedReader reader = new BufferedReader(isr);
        // 一次读入一行，直到读入null为文件结束
        String tempString = null;
        logAnalysis = new LogAnalysis() ;
        while ((tempString = reader.readLine()) != null) {
        	if(!tempString.contains("::"))
        		continue;
        	System.out.println(tempString);
        	logData = new LogData(tempString);
            logAnalysis.modu_visitNum(logData, modulekey, begintime, endtime,map);
        }
        logAnalysis.print();
    }

    //按照文件名称去读取文件
    public File readFile(String fileName) {
        File[] f = new File("d:\\").listFiles();
        for (int i = 0; i < f.length; i++) {
            if (fileName.equals(f[i].getName())) {
                return f[i];
            }
        }
        return null;
    }

    public static void main(String[] args) throws ParseException {

    }


}
