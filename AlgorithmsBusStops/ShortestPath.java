import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ShortestPath {
	ArrayList <Stops> stopsArray = new ArrayList<Stops>();
	ArrayList<StopTimes> stopTimesArray = new ArrayList<StopTimes>();
	ArrayList<Transfers> transfersArray = new ArrayList <Transfers>();


	ShortestPath(String stopFilename, String stopTimesFilename, String transfersFilename)
	{
		//read in all 3 files and add to relevant arraylists
		if (stopFilename !=null && stopFilename != "")
		{
			try {
				File file = new File(stopFilename);
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
		//stop times
		if (stopTimesFilename!= null && stopTimesFilename != "")
		{
			try {
				File file2 = new File(stopTimesFilename);
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
						//copy this method over
						//if ((validateUserInput(line[1]) == true) && (validateUserInput(line[2]) == true))
						{
							//only add valid times to the list
							StopTimes currentStopTime = new StopTimes(Integer.parseInt(line[0]),line[1], line[2], Integer.parseInt(line[3]), Integer.parseInt(line[4]), line[5], Integer.parseInt(line[6]), Integer.parseInt(line[7]), Double.parseDouble(line[8]) );
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


		if ((transfersFilename != null)&& (transfersFilename !=""))
			try {
				File file3 = new File(transfersFilename);
				Scanner scanner3 = new Scanner(file3);
				int i = 0;
				//Transfers currentTransfer;
				
				while(scanner3.hasNextLine())
				{
					String [] line = scanner3.nextLine().trim().split(",");
					if (i == 0)
					{
						//headers do nothing
					}
					else 
					{
						//if times are valid??
						Transfers currentTransfer = new Transfers(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]));
						transfersArray.add(currentTransfer);
					}
				}

			}
			catch(Exception e)
			{
				System.out.print("Invalid transfers file name");
			}

	}
	
	
	public static int costWithEdge(String filename)
	{
		int cost = 0;
		if (filename == "stop_times.txt")
		{
			cost = 1;
		}
		if (filename == "transfers.txt")
		{
			//2 or time/100
		}
		return cost;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
