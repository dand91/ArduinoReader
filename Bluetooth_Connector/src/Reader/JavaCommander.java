package Reader;


public class JavaCommander extends CommandExecutor{
	
	public void interexec(String s){
		
	      String[] cmdList = s.split(":");

	      if(cmdList[1].equals("clear")){
	    	  
	    	 db.clearDB();
	    	  
	      }else if(cmdList[1].equals("exit")){
	    	  
	    	  System.exit(0);
	    	  
	      }else{
	    	  
	    	  System.out.println("Incorrect subcommand");
	      }
		
	}

}
