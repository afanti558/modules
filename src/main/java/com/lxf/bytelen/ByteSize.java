package com.lxf.bytelen;
import java.io.UnsupportedEncodingException;

public class ByteSize{
    /**
     * 计算采用指定的编码方式时字符串所占字节数
     * @param content 带求的字符串
     * @param method 编码方式
     * @return 占用的字节数
     */
    public int getByteSize(String content,String method) {
        int size = 0;
        if (null != content) {
            try {
                size = content.getBytes(method).length;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return size;
    }

    public static void main(String [] args){
        ByteSize byteSize = new ByteSize();
        System.out.println(byteSize.getByteSize("你好","UTF-8"));
    }
}