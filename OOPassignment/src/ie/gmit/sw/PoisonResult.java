package ie.gmit.sw;

public class PoisonResult extends Result implements Resultable{
	
	public PoisonResult(String plainText, int key, double score){ 
		//poison result which extends result added as the last obj into the blocking queue to make sure it finishes
		super(plainText,key,score); // constructor like results constructor hence call to super();
	}
}
