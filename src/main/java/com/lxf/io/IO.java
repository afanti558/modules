package com.lxf.io;

import com.lxf.iptimestamp.IpTimeStamp;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xflin on 2015/2/6.
 */
public class IO {

    public IO() {
    }

    public static void main(String[] args) {
        //图片网络地址
        String strUrl = "http://www.haha365.com/uploadfile/2010/0722/20100722095657946.jpg";
        String savePath = "H:" + File.separator + "downMedia";
        Boolean flag = getMediaFile(strUrl, savePath);
        String str = getMediaFile(strUrl);
//        String charSet = get_charset(new File("H:\\111.txt"));
        System.out.println("图片的字节流字符串为：" + str);

    }


    /**
     * 拉取网络资源并本地保存
     * @param strUrl 网络资源路径
     * @param savePath 保存本地路径
     * @return true/false
     */
    public static Boolean getMediaFile(String strUrl,String savePath) {
        boolean flag = false;
        File file = new File(savePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            // 根据内容类型获取扩展名
            String fileExt = getFileExtName(conn.getHeaderField("Content-Type"));
            IpTimeStamp ipTimeStamp = new IpTimeStamp("192.168.1.110");
            String fileFullName = savePath + File.separator + ipTimeStamp.getTimeStamp() + "." + fileExt;
            File f = new File(fileFullName);
            if (!f.exists()) {
                f.createNewFile();
            }
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            FileOutputStream fis = new FileOutputStream(f);
            byte[] bytes = new byte[8096];
            int size;
            while ((size = bis.read(bytes)) != -1)
                fis.write(bytes, 0, size);
            fis.flush();
            fis.close();
            bis.close();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 获取网络上的资源返回不带换行空格的字节码字符串
     * @param strUrl  网络资源路径
     * @return 资源的字节码字符串
     */
    public static String getMediaFile(String strUrl){
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            // 根据内容类型获取扩展名
            String fileExt = getFileExtName(conn.getHeaderField("Content-Type"));
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            byte[] bytes = new byte[8096];
            int size;
            while ((size = bis.read(bytes)) != -1)
                sb.append(new String(bytes, 0, size,"GBK"));
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }




    /**
     * 根据内容类型判断文件扩展名
     * @param contentType 内容类型
     * @return 文件后缀名
     */
    public static String getFileExtName(String contentType) {
        String fileExt = "";
        if ("image/jpg".equals(contentType) || "image/jpeg".equals(contentType))
            fileExt = ".jpg";
        else if ("audio/mpeg".equals(contentType))
            fileExt = ".mp3";
        else if ("audio/amr".equals(contentType))
            fileExt = ".amr";
        else if ("video/mp4".equals(contentType) || "video/mpeg4".equals(contentType))
            fileExt = ".mp4";
        else if("text/x-vcard".equals(contentType)){
            fileExt = ".vcf";
        }
        return fileExt;
    }

    /**
     * 将字符串中的空格换行等去掉
     * @param str 需要处理的字符串
     * @return 处理之后的字符串
     */
    public static String changeString(String str){
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(str);
        str = m.replaceAll("");
        return str;
    }


    /**
     * 判断文件编码格式
     * @param file
     * @return
     */
    public static String get_charset( File file ) {
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            BufferedInputStream bis = new BufferedInputStream( new FileInputStream( file ) );
            bis.mark( 0 );
            int read = bis.read( first3Bytes, 0, 3 );
            if ( read == -1 ) return charset;
            if ( first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE ) {
                charset = "UTF-16LE";
                checked = true;
            }
            else if ( first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF ) {
                charset = "UTF-16BE";
                checked = true;
            }
            else if ( first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB && first3Bytes[2] == (byte) 0xBF ) {
                charset = "UTF-8";
                checked = true;
            }
            bis.reset();
            if ( !checked ) {
                //	int len = 0;
                int loc = 0;
                while ( (read = bis.read()) != -1 ) {
                    loc++;
                    if ( read >= 0xF0 ) break;
                    if ( 0x80 <= read && read <= 0xBF ) // 单独出现BF以下的，也算是GBK
                        break;
                    if ( 0xC0 <= read && read <= 0xDF ) {
                        read = bis.read();
                        if ( 0x80 <= read && read <= 0xBF ) // 双字节 (0xC0 - 0xDF) (0x80
                            // - 0xBF),也可能在GB编码内
                            continue;
                        else break;
                    }
                    else if ( 0xE0 <= read && read <= 0xEF ) {// 也有可能出错，但是几率较小
                        read = bis.read();
                        if ( 0x80 <= read && read <= 0xBF ) {
                            read = bis.read();
                            if ( 0x80 <= read && read <= 0xBF ) {
                                charset = "UTF-8";
                                break;
                            }
                            else break;
                        }
                        else break;
                    }
                }
                //System.out.println( loc + " " + Integer.toHexString( read ) );
            }

            bis.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return charset;
    }


}
