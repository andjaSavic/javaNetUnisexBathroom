package singleBathroom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SingleBathroomProblem implements UBInterface{

	public static final int capacity = 10;
	private int count = 0;
	private int cCnt = 0;
	int ticket = 0;
	
	int mCnt=0;
	int wCnt=0;
	int jCnt=0;
	int next=0;
	
	ReentrantLock lock = new ReentrantLock();
	Condition waitingForChildren = lock.newCondition();
	Condition queue = lock.newCondition();
	
	@Override
	public void enterMan(int id) {
		lock.lock();
		try {
			int p =0;
			int myTicket = ticket++;
			while (myTicket!=next || wCnt>0 || jCnt>0 || count == capacity) {
				if(p==0) {
					System.out.println("Muskarac "+ id+ "  ceka da udje...");
				}
                p++;
                queue.awaitUninterruptibly();
            }
            
            System.out.println("Muskarac "+ id + " usao...");
            ++count;
            ++mCnt;
            if(lock.hasWaiters(waitingForChildren)) {
            	waitingForChildren.signalAll();
            }
            this.next++;
		} finally {
			lock.unlock();
		}
	}
	@Override
	public void enterWoman(int id) {
		lock.lock();
		try {
			int p=0;
			int myTicket = ticket++;
			while (myTicket!=next || mCnt>0 || jCnt>0 || count == capacity) {
				if(p==0) {
					System.out.println("Zena "+ id+ "  ceka da udje...");
				}
				p++;
                queue.awaitUninterruptibly();
            }
            
            System.out.println("Zena "+ id + "usla...");
            ++count;
            ++wCnt;
            if(lock.hasWaiters(waitingForChildren)) {
            	waitingForChildren.signalAll();
            }
            this.next++;
		} finally {
			lock.unlock();
		}
	}
	@Override
	public void enterChild(int id) {
		lock.lock();
		try {
			int p=0;
			int myTicket = ticket++;
			while(count==capacity || count==0 || jCnt>0 || myTicket!=next || (mCnt+wCnt ==0)) {
				/*
				 * Dete ne moze da udje ako je toalet pun, ako u njemu nema nikoga, ako je u njemu domar ili ako ima drugih osoba ispred toaleta ili
				 * ima osoba koja je unutra i ceka da deca izadju
				 * */
				if(p==0) {
					System.out.println("Dete " + id + " ceka da udje...");
				}
				p++;
                queue.awaitUninterruptibly();
			}
			System.out.println("Dete "+ id+ " uslo...");
			++count;
			++cCnt;
			next++;
			
		} finally {
			lock.unlock();
		}
	}
	@Override
	public void enterJanitor() {
		lock.lock();
		try {
			int myTicket = ticket++;
			int p=0;
			while((mCnt+cCnt+wCnt)>0 || myTicket!=next ) {
				if(p==0) {
					System.out.println("Janitor ceka...");
				}
				p++;
				queue.awaitUninterruptibly();
				
				

			}
			++count;
			++jCnt;
			++next;
			System.out.println("Janitor usao ... ");
			
		} finally {
			lock.unlock();
		}
		
	}
	@Override
	public void exitMan(int id) {
		lock.lock();
		try {
			while(count == cCnt + 1 && cCnt!=0) {
				waitingForChildren.awaitUninterruptibly();
			}
			--count;
			--mCnt;
			System.out.println("Muskarac "+ id + " izasao");
			if(lock.hasWaiters(queue)) {
				queue.signalAll();
			}
		} finally {
			lock.unlock();
		}
	
	}
	@Override
	public void exitWoman(int id) {
		lock.lock();
		try {
			while(count == cCnt + 1 && cCnt!=0) {
				waitingForChildren.awaitUninterruptibly();
			}
			--count;
			--wCnt;
			System.out.println("Zena "+ id + " izasla");
			if(lock.hasWaiters(queue)) {
				queue.signalAll();
			}
		} finally {
			lock.unlock();
		}
	
	}
	@Override
	public void exitJanitor() {
		lock.lock();
		try {
			--count;
			--jCnt;
			System.out.println("Janitor izasao  ...");
			
			if(lock.hasWaiters(queue)) {
				queue.signalAll();
			}
		} finally {
			lock.unlock();
		}
	
	}
	@Override
	public void exitChild(int id) {
		lock.lock();
		try {
			--cCnt;
			--count;
			System.out.println("Dete "+ id + " izaslo  ...");
			if(lock.hasWaiters(waitingForChildren) && cCnt==0) {
				//javi osobi koja ceka na izlazak deteeta da su sva deca izasla
				waitingForChildren.signalAll();
			}
			if(lock.hasWaiters(queue)) {
				queue.signalAll();
			}
		} finally {
			lock.unlock();
		}
	
	}
	
}
