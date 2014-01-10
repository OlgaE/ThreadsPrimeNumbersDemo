package com.demo;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
	
	public static int threadCount = 2;
	public static int stop = 100000;
	
	public static void main(String[] args) {
		
		System.out.println("Threads..");
		
		ArrayList<Thread> threads = new ArrayList<Thread>();
		int increment = stop / threadCount;
		int starting = 1;
		for(int i = 0; i < threadCount; i++){
			if(!((i+1) == threadCount)){
				threads.add(new Thread(new PrimeThread(starting, starting+increment, i + ".txt", false)));
				starting += increment;
			} else {
				threads.add(new Thread(new PrimeThread(starting, starting+increment, i + ".txt", true)));
			}
			
		}
		
		for(int i = 0; i < threads.size(); i++){
			threads.get(i).start();
		}
		
		for(int i = 0; i < threads.size(); i++){
			try{
				threads.get(i).join();
				System.out.println("Thread" + i + "finished.");
				
			} catch (Exception e) {}
		}
		
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for(int i = 0; i < threads.size(); i++){
			
			try{
				File file = new File(i + ".txt");
				Scanner scanner = new Scanner(file);
				while (scanner.hasNextInt()){
					primes.add(scanner.nextInt());
				}
				scanner.close();
				file.delete();
			} catch(Exception e){}
		}
		
		try {
			PrintWriter print = new PrintWriter(new File("primes.txt"));
			for (int i = 0; i < primes.size(); i++){
				print.println(primes.get(i));
			}
			print.close();
		} catch (Exception e) {	}
	}

}
