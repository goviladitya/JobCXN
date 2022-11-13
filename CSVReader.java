package com.example.jobcnx;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CSVReader {

	public static void main(String[] args) {
		
		// Variables to keep track of user information as it is inputed
		int minSalary;
		boolean degree;
		boolean masters;
		String name;
		String email;
		String field;
		String degreeString;
		String mastersString;
		Scanner scan = new Scanner(System.in);
		
		// Scanning user input and storing information
		System.out.println("Enter your name: ");
		name = scan.nextLine();
		
		System.out.println("\nEnter your email: ");
		email = scan.nextLine();
		
		System.out.println("\nEnter your field: ");
		field = scan.nextLine();
		
		System.out.println("\nDo you have an undergraduate degree (yes/no): ");
		degreeString = scan.nextLine();
		if (degreeString.equals("yes")) {
			degree = true;
		}
		else {
			degree = false;
		}
		
		System.out.println("\nDo you have a masters degree (yes/no): ");
		mastersString = scan.nextLine();
		if (mastersString.equals("yes")) {
			masters = true;
		}
		else {
			masters = false;
		}

		System.out.println("\nEnter your desired minimum salary: ");
		minSalary = scan.nextInt();
		if (minSalary < 1000) {
			minSalary = 1000;
		}
				
		// Constructing a new user using the information inputed and stored by the user
		User current = new User(minSalary, degree, name, email, field, masters);
		System.out.println(current);
		System.out.println();
		
		
		// Scanning the file
		String path = "C:\\Users\\sudhe\\Downloads\\NYC_Jobs.csv";
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if (Integer.parseInt(values[2]) > current.getMinSalary()) {
					if (values[3].contains(field)) {
						System.out.println(values[0]);
						Job toScreen = new Job(degree, values[0], Integer.parseInt(values[2]));
					} else if (values[3].contains(field.substring(0, field.length()-1))) {
						System.out.println(values[0]);
						Job toScreen = new Job(degree, values[0], Integer.parseInt(values[2]));
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}