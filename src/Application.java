import inferenceUtilities.InferenceEngine;
import inputHandlers.TextInputHandler;

import java.io.FileInputStream;
import java.util.HashMap;

import operators.ANDOperator;
import operators.OROperator;
import textParsers.TrapeziumFuzzySetTextParser;
import textParsers.TriangleFuzzySetTextParser;

public class Application {
	public static void main(String[] args) throws Exception {
		loadClasses();
		TextInputHandler textInputHandler = new TextInputHandler();
		FileInputStream inputStream = new FileInputStream("sampleInput.txt");
		HashMap<String, Double> inputValues = new HashMap<String, Double>();
		InferenceEngine inferenceEngine = textInputHandler.takeInput(inputStream, inputValues);
		inferenceEngine.run(inputValues);
		System.out.println(inferenceEngine.getPredictedValue("firePosition"));
	}
	
	public static void loadClasses(){
		new TextInputHandler();
		new ANDOperator(null, null);
		new OROperator(null, null);
		new TrapeziumFuzzySetTextParser();
		new TriangleFuzzySetTextParser();
	}
}
