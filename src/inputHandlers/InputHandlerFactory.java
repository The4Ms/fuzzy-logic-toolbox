package inputHandlers;
import java.util.HashMap;

public class InputHandlerFactory {
	private static HashMap<String,InputHandler> inputHandlerMap = new HashMap<String, InputHandler>();
	
	private InputHandlerFactory() {}
	
	public static void register(String inputHandlerID,
			InputHandler inputHandler){
		inputHandlerMap.put(inputHandlerID, inputHandler);
	}
	
	public static InputHandler create(String inputHandlerID){
		return inputHandlerMap.get(inputHandlerID);
	}
}
