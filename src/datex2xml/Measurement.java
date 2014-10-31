package datex2xml;

/**
 * @author J.K.J. Martens
 *	The purpose of this file is to have an object type for Measurements available for processing. 
 *	
 */

public class Measurement {
	// all attributes of a single measurementSite
	String	publicationTime;
	String 	measurementSiteReference;
	String 	measurementTimeDefault;
	int 	siteMeasurementsIndexMeasuredValue;
	String 	measuredValue;
	String	basicData;
	String	trafficFlow;
	int		vehicleFlowRate;
	String	trafficSpeed;
	int		numberOfIncompleteInputs;
	int		numberOfInputValuesUsed;
	float	standardDeviation;
	float	speed;

	//create getters
	public String getPublicationTime() {
		return publicationTime;
	}
	public String getBasicData() {
		return basicData;
	}
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
	public float getStandardDeviation() {
		return standardDeviation;
	}
	public float getSpeed() {
		return speed;
	}
	
	//create setters
	public void setPublicationTime(String publicationTime) {
		this.publicationTime = publicationTime;
	}
	public void setMeasurementSiteReference(String measurementSiteReference) {
		this.measurementSiteReference = measurementSiteReference;
	}
	public void setMeasurementTimeDefault(String measurementTimeDefault) {
		this.measurementTimeDefault = measurementTimeDefault;
	}
	public void setSiteMeasurementsIndexMeasuredValue(int siteMeasurementsIndexMeasuredValue) {
		this.siteMeasurementsIndexMeasuredValue = siteMeasurementsIndexMeasuredValue;
	}
	public void setMeasuredValue(String measuredValue) {
		this.measuredValue = measuredValue;
	}
	public void setBasicData(String basicData) {
		this.basicData = basicData;
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
	public void setStandardDeviation(float standardDeviation) {
		this.standardDeviation = standardDeviation;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
// end of Measurement
}
