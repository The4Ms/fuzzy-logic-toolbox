package inferenceUtilities;
import java.util.HashMap;

import fuzzySets.FuzzySet;

public class FuzzyVariable {
	private String id;
	private HashMap<String, FuzzySet> fuzzySets;
	private HashMap<String, Double> membershipCertainty;
	
	public FuzzyVariable(String _id){
		id = _id;
		fuzzySets = null;
		membershipCertainty = new HashMap<String, Double>();
	}
	
	public FuzzyVariable(String _id,
			HashMap<String, FuzzySet> _fuzzySets){
		id = _id;
		fuzzySets = _fuzzySets;
		membershipCertainty = new HashMap<String, Double>();
	}

	public void setID(String _id) {
		id = _id;
	}
	
	public String getID() {
		return id;
	}
	
	public void fuzzify(double val) {
		if(fuzzySets == null) return;
		
		membershipCertainty.clear();
		for(String id : fuzzySets.keySet())
			membershipCertainty.put(id,fuzzySets.get(id).getMembershipCertainty(val));
	}

	public double defuzzify() {
		if(fuzzySets == null) return Double.NaN;
		
		double num1 = 0.0, num2 = 0.0;
		Double temp;
		for(String id : fuzzySets.keySet()){
			temp = membershipCertainty.get(id);
			if(temp == null) continue;
			num1 += temp*fuzzySets.get(id).getCentroid();
			num2 += temp;
		}
		
		return num1/num2;
	}
	
	public void setMembershipCertainty(String fuzzySetID, double val){
		membershipCertainty.put(fuzzySetID,val);
	}
	
	public double getMembershipCertainty(String fuzzySetID) {
		Double val = membershipCertainty.get(fuzzySetID);
		return ((val != null)?val:0.0);
	}
	
	public void addMembershipCertainty(String fuzzySetID, double val) {
		Double oldVal = membershipCertainty.get(fuzzySetID);
		Double newVal = ((oldVal != null)? (oldVal+val):val);
		membershipCertainty.put(fuzzySetID, newVal);
	}
	
	public void reset(){
		membershipCertainty.clear();
	}

	public void print() {
		if(fuzzySets == null) return;
		
		for(String id : fuzzySets.keySet())
			System.out.println(id + "  " + membershipCertainty.get(id));
	}
}
