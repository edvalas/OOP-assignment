package ie.gmit.sw;

import java.io.ObjectInputStream.GetField;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Runner{
	
	public static void main(String[] args){
		RailFence rf = new RailFence(); //create railfence object
		FileParser fp = new FileParser(); //create fileparser object
		
		Scanner console = new Scanner(System.in); //create console object to read in from user
		Map<String, Double> map = new ConcurrentHashMap<String, Double>(); // create a new map to store the keys and values
		
		String plainText = ""; //string to take in message from user
		String plainUpper = "";// user input converted to upper case as map keys are upper case
		String cypherText = ""; // result string of the encryption
		//String decryptedText; // string after decryption
		
		try {
			map = fp.parse("4grams.txt"); //try to parse the file into map otherwise throw exception
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TextScorer ts = new TextScorer(map); // create a new textscorer and pass it in the map
		System.out.print("Enter the plain text to encrypt: ");
		plainText = console.nextLine(); //enter string to encrypt from console
		
		plainUpper = plainText.toUpperCase(); //convert to uppercase
		//System.out.println("\n" + plainUpper);
		
		cypherText = rf.encrypt(plainUpper, 5); // encrypt the plain text
		//System.out.println(cypherText);
		CypherBreaker cb = new CypherBreaker(cypherText, ts); //create cypherBreaker object
	}
}