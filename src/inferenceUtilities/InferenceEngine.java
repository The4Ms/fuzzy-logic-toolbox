package inferenceUtilities;
import java.util.ArrayList;
import java.util.HashMap;

public class InferenceEngine {
	private ArrayList<Rule> inferenceRules;
	private HashMap<String, FuzzyVariable> variables;
	
	public InferenceEngine(ArrayList<Rule> _inferenceRules,
			HashMap<String, FuzzyVariable> _variables){
		
		inferenceRules = _inferenceRules;
		variables = _variables;
	}
	
	public void setRules(ArrayList<Rule> _inferenceRules){
		inferenceRules = _inferenceRules;
	}
	
	public void run(HashMap<String, Double> inputValues){
		for(String id : inputValues.keySet())
			variables.get(id).fuzzify(inputValues.get(id));
		
		for(int i=0;i<inferenceRules.size();++i)
			inferenceRules.get(i).activate();
	}
	
	public double getPredictedValue(String variableID){
		return variables.get(variableID).defuzzify();
	}
	
	public void reset(){
		for(int i=0;i<inferenceRules.size();++i)
			inferenceRules.get(i).reset();
		
		for(String id : variables.keySet())
			variables.get(id).reset();
	}
}
