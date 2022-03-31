import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrivalTimes {
	//class variables
	ArrayList <Stops> stopsArray = new ArrayList<Stops>();
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
					if (i == 1)
					{
						//store these values
						//create a stop object with each line of the file
						// add to the end of the arraylist
						Stops currentStop = new Stops(Integer.parseInt(line[0]), Integer.parseInt(line[1]), line[2], line[3], Double.parseDouble(line[4]), Double.parseDouble(line[5]), Integer.parseInt(line[6]), line[7], Integer.parseInt(line[8]), Integer.parseInt(line[9]));
						stopsArray.add(currentStop);
					
					}
					i++;
				}
				
			}
			catch (Exception e)
			{
				System.out.print("Invalid file name stops");
			}
		}
		
		if (filenameStopTimes!= null && filenameStopTimes != "")
		{
			try {
				
			}
			catch(Exception e)
			{
				System.out.print("Invalid file name: stop times");
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
