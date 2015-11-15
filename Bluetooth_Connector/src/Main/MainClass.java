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

	private BluetoothReader reader;
	private Thread threadReader;
	private Thread threadCommander;
	private Database db;
	private Commander cmd;

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
		
		String[] python = {"python" , "/Users/Andersson/Desktop/pyPrint.py"};
		Terminal term = new Terminal(python);
		Thread pythonThread = new Thread(term);
		pythonThread.start();
		
	}

	public static void main(String[] args) {

		new MainClass();

	}
}
		

