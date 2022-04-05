import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BusStopByName {
	static ArrayList<Stops> stopsArray = new ArrayList<Stops>();
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
						Stops currentStop = new Stops(Integer.parseInt(line[0]), Integer.parseInt(line[1]), line[2], line[3], Double.parseDouble(line[4]), Double.parseDouble(line[5]), line[6], line[7], line[8], line[9]);
						stopsArray.add(currentStop);
						movingPrefixes(currentStop);
						//the moving prefixes method adds the current stop to the tst

					}
					i++;
				}
				scanner.close();
			}
			catch (Exception e)
			{
				//System.out.println("Invalid file name stops");
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
	
	public static ArrayList<Stops> checkingMatches(String userInput)
	{
		
		Iterable<String> queue = new Queue<String>();
		queue = tst.keysThatMatch(userInput);
		ArrayList <Stops> matchingStops = new ArrayList <Stops> ();
		Stops currentStop;
		for (String key: queue)
		{
			currentStop = tst.get(key);
			//returns value associated with that key
			matchingStops.add(currentStop);
		}
		System.out.print("Matching stops complete");
		return matchingStops;
	
	}


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//BusStopByName bus = new BusStopByName("string");
		System.out.println("Enter the stop name: ");
		Scanner input = new Scanner(System.in);
		String userInput = input.next();
		//FILE IS READING IN WRONG
		//POSSIBLY CANNOT FIND THE FILE
		
		ArrayList <Stops> matchingStops = new ArrayList <Stops> ();
		BusStopByName bus = new BusStopByName("./stops.txt");
		matchingStops = checkingMatches(userInput);
		for (Stops currentStop: matchingStops)
		{
			System.out.println(currentStop);
			//
		}
		

	}

}
