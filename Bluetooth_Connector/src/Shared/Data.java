package Shared;

public class Data {

	String value;
	String date;
	
	public Data(String value, String date){
		
		this.value = value;
		this.date = date;
	}
	
	public String getValue(){
		
		return value;
	}
	public String getDate(){
		
		return date;
	}
}