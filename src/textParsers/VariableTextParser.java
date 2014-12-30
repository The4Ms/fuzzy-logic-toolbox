package textParsers;
import fuzzySets.FuzzySet;
import inferenceUtilities.FuzzyVariable;

import java.util.HashMap;
import java.util.Scanner;

public class VariableTextParser {
	
	public FuzzyVariable parseVariable(Scanner scanner){
		FuzzySetTextParser fuzzySetTextParser = null;
		HashMap<String, FuzzySet> fuzzySets = new HashMap<String, FuzzySet>();
		
		String variableID = scanner.next();
		int fuzzySetsNum = scanner.nextInt();
		for(int i=0;i<fuzzySetsNum;++i){
			String fuzzySetID = scanner.next();
			String fuzzySetType = scanner.next();
			fuzzySetTextParser = FuzzySetTextParserFactory.create(fuzzySetType);
			FuzzySet tempFuzzySet = fuzzySetTextParser.parseFuzzySet(scanner);
			fuzzySets.put(fuzzySetID, tempFuzzySet);
		}
		
		return new FuzzyVariable(variableID, fuzzySets);
	}
}
