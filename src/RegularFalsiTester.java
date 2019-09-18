

import static java.lang.System.out;
import java.util.Scanner;

public class RegularFalsiTester {
				
	public static void main(String args[]) throws Exception
	{
		Scanner scan=new Scanner(System.in);		
		String eqn,temp;
		Boolean hasDegree=true;                                          //deg:t//rad:f		
		RegularFalsi rf=new RegularFalsi();
		out.println("Enter the equation(in terms of x):");
		eqn=scan.next();
		do{
			out.println("Is your equation contain any trignometric function like sin(), cos() etc?(y/n):");
			temp=scan.next();
			}while(!temp.equals("n") && !temp.equals("y"));
				
			if(temp.equals("y"))
			{
				out.println("Is your equation manages degree or radian?(false:rad/true:deg):");
				hasDegree=scan.nextBoolean();
			}
			int correctuptoDigits;
			out.println("Correct upto no. of digits after decimal pt:");
			correctuptoDigits=scan.nextInt();
			rf.showResult(eqn, hasDegree,correctuptoDigits);         
		
		
		
		}

		
}
