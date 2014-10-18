package datex2xml;

import java.io.IOException;
import org.xml.sax.SAXException;

import datex2xml.MySAXApp;

public class Main {
	 public static void main(String[] args) throws SAXException, IOException
	    {
	        boolean ftpTest = false;
	        /**
	        // setup for reading FTP files directly
	        if(ftpTest){
		 		// load the file from the FTP directly        
	        	final String fileFTP = "ftp://83.247.110.3/trafficspeed.gz";
	        	InputStream input = new GZIPInputStream(new FileInputStream(fileFTP));
	        	MySAXApp.readXML(null, input);
	        } else {
	        **/
	        	
	        
	        // run the local file if no arguments are given	
	        final String fileName = "D:/trafficspeedFull";
	        	if(null == args[0]){
	        		MySAXApp.readXML(fileName);
	        	} else {
	        		MySAXApp.readXML(args[0]);
	        	}
		        
	       /**
	    	}
	       **/
	        
	    }
	   
	    
}
