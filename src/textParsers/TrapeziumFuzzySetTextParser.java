package textParsers;
import fuzzySets.FuzzySet;
import fuzzySets.TrapeziumFuzzySet;

import java.util.Scanner;

public class TrapeziumFuzzySetTextParser implements FuzzySetTextParser {
	
	@Override
	public FuzzySet parseFuzzySet(Scanner scanner){
		double pointsX[] = new double[4];
		pointsX[0] = scanner.nextDouble();
		pointsX[1] = scanner.nextDouble();
		pointsX[2] = scanner.nextDouble();
		pointsX[3] = scanner.nextDouble();
		return new TrapeziumFuzzySet(pointsX);
	}
	
	static{
		FuzzySetTextParserFactory.register("trapezium",
				new TrapeziumFuzzySetTextParser());
	}
}
