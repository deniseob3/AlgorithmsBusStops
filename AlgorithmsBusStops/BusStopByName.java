import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class BusStopByName {
	ArrayList <Stops> stopsArray = new ArrayList<Stops>();
	//ArrayList <Stops> movedPrefixesArray = new ArrayList <Stops>();

	//read in stops file
	BusStopByName(String stopFilename) {
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

	}

	public static void movingPrefixes(ArrayList <Stops> stopsArray)
	{
		ArrayList <String> listOfPrefixes = new ArrayList<String>();
		listOfPrefixes.add("FLAGSTOP");
		listOfPrefixes.add("WB");
		listOfPrefixes.add("NB");
		listOfPrefixes.add("SB");
		listOfPrefixes.add("EB");
		Stops currentStop;
		for (int i = 0; i < stopsArray.size(); i++)
		{
			currentStop = stopsArray.get(i);
			for (int a = 0; a < listOfPrefixes.size(); a++) 
			{
				if (currentStop.name.contains(listOfPrefixes.get(i)))
				{
					String [] line = currentStop.name.trim().split(" ");
					String prefix = line[0];
					String newName = "";
					for (int j = 1; j < line.length; j ++)
					{
						newName += line[i];
					}
					//add the prefix to the end
					newName+=line[0];
					currentStop.name = newName;
				}
			}
		}

	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
