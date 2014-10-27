package datex2xml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import org.xml.sax.SAXException;

import datex2xml.Trafficspeed;

public class Main {
	 public static void main(String[] args) throws SAXException, IOException
	    {
	        boolean ftpTest = false;

	        // setup for reading FTP files directly
	        if(ftpTest){
		 		// load the file from the FTP directly        
	        	final String fileFTP = "ftp://83.247.110.3/trafficspeed.gz";
	        	InputStream input = new GZIPInputStream(new FileInputStream(fileFTP));
	        	// Trafficspeed.readXML(input);
	        } else {
        	
	        
	        // run the local file if no arguments are given	
	        final String fileName = "D:/trafficspeedFull";
	        	if(null == args[0]){
	        		Trafficspeed.readXML(fileName);
	        	} else {
	        		Trafficspeed.readXML(args[0]);
	        	}
		        

	    	}

	        
	    }
	   
	    
}
