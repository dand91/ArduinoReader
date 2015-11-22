package Main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import Shared.Data;
import Storage.Database;

public class Display implements Observer {
	
	DescriptiveStatistics stats;
	DecimalFormat df = new DecimalFormat("#.###");
	Database db;
	int nbrSample = 1000;
	
	JTextField valueTextField;
	JTextField meanTextField;
	JTextField stdTextField;
	JTextField kurtTextField;
	JTextField scewTextField;
	
	public Display(){
		
		stats = new DescriptiveStatistics();
		
		try {
			this.db = Database.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		initiateJFrame();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		String value = (String) arg;

		List<Data> list = db.getData(nbrSample);
		
		stats.clear();
		
		for( int i = 0; i < list.size(); i++) {
			
			stats.addValue( Integer.valueOf(list.get(i).getValue()) );
			
		}

		double mean = stats.getMean();
		double std = stats.getStandardDeviation();
		double scew = stats.getSkewness();
		double kurt = stats.getKurtosis();
				
		valueTextField.setText(value);
		meanTextField.setText(df.format(mean));
		stdTextField.setText(df.format(std));
		kurtTextField.setText(df.format(kurt));
		scewTextField.setText(df.format(scew));

	}
	
	public void initiateJFrame(){
		
		JFrame frame = new JFrame("FrameDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			
			JPanel valuePanel = new JPanel();
			valuePanel.setLayout(new BoxLayout(valuePanel, BoxLayout.X_AXIS));
			JLabel valueLabel = new JLabel("Value");
			valueLabel.setPreferredSize(new Dimension(160,30));
			valueTextField = new JTextField();
			valueTextField.setPreferredSize(new Dimension(160,30));
			valuePanel.add(valueLabel);
			valuePanel.add(valueTextField);
		
			JPanel meanPanel = new JPanel();
			meanPanel.setLayout(new BoxLayout(meanPanel, BoxLayout.X_AXIS));
			JLabel meanLabel = new JLabel("Mean");
			meanLabel.setPreferredSize(new Dimension(160,30));
			meanTextField = new JTextField();
			meanTextField.setPreferredSize(new Dimension(160,30));
			meanPanel.add(meanLabel);
			meanPanel.add(meanTextField);
			
			JPanel stdPanel = new JPanel();
			stdPanel.setLayout(new BoxLayout(stdPanel, BoxLayout.X_AXIS));
			JLabel stdLabel = new JLabel("Standard Deviation");
			stdLabel.setPreferredSize(new Dimension(160,30));
			stdTextField = new JTextField();
			stdTextField.setPreferredSize(new Dimension(160,30));
			stdPanel.add(stdLabel);
			stdPanel.add(stdTextField);
			
			JPanel scewPanel = new JPanel();
			scewPanel.setLayout(new BoxLayout(scewPanel, BoxLayout.X_AXIS));
			JLabel scewLabel = new JLabel("Scewness");
			scewLabel.setPreferredSize(new Dimension(160,30));
			scewTextField = new JTextField();
			scewTextField.setPreferredSize(new Dimension(160,30));
			scewPanel.add(scewLabel);
			scewPanel.add(scewTextField);
			
			JPanel kurtPanel = new JPanel();
			kurtPanel.setLayout(new BoxLayout(kurtPanel, BoxLayout.X_AXIS));
			JLabel kurtLabel = new JLabel("Kurtosis");
			kurtLabel.setPreferredSize(new Dimension(160,30));
			kurtTextField = new JTextField();
			kurtTextField.setPreferredSize(new Dimension(160,30));
			kurtPanel.add(kurtLabel);
			kurtPanel.add(kurtTextField);

			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			mainPanel.add(valuePanel);
			mainPanel.add(meanPanel);
			mainPanel.add(stdPanel);
			mainPanel.add(scewPanel);
			mainPanel.add(kurtPanel);

		
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
 
}
