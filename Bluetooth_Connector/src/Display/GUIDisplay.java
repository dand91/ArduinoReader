package Display;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GUIDisplay extends Display {
	
	JTextField valueTextField;
	JTextField meanTextField;
	JTextField stdTextField;
	JTextField kurtTextField;
	JTextField scewTextField;
	
	public GUIDisplay(){
		
		initiateJFrame();
		
	}
	
	public void exec(){
		
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
