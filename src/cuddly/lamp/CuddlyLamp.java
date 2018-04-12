/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuddly.lamp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author programer10
 */
public class CuddlyLamp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        //String xmlFilePath = "D:\\Podaci\\programer10\\Documents\\NetBeansProjects\\cuddly-lamp\\testingXML.xml";
        
//        try {
//            XmlReader reader = new XmlReader(xmlFilePath, "UTF-8");
//            Function<List<String>,Staffer> myFunc = staffData -> new Staffer(Integer.parseInt(staffData.get(0)),staffData.get(1),staffData.get(2),staffData.get(3),Integer.parseInt(staffData.get(4)));
//            
//            List<Staffer> myStaff = reader.getObjects(xmlFilePath, new XmlTag("staff", "id"), myFunc, new XmlTag("firstname"),new XmlTag("lastname"),new XmlTag("nickname"),new XmlTag("salary"));
//            
//            myStaff.forEach((staffer) -> {
//                System.out.println(staffer);
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        

        String newXmlFilePath = "D:\\\\Podaci\\\\programer10\\\\Documents\\\\NetBeansProjects\\\\cuddly-lamp\\\\testingmyXML.xml";
        
        List<TestXmlWriter> sth = new ArrayList<>();
        
        sth.add(new TestXmlWriter(2,"test1","test2"));
        sth.add(new TestXmlWriter(2,"test2","test2"));
        sth.add(new TestXmlWriter(2,"test3","test2"));

        
        XmlWriter.writeObjectData("\\C:\\Users\\programer10\\Desktop\\testic.xml",sth,"korijen");
    }
    
}
