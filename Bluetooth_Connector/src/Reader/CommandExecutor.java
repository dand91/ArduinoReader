package Reader;

import Storage.Database;

public abstract class CommandExecutor {
	
	Database db;
	
	

	public CommandExecutor(){
					
			try {
				db = Database.getInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	public void exec(String s){
		
     interexec(s);	
   	 db.addCommand(s);

	}
	
	public abstract void interexec(String s);

}
