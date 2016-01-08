package ie.gmit.sw;

import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.*;

public class FileParser {
	public  Map<String, Double> parse(String file) throws Exception{
	    Map<String, Double> temp = new ConcurrentHashMap<String, Double>();
	    
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		
		String next; //this was null originally
		
		while((next = br.readLine())!= null){
				// ADD EACH LINE TO THE PARSE
				String [] stuff = next.split(" "); // remove spaces from the line
				
				String b = Arrays.toString(stuff); // make a string from the array
				
				String s = b.substring(1, 5); // 4 letter string
				String x = b.substring(6, b.length()-1); // rest of the string the numbers
				double d = Double.parseDouble(x); // convert the number string into a double
				
				temp.put(s,d); //add the string s and double d to the map
		}
		return temp;
	}
}