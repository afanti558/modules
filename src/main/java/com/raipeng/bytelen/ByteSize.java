package com.raipeng.bytelen;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ByteSize{
    private static Map<String,Integer> map ;

    public ByteSize() {
        map = new HashMap<String,Integer>();
    }

    /**
     * 计算采用指定的编码方式时字符串所占字节数
     * @param content 带求的字符串
     * @param encode 编码方式
     * @return 占用的字节数
     */
    public Map<String,Integer> getByteSize(String content,String encode) {
        Map<String,Integer> map = new HashMap<String,Integer>();
        int size = 0;
        int len = 0;
        int leng = 0;
        if (null != content) {
            try {
                len = content.getBytes().length;//系统默认的字节数组
                map.put("默认",len);
                size = content.getBytes(encode).length;//指定编码
                map.put("utf-8",size);
                leng = content.getBytes("ASCII").length;//字符集
                map.put("ASCII",leng);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static void main(String [] args){
        ByteSize byteSize = new ByteSize();
        map = byteSize.getByteSize("你好", "UTF-8");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}