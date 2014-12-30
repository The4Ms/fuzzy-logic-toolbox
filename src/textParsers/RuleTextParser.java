package textParsers;
import inferenceUtilities.FuzzyVariable;
import inferenceUtilities.Rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import operators.Operand;
import operators.Operator;
import operators.OperatorFactory;

public class RuleTextParser {
	
	public Rule parseRule(Scanner scanner,
			HashMap<String, FuzzyVariable> variables,
			HashMap<String, ArrayList<Rule> > variableDependencies){
		String ruleBody = scanner.nextLine();
		int idx = ruleBody.indexOf("then");
		String ruleCondition = ruleBody.substring(3,idx - 1);
		String ruleAction = ruleBody.substring(idx + 5);
		
		idx = ruleAction.indexOf(' ');
		ArrayList<Rule> priorRules = new ArrayList<Rule>();
		String outputVariableID = ruleAction.substring(0, idx);
		String fuzzySetID = ruleAction.substring(idx + 1);
		Operator rootOperator = parseRuleBody(ruleCondition, priorRules,
									variables, variableDependencies);
		
		FuzzyVariable outputVariable = variables.get(outputVariableID);
		if(outputVariable == null){
			outputVariable = new FuzzyVariable(outputVariableID);
			variables.put(outputVariableID, outputVariable);
			variableDependencies.put(outputVariableID, new ArrayList<Rule>());
		}
		
		Rule rule = new Rule(priorRules, rootOperator,
							outputVariable, fuzzySetID);
		variableDependencies.get(outputVariableID).add(rule);
		return rule;
	}
	
	private Operator parseRuleBody(String infixExp, ArrayList<Rule> priorRules,
			HashMap<String, FuzzyVariable> variables,
			HashMap<String, ArrayList<Rule> > variableDependencies){
		Set<Rule> priorRulesSet = new HashSet<Rule>();
		Set<String> operators = OperatorFactory.getOperatorsID();
		String postfixExp = toPostfixExp(infixExp,operators);
		Stack<Operator> stack = new Stack<Operator>();
		Scanner stringScanner = new Scanner(postfixExp);
		
		while(stringScanner.hasNext()) {
			String item = stringScanner.next();
			boolean isOperator = operators.contains(item);
			if(isOperator){
				Operator operand1 = stack.pop();
				Operator operand2 = stack.pop();
				Operator operator = null;
				try{
					operator = OperatorFactory.create(item, operand1, operand2);
				}
				catch(Exception e){}
				stack.push(operator);
			}
			else{
				FuzzyVariable variable = variables.get(item);
				String fuzzySetID = stringScanner.next();
				if(variable == null){
					variable = new FuzzyVariable(fuzzySetID);
					variables.put(item, variable);
					variableDependencies.put(item, new ArrayList<Rule>());
				}
				
				stack.push(new Operand(variable, fuzzySetID));
				priorRulesSet.addAll(variableDependencies.get(item));
			}
		}
		
		stringScanner.close();
		for(Rule rule : priorRulesSet)
			priorRules.add(rule);
		return stack.pop();
	}
	
	private String toPostfixExp(String infixExp, Set<String> operators){
		String postfixExp = "";
		Stack<String> stack = new Stack<String>();
		Scanner stringScanner = new Scanner(infixExp);
		while(stringScanner.hasNext()) {
			String item = stringScanner.next();
			boolean isOperator = operators.contains(item);
			boolean isLParenthes = item.equals("(");
			boolean isRParenthes = item.equals(")");
			if(isOperator)
				stack.push(item);
			else if(isLParenthes)
				stack.push(item);
			else if(isRParenthes){
				String tempItem = stack.pop();
				while(!tempItem.equals("(")){
					postfixExp += (tempItem + ' ');
					tempItem = stack.pop();
				}
			}
			else
				postfixExp += (item + ' ' + stringScanner.next() + ' ');
		}
		
		while(!stack.isEmpty())
			postfixExp += (stack.pop() + ' ');
		
		stringScanner.close();
		return postfixExp;
	}
}
