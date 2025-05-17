package gym;

import java.sql.Connection;
//import java.sql.Date;
//import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {
		Connection conn = DatabaseConnection.connect();
		if(conn != null) 
			System.out.println("Connected to DB successfully!");
		else 
			System.out.println("connection failed.");
		
		try (Scanner scanner = new Scanner(System.in)) {
			ArrayList<Gym> ar = new ArrayList<>();
			
			System.out.println("Enter numbers of clients");
			int n = scanner.nextInt();
			scanner.nextLine();
			
			for (int i = 0; i < n; i++) {
				
				System.out.println("Client number "+(i+1));
				
				System.out.println("Name");
				String name = scanner.nextLine();
				
				System.out.println("Age");
				int age =scanner.nextInt();
						   
			    System.out.println("Height");
				float height =scanner.nextFloat();
				
			   System.out.println("Weight");
			   float weight =scanner.nextFloat();
			   scanner.nextLine();
						   
			  System.out.println("Enter subscription Start date");
			  String subscriptionStartDate  =scanner.nextLine();
			  
			  System.out.println("Enter subscription End date");
			  String subscriptionEndDate =scanner.nextLine();
			  
			  Gym gym = new Gym(name, age, height, weight, subscriptionStartDate, subscriptionEndDate);
			  ar.add(gym);
			 
			}
			System.out.println("Customer List ");
			 for (Gym gym : ar) {
				 System.out.println(gym);
				
			}
		}
		  
		}
	
				
	

}
