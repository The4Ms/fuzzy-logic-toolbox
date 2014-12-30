package operators;

public class ANDOperator implements Operator {
	private Operator operand1, operand2;
	
	public ANDOperator(Operator _operand1, Operator _operand2){
		operand1 = _operand1;
		operand2 = _operand2;
	}
	
	@Override
	public double operate() {
		return Math.min(operand1.operate(), operand2.operate());
	}
	
	static{
		try {
			OperatorFactory.register("and", 
					ANDOperator.class.getConstructor(Operator.class,
							Operator.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
