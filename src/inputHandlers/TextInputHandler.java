package inputHandlers;
import inferenceUtilities.FuzzyVariable;
import inferenceUtilities.InferenceEngine;
import inferenceUtilities.Rule;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import textParsers.RuleTextParser;
import textParsers.VariableTextParser;

public class TextInputHandler implements InputHandler {

	@Override
	public InferenceEngine takeInput(InputStream inputStream,
			HashMap<String, Double> inputValues) {
		VariableTextParser variableTextParser = new VariableTextParser();
		RuleTextParser ruleTextParser = new RuleTextParser();
		HashMap<String, FuzzyVariable> variables = new HashMap<String, FuzzyVariable>();
		HashMap<String, ArrayList<Rule> > variableDependencies = new HashMap<String, ArrayList<Rule>>();
		ArrayList<Rule> rules = new ArrayList<Rule>();
		
		Scanner scanner =  new Scanner(inputStream);
		
		int variablesNum = scanner.nextInt();
		for(int i=0;i<variablesNum;++i){
			FuzzyVariable tempVariable = variableTextParser.parseVariable(scanner);
			variables.put(tempVariable.getID(), tempVariable);
			variableDependencies.put(tempVariable.getID(), new ArrayList<Rule>());
		}
		
		int rulesNum = scanner.nextInt();
		scanner.nextLine();
		for(int i=0;i<rulesNum;++i){
			Rule tempRule = ruleTextParser.parseRule(scanner,variables,
												variableDependencies);
			rules.add(tempRule);
		}
		
		int inputValuesNum = scanner.nextInt();
		for(int i=0;i<inputValuesNum;++i){
			String variableID = scanner.next();
			double inputValue = scanner.nextDouble();
			inputValues.put(variableID, inputValue);
		}
		
		return new InferenceEngine(rules, variables);
	}
	
	static{
		InputHandlerFactory.register("text", new TextInputHandler());
	}
}
