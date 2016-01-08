package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.*;

public class CypherBreaker {
	private static final int MAX_QUEUE_SIZE = 100;
	private BlockingQueue<Resultable> queue;
	private String cypherText;
	private Resultable result = new Result("", 0, -500.00);
	private TextScorer ts;
	
	public CypherBreaker(String cypherText, TextScorer ts){ // passing in textscorer object as it contain the map in its constructor
		queue = new ArrayBlockingQueue<Resultable>(MAX_QUEUE_SIZE);
		this.cypherText = cypherText;
		this.ts = ts;
		init();
	}
	
	public void init(){
		//start a load of producers
		for(int i = 2; i <= cypherText.length()/2; i++){
			new Thread(new Decryptor(queue, cypherText, i, ts)).start();
		}
		
		new Thread(new Runnable(){
			public void run(){
				
				while(!queue.isEmpty()){
					try {
						
						Resultable r = queue.take();
						System.out.println("size of queue : " + queue.size());
						System.out.println("object taken"); //print size of queue to check is working
						
						if(Counter.count == (cypherText.length()/2)){
							r = new PoisonResult("", 0, -500.00); //create a poison result if count is same as size of text/2
							queue.put(r); //add poisoned result
							System.out.println("Added poison");
						}
						if(r instanceof PoisonResult){ // if r is poisoned object return
							getHighScore(); //get the highest score
							return;
						}
						//do something here check for poisoned object
						
						Counter.increment(); //increment counter each time something is taken of the queue
						System.out.println("count: " + Counter.count);
						Thread.sleep(500); // sleep to let other processes execute
						
						if(result.getScore() < r.getScore()){
							result = r; // checks if score or result is smaller than score of r off the queue
							System.out.println("New highscore: " + result.getScore() + " for key: " + result.getKey());
						} //if yes then over write the score to keep the highest scoring object
						
					} catch (InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		}).start(); //start the thread
	}
	
	public void getHighScore(){ //method to print out the score , plain text and key
		System.out.println("\nHighest Score: " + result.getScore() + " \nPlain Text: " + result.getPlainText() + " \nKey: "
				+ result.getKey());
	}
}