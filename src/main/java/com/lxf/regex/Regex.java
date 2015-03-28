package com.lxf.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	public static String regexText(String str){
//		String regex1 = "\".*?/>";
		String regex1 = "<img.*?/>";
//		String regex = "\\{   [^\\}]*     \\}";
//		String string = "你好{abc}我是{def}早上好";
		
		
		Pattern pattern = Pattern.compile (regex1);
		Matcher match = pattern.matcher (str);
		while (match.find ())
		{
			str = str.replace(match.group (), "");
//		    System.out.println (match.group ());
		}
		System.out.println(str);
		return str;
	}

	public static void main(String args []){
		String content = "<p><img alt=\"微笑\" src=\"staticmedia/xhEditor/xheditor_emot/default/smile.gif\" />abcdefg<img alt=\"吐舌头\" src=\"staticmedia/xhEditor/xheditor_emot/default/tongue.gif\" />你好啊下面一个换行</p><p>第二段内容<img alt=\"生气\" src=\"staticmedia/xhEditor/xheditor_emot/default/mad.gif\" /><br /></p>";
		String str = regexText(content);
		System.out.println(str);
	}
	
	
}
