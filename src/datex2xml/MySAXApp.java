package datex2xml;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.DefaultHandler;


public class MySAXApp extends DefaultHandler
{

	boolean 	parseHelper 	= false;	// boolean to indicate whether an element should be processed
	String 		elementName;				// help variable to set the Name of an Element <elementName></elementName>
	String 		elementValue;				// help variable to set the Value of an Element tag i.e. <element id="30">
	String 		elementContent;				// help variable to set the Content between Element tags <el>Content</el>
	int 		parserCounter 	= 0;		// help variable to count the number of elements parsed
		
    public static void main (String args[])
	throws Exception
    {

    }


    public static void readXML(String fileName) throws SAXException, IOException    {
    	XMLReader xr = XMLReaderFactory.createXMLReader();
    	MySAXApp handler = new MySAXApp();
    	xr.setContentHandler(handler);
    	xr.setErrorHandler(handler);
    	FileReader r = new FileReader(fileName);
    	xr.parse(new InputSource(r));

    	
    	
    }
    ////////////////////////////////////////////////////////////////////
    // Event handlers.
    ////////////////////////////////////////////////////////////////////

    // When the start of the XML document is reached, initialize the output
    public void startDocument ()
    {
	System.out.println("Start document");
    }

    public void startElement (String uri, String name,
			      String qName, Attributes atts)
    {
    	if(name == "measurementSiteReference")	{
    		processElement(name, atts, true, "id");
	    } 
    	else if(name == "measuredValue"){
    		if (getValue("index", atts)){
    			parseHelper = true;
    	    	elementName = name.toString();
    	    	System.out.print(atts.getValue("xsi:type") + ": " + atts.getValue("index"));
    	    	System.out.print("\n"); // add the newline
    		}
	    }
    	
    	else if(name == "basicData"){
    		processElement(name, atts, true, "xsi:type");
		}
    	
    	else if(name == "vehicleFlowRate"){
			processElement(name, atts, false, null);
	    }
    	
    	else if(name == "speed"){
			processElement(name, atts, false, null);
	    }
    	
    	else if(name == "measurementOrCalculationTime")	{
    		processElement(name, atts, false, null);
	    }
    	
	    
    }

   public void characters (char ch[], int start, int length)
   {
	   // when parseHelper is true, content is important to output
	   if(parseHelper){
		   parserCounter++; // update the counter
		   elementContent = ""; // set the initial Content value to an empty string
		//System.out.print("Characters:    \"");
		for (int i = start; i < start + length; i++) {
		    switch (ch[i]) {
			    case '\\':
				System.out.print("\\\\");
				break;
			    case '"':
				System.out.print("\\\"");
				break;
			    case '\n':
				System.out.print("");
				break;
			    case '\r':
				System.out.print("");
				break;
			    case '\t':
				System.out.print("");
				break;
			    default:
				// System.out.print(ch[i]);
				elementContent = elementContent + ch[i];
				break;
		    	}
		    }
		// print a new line after the content is shared
		System.out.print(elementContent); // the added up content of the character string is printed
	    System.out.print("\n");
		}
   }

   public void endElement (String uri, String name, String qName)
   {
   	// Whenever content is reached to an end, set parseHelper to false
   	parseHelper 	= false;  
   	elementName 	= null;
   	elementValue 	= null;
   	elementContent 	= null;
   	
   	if(name == "siteMeasurements"){
   		System.out.println("**** END OF SITEMEASUREMENTS ****\n");
   	}
   }


   // When the end of the XML document is reached, wrap up the output
   public void endDocument ()
   {
	System.out.println("End document; " + parserCounter + " elements parsed");
   }

   // Helper function to determine whether there is content in a value
   public boolean getValue (String string, Attributes atts)
   {
	if(atts.getValue(string) == null){
		return false;
	}
	return true;
   }
   
   public void processElement(String name, Attributes atts, boolean check, String value){
	   	parseHelper = true;
   		elementName = name.toString();
   		if (check){
   		System.out.print(elementName + ": " + atts.getValue(value));
   		} else {
		System.out.print(elementName + ": ");
   		}
   }
   
}
