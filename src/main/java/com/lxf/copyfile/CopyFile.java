package com.lxf.copyfile;

import java.io.*;

/**
 * Created by xiaofan on 2015/3/17.
 */
public class CopyFile {
    private OutputStream out ;
    private InputStream in;

    public String copyFile(String src,String dest) throws FileNotFoundException,IOException {
        String flag = "拷贝失败";
        File file1 = new File(src);
        File file2=new File(dest);
        if(!file1.exists()){
            flag = "待拷贝文件不存在";
        }else {
            in = new FileInputStream(file1);
            out = new FileOutputStream(file2,true);
            byte[] b = new byte[1024];
            int temp = 0,len = 0;
            while ((temp = in.read()) != -1){
                out.write(temp);
            }
            in.close();
            out.close();
            flag = "拷贝成功";
        }
        return flag;
    }

    public static void main(String []args){
        String src = "d:" + File.separator + "ljl.docx";
        String dest = "d:" + File.separator + "ljl-副本.docx";
        CopyFile copyFile = new CopyFile();
        try {
            System.out.println(copyFile.copyFile(src, dest));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
