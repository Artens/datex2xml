package datex2xml;

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
	
	public void reset(){
	// RESET all values for the measurement
		this.publicationTime = "";
		this.measurementSiteReference = "";
		this.measurementTimeDefault = "";
		this.siteMeasurementsIndexMeasuredValue = 0;
		this.measuredValue = "";
		this.basicData = "";
		this.trafficFlow = "";
		this.vehicleFlowRate = 0;
		this.trafficSpeed = "";
		this.numberOfIncompleteInputs = 0;
		this.numberOfInputValuesUsed = 0;
		this.standardDeviation = 0;
		this.speed = 0;
		System.out.println("measurement was reset");
	}
// end of Measurement
}
