package datex2xml;

import java.util.ArrayList;
import java.util.List;

import datex2xml.Measurement;
import datex2xml.DataBaseHelper;

public class MeasurementsList {

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
		if(measurementsList.size() == 5000){
			DataBaseHelper uploadDB = new DataBaseHelper();
			uploadDB.uploadMeasurementsList(measurementsList);
			measurementsList.clear(); // clear the entries in the list
			return true;
		} else {
			return false;
		}
		
	}
	
	public void sendLastMeasurements() {
		DataBaseHelper uploadDB = new DataBaseHelper();
		uploadDB.uploadMeasurementsList(measurementsList);
	}
} // End Of File
