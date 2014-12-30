package operators;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Set;

public class OperatorFactory {
	private static HashMap<String,Constructor<?> > operatorMap = new HashMap<String, Constructor<?>>();
	
	private OperatorFactory(){}
	
	public static void register(String operatorID,
			Constructor<?> operatorConstructor){
		operatorMap.put(operatorID, operatorConstructor);
	}
	
	public static Operator create(String operatorID,
			Object... constructorArgs) throws Exception {
		return (Operator)operatorMap.get(operatorID).newInstance(constructorArgs);
	}
	
	public static Set<String> getOperatorsID(){
		return operatorMap.keySet();
	}
}
