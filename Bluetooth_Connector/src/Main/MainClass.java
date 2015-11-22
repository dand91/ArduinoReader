package Main;

import java.util.Scanner;

import jssc.SerialPortException;
import Command.Commander;
import Display.GUIDisplay;
import Display.TerminalDisplay;
import Reader.BluetoothReader;
import Storage.Database;
import Terminal.Terminal;

/**
 * @author nossredan Frame for displaying program. Also initiate components and
 *         threads.
 */

public class MainClass {

	protected BluetoothReader reader;
	protected Thread threadReader;
	protected Thread threadCommander;
	protected Database db;
	protected Commander cmd;
	
	public MainClass() {
		
		initiateBluetoothRestart();
		initiatePython();
		initiateDatabase();
		initiateReader();
		initiateDisplay();
		initiateCommander(); 
	}

	public void initiateDatabase() {

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

	public void initiateReader() {

		try {
			reader = new BluetoothReader();
		} catch (SerialPortException e) {

			System.out.println("Unable to start port");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		threadReader = new Thread(reader);
		threadReader.start();
		reader.deleteObservers();
	}
	

	public void initiateCommander() {

		cmd = new Commander(reader);
		threadCommander = new Thread(cmd);
		threadCommander.start();
	}

	public void initiateDisplay() {

		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("Do you want do display in GUI or terminal? ");
		System.out.println("D - Display");
		System.out.println("T - Terminal");
		
	    String s = in.nextLine();	
	    
	    if(s.equals("D")){
	    	
		GUIDisplay ds = new GUIDisplay();
		reader.addObserver(ds);
		
	    }else if(s.equals("T")){
	    
	    TerminalDisplay ds = new TerminalDisplay();
	    reader.addObserver(ds);
	    	
	    }else{
	    	
	    	initiateDisplay();
	    }
	}
	
	public void initiatePython(){
		
		String[] python = {"python" , "/Users/Andersson/Google Drive/Java_saved_files/Bluetooth_workspace/Bluetooth_Connector/python/pyprint.py"};
		Terminal term = new Terminal(python);
		Thread pythonThread = new Thread(term);
		pythonThread.start();
		
	}
	public void initiateBluetoothRestart(){
		
		String[] restart = {"/Users/Andersson/Google Drive/Java_saved_files/Bluetooth_workspace/Bluetooth_Connector/bluetooth/bluetoothRestart"};
		Terminal term = new Terminal(restart);
		Thread restartThread = new Thread(term);
		restartThread.start();
		
		while(restartThread.isAlive()){
			
			try {

				Thread.sleep(100);
				
			} catch (InterruptedException e) {
				System.out.println("Main thread sleep error");
			}
		}
	}

	public static void main(String[] args) {

		MainClass mc = null;
		Runtime.getRuntime().addShutdownHook(new Shutdown(mc));

		
		 try {
			
				 mc = new MainClass();
				
			 } catch (Exception ex) {
				 
				 System.out.println("Execution error, shutting down.");
				 ex.getStackTrace();
				 System.exit(0);
			 }
		 
	}
}
		

class Shutdown extends Thread{
	
	MainClass mc;

	public Shutdown(MainClass mc){
		
		this.mc = mc;

	}
	
	public void start(){
		
		System.out.println("Shutting down");
		mc.reader.closePort();
		mc.db.closeConnection();

	}
}
