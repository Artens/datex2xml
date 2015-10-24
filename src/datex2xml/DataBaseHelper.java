package datex2xml;

import java.sql.*;
import java.util.List;

import com.mysql.jdbc.Driver;

/**
 * @author J.K.J. Martens
 *  The purpose of the DataBaseHelper file is to connect to a MySQL database
 *  
 *	In this file some specific information is redacted, 
 *	as one wouldn't want to publicize ones database credentials...
 *	
 *	The test variable in this class allows you to test your queries
 *	by outputting the full query to the console
 **/

public class DataBaseHelper {
	private String connector 	= "jdbc:mysql://";
	private String host			;
	private String port 		= "3306";
	private String database		;
	private String table		;
	private String user 		;
	private String password 	;
	private boolean test		= true;

	
	public void setTest(boolean testValue){
		this.test = testValue;
	}
	
	public void setTable(String table){
		this.table = table;
	}
	
	public DBase newDataBase(){
		DBase dataBase = new DBase();
		return dataBase;
		}
	
	public void uploadMeasurement(Measurement measurement){
		DBase dataBase = newDataBase();
		// connect to the database
		Connection connection = dataBase.connect(
				this.connector + 
				this.host + ":"	+ 
				this.port + "/" + 
				this.database, 
				user, 
				password
				);
		// Load the content (test is used for output query)
		dataBase.importMeasurement(	connection, 
									this.table, 
									measurement, 
									test
									);
	}
	
	public void uploadMeasurementsList(List<Measurement> measurementsList){
		DBase dataBase = newDataBase();
		// connect to the database
		Connection connection = dataBase.connect(
				this.connector + 
				this.host + ":"	+ 
				this.port + "/" + 
				this.database, 
				user, 
				password
				);
		dataBase.uploadMeasurementsList(connection, table, measurementsList, test);
	}
	
}

class DBase
{
    public DBase()
    {
    }

	public Connection connect(String db_connect_str, 
			  String db_userid, String db_password)
			    {
			        Connection conn;
			        try 
			        {
			            Class.forName("com.mysql.jdbc.Driver").newInstance();
			            conn = DriverManager.getConnection(
			            		db_connect_str, 
			            		db_userid, 
			            		db_password);
			        
			        }
			        catch(Exception e)
			        {
			            e.printStackTrace();
			            conn = null;
			        }

			        return conn;    
			    }    
            
    public void importMeasurement(Connection conn, String table, Measurement measurement, boolean test){
            Statement stmt;
            String query = null;

            try
            {
                stmt = conn.createStatement(
                			ResultSet.TYPE_SCROLL_INSENSITIVE,
                			ResultSet.CONCUR_UPDATABLE);
                
                // Create the query header
                query = createTableQuery(query, table);
                
                           
                // for each measurement, create an entry
            	query = query 	+ "VALUES " 
            					+ "(NULL,'"
            					+ measurement.getPublicationTime() + "','" 
            					+ measurement.getMeasurementSiteReference() + "','"
            					+ measurement.getMeasurementTimeDefault() + "','"
            					+ measurement.getSiteMeasurementsIndexMeasuredValue() + "','"
            					+ measurement.getBasicData() + "','"
            					+ measurement.getVehicleFlowRate() + "','"
            					+ measurement.getNumberOfIncompleteInputs() + "','"
            					+ measurement.getNumberOfInputValuesUsed() + "','"
            					+ measurement.getSpeed() + "','"
            					+ measurement.getStandardDeviation() + "'"
            					+ ")";
            	// mark the end of the insertion
                query = query + ";"; 
                
                // check for test, if true, output the query
                if(true == test){
                	System.out.println(query);
                } else { // else, go ahead with loading the query
                stmt.executeUpdate(query);
                }
                    
            }
            catch(Exception e)
            {
                e.printStackTrace();
                stmt = null;
            }
    }
    
    private String createTableQuery(String query, String table) {
    	query = "INSERT INTO `"+ table + "` ("
        		+ "`record_ID` , "
        		+ "`publicationTime`, "
        		+ "`measurementSiteReference` , "
        		+ "`measurementTimeDefault` , "
        		+ "`_SiteMeasurementsIndexMeasuredValue` , "
        		+ "`basicData` , "
        		+ "`vehicleFlowRate` , "
        		+ "`numberOfIncompleteInputs` , "
        		+ "`numberOfInputValuesUsed` , "
        		+ "`speed` , "
        		+ "`standardDeviation`"
        		+ ") \n ";
		return query;
	}

