package Main;

import java.util.Observable;
import java.util.Observer;

import Terminal.Terminal;


public class Display implements Observer {

	
	@Override
	public void update(Observable o, Object arg) {
		
		String input = (String) arg;
		System.out.println("Logged data: " + input);
		
	}
 
}
