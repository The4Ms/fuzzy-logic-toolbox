package fuzzySets;
import java.util.ArrayList;

import generalUtilities.PolynomialFunction;

public class TriangleFuzzySet implements FuzzySet {
	private ArrayList<PolynomialFunction> functions;
	private double centroid;

	public TriangleFuzzySet(double pointsX[]) {
		functions = new ArrayList<PolynomialFunction>();
		double pointsY[] = {0,1,0}, tempCoefficients[];
		for(int i=0;i<2;++i){
			if(pointsX[i] == pointsX[i+1])
				continue;
			
			tempCoefficients = new double[2];
			tempCoefficients[1] = ((pointsY[i] - pointsY[i+1])/(pointsX[i] - pointsX[i+1]));
			tempCoefficients[0] = pointsY[i] - (tempCoefficients[1]*pointsX[i]);
			functions.add(new PolynomialFunction(tempCoefficients,pointsX[i], pointsX[i+1]));
		}
		
		centroid = (pointsX[0]+pointsX[1]+pointsX[2])/3;
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
