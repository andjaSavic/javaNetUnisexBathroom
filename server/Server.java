package singleBathroom;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	
	int port;
	int cap;
	
	
	public Server(int port, int cap) {
		this.port = port;
		this.cap = cap;
	}
	
	private static void run(int port, int CAP) {
		UBInterface ub = new SingleBathroomProblem();
		ExecutorService pool = Executors.newCachedThreadPool();
		try(ServerSocket server = new ServerSocket(port)) {
			while(true) {
				Socket client = server.accept();
				pool.execute(new RequestHandler(client, ub)); 
				
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		run(5555,8);
	}
}
