/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuddly.lamp;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
/**
 *
 * @author programer10
 */
public final class XmlHelpers {
       private XmlHelpers() { }

    public static Document TryGetXmlDocument(String xmlFilePath){
        return TryGetXmlDocument(xmlFilePath, null);
    }

    public static Document TryGetXmlDocument(String xmlFilePath, String encoding){
        try {
            InputStream inputStream = new FileInputStream(xmlFilePath);
            Reader reader;
            InputSource inputSource;

            if(encoding != null) {
                reader = new InputStreamReader(inputStream, encoding);
                inputSource = new InputSource(reader);
                inputSource.setEncoding(encoding);
            }
            else{
                reader = new InputStreamReader(inputStream);
                inputSource = new InputSource(reader);
            }

            return DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(inputSource);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }
}
