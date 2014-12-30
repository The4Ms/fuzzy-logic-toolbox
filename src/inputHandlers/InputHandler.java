package inputHandlers;
import inferenceUtilities.InferenceEngine;

import java.io.InputStream;
import java.util.HashMap;

public interface InputHandler {
	public abstract InferenceEngine takeInput(InputStream inputStream,
			HashMap<String, Double> inputValues);
}
