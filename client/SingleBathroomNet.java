package singleBathroom;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SingleBathroomNet implements UBInterface {

	private String host;
	private int port;
	
	public SingleBathroomNet(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}
	
	@Override
	public void enterMan(int id) {
		try(Socket client = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(client.getInputStream());
				) {
			System.out.println("Muskarac " + id+" hoce da udje...");

			out.writeObject("enterMan" + "#" + id);
			out.flush();
			String s = (String)in.readObject();
			System.out.println(s);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enterWoman(int id) {
		try(Socket client = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {
			System.out.println("Zena " + id+" hoce da udje...");

			out.writeObject("enterWoman" + "#" + id);
			out.flush();
			String s = (String)in.readObject();
			System.out.println(s);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enterChild(int id) {
		try(Socket client = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {
			System.out.println("Dete " + id+" hoce da udje...");
			out.writeObject("enterChild" + "#" + id);
			out.flush();
			String s = (String)in.readObject();
			System.out.println(s);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enterJanitor() {
		try(Socket client = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {
			System.out.println("Janitor hoce da udje...");
			out.writeObject("enterJanitor" + "#" + -1);
			out.flush();
			String s = (String)in.readObject();
			System.out.println(s);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitMan(int id) {
		try(Socket client = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {
			System.out.println("Muskarac " + id+" hoce da izadje...");

			out.writeObject("exitMan" + "#" + id);
			out.flush();
			String s = (String)in.readObject();
			System.out.println(s);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitWoman(int id) {
		try(Socket client = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {
			System.out.println("Zena " + id+" hoce da izadje...");
			out.writeObject("exitWoman" + "#" + id);
			out.flush();
			String s = (String)in.readObject();
			System.out.println(s);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitChild(int id) {
		try(Socket client = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {
			System.out.println("Dete " + id+" hoce da izadje...");
			out.writeObject("exitChild" + "#" + id);
			out.flush();
			String s = (String)in.readObject();
			System.out.println(s);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exitJanitor() {
		try(Socket client = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {
			System.out.println("Janitor hoce da izadje...");
			out.writeObject("exitJanitor" + "#" + -1);
			out.flush();
			String s = (String)in.readObject();
			System.out.println(s);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
