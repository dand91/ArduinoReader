package Main;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Display implements Observer {
	
	List<Integer> list = new ArrayList<Integer>();
	DescriptiveStatistics stats;
	DecimalFormat df = new DecimalFormat("#.###");
	int nbr;
	int nbrSample = 1000;
	
	public Display(){
		
		stats = new DescriptiveStatistics();

	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		String input = (String) arg;
		list.add(Integer.valueOf(input));
		
		if(list.size() == nbrSample){
			
			list.remove(0);
		}
		
		stats.clear();
		for( int i = 0; i < list.size(); i++) {
			stats.addValue(list.get(i));
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
