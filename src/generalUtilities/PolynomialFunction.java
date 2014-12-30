package generalUtilities;

public class PolynomialFunction implements Function {
	private int degree;
	private double coefficients[];
	private double domainL, domainH;
	
	public PolynomialFunction(){
		degree = 0;
		coefficients = new double[1];
		coefficients[0] = 0.0;
		domainL = -(1<<63);
		domainH = (1<<63);
	}
	
	public PolynomialFunction(double _cofficients[], double _domainL, double _domainH){
		degree = _cofficients.length - 1;
		coefficients = _cofficients;
		domainL = _domainL;
		domainH = _domainH;
	}
	
	@Override
	public boolean isInDomain(double x){
		return ((domainL<=x)&&(x<=domainH));
	}
	
	@Override
	public double getY(double x){
		double y = 0.0;
		for(int i=0;i<=degree;++i)
			y += (coefficients[i]*Math.pow(x, i));
		
		return y;
	}
}
