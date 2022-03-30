
public class Stops {
	public int id;
	public int code;
	public String name;
	public String description;
	public double latitude;
	public double longitude;
	public int zoneID;
	//public int url; //type of url??
	public int locationType;
	//public int parentStation;
	
	Stops(int id, int code, String name, String description, double latitude, double longitude, int zoneID, int locationType)
	{
		//creating a stop
		this.id = id;
		this.code = code;
		this.name = name;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.zoneID = zoneID;
		this.locationType = locationType;
	}
	
	
	
}
