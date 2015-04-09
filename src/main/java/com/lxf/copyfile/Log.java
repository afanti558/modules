package com.lxf.copyfile;

import org.junit.Test;

import java.io.*;
import java.util.*;

public class Log {

    @Test
    public void test() throws IOException {
        File f = readFile("mp.2015-03-26.log");
            Map<String,String> map = new Log().getData(f);
            for (Map.Entry<String,String> entry:map.entrySet()){
                System.out.println(entry.getKey() + "----->" + entry.getValue());
            }
    }

    public Map<String, String> getDatas(File file) throws IOException {
        InputStream in = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(in, "UTF-8");
        BufferedReader reader = new BufferedReader(isr);
        Map<String, String> map = new HashMap<String, String>();
        // 一次读入一行，直到读入null为文件结束
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
            String[] temp = tempString.split("::");
//            for (int i = 0; i < temp.length; i++) {
//                System.out.println("temp["+i+"]" + "------->" + temp[i]);
//            }
            map.put("module",temp[0]);
            map.put("time",temp[1]);
            map.put("session",temp[2]);
            map.put("params",temp[3]);
        }
        return map;
    }

    public Map<String, String> getData(File file) throws IOException {
        InputStream in = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(in, "GBK");
        BufferedReader reader = new BufferedReader(isr);
        Map<String, String> map = new HashMap<String, String>();
        // 一次读入一行，直到读入null为文件结束
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
            List list = new ArrayList();
            while (tempString.indexOf("::") > 0 ) {
                int index = tempString.indexOf("::");
                list.add(index);
                tempString = tempString.replaceFirst("::",":");
            }
            map.put("module",tempString.substring(0, (Integer) list.get(0)));
            map.put("time",tempString.substring((Integer) list.get(0)+1, (Integer) list.get(1)));
            map.put("session",tempString.substring((Integer) list.get(1)+1, (Integer) list.get(2)));
            map.put("params",tempString.substring((Integer) list.get(2)+1));
        }
        return map;
    }

    //按照文件名称去读取文件
    public File readFile(String fileName) {
        File file = null;
        File[] f = new File("d:\\").listFiles();
        for (int i = 0; i < f.length; i++) {
//            System.out.println("**********" + f[i].getName());
            if (fileName.equals(f[i].getName())) {
                return f[i];
            }
        }
        return null;
    }


}
