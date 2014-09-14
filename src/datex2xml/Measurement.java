package datex2xml;

public class Measurement {
	// all attributes of a single measurementSite
	String 	measurementSiteReference;
	String 	measurementTimeDefault;
	
	int 	siteMeasurementsIndexMeasuredValue;
	String 	measuredValue;
	String	trafficFlow;
	int		vehicleFlowRate;
	String	trafficSpeed;
	int		numberOfIncompleteInputs;
	int		numberOfInputValuesUsed;
	int		standardDeviation;
	int		speed;

	//create getters
	
	public String getMeasurementSiteReference() {
		return measurementSiteReference;
	}
	public String getMeasurementTimeDefault() {
		return measurementTimeDefault;
	}
	public int getSiteMeasurementsIndexMeasuredValue() {
		return siteMeasurementsIndexMeasuredValue;
	}
	public String getMeasuredValue() {
		return measuredValue;
	}
	public String getTrafficFlow() {
		return trafficFlow;
	}
	public int getVehicleFlowRate() {
		return vehicleFlowRate;
	}
	public String getTrafficSpeed() {
		return trafficSpeed;
	}
	public int getNumberOfIncompleteInputs() {
		return numberOfIncompleteInputs;
	}
	public int getNumberOfInputValuesUsed() {
		return numberOfInputValuesUsed;
	}
	public int getStandardDeviation() {
		return standardDeviation;
	}
	public int getSpeed() {
		return speed;
	}
	
	//create setters
	
	public void setMeasurementSiteReference(String measurementSiteReference) {
		this.measurementSiteReference = measurementSiteReference;
	}
	public void setMeasurementTimeDefault(String measurementTimeDefault) {
		this.measurementTimeDefault = measurementTimeDefault;
	}
	public void setSiteMeasurementsIndexMeasuredValue(
			int siteMeasurementsIndexMeasuredValue) {
		this.siteMeasurementsIndexMeasuredValue = siteMeasurementsIndexMeasuredValue;
	}
	public void setMeasuredValue(String measuredValue) {
		this.measuredValue = measuredValue;
	}
	public void setTrafficFlow(String trafficFlow) {
		this.trafficFlow = trafficFlow;
	}
	public void setVehicleFlowRate(int vehicleFlowRate) {
		this.vehicleFlowRate = vehicleFlowRate;
	}
	public void setTrafficSpeed(String trafficSpeed) {
		this.trafficSpeed = trafficSpeed;
	}
	public void setNumberOfIncompleteInputs(int numberOfIncompleteInputs) {
		this.numberOfIncompleteInputs = numberOfIncompleteInputs;
	}
	public void setNumberOfInputValuesUsed(int numberOfInputValuesUsed) {
		this.numberOfInputValuesUsed = numberOfInputValuesUsed;
	}
	public void setStandardDeviation(int standardDeviation) {
		this.standardDeviation = standardDeviation;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
// end of Measurement
}