	// Read the measurement list, process the entries and upload them
 	public void uploadMeasurementsList(Connection conn, String table, List<Measurement> measurementsList, boolean test){
 		Statement stmt;
 		String query;
 		// static header with an insert statement, the table structure and the values declaration
 		try
        {
            stmt = conn.createStatement(
            			ResultSet.TYPE_SCROLL_INSENSITIVE,
            			ResultSet.CONCUR_UPDATABLE);

            query = "INSERT INTO `" + table + "` ("
            		+ "`record_ID` , "
            		+ "`publicationTime`, "
            		+ "`measurementSiteReference` , "
            		+ "`measurementTimeDefault` , "
            		+ "`_SiteMeasurementsIndexMeasuredValue` , "
            		+ "`basicData` , "
            		+ "`vehicleFlowRate` , "
            		+ "`numberOfIncompleteInputs` , "
            		+ "`numberOfInputValuesUsed` , "
            		+ "`speed` , "
            		+ "`standardDeviation`"
            		+ ") "
            		+ "VALUES \n";

            // dynamic body part for each measurement in the list...
	 		for(int measurementEntry = 0; measurementEntry < measurementsList.size(); measurementEntry++){
	 		query = query + "(NULL,'"
					+ measurementsList.get(measurementEntry).getPublicationTime() + "','" 
					+ measurementsList.get(measurementEntry).getMeasurementSiteReference() + "','"
					+ measurementsList.get(measurementEntry).getMeasurementTimeDefault() + "','"
					+ measurementsList.get(measurementEntry).getSiteMeasurementsIndexMeasuredValue() + "','"
					+ measurementsList.get(measurementEntry).getBasicData() + "','"
					+ measurementsList.get(measurementEntry).getVehicleFlowRate() + "','"
					+ measurementsList.get(measurementEntry).getNumberOfIncompleteInputs() + "','"
					+ measurementsList.get(measurementEntry).getNumberOfInputValuesUsed() + "','"
					+ measurementsList.get(measurementEntry).getSpeed() + "','"
					+ measurementsList.get(measurementEntry).getStandardDeviation() + "'"
					+ ")";
	 			
	 			// ...validate that the last entry is given; then apply semicolon instead of comma
	 			if(measurementEntry < (measurementsList.size()-1)){
	 				query = query + ", \n";
	 			} else {
	 				query = query + ";\n";
	 			}
	 		}

 		// upload the query 
	 		// check for test, if true, output the query
            if(true == test){
            	System.out.print(query);
            } else { // else, go ahead with loading the query
            stmt.executeUpdate(query);
            }

 		// reset the query variable
 		query = "";	
        }
        catch(Exception e)
        {
            e.printStackTrace();
            stmt = null;
        }
 	}
  
