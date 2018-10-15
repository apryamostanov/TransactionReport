package com.sample.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.sample.model.Result;
import com.sample.model.TranxData;

public class CSVReader {
	
	public Result calculator(String csvFile, String fromDateStr, String toDateStr, String merchant) throws ParseException {
		FileInputStream inputStream = null;
		Scanner sc = null;
		Result result;
		Map<String, TranxData> hashMap = new HashMap<String, TranxData>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date fromDate = sdf.parse(fromDateStr);
		Date toDate = sdf.parse(toDateStr);
		double totalAmt = 0;
		
		try {
		    inputStream = new FileInputStream(csvFile);
		    sc = new Scanner(inputStream, "UTF-8");
		    //Ignore the first line
		    sc.nextLine();
		    while (sc.hasNextLine()) {
		    	//paste each line to TranxData obj
		    	TranxData data = parseLine(sc.nextLine());
		        //compare with the criteria
		    	if((data.getDate().compareTo(fromDate) >= 0) && (data.getDate().compareTo(toDate) <= 0) 
		    			&& data.getMerchant().equals(merchant) && data.isPaymentTranx()) {
		    		hashMap.put(data.getId(), data);
		    		totalAmt += data.getAmount();
		    	} else if(!data.isPaymentTranx()) {
		    		//check if a transaction has a reversing transaction
			    	String relationTranx = data.getRelationTranx();
			    	if(relationTranx!="" && hashMap.containsKey(relationTranx)) {
			    		hashMap.remove(relationTranx);
			    		totalAmt -= data.getAmount();
			    	}
		    	}
		    }
		    // note that Scanner suppresses exceptions
		    if (sc.ioException() != null) {
		        throw sc.ioException();
		    }
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
		    if (inputStream != null) {
		        try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    if (sc != null) {
		        sc.close();
		    }
		}
		
		//Number of Transaction
		int noOfTranx = hashMap.size();
		//calculator Average Transaction Value 
		double averageValue = 0;
		if(noOfTranx != 0) {
			DecimalFormat df = new DecimalFormat(".###");
			averageValue= Double.parseDouble(df.format(totalAmt/noOfTranx));
		}
		
		result = new Result(noOfTranx, averageValue);
		return result;
	}

	private TranxData parseLine(String nextLine) {
		// TODO Auto-generated method stub
		String[] transaction = nextLine.split(",", -1);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String id = transaction[0].trim();
		Date date = null;
		try {
			date = sdf.parse(transaction[1].trim());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double amount = Double.parseDouble(transaction[2]);
		String merchant = transaction[3].trim();
		boolean paymentTranx = transaction[4].trim().equals("PAYMENT");
		String relationTranx = transaction[5].trim();
		
		TranxData data = new TranxData(id, date, amount, merchant, paymentTranx, relationTranx);
		return data;
	}
	
}
