package com.raipeng.loganalysis;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Log {
    private LogAnalysisImpl logAnalysis = null;
    private LogData logData;

    @Test
    public void test() throws IOException, ParseException {
        File[] f = readFile("2015-04-10","2015-04-11");
        Map<String,String> map = new HashMap<String,String>();
        map.put("options", "coco");
        map.put("industryId", "d2fc1c67fb57495b916ee8ad4526e426");
        for (int i = 0; i <f.length ; i++) {
            statistics(f[i], "shopping", "2010-04-10 14:00:00", "2015-04-10 14:00:00",map);
        }


    }

    /**
     * @param file 读取的文件
     * @param modulekey 模块关键词
     * @param begintime 开始统计日期，如果为null或""表示从最初日期
     * @param endtime 开始统计日期，如果为null或""表示到统计时间
     */
    public void statistics(File file,String modulekey,String begintime,String endtime,Map<String,String> map) throws IOException, ParseException {
//        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
//        BufferedReader reader = new BufferedReader(isr);


        BufferedReader reader = new BufferedReader(new FileReader(file),5*1024*1024);



        // 一次读入一行，直到读入null为文件结束
        String tempString = null;
        logAnalysis = new LogAnalysis() ;
        while ((tempString = reader.readLine()) != null) {
        	if(!tempString.contains("::")){
                continue;
            }
//        	System.out.println(tempString);
        	logData = new LogData(tempString);
            logAnalysis.statistic(logData, modulekey, begintime, endtime, map);
        }
        logAnalysis.print();
    }

    //按照文件名称去读取文件
    public File[] readFile(String begin,String end) {
        File[] f = new File("d:\\").listFiles();
        List<File> list = new ArrayList<File>();
        for (int i = 0; i < f.length; i++) {
            String name = f[i].getName();
            if (name.matches("\\d{4}-\\d{2}-\\d{2}\\.log")) {

                if (name.compareTo(begin+".log")>=0 && name.compareTo(end+".log")<=0) {
                    list.add(new File("d:\\" + name));
                }
            }
        }

        File[] dest = list.toArray(new File[list.size()]);

//        for (int i = 0; i < f.length; i++) {
//            if (fileName.equals(f[i].getName())) {
//                return f[i];
//            }
//        }
        return dest;
    }

    @Test
    public void test2() {
        String str = "2015-04-05";
        String begin = "2015-04-03";
        String end = "2015-04-05";
        System.out.println("begin:" +str.compareTo(begin) );
        System.out.println("end:" +str.compareTo(end) );
    }

}
