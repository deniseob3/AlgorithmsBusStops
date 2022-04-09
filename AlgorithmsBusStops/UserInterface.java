import java.io.IOException;
import java.util.Scanner;

public class UserInterface {

	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to the Vancouver bus system!");
		System.out.println("Which feature would you like to use? ");
		System.out.println("Options: shortest path, arrival times or bus stop by name");
		Scanner input = new Scanner(System.in);
		String userInput = input.nextLine();
		if (userInput.equals("shortest path"))
		{
			ShortestPath.shortestPathMethod();
			//user enters 2 stop IDs
		}
		if (userInput.equals("arrival times"))
		{
			ArrivalTimes.arrivalTimesMethod();
			//user enters 1 time in the form hours:minutes:seconds
		}
		if (userInput.equals("bus stop by name")) 
		{
			BusStopByName.busStopByNameMethod();
			//if size = 0, no stops exist
		}
		else
		{
			System.out.println("Goodbye :) ");
			input.close();
		}
		//must cater for wrong entries

	}

}
