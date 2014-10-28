package datex2xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

import org.xml.sax.SAXException;

import datex2xml.Trafficspeed;

public class Main {
	 public static void main(String[] args) throws SAXException, IOException
	    {
	        boolean ftpTest = 	false;
	        String fileType = 	"trafficspeed.gz";
	        String ftpSite	=	"83.247.110.3";
	        String httpSite	=	"http://artens.nl/jef/";
	        final String localFileName = "D:/trafficspeedFull";
	        
	        // setup for reading FTP files directly
	        if(ftpTest && "trafficspeed.gz" == fileType){
		 		// load the file from the FTP directly        
	        	final String fileFTP = "ftp://"+ ftpSite + "/" + fileType;
	        	InputStream input = new GZIPInputStream(new FileInputStream(fileFTP));
	        	// TODO write the file to temp location
	        	
	        	// Store location for parsing
	        	String inputFile = "";
	        	// parse the file
	        	Trafficspeed.readXML(inputFile);
	        	// TODO delete the file from temp location
	        	
	        } else {
		        // process a local file or the file provided in the arguments  	
	           	if(0 == args.length){
	           		Trafficspeed.readXML(localFileName);
	    	
	        	} else {
	        		Trafficspeed.readXML(args[0]);
	        	} // end if args.length
	    	} // end if ftpTest && fileType
	    } // end Main
} // END OF FILE
