/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuddly.lamp;

/**
 *
 * @author programer10
 */
public class XmlTag {
    private String tagName;
    private String[] attributeNames;
    

    public String getTagName() {
        return tagName;
    }


    public String[] getAttributeNames() {
        return attributeNames;
    }

    public XmlTag(String tagName,String... attributeNames) {
        this.attributeNames = attributeNames;
        this.tagName = tagName;
    }

    public XmlTag(String tagName) {
        this.tagName = tagName;
    }
    
    public boolean hasAttributes(){
        return attributeNames != null && attributeNames.length > 0;
    }

}
