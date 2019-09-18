
import static java.lang.System.out;
import java.math.*;
public class RegularFalsi {
	
	StringEvaluater eval=new StringEvaluater();
	private String eqn;
	private Boolean hasDegree;
	
	public void showResult(String eqn,boolean hasDegree,int correctuptoDigits) throws Exception
	{
		this.eqn=eqn;
		this.hasDegree=hasDegree;
		
		for(int rootA: findRoots( ))
			if(rootA!=0)			
				out.println("\n\nRoot between "+rootA+" & "+(rootA+1)+" is: "+applyRegularFalsi(eqn,rootA,rootA+1,correctuptoDigits)+"...(correct upto "+correctuptoDigits+" digits).");			
		
	}

	private double applyRegularFalsi(String eqn, double x0, double x1,int correctuptoDigits) throws Exception {
		double x2,fx0,fx1,fx2,ifx=0.0;
		long tempi=0L,temp=0L;
		int counter=0;
		
		out.printf("\n\n\t_________________________________________________________________________________________________________\n");
		out.printf("\t| sr.|   x0\t\t\t|   x1\t\t\t|   x2\t\t\t|   fx2\t\t\t|\n");
		out.printf("\t_________________________________________________________________________________________________________\n");
		
		do {			
			fx0=f(x0);
			fx1=f(x1);
		
			x2=( x0*fx1-x1*fx0 )/( fx1-fx0 );		
			fx2=f(x2);
			
			//out.println("\t|"+(++counter)+".|"+x0+"\t\t\t|"+x1+"\t\t\t|"+x2+"\t\t\t|"+fx2+"\t\t\t|");
			out.printf("\t|%3d.|%12f\t\t|%12f\t\t|%12f\t\t|%12f\t\t|\n",(++counter),x0,x1,x2,fx2);
			
			if(fx2<0)
				x0=x2;
			else if(fx2==0)
			{
				out.println("\t\tRoot is:"+fx2);
				return x2;
			}
			else
				x1=x2;
			
			temp=(long) (fx2*Math.pow(10,correctuptoDigits+1));
			tempi=(long) (ifx*Math.pow(10,correctuptoDigits+1));			
			ifx=fx2;			
		}while(temp!=tempi);
		return x2;		
	}
	
	private double f(double value) throws Exception
	{
		return eval.evaluate(eqn, hasDegree,1,new char[] {'x'},new double[] {value});
	}

	private int[] findRoots() throws Exception {
		
		int a=-500, b=+500,counter=-1;			// raise difference between gives more results, but more slow
		double result1,result2;
		int[] roots=new int[50];
				
		for(int i=a;i<=b;i++)
		{
			result1 = eval.evaluate(eqn, hasDegree,1,new char[] {'x'},new double[] {i});
			result2 = eval.evaluate(eqn, hasDegree,1,new char[] {'x'},new double[] {i+1});
			if(result1*result2<=0)
				roots[++counter]=i;
		}
		return roots;
	}
}
