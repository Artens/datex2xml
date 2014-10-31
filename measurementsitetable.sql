/**
 * @author J.K.J. Martens
 *  The purpose of this file is a predefined file for a table that can be loaded into a database to make it fit for processing the trafficspeed files.
 **/
CREATE TABLE `measurementsitetable` (
  `record_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique Record ID',
  `publicationTime` varchar(24) NOT NULL COMMENT 'Publication time of the file',
  `measurementSiteReference` varchar(40) NOT NULL COMMENT 'Site reference code',
  `measurementTimeDefault` varchar(24) NOT NULL COMMENT 'Timestamp of generated file',
  `_SiteMeasurementsIndexMeasuredValue` int(4) NOT NULL COMMENT 'Index for measurement site (loop in road)',
  `basicData` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'Define what type of measurement is applied (speed or flow)',
  `vehicleFlowRate` int(4) NOT NULL COMMENT 'Measurement value of flow',
  `numberOfIncompleteInputs` int(4) NOT NULL COMMENT 'Measurement accuracy indicator for complete inputs',
  `numberOfInputValuesUsed` int(4) NOT NULL COMMENT 'Measurement accuracy indicator for input values used',
  `speed` int(4) NOT NULL COMMENT 'Measurement value of speed',
  `standardDeviation` int(4) NOT NULL COMMENT 'Measurement accuracy indicator for standard deviation',
  PRIMARY KEY (`record_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;