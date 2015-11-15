package Terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Terminal implements Runnable {
	 
	String[] cmd;
	
	public Terminal(String[] cmd) {
		
		this.cmd = cmd;
		
	}
	
	public void run(){
		
		String s = null;
		
		try {
			
			Process p = Runtime.getRuntime().exec(cmd);			
			
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
 
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}

			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
	}
}
