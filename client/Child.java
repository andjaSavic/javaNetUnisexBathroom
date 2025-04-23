package singleBathroom;

public class Child extends Thread {
	
	public int id;
	public static int running_id2 = 0;
	
	private SingleBathroomNet bath;
	
	
	public Child(SingleBathroomNet bath) {
		id = running_id2++;
		this.bath = bath;
	}
	
	@Override
	public void run() {
		
		bath.enterChild(id);
		
		try {
			Thread.sleep((int)(Math.random()*1000));
		} catch(InterruptedException e){
			throw new RuntimeException(e);
		}
		bath.exitChild(id);
		
	}

}
