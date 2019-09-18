/**
 * Created by bablu on 18/7/17.
 */

import java.text.DecimalFormat;


public class Additionals {

	public String replacePartOfString(int startingIndex,int endingIndex,String dataToReplace,String data) throws Exception
	{
		String newData="";
		String rightPart="";
		String leftPart=data.substring(0,startingIndex);
		if(data.substring(endingIndex+1).length()>0)
			rightPart=data.substring(endingIndex+1);
		newData=leftPart+dataToReplace+rightPart;
		return newData;
	}
	
	public String calculatorFormat(Double data) throws Exception
	{		
		DecimalFormat f=new DecimalFormat() ;
		if(data>=10000000 || data<=-10000000)
		{	f = new DecimalFormat("##.####E0");
			return f.format(data);
		}else{
			f = new DecimalFormat("0.####");
			return f.format(data);
		}		
	}
	
	public char[] invalidVarSet()
	{
		int[] frequency=new int[26];		
		Name name=new Name();
		String funcName="";
		funcName+=name.sin();		
		funcName+=name.cos();
		funcName+=name.tan();
		funcName+=name.cosec();
		funcName+=name.sec();
		funcName+=name.cot();
		funcName+=name.ln();
		funcName+=name.log();
		for(char i:funcName.toCharArray())		
			++frequency[i-97];
		
		char[] charSet=new char[26];
		int index=-1;
		for(int i=0;i<26;++i)
			if(frequency[i]!=0)
				charSet[++index]=(char) (i+97);
		return charSet;
	}
}

