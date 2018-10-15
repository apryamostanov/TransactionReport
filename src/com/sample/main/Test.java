package com.sample.main;

import java.util.Scanner;

import com.sample.dao.CSVReader;
import com.sample.model.Result;

public class Test {
	public static void main(String[] args) {
		try {
			String csvFile = "src/input/input.csv";
			Scanner scan = new Scanner(System.in);
			System.out.println("Input From Date:");
			String fromDate = scan.nextLine();
			System.out.println("Input To Date:");
			String toDate = scan.nextLine();
			System.out.println("Input Merchant:");
			String merchant = scan.nextLine();
			
			CSVReader csvReader = new CSVReader();
			Result result = csvReader.calculator(csvFile, fromDate, toDate, merchant);
			System.out.println(result.toString());
		} catch(Exception e) {
			System.out.println("Error: " + e.toString());
		}
	}
}
