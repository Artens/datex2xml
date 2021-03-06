package datex2xml;

import java.io.IOException;

import org.xml.sax.SAXException;


/**
 * @author J.K.J. Martens
 * 	Welcome to the Datex2XML package. 
 *	Further information is found in the README.md file in the root of the package.
 *
 *	The purpose of this file is to handle the startup of the process of parsing a DATEX2 file.
 *	
 *	httpSite is used as a backup to have a known, static file available for testing purposes
 *	
 */
public class Main {
	 public static void main(String[] args) throws SAXException, IOException
	    {
	        boolean ftpDownload	= 	false;
	        String 	fileType	= 	"trafficspeed.gz";
	        // String ftpSite	=	"ftp://83.247.110.3/";
	        // String httpSite	=	"http://artens.nl/jef/";
	        final String localFileName = "D:/trafficspeedFull";
	        
	        // setup for reading FTP files directly
	        if(ftpDownload && "trafficspeed.gz" == fileType){
		 		// load the file from the FTP directly
		 		loadFile(ftpSite, fileName);
					        		        	
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
      
   private static File loadFile (String ftpSite, String fileName) throws IOException
   		{
      		savedFile = "";
     			try{ 
              		InputStream fileStream = new FileInputStream(ftpSite+fileName);
						InputStream gzipStream = new GZIPInputStream(fileStream);
						Reader decoder = new InputStreamReader(gzipStream, encoding);
						BufferedReader buffered = new BufferedReader(decoder);       
            	} 
     			finally {
              // TODO catch whatever issue on ftp site loading
            	}
     			return savedFile;
   		}
	    } // end Main
} // END OF FILE
