package com.lxf.copyfile;
import java.io.*;

public class CopyFile {
    private OutputStream out ;
    private InputStream in;

    public String copyFile(String src,String dest) throws FileNotFoundException,IOException {
        String msg = "拷贝失败";
        File file1 = new File(src);
        File file2=new File(dest);
        if(!file1.exists()){
            msg = "待拷贝文件不存在";
        }else {
            in = new FileInputStream(file1);
            out = new FileOutputStream(file2,true);//追加
            byte[] b = new byte[1024];//分块读取提高速率
            int temp = 0,len = 0;
            while ((temp = in.read()) != -1){
                out.write(temp);
            }
            in.close();
            out.close();
            msg = "拷贝成功";
        }
        return msg;
    }

    public static void main(String []args){
        String src = "d:" + File.separator + "ljl.docx";
        String dest = "d:" + File.separator + "ljl-副本.docx";
        String msg = "";
        CopyFile copyFile = new CopyFile();
        try {
            msg = copyFile.copyFile(src, dest);
        } catch (FileNotFoundException e) {
            System.out.println("msg:" + msg);
        } catch (IOException e) {
            System.out.println("msg:" + msg);
        }
    }

}
