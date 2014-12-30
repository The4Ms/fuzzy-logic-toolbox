package textParsers;
import java.util.HashMap;

public class FuzzySetTextParserFactory {
	private static HashMap<String,FuzzySetTextParser> parserMap = new HashMap<String, FuzzySetTextParser>();
	
	public static void register(String parserID,
			FuzzySetTextParser fuzzySetTextParser){
		parserMap.put(parserID, fuzzySetTextParser);
	}
	
	public static FuzzySetTextParser create(String parserID){
		return parserMap.get(parserID); 
	}
}
