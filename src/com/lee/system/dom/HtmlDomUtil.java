package com.lee.system.dom;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by libia on 2016/2/1.
 */
public class HtmlDomUtil {

    public static Document parseDoc(String doc) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory
                .newInstance();


        //解析XML




            Document document = Jsoup.parse(doc);
//            Element content = document.getElementById("content");
//            Elements links = content.getElementsByTag("a");
//            for (Element link : links) {
//                String linkHref = link.attr("href");
//                String linkText = link.text();
//            }


//            String s = encodeString(doc);
//            byte [] bys  =s.getBytes();
//            InputStream inputStream = new ByteInputStream(bys,bys.length);
//            DocumentBuilder builder = builderFactory.newDocumentBuilder();
//            document = builder.parse(inputStream);
//            htmlDocument  = (HTMLDocument) document;





//        Element rootElement = document.getDocumentElement(); //获取根节点
//        NodeList childNodes = rootElement.getChildNodes();  //根节点下所有子节点
//
//        for (int i = 0; i < childNodes.getLength(); i++) {  //循环第一层子节点
//            Node childNode = childNodes.item(i);
//            NodeList childNodes_2 = childNode.getChildNodes();
//            for (int j = 0; j < childNodes_2.getLength(); j++) {   //循环第二层子节点
//                Node childNode_2 = childNodes_2.item(j);
//                NodeList childNodes_3 = childNode_2.getChildNodes();
//                for (int k = 0; k < childNodes_3.getLength(); k++) {    //第三层
//                    Node childNode_3 = childNodes_3.item(k);
//                    System.out.println(childNode_3.getNodeValue());
//                }
//            }
//        }

        return document;
    }


    private static   String replaceString(String strData, String regex,
                                       String replacement)
    {
        if (strData == null)
        {
            return null;
        }
        int index;
        index = strData.indexOf(regex);
        String strNew = "";
        if (index >= 0)
        {
            while (index >= 0)
            {
                strNew += strData.substring(0, index) + replacement;
                strData = strData.substring(index + regex.length());
                index = strData.indexOf(regex);
            }
            strNew += strData;
            return strNew;
        }
        return strData;
    }

    private static String encodeString(String strData)
    {
        if (strData == null)
        {
            return "";
        }
        strData = replaceString(strData, "&", "&amp;");
        strData = replaceString(strData, "<", "&lt;");
        strData = replaceString(strData, ">", "&gt;");
        strData = replaceString(strData, "&apos;", "&apos;");
        strData = replaceString(strData, "\"", "&quot;");
        return strData;
    }



//    return"img_1.jsp";
}
