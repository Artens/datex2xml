datex2xml - XML reader for Datex II files
=========

Read XML files in the DATEX II standard.
The Datex II standard is used for traffic data sharing across parties.
For more information on the DATEX II standard, you can visit the EU site:
http://www.datex2.eu/

This implementation is focused on the raw data shared in the open data resource of the NDW (Nationale Databank Wegverkeersgegevens or in English: National Data Warehouse for Traffic Information), the party suppliying Dutch traffic monitoring data.

For more information on the NDW and their services please visit:
http://www.ndw.nu/

The data FTP address is listed below:
ftp://83.247.110.3/

This java application focuses on:
*  traffic speed data using the trafficspeed.gz files which are 
** compressed around 2Mb
** uncompressed >50Mb containing traffic speed and traffic flow data combined in one XML file 
** one XML file contains >17k measurement points in the Dutch roads
*  reading measurements from the large XML files using SAX (DOM failed due to filesize)
*  creating java objects with each measurement to be pushed to a MySQL database

To Do:
Implement direct download from FTP site to process file
Read compressed  .gz file
Create output file with SQL statements
Add support for additional files
* traveltime
* wegwerkzaamheden (road maintenance)
* brugopeningen (bridges)
* additional specifications for trafficspeed (external party data reliability implementation)

Done:
* Read XML file for trafficspeed xml file
* Create SQL statements in output text
* Direct upload to MySQL database (requires mysql-connector-java-5.0.8-bin.jar)

Changelog:

V0.4
* increased performance; sets of measurements are captured in a list and sent to process as SQL statements per 1000 measurements.
* moved measurement object creation to end of sequence
* created a sql file for the supporting table creation
* removed reset function for a measurement, as a measurement should be destroyed if reset would be required
* added headers for all files

V0.31
- Fixed measurement entry in DataBaseHelper class

V0.3
- Moved from logging to measurement object creation
- Added all relevant entries for datapoints
- Integrated MySQL jarfile
- Reading objects and push attributes to database

V0.2
- Implemented SAX for large XML files, as DATEX II files for NL are large
- Added the structure for uploads to MySQL database

V0.1
- Generic setup for reading and parsing XML
- Trying to determine whether to user DOM or SAX
