package datex2xml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import org.xml.sax.InputSource;
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
	        	// else run the local file
	        	final String fileName = "/home/jef/trafficspeed";
		        
	        	
	        	MySAXApp.readXML(fileName);
		        
		        
	      /**  
	    	}
	        **/
	        
	    }
	   
	    
}
