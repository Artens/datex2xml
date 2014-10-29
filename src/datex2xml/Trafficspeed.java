package datex2xml;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.DefaultHandler;

public class Trafficspeed extends DefaultHandler
{

	boolean 	parseHelper 			= false;		// boolean to indicate whether an element should be processed
	String 		elementName;							// help variable to set the Name of an Element <elementName></elementName>
	String 		elementValue;							// help variable to set the Value of an Element tag i.e. <element id="30">
	String 		elementContent;							// help variable to set the Content between Element tags <el>Content</el>
	int 		parserCounter 			= 0;			// help variable to count the number of elements parsed
	Date 		date = new Date();
	
	/** temporary variables for measurements **/ 
	String		publicationTime;						// temp variable to set publication Time
	String		measurementSiteReference;				//
	String		measurementTimeDefault;					//
	int			siteMeasurementsIndexMeasuredValue;		//
	String		measuredValue;							//
	String		basicData;								//
	int			vehicleFlowRate;						//
	Float		speed = null;							//
	int			numberOfIncompleteInputs;				//
	int			numberOfInputValuesUsed;				//
	Float		standardDeviation = null;				//
	
	MeasurementsList measurementsList 	= new MeasurementsList(); // measurementlist for object of type Measurement
	
    public static void main (String args[])
	throws Exception
    {

    }


    public static void readXML(String fileName) throws SAXException, IOException    {
    	XMLReader xr = XMLReaderFactory.createXMLReader();
    	Trafficspeed handler = new Trafficspeed();
    	xr.setContentHandler(handler);
    	xr.setErrorHandler(handler);
    	FileReader r = new FileReader(fileName);
    	xr.parse(new InputSource(r));
    }
    
    public Measurement createNewMeasurement(){
    	Measurement measurement	= new Measurement();
    	return measurement;
    }
    
        
    ////////////////////////////////////////////////////////////////////
    // Event handlers.
    ////////////////////////////////////////////////////////////////////

    // When the start of the XML document is reached, initialize the output
    public void startDocument ()
    {
    	Date startDate = new Date();
    	System.out.println("Start parsing document at " 
    						+ new Timestamp(startDate.getTime())
    						);
    }
    
    // Parsing of tags
    public void startElement (String uri, String name,
			      String qName, Attributes atts)
    {
    	if(name == "publicationTime"){
    		this.elementName = name;
    		parseHelper = true;
    	}
    	
    	if(name == "measurementSiteReference")	{
    		measurementSiteReference = processStringElement(name, atts, true, "id");
    		// measurement.setMeasurementSiteReference(processStringElement(name, atts, true, "id"));
	    } 
    	
    	if(name == "measurementTimeDefault")	{
    		this.elementName = name;
    		parseHelper = true;
    	} 
    	
    	if(name == "measuredValue"){
    		if (getValue("index", atts)){
    	    	siteMeasurementsIndexMeasuredValue = Integer.parseInt(atts.getValue("index"));
    			// measurement.setSiteMeasurementsIndexMeasuredValue(Integer.parseInt(atts.getValue("index")));
    	    	measuredValue = "MeasuredValue";
    	    	// measurement.setMeasuredValue("MeasuredValue");
    		}
	    }
    	
    	if(name == "basicData"){
    		basicData = processStringElement(name, atts, true, "xsi:type");
    		// measurement.setBasicData(processStringElement(name, atts, true, "xsi:type"));
		}
    	
    	if(name == "vehicleFlow"){
    		if(getValue("numberOfIncompleteInputs", atts)){
	    		numberOfIncompleteInputs = processIntElement(name, atts, true, "numberOfIncompleteInputs");
	    		// measurement.setNumberOfIncompleteInputs(processIntElement(name, atts, true, "numberOfIncompleteInputs"));
			}
			if(getValue("numberOfInputValuesUsed", atts)){
				numberOfInputValuesUsed = processIntElement(name, atts, true, "numberOfInputValuesUsed");
				// measurement.setNumberOfInputValuesUsed(processIntElement(name, atts, true, "numberOfInputValuesUsed"));
			}
			if(getValue("standardDeviation", atts)){
				standardDeviation = Float.parseFloat(processStringElement(name, atts, true, "standardDeviation"));
				// measurement.setStandardDeviation(processIntElement(name, atts, true, "standardDeviation"));
			}
	    }
    	
    	if(name == "averageVehicleSpeed"){
			if(getValue("numberOfIncompleteInputs", atts)){
				numberOfIncompleteInputs = processIntElement(name, atts, true, "numberOfIncompleteInputs");
				// measurement.setNumberOfIncompleteInputs(processIntElement(name, atts, true, "numberOfIncompleteInputs"));
			}
			if(getValue("numberOfInputValuesUsed", atts)){
				numberOfInputValuesUsed = processIntElement(name, atts, true, "numberOfInputValuesUsed");
				// measurement.setNumberOfInputValuesUsed(processIntElement(name, atts, true, "numberOfInputValuesUsed"));
			}
			if(getValue("standardDeviation", atts)){
				standardDeviation = Float.parseFloat(processStringElement(name, atts, true, "standardDeviation"));
				// measurement.setStandardDeviation(Float.parseFloat(processStringElement(name, atts, true, "standardDeviation")));
			}
	    }
    	
    	if(name == "vehicleFlowRate"){
    		this.elementName = name;
    		parseHelper = true;
	    }
    	
    	if(name == "speed"){
    		this.elementName = name;
    		parseHelper = true;
    	}
	    
	    
    }

