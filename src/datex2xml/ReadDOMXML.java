package datex2xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadDOMXML {
	public static void readXML(String fileName) {
        Document document;
        DocumentBuilder documentBuilder;
        DocumentBuilderFactory documentBuilderFactory;
        NodeList nodeList;
        File xmlInputFile;

        try
        {
            xmlInputFile = new File(fileName);
            documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(xmlInputFile);
            nodeList = document.getElementsByTagName("*");

            document.getDocumentElement().normalize();

            for (int index = 0; index < nodeList.getLength(); index++)
            {
                Node node = nodeList.item(index);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if(validateElement(element, "measurementSiteReference")){
                        System.out.println("\tSite ID : " + getAttributeContent(element, "id"));
                        System.out.println("\ttargetClass : " + getAttributeContent(element, "targetClass"));
                        System.out.println("-----");
                    }
                    if(validateElement(element, "measurementTimeDefault")){
                        System.out.println("\tmeasurementTimeDefault : " + element.getTextContent());
                        System.out.println("-----");
                    }
                }
            
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
   
    private static String getAttributeContent(final Element element, String attributeName){
            return element.getAttribute(attributeName);
    }

    private static boolean validateElement(final Element element, String elementName) {
             
        if (element.getTagName().startsWith(elementName))
        {
            return true;

        }
       return false;
    }
}
