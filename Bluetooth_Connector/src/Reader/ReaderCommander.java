package Reader;

public class ReaderCommander {
	
	SerialReader reader;
	
	public ReaderCommander(SerialReader reader){
		
		this.reader = reader;
		
	}
	public void exec(String s){
		
		reader.print(s);
		
	}

}
