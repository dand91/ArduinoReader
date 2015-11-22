package Display;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import Shared.Data;
import Storage.Database;

public abstract class Display implements Observer {
	
	DescriptiveStatistics stats;
	DecimalFormat df = new DecimalFormat("#.###");
	Database db;
	int nbrSample = 1000;
	
	
	String value;
	double mean;
	double std;
	double scew;
	double kurt;
	
	public Display(){
		
		stats = new DescriptiveStatistics();
		
		try {
			this.db = Database.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void update(Observable o, Object arg){
		
		value = (String) arg;

		
		List<Data> list = db.getData(nbrSample);
		
		stats.clear();
		
		for( int i = 0; i < list.size(); i++) {
			
			stats.addValue( Integer.valueOf(list.get(i).getValue()) );
			
		}

		 mean = stats.getMean();
		 std = stats.getStandardDeviation();
		 scew = stats.getSkewness();
		 kurt = stats.getKurtosis();
				
		 exec();
	}
	
	public abstract void exec();
}
