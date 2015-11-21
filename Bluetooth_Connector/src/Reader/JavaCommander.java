package Reader;

import Storage.Database;

public class JavaCommander {
	
	Database db;
	
	public JavaCommander(){
		
		try {
			
			Database.initiate();
			db = Database.getInstance();
			if (db.openConnection()) {
				System.out.println("Database started");
			} else {
				System.out.println("Unable to start database");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	
	public void exec(String s){
		
	      String[] cmdList = s.split(":");

	      if(cmdList[1].equals("clear")){
	    	  
	    	 db.clearDB();
	    	  
	      }
		
	}

}
