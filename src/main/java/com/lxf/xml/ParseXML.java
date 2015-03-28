package com.lxf.xml;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @description dom4j解析XML
 * @author xflin
 */
public class ParseXML {
	/**
	 * 解析xml,将xml文件保存在Map中,这里的xml结构比较简单，根节点下面只有一层子节点，例如：
			    <?xml version="1.0" encoding="UTF-8"?>
				<infomation>
					<name>xiaozhang</name>
					<age>30</age>
					<sex>female</sex>
				</infomation>
	 * @return void
	 * @description 
	 * @author linxiaofan
	 * @time 2014-9-11 上午11:11:46
	 * @param XMLFile
	 * @throws Exception
	 */
	public static Map<String,String> parseSimpleXML(File XMLFile) throws Exception{
		// 将解析结果存储在HashMap中  
	    Map<String, String> map = new HashMap<String, String>();  
//	    InputStream inputStream = request.getInputStream();  
	    // 读取输入流  
	    SAXReader reader = new SAXReader();  
	    Document document = reader.read(XMLFile);  
	    // 得到xml根元素  
	    Element root = document.getRootElement();  
	    // 得到根元素的所有子节点  
	    List<Element> elementList = root.elements();  
	    // 遍历所有子节点  
	    for (Element e : elementList)  
	        map.put(e.getName(), e.getText()); 
	    return map;
	}
	
	/**
	 * @description DOM4J解析XML(需要根据具体的XML文件多重循环)
	 * @author linxiaofan
	 * @param fileName
	 */
	 public static Map<String,String> parseComplexXml(File fileName) {
		Map<String,String> map = new HashMap<String,String>();
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(fileName);
            Element users = document.getRootElement();
            for (Iterator i = users.elementIterator(); i.hasNext();) {
                Element user = (Element) i.next();
                for (Iterator j = user.elementIterator(); j.hasNext();) {
                    Element node = (Element) j.next();
                    map.put(node.getName(), node.getText());
//                    System.out.println(node.getName() + ":" + node.getText());
                }
                System.out.println();
            }
        } catch (DocumentException e) {
            System.out.println(e.getMessage());
        }
        return map;
   }
		
	 //Test
	public static void main(String args []) throws Exception{
		File XMLFile = new File("G:"+File.separator+"collega.xml");
	}
}
