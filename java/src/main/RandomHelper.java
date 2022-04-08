package main;
import java.util.Random;

public class RandomHelper {
	private static final Random random = new Random();
	private static final Object syncLock = new Object();
	public static int randomNumber(int min, int max){
	    synchronized(syncLock){
	    	try{
	        return random.nextInt(max - min + 1) + min;
	    	} catch(Exception ex) {
	    		return 0;
	    	}
	    }
	}
}
