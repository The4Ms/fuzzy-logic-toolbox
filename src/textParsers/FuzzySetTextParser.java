package textParsers;
import fuzzySets.FuzzySet;
import java.util.Scanner;

public interface FuzzySetTextParser {
	public abstract FuzzySet parseFuzzySet(Scanner scanner);
}
