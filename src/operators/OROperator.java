package operators;

public class OROperator implements Operator {
	private Operator operand1, operand2;
	
	public OROperator(Operator _operand1, Operator _operand2){
		operand1 = _operand1;
		operand2 = _operand2;
	}
	
	@Override
	public double operate() {
		return Math.max(operand1.operate(), operand2.operate());
	}
	
	static{
		try {
			OperatorFactory.register("or",
					OROperator.class.getConstructor(Operator.class,
							Operator.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
