package Main;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import Shared.Data;
import Storage.Database;

public class Display implements Observer {
	
	DescriptiveStatistics stats;
	DecimalFormat df = new DecimalFormat("#.###");
	Database db;
	int nbr;
	int nbrSample = 1000;
	
	public Display(){
		
		stats = new DescriptiveStatistics();
		try {
			this.db = Database.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		String input = (String) arg;

		List<Data> list = db.getData(nbrSample);
		
		stats.clear();
		
		for( int i = 0; i < list.size(); i++) {
			
			stats.addValue( Integer.valueOf(list.get(i).getValue()) );
			
		}

		double mean = stats.getMean();
		double std = stats.getStandardDeviation();
		double scew = stats.getSkewness();
		double kurt = stats.getKurtosis();
		
		nbr++;
		
		System.out.println(nbr + " Value: " + input + " Mean: " + df.format(mean) + " Std: " + df.format(std) 
				+ " Skewness: " + df.format(scew) + " Kurtosis: " + df.format(kurt));
	}
 
}
