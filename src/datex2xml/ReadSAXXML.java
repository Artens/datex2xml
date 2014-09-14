package datex2xml;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import datex2xml.Measurement;

public class ReadSAXXML extends DefaultHandler {

	public static void readXML(String fileName){
	
		// read the file using the fileName
		
	// validate startelement
		
		
	}
	
	
	

	public void startElement(
			String uri, 
			String localName, 
			String qName, 
			Attributes attributes) 
					throws SAXException{
		// Whenever a new measurement is found, create a new Measurement and start processing
		if(qName.equalsIgnoreCase("siteMeasurements")){
			// create a new measurements object
			Measurement tempMeasurement = new Measurement();
			tempMeasurement.setSiteMeasurementsIndexMeasuredValue(Integer.parseInt(attributes.getValue("index")));
			
		}
	}
	
	public void endElement(			
			String uri, 
			String localName, 
			String qName, 
			Attributes attributes)
					throws SAXException{
			// add all attribute information to the measurement
			// keep in mind that all measurement attributes are expected in a certain order
		
			if(qName.equalsIgnoreCase("")){
				
		
			} else if(qName.equalsIgnoreCase("")) {
				
			} else if(qName.equalsIgnoreCase("")) {
				
			} else if(qName.equalsIgnoreCase("")) {
				
			} else if(qName.equalsIgnoreCase("") && attributes.getType("type") == "_SiteMeasurementsIndexMeasuredValue") {
				// end of _SiteMeasurement, dump data into Measurement
				
			}
	}
}