
/**
 * Created by bablu on 18/7/17.
 */


public class variableAndSymbolHandler 
{
	private Additionals a=new Additionals();
	
	public double solve(String eqn, boolean degree, int noOfVars, char varChar[], double varValues[] ) throws Exception   //chars[] has crresponding values at varValues 	
	{
		eqn=replaceVarWithValues(eqn,noOfVars,varChar,varValues);		
				
		return solve(eqn,degree);                        		
	}
	
	public double solve(String eqn,boolean degree) throws Exception
	{
		eqn=replaceSymbolsWithValues(eqn);
		FunctionHandler f=new FunctionHandler();
		eqn=f.solve(eqn,degree);                                       //if false then input in radian, else input in degree
		return Double.parseDouble(eqn);
	}		
	
	private String replaceVarWithValues(String eqn, int noOfVars, char varChar[], double varValues[] ) throws Exception
	{		
		int index=-1;
		for(char i:a.invalidVarSet())			
			for(char j:varChar)
				if(i==j)
					throw new Exception("Variable taken from invalid variables set");
		for(index=0;index<noOfVars;index++)
		{
			char var=varChar[index];
			Double varValue=varValues[index];
			eqn=AuxillaryReplaceVarWithValues(eqn,var,varValue);
		}
		return eqn;
	}
	
	private String AuxillaryReplaceVarWithValues(String eqn, char var,Double Value) throws Exception 
	{
		
		while(eqn.contains(String.valueOf(var)))
		{
			int indexOfVar=eqn.indexOf(var);
			boolean isSymbol=false;
			try{
				isSymbol=whetherSymbolOrNot(eqn, indexOfVar-1);
			}catch(IndexOutOfBoundsException e){
				isSymbol=true;
			}
			if( isSymbol )
				eqn=a.replacePartOfString(indexOfVar, indexOfVar,"("+ String.valueOf(Value)+")", eqn);
			else
				eqn=a.replacePartOfString(indexOfVar, indexOfVar, "*"+"("+ String.valueOf(Value)+")", eqn);
		}		
		return eqn;
	}
	
	
	private String replaceSymbolsWithValues(String eqn) throws Exception 
	{		
		while(eqn.contains("π"))
		{
			int indexOfPi=eqn.indexOf('π');
			boolean isSymbol=false;
			try{
				isSymbol=whetherSymbolOrNot(eqn, indexOfPi-1);
			}catch(IndexOutOfBoundsException e){
				isSymbol=true;
			}
			if( isSymbol)
				eqn=a.replacePartOfString(indexOfPi, indexOfPi, String.valueOf(Math.PI), eqn);
			else
				eqn=a.replacePartOfString(indexOfPi, indexOfPi, "*"+String.valueOf(Math.PI), eqn);
		}
			                     // developer want to add exponential e but don't know how symbol to use
		return eqn;
	}
	
	private static boolean whetherSymbolOrNot(String data,int Index) throws IndexOutOfBoundsException
	{		
		char val=data.charAt(Index);
		
		return (val=='+' || val=='-' || val=='*' || val=='/' || val=='√' || val=='(' || val==')' || Character.toLowerCase(val)=='c' || Character.toLowerCase(val)=='p' || Character.toLowerCase(val)=='g'|| Character.toLowerCase(val)=='n');                  //g for loG and n for lN
	}
	
	/*private String replacePartOfString(int startingIndex,int endingIndex,String dataToReplace,String data)
	{
	    String newData;
	    String leftPart=data.substring(0,startingIndex);
	    String rightPart=data.substring(endingIndex+1);
	    newData=leftPart+dataToReplace+rightPart;
	    return newData;
	}*/
}
