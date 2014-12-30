package fuzzySets;
import java.util.ArrayList;

import generalUtilities.PolynomialFunction;

public class TrapeziumFuzzySet implements FuzzySet {
	private ArrayList<PolynomialFunction> functions;
	private double centroid;
	
	public TrapeziumFuzzySet(double pointsX[]) {		
		functions = new ArrayList<PolynomialFunction>();
		double pointsY[] = {0,1,1,0}, tempCoefficients[];
		for(int i=0;i<3;++i){
			if(pointsX[i] == pointsX[i+1])
				continue;
			
			if(pointsY[i] == pointsY[i+1]){
				tempCoefficients = new double[1];
				tempCoefficients[0] = pointsY[i];
			}
			else{
				tempCoefficients = new double[2];
				tempCoefficients[1] = ((pointsY[i] - pointsY[i+1])/(pointsX[i] - pointsX[i+1]));
				tempCoefficients[0] = pointsY[i] - (tempCoefficients[1]*pointsX[i]);
			}
			
			functions.add(new PolynomialFunction(tempCoefficients,pointsX[i], pointsX[i+1]));
		}
		
		double base = pointsX[3] - pointsX[0];
		double top = pointsX[2] - pointsX[1];
		double c = pointsX[1] - pointsX[0];
		centroid = (((2*top*c)+(top*top)+(c*base)+(top*base)+(base*base))/(3*(top+base))) + pointsX[0];
	}
	
	@Override
	public double getMembershipCertainty(double val){
		for(int i=0;i<functions.size();++i){
			if(functions.get(i).isInDomain(val))
				return functions.get(i).getY(val);
		}
		
		return 0.0;
	}
	
	@Override
	public void setCentroid(double val) {
		centroid = val;
	}
	
	@Override
	public double getCentroid(){
		return centroid;
	}
}
