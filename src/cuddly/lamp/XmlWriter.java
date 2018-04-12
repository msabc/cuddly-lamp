/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuddly.lamp;
import java.io.File;
import java.lang.reflect.*;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author programer10
 */
public final class XmlWriter {
    
    private XmlWriter() {}
    
    public static <T> void writeObjectData(String xmlFilePath, List<T> objects, String rootTagName) throws Exception {
        
        
        if (objects == null ||objects.isEmpty()) {
            throw new Exception("You have to pass in at least one object.");
        }
        
        Document xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        
        Element root = xmlDoc.createElement(rootTagName);
        xmlDoc.appendChild(root);
        
        Class<?> objectClass = objects.get(0).getClass();
        
        String tagName = objectClass.getSimpleName();
        
        Map<String,Method> objectGetters = getObjectGetters(objectClass);
        
        for (T object : objects) {
            Element el = xmlDoc.createElement(tagName);
            
            for (String key : objectGetters.keySet()) {
                Element childElement = xmlDoc.createElement(key);
                
                Method getter = objectGetters.get(key);
                String getterValue = getter.invoke(object).toString();
                
                childElement.setTextContent(getterValue);
                
                el.appendChild(childElement);
                
            }
            root.appendChild(el);

        }
        
        
        DOMSource source = new DOMSource(xmlDoc);
        StreamResult result = new StreamResult(new File(xmlFilePath));
        TransformerFactory.newInstance().newTransformer().transform(source, result);
    }
    
    private static <T> Map<String,Method> getObjectGetters(Class<T> objectClass){
        
        Map<String,Method> objectGetters = new HashMap<>();
        
        Method[] methods = objectClass.getDeclaredMethods();
            
            for (Method method : methods) {
                String methodName = method.getName();
                
                if (Modifier.isPublic(method.getModifiers())) {
                    if (methodName.startsWith("get")) {
                        String tagName = methodName.substring(3);
                        
                        objectGetters.put(tagName, method);
                    }
                }
            }
            
        return objectGetters;
    }
    
}
