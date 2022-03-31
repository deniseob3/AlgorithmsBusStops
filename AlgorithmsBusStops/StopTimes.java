
public class StopTimes {
	public int tripID;
	public double arrivalTime;
	public double departureTime;
	public int stopID;
	public int stopSequence;
	public String stopHeadSign;
	public int pickupType;
	public int dropOffType;
	public double shapeDistTravelled;
	
	StopTimes(int tripID, double arrivalTime, double departureTime, int stopID, int stopSequence, String stopHeadSign, int pickupType, int dropOffType, double shapeDistTravelled)
	{
		this.tripID = tripID;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.stopID = stopID;
		this.stopSequence = stopSequence;
		this.stopHeadSign = stopHeadSign;
		this.pickupType= pickupType;
		this.dropOffType = dropOffType;
		this.shapeDistTravelled = shapeDistTravelled;
	}
}
