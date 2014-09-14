package datex2xml;

import java.io.IOException;

import org.xml.sax.SAXException;

import datex2xml.ReadDOMXML;
import datex2xml.MySAXApp;

public class Main {
	 public static void main(String[] args) throws SAXException, IOException
	    {
	        final String fileName = "/home/jef/trafficspeedTest";
	        final String fileNameFinal = "/home/jef/trafficspeed";
	        // ReadDOMXML.readXML(fileName);
	        // ReadSAXXML.readXML(fileName);
	        MySAXApp.readXML(fileName);
	    }
	   
	    
}
