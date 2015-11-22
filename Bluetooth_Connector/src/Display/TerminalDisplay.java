package Display;


public class TerminalDisplay extends Display {
	
	public void exec(){
		
			System.out.println("Value: " + value + " Mean: " + df.format(mean) + " Std: " + df.format(std)
					+ " Kurt: " + df.format(kurt) + " Scew " +  df.format(scew));
	}

}
