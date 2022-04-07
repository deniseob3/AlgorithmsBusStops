import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BusStopByName {
	static ArrayList<Stops> stopsArray = new ArrayList<Stops>();
	static ArrayList<String> stopNamesArray = new ArrayList<String>();
	static ArrayList<Stops> stopsThatMatchArray = new ArrayList <Stops>();
	static TST<Stops> tst = new TST<Stops>();
	//value of tst = stops

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
					newName += line[j] + " ";
				}
				//add the prefix to the end
				newName+=line[0];
				currentStop.name = newName;
				
				if (tst.contains(currentStop.name) == true)
				{
					//do not add names that are not unique to the tst
					//if the tst already contains that key
					//not sure if that is necessary
				}
				else
				{
					//System.out.println("Added to tree");
					tst.put(currentStop.name, currentStop);
				}
				//overrides the existing
				//changed the put method, not sure if it works
				//key = stop name changed
				//value = all details of the stop
			}
		}
		//System.out.println("Moving prefixes complete");



	}

	public static ArrayList <String> checkingMatches(String userInput)
	{
		//System.out.println("Checking matches method called");
		Iterable<String> queueOfMatchingNames = tst.keysWithPrefix(userInput);
		ArrayList <String> listOfMatchingNames = new ArrayList <String>();
		for(String name: queueOfMatchingNames)
		{
			listOfMatchingNames.add(name);
			//System.out.println("Added to list of matching names");
		}
		return listOfMatchingNames;
	}
	
	public static void matchingNamesToStops(ArrayList <String> matchingNames)
	{
		//System.out.println("Matching names to stop method called");
		for (String name: matchingNames)
		{
			Stops currentStop = tst.get(name);
			stopsThatMatchArray.add(currentStop);
			//System.out.println("Added to stops that match array");
		}
		
	}
	
	public static void stopTimesToString(Stops currentStop)
	{
		System.out.println("Stop id: " + currentStop.id + " Stop code: " + currentStop.code + " stop name: " + currentStop.name + " stop description " + currentStop.description+ " latitude: "+ currentStop.latitude + " longitude " + currentStop.longitude + " zone id " + currentStop.zoneID);
	}

	public static void busStopByNameMethod() throws IOException {
	
		System.out.println("Enter the stop name: ");
		Scanner input = new Scanner(System.in);
		String userInput = input.next();
		

		ArrayList <String> matchingNames = new ArrayList <String> ();
		BusStopByName bus = new BusStopByName("./stops.txt");
		//this sets up the tst and moves the prefixes
		matchingNames = checkingMatches(userInput);
		//return values associated with the matchingNamesKeys
		matchingNamesToStops(matchingNames);
		
		for (Stops currentStop: stopsThatMatchArray)
		{
			stopTimesToString(currentStop);
		}
		
		//checking matches
		
		
		
		


	}

}
