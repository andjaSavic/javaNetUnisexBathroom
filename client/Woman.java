package singleBathroom;

public class Woman extends Thread {
	
	public int id;
	public static int running_id1 = 0;
	
	private SingleBathroomNet bath;
	
	
	public Woman(SingleBathroomNet bath) {
		id=running_id1++;
		this.bath = bath;
	}
	
	@Override
	public void run() {
		
		bath.enterWoman(id);
		
		try {
			Thread.sleep((int)(Math.random()*1000));
		} catch(InterruptedException e){
			throw new RuntimeException(e);
		}
		bath.exitWoman(id);
	}

}
