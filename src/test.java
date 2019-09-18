/**
 * Created by bablu on 18/7/17.
 */


public class test {

	public static void main(String[] args) throws Exception 
	{
		String eqn="5x^2+5x-2";
		StringEvaluater e=new StringEvaluater();
		try{
			System.out.println(e.evaluate("x^2-10x+21", true, 1, new char[]{'x'}, new double[]{3}));
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}		
	}
}

