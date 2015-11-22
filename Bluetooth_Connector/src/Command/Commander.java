package Command;

import java.util.NoSuchElementException;
import java.util.Scanner;

import Reader.SerialReader;

public class Commander implements Runnable {

	JavaCommander jcmd;
	ReaderCommander rcmd;
	
	public Commander(SerialReader reader){
		
		jcmd = new JavaCommander();
		rcmd = new ReaderCommander(reader);
		
	}
	@Override
	public void run() {
		
		  @SuppressWarnings("resource")
	   	  Scanner in = new Scanner(System.in);
		  
		  while(true){
			  
	      System.out.println("Enter command:");
	      
	      try {

	      String s = in.nextLine();	
	      
	      String[] cmdList = s.split(":");
	      
	      if(cmdList[0].equals("CMD") || cmdList[0].equals("PWD")){
	    	  
	    	  rcmd.exec(s);
	    	  
	      }else if(cmdList[0].equals("CMDJ") || cmdList[0].equals("PWDJ")){
	    	  
	    	  jcmd.exec(s);
	    	  
	      }else if(cmdList[0].equals("help") || cmdList[0].equals("?")){
	    	  
	    	  System.out.println("PWD:___");
	    	  System.out.println("CMD:stop");
	    	  System.out.println("CMD:rate:___");
	    	  System.out.println("CMDJ:clear");
	    	  System.out.println("CMDJ:exit");

	      }else{
	    	  
	    	  System.out.println("Incorrect command");
	      }
	      
	      Thread.sleep(500);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch(NoSuchElementException e1){
			System.out.println("Unable to input string");
			
		}
		  }		
	}

}
