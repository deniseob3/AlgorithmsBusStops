import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ArrivalTimes {
	//class variables
	//static ArrayList <Stops> stopsArray = new ArrayList<Stops>();
	static ArrayList<StopTimes> stopTimesArray = new ArrayList<StopTimes>();
	static ArrayList<StopTimes> tripsThatMatchArrivalTime = new ArrayList<StopTimes>();
	//each element of the arraylist stores an object of type stops
	//just stop times array
	//pretty sure stops array is not needed here. Keep code for somewhere else
	
	ArrivalTimes(String filenameStopTimes) throws IOException
	{
		//read in stop file and assign it to a 2d array
		/*if (filenameStops !=null && filenameStops != "")
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
		}*/
		
		if (filenameStopTimes!= null && filenameStopTimes != "")
		{
			//try {
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
			//catch(Exception e)
			{
				//System.out.print("Invalid file name: stop times");
		//	}
		}
	}
	public static boolean validateUserInput(String userInput)
	{
		boolean valid = true;
		String [] line = userInput.trim().split(":");
		int hours = Integer.parseInt(line[0]);
		int minutes = Integer.parseInt(line[1]);
		int seconds = Integer.parseInt(line[2]);
		if ((hours > 23)|| (minutes > 59) || (seconds > 59))
		{
			valid = false;
		}
		return valid;
		//must validate the times in the file too!!
		//in the constructor before I read it into the array, 
		//validate each entry using the same function
		//only add it to the array if it is valid entry
		//otherwise skip.
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
			if (currentStopTimes.arrivalTime == userInputOfTime)
				//two equal strings could cause problems possibly .equals?
			{
				//add to new matching arrival time to the arraylist
				currentTripID = currentStopTimes.tripID;
				//find stop with that ID
				for (int j = 0; j < stopTimesArray.size(); j++)
				{
					if (currentTripID == stopTimesArray.get(i).stopID)
					{
						tripsThatMatchArrivalTime.add(stopTimesArray.get(i));
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
		ArrivalTimes at = new ArrivalTimes("stop_times.txt");
		ArrayList <StopTimes> arrayListSorted = new ArrayList <StopTimes>();
		arrayListSorted = at.findingMatchingArrivalTimes(userTime);
		arrayListSortedByStopID(arrayListSorted);
		arrayListStopTimesToString(arrayListSorted);
		
		//for (int i= 0; i < arrayListSorted.size(); i++)
		//{
			//System.out.println(arrayListSorted.get(i));
		//}
		//running this file????
		//
		
		//terminating after 10 seconds
		//file opening but not doing anything
		//trying to commit again
		

	}

}