  public void removeMeasurementTables(Connection conn){
    // Initialize help variables
    String query = "";
    
    // Remove MeasurementSiteTable
    try{
      query = "DROP TABLE IF EXISTS MeasurementSiteTable;";
    } catch(Exception e)
        {
            e.printStackTrace();
            query = null;
        }
  }
  
  
  public void createMeasurementTables(Connection conn){
     // Initialize help variables
     String query = "";
     Statement stmt;
    /*
     *
     Table design version 2.3
     
     http://www.datex2.eu/datex-model/HTML.Version_2.3/index.htm
     
      Name = MeasurementSiteTable
      Description =  	A Measurement Site Table comprising a number of sets of data, each describing the location from where a stream of measured data may be derived. Each location is known as a "measurement site" which can be a point, a linear road section or an area.
      Columns
      measurementSiteTableIdentification	(String) definition=An alphanumeric identification for the measurement site table, possibly human readable.
	*
   */
    try{
      query = "CREATE TABLE IF NOT EXISTS " +
        			"MeasurementSiteTable"+
        			" ("+
        			"measurementSiteTableIdentification VARCHAR(32) PRIMARY KEY NOT NULL COMMENT 'An alphanumeric identification for the measurement site table, possibly human readable.'"+
        			") "+
        			"COMMENT 'A Measurement Site Table comprising a number of sets of data, each describing the location from where a stream of measured data may be derived."+
        			" Each location is known as a \"measurement site\" which can be a point, a linear road section or an area.';"
        			;
      stmt = conn.createStatement(
            			ResultSet.TYPE_SCROLL_INSENSITIVE,
            			ResultSet.CONCUR_UPDATABLE);
      stmt.executeUpdate(query);
    } catch(Exception e)
        {
            e.printStackTrace();
            stmt = null;
        }
    
    
    /*
     * 
		Name = MeasurementSiteRecord (Indexed)
		Description = An identifiable single measurement site entry/record in the Measurement Site table.
      Columns
      measurementSiteIdentification	(String) definition=Identification of a measurement site used by the supplier or consumer systems.
      computationMethod		(String) definition=Method of computation which is used to compute the measured value(s) at the measurement site.
		measurementEquipmentReference (String) definition=The reference given to the measurement equipment at the site.
      measurementEquipmentTypeUsed	(MultilingualString) definition=The type of equipment used to gather the raw information from which the data values are determined, e.g. 'loop', 'ANPR' (automatic number plate recognition) or 'urban traffic management system' (such as SCOOT).
      measurementSide (DirectionEnum) definition=Side of the road on which measurements are acquired, corresponding to the direction of the road.
      measurementSiteName (String) definition=Name of a measurement site.
      measurementSiteNumberofLanes	(Int) definition=The number of lanes over which the measured value is determined.
      measurementSiteRecordVersionTime	(DateTime) definition=The date/time that this version of the measurement site record was defined. The identity and version of the measurement site record are defined by the class stereotype implementation.
      
      */
    try{
      query =	"CREATE TABLE IF NOT EXISTS "+ 
        			"MeasurementSiteRecord"+
        			" ("+
        			"measurementSiteIdentification VARCHAR(32) NOT NULL PRIMARY KEY COMMENT 'Identification of a measurement site used by the supplier or consumer systems.',"+
      			"computationMethod VARCHAR(32) COMMENT 'Method of computation which is used to compute the measured value(s) at the measurement site.',"+
					"measurementEquipmentReference VARCHAR(32) COMMENT 'The reference given to the measurement equipment at the site.',"+
      			"measurementEquipmentTypeUsed VARCHAR(15) COMMENT 'The type of equipment used to gather the raw information from which the data values are determined, e.g. \'loop\', \'ANPR\' (automatic number plate recognition) or \'urban traffic management system\' (such as SCOOT).',"+
      			"measurementSide VARCHAR(10) COMMENT 'Side of the road on which measurements are acquired, corresponding to the direction of the road.',"+
      			"measurementSiteName VARCHAR(25) COMMENT 'Name of a measurement site.',"+
      			"measurementSiteNumberofLanes INT(2) COMMENT 'The number of lanes over which the measured value is determined.',"+
      			"measurementSiteRecordVersionTime	DATETIME COMMENT 'The date/time that this version of the measurement site record was defined. The identity and version of the measurement site record are defined by the class stereotype implementation.'"+
        			") "+ 
        			"COMMENT='An identifiable single measurement site entry/record in the Measurement Site table.';"
      			;
      stmt = conn.createStatement(
            			ResultSet.TYPE_SCROLL_INSENSITIVE,
            			ResultSet.CONCUR_UPDATABLE);
      stmt.executeUpdate(query);
      
    	} catch(Exception e)
        {
            e.printStackTrace();
            stmt = null;
        }
    
    
      /*
      Name = MeasurementSpecificCharacteristics
      Description = Characteristics which are specific to an individual measurement type (specified in a known order) at the given measurement site.
      Columns
      measurementSpecificCharacteristicsID (Integer)
      accuracy (Percentage) definition=The extent to which the value is expected to be free from error, measured as a percentage of the data value. 100% means fully accurate.
      period (Seconds) definition=The time elapsed between the beginning and the end of the sampling or measurement period. This item may differ from the unit attribute; e.g. an hourly flow can be estimated from a 5-minute measurement 
      smoothingFactor (Float) definition=Coefficient required when a moving average is computed to give specific weights to the former average and the new data. A typical formula is, F being the smoothing factor: New average = (old average) F + (new data) (1 - F).
      specificLane (Integer) definition=The lane to which the specific measurement at the measurement site relates. This overrides any lane specified for the measurement site as a whole.
      specificMeasurementValueType (MeasuredOrDerivedDataTypeEnum) definition=The type of this specific measurement at the measurement site.
      
      Name = VehicleCharacteristics
      Description = The characteristics of a vehicle, e.g. lorry of gross weight greater than 30 tonnes.
      Columns
      fuelType (String) definition=The type of fuel used by the vehicle.
      loadType (String) definition=The type of load carried by the vehicle, especially in respect of hazardous loads.
      vehicleEquipment (String) definition=The type of equipment in use or on board the vehicle.
      vehicleType (String) definition=Vehicle type.
      vehicleUsage (String) definition=The type of usage of the vehicle (i.e. for what purpose is the vehicle being used).
      
      
      Locations
      locationID
      
      PointCoordinates
      Description =  	A pair of coordinates defining the geodetic position of a single point using the European Terrestrial Reference System 1989 (ETRS89).
      Columns
      latitude (Float) definition=Latitude in decimal degrees using the European Terrestrial Reference System 1989 (ETRS89).
      longitude (Float) definition=Longitude in decimal degrees using the European Terrestrial Reference System 1989 (ETRS89).
      
      
     * 
     */
  }
}
