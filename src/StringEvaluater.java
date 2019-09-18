
/**
 * Created by bablu on 18/7/17.
 */


public class StringEvaluater 
{
	private variableAndSymbolHandler proceedFurther=new variableAndSymbolHandler();
	
	public Double evaluate(String eqn, boolean degree, int noOfVars, char varChar[], double varValues[] ) throws Exception
	{
		return proceedFurther.solve(eqn, degree, noOfVars, varChar, varValues);
	}
	
	public Double evaluate(String eqn, boolean degree) throws Exception
	{
		return proceedFurther.solve(eqn, degree);
	}
	
}