   public void characters (char ch[], int start, int length)
   {
	   if(true == parseHelper){
	   // when parseHelper is true, content is important to output
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
	    }
	   
    	if(this.elementName == "publicationTime"){
    		publicationTime = elementContent;
    	}

    	if(this.elementName == "measurementTimeDefault"){
    		measurementTimeDefault = elementContent;
    		// measurement.setMeasurementTimeDefault(elementContent);
    	}
    	
    	if(this.elementName == "vehicleFlowRate"){
    		vehicleFlowRate = Integer.parseInt(elementContent);
    		// measurement.setVehicleFlowRate(Integer.parseInt(elementContent));
	    }
    	
    	if(this.elementName == "speed"){
    		speed = Float.parseFloat(elementContent);
    		// measurement.setSpeed(Float.parseFloat(elementContent));
	    }
   }

   public void endElement (String uri, String name, String qName)
   {
   	// Whenever content is reached to an end, set parseHelper to false
   	parseHelper 	= false;  
   	elementName 	= null;
   	elementValue 	= null;
   	elementContent 	= null;
   	
   	
   	// When we reach the end of the measurement, push the measurement to the database
   	if(name == "basicData"){
   		// Create a measurement
   		Measurement measurement = createNewMeasurement(); 
   		// Fill the measurement
   		addMeasurementData(measurement);
   		// Push the measurement
   		measurementsList.addMeasurementToList(measurement, false); // use list functionality for creating multiple entries
   		// DataBaseHelper myDBH = new DataBaseHelper();
   		// myDBH.uploadMeasurement(measurement); // push individual measurement to database
   		   		 
   	}
   	// When we reach the end of the siteMeasurements, post a result
   	if(name == "siteMeasurements"){
   		// measurement.reset(); // reset the measurement
   		// measurement.setPublicationTime(publicationTime);
   		resetMeasurementContent();
   		}
   	}

// When the end of the XML document is reached, wrap up the output
   public void endDocument ()
   {
	   // measurementsList.addMeasurementToList(measurement, true);
	   measurementsList.sendLastMeasurements();
	   Date endDate = new Date();
	   System.out.println("End document; " 
			   + parserCounter 
			   + " elements parsed and finished at " 
			   + new Timestamp(endDate.getTime())
			   );
   }

   // Helper function to determine whether there is content in a value
   public boolean getValue (String string, Attributes atts)
   {
	if(atts.getValue(string) == null){
		return false;
	}
	return true;
   }
   
   private Measurement addMeasurementData(Measurement measurement) {
	   measurement.setPublicationTime(publicationTime);
	   measurement.setMeasurementSiteReference(measurementSiteReference);
	   measurement.setMeasurementTimeDefault(measurementTimeDefault);
	   measurement.setSiteMeasurementsIndexMeasuredValue(siteMeasurementsIndexMeasuredValue);
	   measurement.setMeasuredValue(measuredValue);
	   measurement.setBasicData(basicData);
	   measurement.setVehicleFlowRate(vehicleFlowRate);
	   if(speed != null){measurement.setSpeed(speed);}
	   measurement.setNumberOfIncompleteInputs(numberOfIncompleteInputs);
	   measurement.setNumberOfInputValuesUsed(numberOfInputValuesUsed);
	   if (standardDeviation != null){measurement.setStandardDeviation(standardDeviation);}
	   
	   return measurement;
   }
   
   private void resetMeasurementContent(){
		measurementSiteReference 			= "";	//
		measurementTimeDefault 				= "";	//
		siteMeasurementsIndexMeasuredValue 	= 0;	//
		measuredValue 						= "";	//
		basicData							= "";	//
		vehicleFlowRate						= 0;	//
		speed								= null;	//
		numberOfIncompleteInputs			= 0;	//
		numberOfInputValuesUsed				= 0;	//
		standardDeviation					= null;	//
   }
   
   public void printElement(String name, Attributes atts, boolean check, String value){
	   	parseHelper = true;
   		elementName = name.toString();
   		if (check){
   		System.out.print(elementName + ": " + atts.getValue(value));
   		} else {
		System.out.print(elementName + ": ");
   		}
   	}
   
   public String processStringElement(String name, Attributes atts, boolean check, String value){
	   	parseHelper = true;
  		if (check){
  		return atts.getValue(value);
  		} else {
		return null;
  		}
  	}
   
   public int processIntElement(String name, Attributes atts, boolean check, String value){
	   	parseHelper = true;
 		if (check){
 		return Integer.parseInt(atts.getValue(value));
 		} else {
		return 0;
 		}
 	}
}
