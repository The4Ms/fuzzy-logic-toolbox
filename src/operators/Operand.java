package operators;
import inferenceUtilities.FuzzyVariable;

public class Operand implements Operator {
	private FuzzyVariable variable;
	private String fuzzySetID;
	
	public Operand(FuzzyVariable _variable, String _fuzzySetID){
		variable = _variable;
		fuzzySetID = _fuzzySetID;
	}
	
	@Override
	public double operate() {
		return variable.getMembershipCertainty(fuzzySetID);
	}
}
