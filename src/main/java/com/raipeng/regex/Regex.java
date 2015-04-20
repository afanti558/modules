package com.raipeng.regex;

import org.junit.Test;

import java.text.MessageFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	public static String regexText(String str){
		String regex1 = "<img.*?/>";
		//.匹配除“\r\n”之外的任何单个字符,匹配前面的子表达式零次或多次(大于等于0次)
        //？当该字符紧跟在任何一个其他限制符（*,+,?，{n}，{n,}，{n,m}）后面时，匹配模式是非贪婪的。非贪婪模式尽可能少的匹配所搜索的字符串，
            // 而默认的贪婪模式则尽可能多的匹配所搜索的字符串。例如，对于字符串“oooo”，“o+?”将匹配单个“o”，而“o+”将匹配所有“o”。
		Pattern pattern = Pattern.compile (regex1);
		Matcher match = pattern.matcher (str);
		while (match.find ()){
            String temp = match.group();
            System.out.println("temp:" + temp);
            str = str.replace(temp, "");
		}
		return str;
	}

    @Test
    public void test1(){
		String content = "<p><img alt=\"微笑\" src=\"staticmedia/xhEditor/xheditor_emot/default/smile.gif\" />" +
                "abcdefg<img alt=\"吐舌头\" src=\"staticmedia/xhEditor/xheditor_emot/default/tongue.gif\" />" +
                "你好啊下面一个换行</p><p>第二段内容<img alt=\"生气\" src=\"staticmedia/xhEditor/xheditor_emot/default/mad.gif\" /><br /></p>";
		String str = regexText(content);
		System.out.println(str);
	}

    @Test
    public void test2(){
        String str = "111.3.22.11";
        str=str.replaceAll("(^|\\.)(\\d)(\\.|$)","$100$2$3");
        str=str.replaceAll("(^|\\.)(\\d{2})(\\.|$)","$10$2$3");
        str=str.replaceAll("(^|\\.)(\\d{2})(\\.|$)","$10$2$3");
        str=str.replaceAll("(^|\\.)(\\d{1})(\\.|$)","$100$2$3");

        System.out.println(str);
    }

    @Test
    public void test3(){
        String str = "2015-04-10 11:57:39.601 INFO  c.w.d.controller.front.HandHeldControllerECommerce - shopping::127.0.0.1::32E212F2DBE20AE6ACE92726C7D1A30F.neweduty::[businessId" +
                "=B23343534545,userId=WXoONw2uHUqrl7v2USAybOM8dVUHQc,productIds=077114878f5a4a7495e85a234f02eaa6,industryId=d2fc1c67fb57495b916ee8ad4526e426,options=薯片]";
        //+匹配前面的子表达式一次或多次(大于等于1次）
        //[^:]+?   匹配一次或多次不是：的字符
        String ps = str.replaceAll(".+::([^:]+?)::([^:]+?)::([^:]+?.+)","$1");
        System.out.println(ps);
    }

    @Test
    //String中占位符替换
    public void test4() {
        String stringFormat  = "lexical error at position %s, encountered %s, expected %s ";
        String messageFormat ="lexical error at position {0}, encountered {1}, expected {2}";
        System.out.println(String.format(stringFormat, 123, 100, 456));
        System.out.println(MessageFormat.format(messageFormat, new Date(), 100, 456));

    }

}
