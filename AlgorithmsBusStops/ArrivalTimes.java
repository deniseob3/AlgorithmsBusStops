import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ArrivalTimes {
	
	static ArrayList<StopTimes> stopTimesArray = new ArrayList<StopTimes>();
	static ArrayList<StopTimes> tripsThatMatchArrivalTime = new ArrayList<StopTimes>();
	//each element of the arraylist stores an object of type stops
	
	ArrivalTimes(String filenameStopTimes) throws IOException
	{
		
		if (filenameStopTimes!= null && filenameStopTimes != "")
		{
			try {
				File file2 = new File("./stop_times.txt");
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
						//check arrival time valid and departure time valid
						if ((validateUserInput(line[1]) == true) && (validateUserInput(line[2]) == true))
						{
							//only add valid times to the list
							StopTimes currentStopTime = new StopTimes(Integer.parseInt(line[0]),line[1], line[2], Integer.parseInt(line[3]), Integer.parseInt(line[4]), line[5], Integer.parseInt(line[6]), Integer.parseInt(line[7]) );
							stopTimesArray.add(currentStopTime);
						}
						
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
	public static boolean validateUserInput(String userInput)
	{
		boolean valid = true;
		String [] line = userInput.trim().split(":");
		int hours = Integer.parseInt(line[0]); //if is an integer??
		int minutes = Integer.parseInt(line[1]);
		int seconds = Integer.parseInt(line[2]);
		if ((hours > 23)|| (minutes > 59) || (seconds > 59))
		{
			valid = false;
		}
		return valid;
	}
	
	public static ArrayList <StopTimes> findingMatchingArrivalTimes(String userInputOfTime)
	{
		//check user input is valid
		if (validateUserInput(userInputOfTime) == true) {
		StopTimes currentStopTimes;
		int currentTripID;
		for (int i = 0; i < stopTimesArray.size(); i ++)
		{
			currentStopTimes = stopTimesArray.get(i);
			if (currentStopTimes.arrivalTime.equals(userInputOfTime))
				//two equal strings could cause problems possibly .equals?
			{
				//add to new matching arrival time to the arraylist
				currentTripID = currentStopTimes.tripID;
				//find stop with that ID
				for (int j = 0; j < stopTimesArray.size(); j++)
				{
					if (currentTripID == stopTimesArray.get(j).stopID)
					{
						//loop through stop times array to find all the ID's that match
						tripsThatMatchArrivalTime.add(stopTimesArray.get(j));
						//add the trip to the arraylist of all trips that arrive at that given time
					}
				}
			}
		}
		return tripsThatMatchArrivalTime;
		}
		else {
			return null;//return null if user inputs an invalid time
		}
	}
	public static void arrayListSortedByStopID(ArrayList <StopTimes> tripsThatMatchArrivalTime)
	{
		//MUST PASS IN AN ARRAY OF TYPE STOP TIMES (NOT STOPS)
		Collections.sort(tripsThatMatchArrivalTime);
		
		//implemented comparable interface and made a sort by ID method
		//might not work
	}
	
	public static void arrayListStopTimesToString(ArrayList <StopTimes> tripsThatMatchArrivalTime)
	{
		StopTimes currentTrip;
		//make sure this array that is read in is sorted
		for (int i = 0; i < tripsThatMatchArrivalTime.size(); i ++)
		{
			currentTrip = tripsThatMatchArrivalTime.get(i);
			//print out all elements of the stops that match (already sorted).
			//
			System.out.println("Trip ID: " + currentTrip.tripID + " arrival time: " + currentTrip.arrivalTime + " departure time " + currentTrip.departureTime + " stop ID " + currentTrip.stopID + " stop sequence " + currentTrip.stopSequence + " stop head sign " + currentTrip.stopHeadSign + " pickup type " + currentTrip.pickupType + "  drop off type " + currentTrip.dropOffType + " shape dist travelled");
			//print all info about the trips which match the arrival time
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.print("enter a time: ");
		Scanner input = new Scanner(System.in);
		String userTime = input.next();
		ArrivalTimes at = new ArrivalTimes("\stop_times.txt");
		arrayListSortedByStopID(stopTimesArray);
		arrayListStopTimesToString(stopTimesArray);
		
		
		//ArrayList <StopTimes> arrayListSorted = new ArrayList <StopTimes>();
		stopTimesArray = ArrivalTimes.findingMatchingArrivalTimes(userTime);
		//finding matching arrival times isn't working
		//arrayListSortedByStopID(stopTimesArray);
		arrayListStopTimesToString(stopTimesArray);
		
		
		//terminating after 10 seconds
		//file opening but not doing anything
		//trying to commit again
		

	}

}
