import java.io.File;
import java.util.Scanner;

public class ArrivalTimes {
	//class variables
	
	ArrivalTimes(String filename)
	{
		//read in stop times file and assign it to a 2d array
		if (filename !=null && filename != "")
		{
			try {
				File file = new File(filename);
				Scanner scanner = new Scanner(file);
				int i = 0;

				while(scanner.hasNextLine())
				{
					String [] line = scanner.nextLine().trim().split(",");
					//split each input line by commas
					//How big is the 2d array??/1d array
				}
				
			}
			catch (Exception e)
			{
				System.out.print("Invalid file name");
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
