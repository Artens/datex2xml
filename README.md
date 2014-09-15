datex2xml - XML reader for Datex II files
=========

Read XML files in the DATEX II standard.
The Datex II standard is used for traffic data sharing across parties.

This implementation is focused on the raw data shared in the open data resource of the NDW (Nationale Databank Wegverkeersgegevens or in English: National Data Warehouse for Traffic Information), the party suppliying Dutch traffic monitoring data.

For more information on the NDW and their services please visit:
http://www.ndw.nu/

The data FTP address is listed below:
ftp://83.247.110.3/

Version 0.2 of this java application focuses on traffic speed data:
* trafficspeed.gz : files which are compressed around 2Mb, uncompressed >50Mb containing traffic speed and traffic flow data combined in one XML file for >17k measurement points in the Dutch roads.
