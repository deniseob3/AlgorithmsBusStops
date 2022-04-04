import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ShortestPath {
	static ArrayList <Stops> stopsArray = new ArrayList<Stops>();
	static ArrayList<StopTimes> stopTimesArray = new ArrayList<StopTimes>();
	static ArrayList<Transfers> transfersArray = new ArrayList <Transfers>();
	static ArrayList <Stops> route = new ArrayList<Stops>();
	static ArrayList <DirectedEdge> directedEdgesArray = new ArrayList<DirectedEdge>();
	///directed edges arraylist is in no particular order
	static double [][] distanceTo = new double[stopsArray.size()][stopsArray.size()];



	ShortestPath(String stopFilename, String stopTimesFilename, String transfersFilename)
	{
		//read in all 3 files and add to relevant arraylists
		//stops
		if (stopFilename !=null && stopFilename != "")
		{
			try {
				File file = new File("./stops.txt");
				Scanner scanner = new Scanner(file);
				int i = 0;

				while(scanner.hasNextLine())
				{
					String [] line = scanner.nextLine().trim().split(",");
					//split each input line by commas
					if (i == 0)
					{
						//column headings
						//do nothing with these
					}
					else
					{
						//create a stop object with each line of the file

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
						//copy this method over
						//if ((validateUserInput(line[1]) == true) && (validateUserInput(line[2]) == true))
						{
							//only add valid times to the list
							StopTimes currentStopTime = new StopTimes(Integer.parseInt(line[0]),line[1], line[2], Integer.parseInt(line[3]), Integer.parseInt(line[4]), line[5], Integer.parseInt(line[6]), Integer.parseInt(line[7]) );
							stopTimesArray.add(currentStopTime);
							//DirectedEdge currentEdge = new DirectedEdge(Integer.parseInt(line[0]), Integer.parseInt(line[0]), cost);
						}

					}
					i++;
				}
				scanner2.close();
				for (int k = 1; k <= stopTimesArray.size(); k ++)
				{
					if (stopTimesArray.get(k-1).tripID == (stopTimesArray.get(k).tripID))
						//if two consecutive trips have the same ID, create a directed edge between them
					{
						DirectedEdge currentEdge = new DirectedEdge(stopTimesArray.get(k-1).stopID, stopTimesArray.get(k).stopID, 1);
						//cost is 1 from stop times file
						directedEdgesArray.add(currentEdge);

					}
				}

			}
			catch(Exception e)
			{
				System.out.print("Invalid file name: stop times");
			}
		}

		//transfers
		if ((transfersFilename != null)&& (transfersFilename !=""))
			try {
				File file3 = new File("transfers.txt");
				Scanner scanner3 = new Scanner(file3);
				int i = 0;
				int cost = 0;
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
						if(ArrivalTimes.validateUserInput(line[3]) == true) {
							//if the time is valid then create a transfer object
							Transfers currentTransfer = new Transfers(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]), line[3]);
							transfersArray.add(currentTransfer);
							
							int transferType = Integer.parseInt(line[2]);
							if (transferType == 0)
							{
								cost = 2;
							}
							if (transferType == 2)
							{
								cost = (Integer.parseInt(line[3]))/100;
								//cost = minimum transfer time /100
							}
							DirectedEdge currentEdge = new DirectedEdge(Integer.parseInt(line[0]), Integer.parseInt(line[0]), cost);
							//creates directed edge between the two edges
							directedEdgesArray.add(currentEdge);
						}
					}
				}
				scanner3.close();

			}
		catch(Exception e)
		{
			System.out.print("Invalid transfers file name");
		}

	}

	public static void making2DArray()
	{
		//two for loops
		Stops currentOrigin;
		Stops currentDestination;
		//what are i and j? how do they correspond to the individual stops
		//maybe sort each array?
		for (int i = 0; i < stopsArray.size(); i++)
		{
			for (int j = 0; j < stopsArray.size(); j++)
			{
				for (int k = 0; k < directedEdgesArray.size(); k++)
				{
					currentOrigin = stopsArray.get(i);
					currentDestination = stopsArray.get(j);
					if ((directedEdgesArray.get(k).fromID == currentOrigin.id)&&(directedEdgesArray.get(k).toID == currentDestination.id))
					{
						//if there is a directed edge between current origin and current destination
						distanceTo[i][j] = directedEdgesArray.get(k).cost;
					}
					else
					{
						distanceTo[i][j] = Integer.MAX_VALUE;
					}
				}
			}
		}
	}

	public static void shortestPath(int fromStopID, int toStopId)
	{
		int k = fromStopID;
		Stops currentStop;
		Stops originStop;
		for (int i = 0; i < stopsArray.size(); i++)
		{
			currentStop = stopsArray.get(i);
			if (currentStop.id == fromStopID)
			{
				originStop = currentStop;
				route.add(originStop);
				break;
				//exit for loop
				
			}
			//returning that no such stop exists
		}
		boolean[] shortestPath = new boolean[distanceTo.length];
		//route.add(fromStopID);
		shortestPath[k] = true;
		while (true) {
			int x = -1;
			//check what is i?? need it to be the ID of all the possible stops
			//doesn't start at 0
			for (int i = 0; i < distanceTo.length; i++) {
				if ((shortestPath[i] == false) && (distanceTo[k][i] !=Integer.MAX_VALUE)) {
					x = i;
					break; // break as new vertex is found
				}
			}
			if (x == -1) {
				return;
			}
			shortestPath[x] = true;

			for (int i = 0; i < distanceTo.length; i++) {
				if (distanceTo[k][x] + distanceTo[x][i] < distanceTo[k][i]) {
					distanceTo[k][i] = distanceTo[k][x] + distanceTo[x][i];
					shortestPath[i] = false;
					//edgeTo[k][i] = x;
				}
			}
		}

	}






	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShortestPath st = new ShortestPath("stops", "stop_times", "transfers");
		making2DArray();
		//create the 2d array

		System.out.println("Enter the origin stop");
		Scanner input = new Scanner(System.in);
		int originStop = input.nextInt();
		System.out.println("Enter the destination stop");
		int destination = input.nextInt();

		shortestPath(originStop, destination);

	}

}
