package Reader;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import Storage.Database;
import jssc.SerialPortException;

public class BluetoothReader extends SerialReader{

	
	private StringTokenizer st;
	private Database db;
	
	public BluetoothReader() throws SerialPortException  {
		
		try {
			db = Database.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void run(){

			try{		        

				while(true){
					
					soundInfo("S");
					soundInfo("C");
					Thread.sleep(100);
										
				}
					
			}catch (Exception e) {
	        	
				e.printStackTrace();
				
			}finally{ 
				
				closePort();
				
				}	
			}  	
		
	
	private void soundInfo(String s){
		
		
		String string = sb.toString();
		String newString = "";

			
		if(string.contains(s) && string.contains( s + "*")){
								
			int start = string.indexOf(s);
			int end = string.indexOf( s + "*");
									
			newString = (String) sb.substring(start, end + 2);
			sb.delete(start, end + 2 );
			
			try{
				
			st = new StringTokenizer(newString);
			
			st.nextToken();
			String info = st.nextToken();
			st.nextToken();
			
			
			if(s.equals("S")){
								
			notifyObservers(info);
			setChanged();	
			
			try{
				
				if(db.getDatabase().isConnected()){
					
					db.getDatabase().addData(info);

				}else{
					
					System.out.println("Unable to logg data, database not connected");

				}
				
			}catch(Exception e){
				
				System.out.println("Unable to logg data, database error");
				
			}
			
			}else if(s.equals("C")){
				
				info = info.replaceAll("_", " ");
				System.out.println(info);
				
				try{
					
					if(db.getDatabase().isConnected()){
						
						db.getDatabase().addComment(info);

					}else{
						
						System.out.println("Unable to logg comment, database not connected");

					}
					
				}catch(Exception e){
					
					System.out.println("Unable to logg comment, database error");
					
				}

			}
									
			}catch(NoSuchElementException e){	
				
				System.out.println("Retreived corrupted string");
				
				}	 
		}	
	}
}


