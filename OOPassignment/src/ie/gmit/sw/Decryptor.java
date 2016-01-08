package ie.gmit.sw;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Decryptor implements Runnable {
	private BlockingQueue<Resultable> queue;
	private String cypherText;
	private int key;
	private TextScorer ts;
	
	
	public Decryptor(BlockingQueue<Resultable> queue, String cypherText, int key, TextScorer ts) {
		super();
		this.queue = queue;
		this.cypherText = cypherText;
		this.key = key;
		this.ts = ts; //add textscorer object in constructor , a way to pass the map around the classes from runner
	}

	public void run(){
		RailFence rf = new RailFence(); //create railfence obj

		String plainText = rf.decrypt(cypherText, key); //decrypt the cypher text using a given key
		//get the score
		double score = ts.getScore(plainText); // get the score of text
		
		Resultable r = new Result(plainText, key, score);//Create a result object with text,key,score
		
		try{
			queue.put(r); //put r on the queue
			System.out.println("object added");			
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}