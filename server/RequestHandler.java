package singleBathroom;

import java.io.*;
import java.net.Socket;

public class RequestHandler extends Thread{
	Socket sock;
	UBInterface ub;
	
	
	public RequestHandler(Socket c, UBInterface ub) {
		this.sock = c;
		this.ub = ub;
	}
	
	@Override
	public void run() {
		
		try(Socket client = this.sock;
				ObjectInputStream in = new ObjectInputStream(client.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream())
){
			
			/*
			 * 
			 * System.out.println("Waiting for message from client...");
			String mess = (String)in.readObject();
			System.out.println("Received message: " + mess);
			*/
			String mess = (String)in.readObject();
			String data[] = mess.split("#");
			String op = data[0];
			
			int id = Integer.parseInt(data[1]);
			
			
			//System.out.println(op);
			//System.out.println(data[1]);
			
			switch(op) {
			case "enterMan":{
				ub.enterMan(id);
				out.writeObject("Muskarac " + id + " usao u WC");
				out.flush();
				break;
			}
			case "enterWoman":{
				ub.enterWoman(id);
				out.writeObject("Zena " + id + " usla u WC");
				out.flush();
				break;
			}
			case "enterChild":{
				ub.enterChild(id);
				out.writeObject("Dete " + id + " uslo ");
				out.flush();
				break;
			}
			case "enterJanitor":{
				ub.enterJanitor();
				out.writeObject("Janitor usao...");
				out.flush();
				break;
			}
			case "exitMan":{
				ub.exitMan(id);
				out.writeObject("Muskarac " + id + " izasao iz WC-a");
				out.flush();
				break;
			}
			case "exitWoman":{
				ub.exitWoman(id);
				out.writeObject("Zena " + id + " izasla iz WC-a");
				out.flush();
				break;
			}
			case "exitChild":{
				ub.exitChild(id);
				out.writeObject("Dete " + id + " izaslo iz WC-a");
				out.flush();
				break;
			}
			case "exitJanitor":{
				ub.exitJanitor();
				out.writeObject("Janitor " + id + " izasao iz WC-a");
				out.flush();
				break;
			}
			default:{
				throw new IllegalArgumentException("Unexpected value: " + op);
			}
			}
		} catch (IOException | ClassNotFoundException e) {
			
			e.printStackTrace();
			System.out.println("Konekcija prekinuta!");
		}
	}
	
}
