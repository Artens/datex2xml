package datex2xml;

import java.sql.*;
import java.util.List;

import com.mysql.jdbc.Driver;

/**
 * @author J.K.J. Martens
 * 
 *	In this file some specific information is redacted, 
 *	as one wouldn't want to publicize ones database credentials...
 *	
 *	The test variable in this class allows you to test your queries
 *	by outputting the full query to the console
 */

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
    
    public void importData(Connection conn, String table, String[][] arrayList){
        Statement stmt;
        String query;

        try
        {
            stmt = conn.createStatement(
            			ResultSet.TYPE_SCROLL_SENSITIVE,
            			ResultSet.CONCUR_UPDATABLE);

            query = "INSERT INTO TABLE testtable (id, text, price)" + 
            		"VALUES ";
            // For each entry in the arrayList, create an entry
            for(int i = 0; i < arrayList.length; i++){
            	if(0 != i){
            		// Add a comma to separate entries
            		query = query + ",";
            	}
            	// for each entry in the arrayList, create an entry
            	query = query + "('" + arrayList[i][1] + "','" + arrayList[i][2] + "')";
            }
            
            query = query + ";"; // mark the end of the insertion

            stmt.executeUpdate(query);
                
        }
        catch(Exception e)
        {
            e.printStackTrace();
            stmt = null;
        }
    }
    
    public void importMeasurement(Connection conn, String table, Measurement measurement, boolean test){
            Statement stmt;
            String query;

            try
            {
                stmt = conn.createStatement(
                			ResultSet.TYPE_SCROLL_INSENSITIVE,
                			ResultSet.CONCUR_UPDATABLE);

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
                		+ ") ";
                           
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
            		+ ") "
            		+ "VALUES ";

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
	 			if(measurementEntry < measurementsList.size()){
	 				query = query + ", \n";
	 			} else {
	 				query = query + ";";
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
}
