package datex2xml;

import java.sql.*;

/**
 * @author J.K.J. Martens
 * 
 * In this file some specific information is redacted, 
 * as one wouldn't want to publicize ones database credentials...
 *
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
	private String[][] testArray = {
									{"product1", "23"},
									{"product2", "42"}
									}; 
	
	public void setTest(boolean testValue){
		this.test = testValue;
	}
	
	public void setTable(String table){
		this.table = table;
	}
	
	private void loadDatabase(){
		DBase dataBase = new DBase();
		Connection connection = dataBase.connect(	this.connector + 
													this.host + ":"	+ 
													this.port + "/" + 
													this.database, 
													user, 
													password
													);
		// confirm that this is no test
		if(true == this.test){
		dataBase.importData(connection, this.table, testArray);
		} else{
			String[] args;
			dataBase.importData(connection, this.table, testArray);
		}
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
			            Class.forName(  
			    "com.mysql.jdbc.Driver").newInstance();

			            conn = DriverManager.getConnection(db_connect_str, 
			    db_userid, db_password);
			        
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
}
