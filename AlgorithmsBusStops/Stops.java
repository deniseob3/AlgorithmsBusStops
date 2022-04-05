
public class Stops /*implements Comparable<Stops> */{
	public static int id;
	public int code;
	public String name;
	public String description;
	public double latitude;
	public double longitude;
	public String zoneID;
	public String url; //type of url??
	public String locationType;
	public String parentStation;
	//Not all stops have a parent station

	Stops(int id, int code, String name, String description, double latitude, double longitude, String zoneID, String url, String locationType, String parentStation)
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


	/*
	@Override
	public int compareTo(Stops compareStop) {
		// TODO Auto-generated method stub
		int compareID =((Stops)compareStop).id;
		/* For Ascending order*/
		//return Stops.id-compareID;




}
