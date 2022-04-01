
public class StopTimes implements Comparable <StopTimes>{
	public static int tripID;
	//time = string
	public String arrivalTime;
	public String departureTime;
	public int stopID;
	public int stopSequence;
	public String stopHeadSign;
	public int pickupType;
	public int dropOffType;
	//public double shapeDistTravelled;
	
	StopTimes(int tripID, String arrivalTime, String departureTime, int stopID, int stopSequence, String stopHeadSign, int pickupType, int dropOffType)
	{
		StopTimes.tripID = tripID;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.stopID = stopID;
		this.stopSequence = stopSequence;
		this.stopHeadSign = stopHeadSign;
		this.pickupType= pickupType;
		this.dropOffType = dropOffType;
		//this.shapeDistTravelled = shapeDistTravelled;
	}

	@Override
	public int compareTo(StopTimes compareTrips) {
			
			int compareID =((StopTimes)compareTrips).tripID;
			/* For Ascending order*/
			return StopTimes.tripID-compareID;
	}
	 
}
