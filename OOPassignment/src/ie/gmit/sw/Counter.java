package ie.gmit.sw;

public class Counter {
	public static volatile int count = 2;
	private static Object lock = new Object();
	
	public static void increment(){ //counter class to count how many threads have finished their work
		synchronized(lock){
			count++; //synch lock on counter to make sure threads do not interleave and count incorrect
		}	
	}

}
