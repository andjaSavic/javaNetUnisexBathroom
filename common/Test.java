package singleBathroom;

public class Test {

	public static void main(String[] args) {
		int m = 5;
		
		
		int N = 4; 
		
		SingleBathroomNet sbn = new SingleBathroomNet("localhost", 5555);
		
		
		
		Thread mt[] = new Thread[m];
		Thread wt[] = new Thread[m];
		Thread ct[] = new Thread[m];
		
		Thread niz[] = new Thread[16];
		
		for(int i=0;i<16;i++) {
			if(i<5) {
				niz[i] = new Man(sbn);
			}else if(i>=5 && i<10) {
				niz[i] = new Woman(sbn);
				
			}else if(i>=10 && i<15){
				niz[i] = new Child(sbn);
			}else {
				niz[i] = new Janitor(sbn);
			}
		}
		for(int i=0;i<16;i++) {
			niz[i].start();
		}
		
		
		
	}
}
