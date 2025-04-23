package singleBathroom;

public class Man extends Thread {
	
	public int id;
	public static int running_id = 0;
	
	//SingleBathroomProblem bath;
	private SingleBathroomNet bath;
	
	
	public Man(SingleBathroomNet bath) {
		id=running_id++;
		this.bath = bath;
	}
	
	@Override
	public void run() {
		
		
		bath.enterMan(id);
		
		try {
			Thread.sleep((int)(Math.random()*1000));
		} catch(InterruptedException e){
			throw new RuntimeException(e);
		}
		bath.exitMan(id);
		
	}

}
