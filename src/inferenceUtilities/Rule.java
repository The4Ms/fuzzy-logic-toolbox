package inferenceUtilities;
import java.util.ArrayList;

import operators.Operator;

public class Rule {
	private ArrayList<Rule> priorRules;
	private Operator rootOperator;
	private FuzzyVariable outputVariable;
	private String fuzzySetID;
	private boolean isActivated = false;
	private int id;
	
	private static int GLOBAL_ID = 0;
	
	public Rule(ArrayList<Rule> _priorRules, Operator _rootOperator, 
			FuzzyVariable _outputVariable, String _fuzzySetID){
		
		priorRules = _priorRules;
		rootOperator = _rootOperator;
		outputVariable = _outputVariable;
		fuzzySetID = _fuzzySetID;
		isActivated = false;
		id = GLOBAL_ID++;
	}
	
	public void activate(){
		if(isActivated) return;
		isActivated = true;
		
		for(int i=0;i<priorRules.size();++i)
			priorRules.get(i).activate();
		
		outputVariable.addMembershipCertainty(fuzzySetID,
												rootOperator.operate());
	}
	
	public void reset(){
		isActivated = false;
	}
	
	@Override
	public boolean equals(Object r) {
		return (this.id==((Rule)r).id);
	}
}
