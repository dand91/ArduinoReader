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
		
		initiateBluetoothRestart();
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
