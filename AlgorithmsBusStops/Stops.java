
public class Stops implements Comparable<Stops> {
	public static int id;
	public int code;
	public String name;
	public String description;
	public double latitude;
	public double longitude;
	public int zoneID;
	public String url; //type of url??
	public int locationType;
	public int parentStation;

	Stops(int id, int code, String name, String description, double latitude, double longitude, int zoneID, String url, int locationType, int parentStation)
	{
		//creating a stop
		this.id = id;
		this.code = code;
		this.name = name;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.zoneID = zoneID;
		this.url = url;
		this.locationType = locationType;
		this.parentStation = parentStation;
	}

	@Override
	public int compareTo(Stops compareStop) {
		// TODO Auto-generated method stub
		int compareID =((Stops)compareStop).id;
		/* For Ascending order*/
		return Stops.id-compareID;

	}



}
