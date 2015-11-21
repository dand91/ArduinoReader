package Main;

import jssc.SerialPortException;
import Reader.BluetoothReader;
import Reader.Commander;
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
		
		initiatePython();
		initiateDatabase();
		initiateReader();
		initiateCommander(); 
		initiateDisplay();
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

		Display ds = new Display();
		reader.addObserver(ds);

	}
	
	public void initiatePython(){
		
		String[] python = {"python" , "/Users/Andersson/Google Drive/Java_saved_files/Bluetooth_workspace/Bluetooth_Connector/python/pyprint.py"};
		Terminal term = new Terminal(python);
		Thread pythonThread = new Thread(term);
		pythonThread.start();
		
	}

	public static void main(String[] args) {

		MainClass mc = null;
		
		 try {
				 mc = new MainClass();
				
			 } catch (Exception ex) {
				 
				 System.out.println("Execution error, shutting down.");
				 ex.getStackTrace();
				 System.exit(0);
			 }
		 
		 Runtime.getRuntime().addShutdownHook(new Shutdown(mc));
	}
}
		

class Shutdown extends Thread{
	
	MainClass mc;

	public Shutdown(MainClass mc){
		
		this.mc = mc;
	}
	
	public void start(){
		
		mc.reader.closePort();
		mc.db.closeConnection();
		System.out.println("Shutting down");

	}
}
