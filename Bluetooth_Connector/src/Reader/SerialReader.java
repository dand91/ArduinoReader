package Reader;

import java.util.Observable;
import java.util.Scanner;
import java.util.regex.Pattern;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 * @author nossredan
 * 
 * Class for making sure that future readers will be observable and runnable, as well as making 
 * sure that the COM port is closed properly when needed, outside of the class.
 */
public abstract class SerialReader extends Observable implements SerialPortEventListener, Runnable{
	
	protected StringBuilder sb = new StringBuilder();
	protected SerialPort serialPort;
	protected String currentPort; 
	
	public SerialReader() throws SerialPortException {
		
		
		String[] portNames = SerialPortList.getPortNames("/dev/", Pattern.compile("tty."));
		
		for(int i = 0; i < portNames.length; i++){
			
            System.out.println( i + ".       " + portNames[i]);
            
        }
		
		  @SuppressWarnings("resource")
		  Scanner in = new Scanner(System.in);
		  System.out.println("Enter portnumber: ");
	      int portNbr = Integer.valueOf(in.nextLine());	
		  currentPort = portNames[portNbr];
		  initiatePort();
		  System.out.println("RLSD - Receive Line Signal Detect "  + serialPort.isRLSD());

	}
	
	public void initiatePort() throws SerialPortException{
		
		  serialPort = new SerialPort(currentPort);
		  serialPort.openPort();
		  serialPort.setParams(SerialPort.BAUDRATE_9600, 8, 1, 0);
		  serialPort.addEventListener(this);
		  
	}
	
	/**
	 * Method for adding to the stream of information currently at COM port. 
	 * stream is represented by a String builder sb. 
	 */
	@Override
	public void serialEvent(SerialPortEvent arg0) {
		
		try {
			if(arg0.isRXCHAR()){
			sb.append(serialPort.readString());
			}
		} catch (SerialPortException e) {
				
			e.printStackTrace();
			closePort();
		}
	}
	
	/**
	 * Write string to active serial port.
	 */
	public void print(String s){
		
		try {
			serialPort.writeString(s);
		} catch (SerialPortException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Closes active serial port.
	 */
	public void closePort() {

		try {
			System.out.println("Closing serial port");
			serialPort.closePort();
		} catch (SerialPortException e) {
			e.printStackTrace();
		}

	}	
}
