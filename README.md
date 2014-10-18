datex2xml - XML reader for Datex II files
=========

Read XML files in the DATEX II standard.
The Datex II standard is used for traffic data sharing across parties.

This implementation is focused on the raw data shared in the open data resource of the NDW (Nationale Databank Wegverkeersgegevens or in English: National Data Warehouse for Traffic Information), the party suppliying Dutch traffic monitoring data.

For more information on the NDW and their services please visit:
http://www.ndw.nu/

The data FTP address is listed below:
ftp://83.247.110.3/

This java application focuses on:
*  traffic speed data using the trafficspeed.gz files which are compressed around 2Mb, uncompressed >50Mb containing traffic speed and traffic flow data combined in one XML file for >17k measurement points in the Dutch roads.
*  reading measurements from the large XML files using SAX (DOM failed due to filesize)
*  creating java objects with each measurement to be pushed to a MySQL database

Changelog
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
