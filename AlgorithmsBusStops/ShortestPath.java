import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ShortestPath {
	//for reading in the files
	static ArrayList <Stops> stopsArray = new ArrayList<Stops>();
	static ArrayList<StopTimes> stopTimesArray = new ArrayList<StopTimes>();
	static ArrayList<Transfers> transfersArray = new ArrayList <Transfers>();

	static ArrayList <DirectedEdge> directedEdgesArray = new ArrayList<DirectedEdge>();
	///directed edges arraylist is in no particular order
	static ArrayList <Stops> stopsOnRoute = new ArrayList <Stops> ();
	static int countOfStops;
	static double totalCost;
	//static EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(countOfStops);



	ShortestPath(String stopFilename, String stopTimesFilename, String transfersFilename)
	{
		//All files are reading in correctly
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

						Stops currentStop = new Stops(Integer.parseInt(line[0]), line[1], line[2], line[3], Double.parseDouble(line[4]), Double.parseDouble(line[5]), line[6]);
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
		countOfStops = stopsArray.size();
		//once stops array has run
		
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
				for (int k = 1; k < stopTimesArray.size(); k ++)
				{
					if (stopTimesArray.get(k-1).tripID == (stopTimesArray.get(k).tripID))
						//if two consecutive trips have the same ID, create a directed edge between them
					{
						DirectedEdge currentEdge = new DirectedEdge(stopTimesArray.get(k-1).stopID, stopTimesArray.get(k).stopID, 1);
						//cost is 1 from stop times file
						directedEdgesArray.add(currentEdge);
						//ewd.addEdge(currentEdge);

					}
				}

			}
			catch(Exception e)
			{
				System.out.println("Invalid file name: stop times");
				e.printStackTrace();
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
							//ewd.addEdge(currentEdge);
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
	public static EdgeWeightedDigraph creatingEdgeWeightedDigraph()
	{
		EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(100000);
		//must change this back!!
		for (DirectedEdge currentDirectedEdge: directedEdgesArray)
		{
			ewd.addEdge(currentDirectedEdge);
			//add the edges once all the files are read in
		}
		return ewd;
		
	}

	public static ArrayList<DirectedEdge> dijkstraDist(int fromStopID, int toStopID, EdgeWeightedDigraph ewd)
	{
		//validate user input
		//valid stop Id's

		//create an edge weighted digraph
		//stopsArray.size() = number of vertices = number of stops
		//EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(stopsArray.size());
		//edge weighted digraph is empty!
		//number of stops = stopsArray.size();
		//stop ID number > stopsArray.size();
		DijkstraSP shortestPath = new DijkstraSP(ewd, fromStopID);

		if(shortestPath.hasPathTo(toStopID) == true)
		{
			Iterable <DirectedEdge> shortestPathIterable = shortestPath.pathTo(toStopID);
			ArrayList <DirectedEdge> stopIDsOnRoute = new ArrayList <DirectedEdge>();

			for(DirectedEdge edge: shortestPathIterable)
			{
				stopIDsOnRoute.add(edge);
				totalCost += edge.weight();
			}
			System.out.println("Has shortest path");
			//System.out.println("Dijkstra dist: stop ID's on route: " + stopIDsOnRoute.size());
			//11 stops on the route (646 to 1277)
			return stopIDsOnRoute;
		}
		else
		{
			System.out.println("Has no shortest path");
			return null;
		}


	}

	public static ArrayList <Stops> gettingStops(ArrayList <DirectedEdge> directedEdgesOnRoute)
	{
		for (DirectedEdge edge: directedEdgesOnRoute)
		{
			int from = edge.from();
			for (int i = 0; i < stopsArray.size(); i ++)
			{
				//need a get method
				if (stopsArray.get(i).id == from)
				{
					stopsOnRoute.add(stopsArray.get(i));
					//System.out.println("ADDED");
				}
			}
		}
		//System.out.println("Getting stops on route is done");
		//System.out.println("STOPS ON ROUTE SIZE: " + stopsOnRoute.size());
		return stopsOnRoute;
	}
	
	public static boolean userEnteredInteger(String userInput)
	{
		boolean valid = true;
		try 
		{
	        int d = Integer.parseInt(userInput);
	    } 
		catch (NumberFormatException nfe) 
		{
			valid = false;
	    }
		return valid;
	}
	public static boolean isStopInBusSystem(int stopID)
	{
		boolean validStopID = false;
		for (Stops currentStop: stopsArray)
		{
			if (currentStop.id == stopID)
			{
				validStopID = true;
				// the stop entered by the user is one of the possible stops
			}
		}
		return validStopID;
	}


	public static void shortestPathMethod() {
		// TODO Auto-generated method stub
		ShortestPath st = new ShortestPath("stops", "stop_times", "transfers");
		//create the 2d array

		System.out.println("Enter the origin stop");
		Scanner input = new Scanner(System.in);
		int originStop = 0;
		int destination = 0;
		boolean userEnteredIntegerO = false;
		boolean userEnteredIntegerD = false;
		//o = origin and d= destination 
		String originString = input.next();
		if(userEnteredInteger(originString) == true)
		{
			userEnteredIntegerO = true;
			originStop = Integer.parseInt(originString);
		}
		while(userEnteredIntegerO == false)
		{
			System.out.print("Enter an integer");
			originString = input.next();
			if(userEnteredInteger(originString) == true)
			{
				userEnteredIntegerO = true;
				originStop = Integer.parseInt(originString);
			}
		}
		
		System.out.println("Enter the destination stop");		
		String destinationString = input.next();
		if(userEnteredInteger(destinationString) == true)
		{
			userEnteredIntegerD = true;
			destination = Integer.parseInt(destinationString);
		}
		while(userEnteredIntegerD == false)
		{
			System.out.print("Enter an integer");
			destinationString = input.next();
			if (userEnteredInteger(destinationString) == true)
			{
				userEnteredIntegerD = true;
				destination = Integer.parseInt(destinationString);
			}
		}
		//is stop in graph?
		if ((isStopInBusSystem(originStop) == false) ||(isStopInBusSystem(destination) == false))
		{
			System.out.println("That stop is not in the bus system.");
			input.close();
			return;		
		}
		
		//EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(stopsArray.size());
		EdgeWeightedDigraph ewd = creatingEdgeWeightedDigraph();

		ArrayList <DirectedEdge> directedEdgesOnRoute = dijkstraDist(originStop, destination, ewd);
		if(directedEdgesOnRoute == null)
		{
			System.out.println("No route exists!");
			
		}
		else 
		{
			stopsOnRoute = gettingStops(directedEdgesOnRoute);
			System.out.println("Number of stops on route: "+stopsOnRoute.size() );
			//is of size zero
			Stops currentStop;
			System.out.println("List of stops on route: ");
			for (int i = 0; i < stopsOnRoute.size(); i ++)
			{
				currentStop = stopsOnRoute.get(i);
				BusStopByName.stopTimesToString(currentStop);
				//just cost left now
			}
			
			System.out.println("Total cost is : " + totalCost);
			//total cost for those inputs is just the amount of edges (cost 1 each)
			//try one with transfers
		}
		input.close();



	}

}
