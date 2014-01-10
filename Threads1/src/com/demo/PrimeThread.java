package com.demo;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PrimeThread implements Runnable {
	
	int start;
	int stop;
	String file;
	boolean lead = false;
	
	public PrimeThread(int startValue, int stopValue, String fileName, boolean isLead){
		
		start = startValue;
		stop = stopValue;
		file = fileName;
		lead = isLead;
	}
	public void run(){
		
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for (int i = start; i < stop; i+=2){
			
			if(isPrime(i)){
				primes.add(i);
			}
			if(lead && (i+1)%100 == 0){
				System.out.println(i + " / " + stop);
			}
		}
		
		try{
			PrintWriter print = new PrintWriter(new File(file));
			for(int i = 0; i < primes.size(); i++){
				print.println(primes.get(i));
			}
			print.close();
		} catch (Exception e){
			
		}
	}

	public static boolean isPrime(int val){
		
		if (val == 2){
			return true;
		}
		for (int n = 3; n < val; n+=2){
			if(val%n == 0){
				return false;
			}
		}
		return true;
	}
}
