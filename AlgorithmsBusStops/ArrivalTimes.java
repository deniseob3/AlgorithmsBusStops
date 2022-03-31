import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ArrivalTimes {
	//class variables
	static ArrayList <Stops> stopsArray = new ArrayList<Stops>();
	static ArrayList<StopTimes> stopTimesArray = new ArrayList<StopTimes>();
	static ArrayList<Stops> stopsThatMatchArrivalTime = new ArrayList<Stops>();
	//each element of the arraylist stores an object of type stops
	
	ArrivalTimes(String filenameStops, String filenameStopTimes)
	{
		//read in stop file and assign it to a 2d array
		if (filenameStops !=null && filenameStops != "")
		{
			try {
				File file = new File(filenameStops);
				Scanner scanner = new Scanner(file);
				int i = 0;

				while(scanner.hasNextLine())
				{
					String [] line = scanner.nextLine().trim().split(",");
					//split each input line by commas
					//How big is the 2d array??/1d array (arraylist??)
					if (i == 0)
					{
						//column headings
						//do nothing with these
					}
					else
					{
						//store these values
						//create a stop object with each line of the file
						// add to the end of the arraylist
						Stops currentStop = new Stops(Integer.parseInt(line[0]), Integer.parseInt(line[1]), line[2], line[3], Double.parseDouble(line[4]), Double.parseDouble(line[5]), Integer.parseInt(line[6]), line[7], Integer.parseInt(line[8]), Integer.parseInt(line[9]));
						stopsArray.add(currentStop);
					
					}
					i++;
				}
				scanner.close();
			}
			catch (Exception e)
			{
				System.out.print("Invalid file name stops");
			}
		}
		
		if (filenameStopTimes!= null && filenameStopTimes != "")
		{
			try {
				File file2 = new File(filenameStopTimes);
				Scanner scanner2 = new Scanner(file2);
				int i = 0;
				
				while(scanner2.hasNextLine())
				{
					String [] line = scanner2.nextLine().trim().split(",");
					if (i == 0)
					{
						//row headings do nothing
					}
					else
					{
						StopTimes currentStopTime = new StopTimes(Integer.parseInt(line[0]), Double.parseDouble(line[1]), Double.parseDouble(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]), line[5], Integer.parseInt(line[6]), Integer.parseInt(line[7]), Double.parseDouble(line[8]) );
						stopTimesArray.add(currentStopTime);
						
					}
					i++;
				}
				scanner2.close();
				
			}
			catch(Exception e)
			{
				System.out.print("Invalid file name: stop times");
			}
		}
	}
	
	public static ArrayList <Stops> findingMatchingArrivalTimes(Double userInputOfTime)
	{
		//check user input is valid
		StopTimes currentStopTimes;
		int currentStopID;
		for (int i = 0; i < stopTimesArray.size(); i ++)
		{
			currentStopTimes = stopTimesArray.get(i);
			if (currentStopTimes.arrivalTime == userInputOfTime)
			{
				//add to new valid arraylist
				currentStopID = currentStopTimes.stopID;
				//find stop with that ID
				for (int j = 0; j < stopsArray.size(); j++)
				{
					if (currentStopID == stopsArray.get(i).id)
					{
						stopsThatMatchArrivalTime.add(stopsArray.get(i));
						//add the stop to the arraylist of all stops that arrive at that given time
					}
				}
			}
		}
		return stopsThatMatchArrivalTime;
	}
	public static void arrayListSortedByStopID(ArrayList <StopTimes> stopsThatMatchArrivalTime)
	{
		//MUST PASS IN AN ARRAY OF TYPE STOP TIMES (NOT STOPS)
		Collections.sort(stopsThatMatchArrivalTime);
		//implemented comparable interface and made a sort by ID method
		//might not work
	}
	
	public static void arrayListToString(ArrayList <Stops> stopsThatMatchArrivalTime)
	{
		Stops currentStop;
		//make sure this array that is read in is sorted
		for (int i = 0; i < stopsThatMatchArrivalTime.size(); i ++)
		{
			currentStop = stopsThatMatchArrivalTime.get(i);
			//print out all elements of the stops that match (already sorted).
			
			System.out.println("ID: " + currentStop.id + " code: " + currentStop.code + " name " + currentStop.name + " description " + currentStop.description + " latitude " + currentStop.latitude + " longitude " + currentStop.longitude + " zone ID " + currentStop.zoneID + " Location Type " + currentStop.locationType);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
