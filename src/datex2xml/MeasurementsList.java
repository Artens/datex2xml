package datex2xml;

import java.util.ArrayList;
import java.util.List;

import datex2xml.Measurement;
import datex2xml.DataBaseHelper;
/**
 * @author J.K.J. Martens
 *	The purpose of this file is to have an list object to use for processing measurements
 *	Two functional elements are important:
 *	- the threshold driven upload function
 *	- the generic upload function whenever the threshold is not met 
 */

public class MeasurementsList {
	private int measurementCounter;
	private List<Measurement> measurementsList = new ArrayList<Measurement>();
		
	public void addMeasurementToList(Measurement measurement, Boolean end){
		if(confirmListSize()){
			// validate measurementsList is < threshold or upload (threshold) entries
		} else {
			// add measurement to measurementsList
			measurementsList.add(measurement);
		}
	}
	
	private boolean confirmListSize(){
		// validate the length of the measurement list or end of measurements; upload if threshold is hit
		if(measurementsList.size() == 1000){
			measurementCounter = measurementCounter + 1000;
			DataBaseHelper uploadDB = new DataBaseHelper();
			uploadDB.uploadMeasurementsList(measurementsList);
			measurementsList.clear(); // clear the entries in the list
			return true;
		} else {
			return false;
		}
		
	}
	
	public void sendLastMeasurements() {
		measurementCounter = measurementCounter + measurementsList.size();
		DataBaseHelper uploadDB = new DataBaseHelper();
		uploadDB.uploadMeasurementsList(measurementsList);
	}
	
	public int getMeasurementCount(){
		return measurementCounter;
	}
} // End Of File
