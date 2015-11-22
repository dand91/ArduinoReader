package Command;

import Reader.SerialReader;

public class ReaderCommander extends CommandExecutor{
	
	SerialReader reader;
	
	public ReaderCommander(SerialReader reader){
		
		this.reader = reader;
		
	}
	public void interexec(String s){
		
		reader.print(s);

	}	
}
