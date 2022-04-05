import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BusStopByName {
	static ArrayList<Stops> stopsArray = new ArrayList<Stops>();
	static ArrayList<String> stopNamesArray = new ArrayList<String>();
	static ArrayList<Stops> stopsThatMatchArray = new ArrayList <Stops>();
	static TST<Stops> tst = new TST<Stops>();

	//read in stops file
	BusStopByName(String stopFilename) throws IOException {
		if (stopFilename !=null && stopFilename != "")
		{
			try {
				File file = new File("./stops.txt");
				/*File f = new File("stops.txt");
				try {
				    System.out.println(f.getCanonicalPath());
				} catch (IOException e) {
				    e.printStackTrace();
				}*/
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

						Stops currentStop = new Stops(Integer.parseInt(line[0]), line[1], line[2], line[3], Double.parseDouble(line[4]), Double.parseDouble(line[5]), line[6] );
						stopsArray.add(currentStop);
						//Some have no stop code
						//System.out.println(currentStop.id);
						movingPrefixes(currentStop);
						//the moving prefixes method adds the current stop to the tst

					}
					i++;
				}
				scanner.close();
			}
			catch (Exception e)
			{
				System.out.println("Invalid file name stops");
				e.printStackTrace();
			}
		}

	}

	public static void movingPrefixes(Stops currentStop)
	{
		ArrayList <String> listOfPrefixes = new ArrayList<String>();
		listOfPrefixes.add("FLAGSTOP");
		listOfPrefixes.add("WB");
		listOfPrefixes.add("NB");
		listOfPrefixes.add("SB");
		listOfPrefixes.add("EB");

		for (int a = 0; a < listOfPrefixes.size(); a++) 
		{
			if (currentStop.name.contains(listOfPrefixes.get(a)))
			{
				String [] line = currentStop.name.trim().split(" ");
				String prefix = line[0];
				String newName = "";
				for (int j = 1; j < line.length; j ++)
				{
					newName += line[j];
				}
				//add the prefix to the end
				newName+=line[0];
				currentStop.name = newName;
				tst.putt(currentStop.name, currentStop);
				//changed the put method, not sure if it works
				//key = stop name changed
				//value = all details of the stop
			}
		}
		System.out.println("Moving prefixes complete");



	}

	public static String checkingMatches(String userInput)
	{

		//Iterable<String> queueNames = new Queue<String>();
		//queueNames = tst.keysThatMatch(userInput);
		//ArrayList <Stops> matchingStops = new ArrayList <Stops> ();
		//for (int i =0; i < stopsArray.size(); i++)
		//{
			 //queueNames.get(i);
		//}
		//using the tst to find keys that match the key we have provided
		//System.out.print("Matching stops complete");
		String matches = tst.get(userInput).toString();
		System.out.print("Matching stops complete");
		return matches;
		//problem is matching the stop name to the list of stops
	}


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//BusStopByName bus = new BusStopByName("string");
		System.out.println("Enter the stop name: ");
		Scanner input = new Scanner(System.in);
		String userInput = input.next();
		

		ArrayList <Stops> matchingStops = new ArrayList <Stops> ();
		BusStopByName bus = new BusStopByName("./stops.txt");
		String matchingNames = checkingMatches(userInput);
		String currentStop;
		for (int i = 0; i < matchingStops.size(); i++)
		{
			
		}
		


	}

}
