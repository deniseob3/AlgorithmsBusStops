import java.io.IOException;
import java.util.Scanner;

public class UserInterface {

	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to the Vancouver bus system!");
		System.out.println("Which feature would you like to use? ");
		System.out.println("Options: shortest path, arrival times or bus stop by name");
		Scanner input = new Scanner(System.in);
		String userInput = input.next();
		if (userInput.equals("shortest path"))
		{
			ShortestPath.shortestPathMethod();
		}
		if (userInput == "arrival times")
		{
			ArrivalTimes.arrivalTimesMethod();
		}
		if (userInput == "bus stop by name") 
		{
			BusStopByName.busStopByNameMethod();
		}
		else
		{
			System.out.println("Goodbye :) ");
		}

	}

}
