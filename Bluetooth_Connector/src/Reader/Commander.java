package Reader;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Commander implements Runnable {

	SerialReader reader;
	
	public Commander(SerialReader reader){
		
		this.reader = reader;
	}
	@Override
	public void run() {
		
		  @SuppressWarnings("resource")
	   	  Scanner in = new Scanner(System.in);
		  
		  while(true){
	      System.out.println("Enter command:");
	      try {

	      String s = in.nextLine();	
	      reader.print(s);
	      
	      Thread.sleep(500);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch(NoSuchElementException e1){
			System.out.println("Unable to input string");
			
		}
		  }		
	}

}
