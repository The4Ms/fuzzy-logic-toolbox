package textParsers;
import java.util.Scanner;

import fuzzySets.FuzzySet;
import fuzzySets.TriangleFuzzySet;

public class TriangleFuzzySetTextParser implements FuzzySetTextParser {

	@Override
	public FuzzySet parseFuzzySet(Scanner scanner) {
		double pointsX[] = new double[3];
		pointsX[0] = scanner.nextDouble();
		pointsX[1] = scanner.nextDouble();
		pointsX[2] = scanner.nextDouble();
		return new TriangleFuzzySet(pointsX);
	}
	
	static{
		FuzzySetTextParserFactory.register("triangle",
				new TriangleFuzzySetTextParser());
	}
}
