/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuddly.lamp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;


/**
 *
 * @author programer10
 */
public class XmlReader {
    
    private Document xmlDoc;

    public XmlReader(String xmlFilePath) throws Exception {
        this(xmlFilePath, null);
    }

    public XmlReader(String xmlFilePath, String encoding) throws Exception {
        xmlDoc = XmlHelpers.TryGetXmlDocument(xmlFilePath, encoding);

        if(xmlDoc == null)
            throw new Exception("Couldn't fetch the XML document...");
        
        xmlDoc.getDocumentElement().normalize();
    }
    
    /**
     * 
     * @param converterMethod Method used to convert the input tags into Objects of type T 
     * @throws java.lang.Exception 
     */
    
    public <T> List<T> getObjects(String xmlFilePath, XmlTag targetTag, Function<List<String>,T> converterMethod ,XmlTag... childTagsToFind) throws Exception{
        List<T> objects = new ArrayList<>();
        
        NodeList targetNodes = xmlDoc.getElementsByTagName(targetTag.getTagName());
        
        //1) find all tags with name = targetTag
        
        //2) loop through the found tags
        for (int i = 0; i < targetNodes.getLength(); i++) {
            
            //3) create a list that holds the current nodes attribute values and its child node and attribute values
            List<String> itemData = new ArrayList<>();
            
            //4) Node that holds all the wanted values (its children are leaf nodes)
            Node targetNode = targetNodes.item(i);
            
            //if there are attributes that need to be found, try to add them to the list
            if (targetTag.hasAttributes()) {
                itemData.addAll(getAttributeValues(targetNode,targetTag.getAttributeNames()));
            }
            
            //get the child nodes of the current target node
            NodeList children = targetNode.getChildNodes();
            for (XmlTag childTag : childTagsToFind) {
                for (int j = 0; j < children.getLength(); j++) {
                    
                    //current child node (j loop) of the current target node (i loop)
                    Node childNode = children.item(j);
                    
                    //if the child node's name matches the one that was requested by the user,
                    //try to add its node value and its attribute values to the collection
                    if(childTag.getTagName().equals(childNode.getNodeName())){
                        String nodeValue = childNode.getTextContent();
                        if (nodeValue != null) {
                            itemData.add(nodeValue);
                        }
                        if (childTag.hasAttributes()) {
                            itemData.addAll(getAttributeValues(childNode,childTag.getAttributeNames()));
                        }
                        
                    }
                    
                }//for (j) - loops through the children of the target tag
            }// foreach - loops through the requested child nodes that are used to create the object 
            
            //Create an instance of the object using the converterMethod and add it the list
            
            
            objects.add(converterMethod.apply(itemData));
        }// for - loops through the target nodes
        
        return objects;
    }
    
    private List<String> getAttributeValues(Node xmlNode, String[] attributeNames){
        List<String> values = new ArrayList<>();
        
        NamedNodeMap attributes = xmlNode.getAttributes();
        
        for (String attributeName : attributeNames) {
            Node node = attributes.getNamedItem(attributeName);
            if (node == null) {
                continue;
            }
            String value = node.getNodeValue();
            if (value != null) {
                values.add(value);
            }
        }
        
        return values;
    }
}