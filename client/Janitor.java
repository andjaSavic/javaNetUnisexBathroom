package singleBathroom;

public class Janitor extends Thread {
	
	public int id;
	public static int running_id3 = 0;
	
	private SingleBathroomNet bath;
	
	
	public Janitor(SingleBathroomNet bath) {
		id=running_id3++;
		this.bath = bath;
	}
	
	@Override
	public void run() {
		
		
		bath.enterJanitor();
		
		try {
			Thread.sleep((int)(Math.random()*1000));
		} catch(InterruptedException e){
			throw new RuntimeException(e);
		}
		bath.exitJanitor();
		
		
	}

}
