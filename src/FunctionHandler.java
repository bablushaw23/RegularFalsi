
/**
 * Created by bablu on 18/7/17.
 */


public class FunctionHandler
{
    public String solve(String data,boolean degree) throws Exception                               //degree true, radian false
    {
        data=data.toLowerCase();
        BracketMagnagement solveFurther=new BracketMagnagement();
        while(checkInsideFunction(data))
        {
            data=solveFunction(data,degree);
        }
        return solveFurther.start(data);
    }

    Name n=new Name();
    Additionals a=new Additionals();

    private String solveFunction(String data,boolean degree) throws Exception
    {
        Double answer=0d;
        String functionName=returnFirstFunc(data);
        int lastWordIndex=lastIndexOfGivenWord(data,functionName);
        int termLastIndex=returnTermLastIndex(data, lastWordIndex);
        String parameter=returnUnderPart(data, lastWordIndex, termLastIndex,degree);
        if(functionName.equals(n.sin()))
            answer=solveForSine(parameter,degree);  
        else if(functionName.equals(n.cosec()))
            answer=solveForCoSecent(parameter,degree);
        else if(functionName.equals(n.cos()))
            answer=solveForCoSine(parameter,degree);
        else if(functionName.equals(n.tan()))
            answer=solveForTangent(parameter,degree);
        
        else if(functionName.equals(n.sec()))
            answer=solveForSecent(parameter,degree);
        else if(functionName.equals(n.cot()))
            answer=solveForCoTangent(parameter,degree);
        else if(functionName.equals(n.log()))
            answer=solveForLog(parameter);
        else if(functionName.equals(n.ln()))
            answer=solveForLn(parameter);

        int startingIndex=lastWordIndex-functionName.length()+1;
        Additionals ad=new Additionals();
        data=ad.replacePartOfString(startingIndex, termLastIndex, String.valueOf(answer), data);
        return data;
    }



    private String returnUnderPart(String data,int lastWordIndex,int termLastIndex,boolean degree) throws Exception
    {
        char letter=data.charAt(lastWordIndex+1);
        String answer;
        if(letter=='(')
            answer=data.substring(lastWordIndex+2,termLastIndex);
        else
            answer=data.substring(lastWordIndex+1,termLastIndex+1);
        if(checkInsideFunction(answer))
            answer=solve(answer,degree);
        return answer;
    }

    @SuppressWarnings("unused")
	private enum enumFunc
    {
        sin,
        cos,
        tan,
        cosec,
        sec,
        cot
    }

    @SuppressWarnings("unused")
	private String nameOfFunction(int code) throws Exception
    {
        //if(code==enumFunc.sin)
        throw new Exception("Function not implemented as developer wants to use enum but dont know how to use");
        //return "sin";
    }

    private boolean checkInsideFunction(String data) throws Exception
    {
        Name name=new Name();
        return (data.contains(name.sin()) || data.contains(name.cos()) || data.contains(name.tan()) || data.contains(name.cosec()) || data.contains(name.sec()) || data.contains(name.cot()) || data.contains(name.ln()) || data.contains(name.log()) ) ;          //add function name if want to add
    }

    private String returnFirstFunc(String data) throws Exception
    {
    	int counter=-1;
        if(checkInsideFunction(data))
        {
            String dataPart="";
            for(char i:data.toCharArray())
            {
            	++counter;
                dataPart+=String.valueOf(i);
                if(dataPart.contains(n.sin()))
                    return n.sin();                
                else if(dataPart.contains(n.cos()) )
                {
                	try{                
                		if(data.charAt(counter+1)=='e' && data.charAt(counter+2)=='c')
                			return n.cosec();
                		}catch(NullPointerException e){;}                	
                		return n.cos();
                }
                else if(dataPart.contains(n.tan()))
                    return n.tan();                
                else if(dataPart.contains(n.sec()))
                    return n.sec();
                else if(dataPart.contains(n.cosec()))
                    return n.cosec();
                else if(dataPart.contains(n.cot()))
                    return n.cot();
                else if(dataPart.contains(n.log()))
                    return n.log();
                else if(dataPart.contains(n.ln()))
                    return n.ln();
            }
        }
        return "nil";
    }

    private int returnTermLastIndex(String data,int termLastIndex) throws Exception
    {
        char letter=data.charAt(termLastIndex+1);
        int returnIndex=-1;           // -1 kept for check, not redundent
        if(letter=='(')
        {
            int closingBracesIndex=findCorrespondingClosingBracses(data, termLastIndex+1);
            if(closingBracesIndex==-1)
                throw new Exception("internal error occured");
            else if(closingBracesIndex==0)
                returnIndex=	data.length()-1;
            else
                returnIndex=closingBracesIndex;
        }
        else{
            int index=termLastIndex+1;
            if(data.charAt(index)=='+' || data.charAt(index)=='-')
                index++;
            while(true)
            {
                if(index<data.length())
                {
                    letter=data.charAt(index++);

                    if(letter=='+' || letter=='-' || letter=='*' || letter=='/' || letter =='(')
                    {
                        returnIndex=index-2;
                        break;
                    }
                }
                else
                {
                    returnIndex=data.length()-1;
                    break;
                }
            }
        }
        return returnIndex;
    }


    private int findCorrespondingClosingBracses(String data, int indexOfOpeningBraces) throws Exception
    {
        if(data.charAt(indexOfOpeningBraces)=='(')
        {
            int counter=1;
            for(int i=indexOfOpeningBraces+1;i<data.length();i++)
            {
                char letter=data.charAt(i);
                if(letter=='(')
                    counter++;
                else if(letter==')')
                    if(--counter==0)
                        return i;
            }
        }
        throw new Exception("not proper braces");
    }


    private int lastIndexOfGivenWord(String dataSet, String word) throws Exception
    {
        int j;
        String data="";
        for(int i=0;i<=dataSet.length()-word.length()+1;i++)
        {
            if(dataSet.charAt(i)==word.charAt(0))
            {
                for(j=i;j<i+word.length();j++)
                    data+=String.valueOf(dataSet.charAt(j));
                if(word.equalsIgnoreCase(data))
                    return --j;
                else
                    data="";

            }
        }
        return -1;           //not found
    }

    private double solveForSine(String parameter,boolean degree) throws Exception
    {
        Double answer;
        BracketMagnagement a=new BracketMagnagement();
        if(degree)
            answer=Math.sin(Math.toRadians(Double.parseDouble(a.start(parameter))));
        else
            answer=Math.sin(Double.parseDouble(a.start(parameter)));
        return Double.parseDouble(this.a.calculatorFormat(answer));
    }

    private double solveForCoSine(String parameter,boolean degree) throws Exception
    {
        Double answer;
        BracketMagnagement a=new BracketMagnagement();
        if(degree)
            answer=Math.cos(Math.toRadians(Double.parseDouble(a.start(parameter))));
        else
            answer=Math.cos(Double.parseDouble(a.start(parameter)));
        return Double.parseDouble(this.a.calculatorFormat(answer));
    }

    private double solveForTangent(String parameter,boolean degree) throws Exception
    {
        Double answer;
        BracketMagnagement a=new BracketMagnagement();
        if(degree)
            answer=Math.tan(Math.toRadians(Double.parseDouble(a.start(parameter))));
        else
            answer=Math.tan(Double.parseDouble(a.start(parameter)));
        return Double.parseDouble(this.a.calculatorFormat(answer));
    }

    private double solveForCoSecent(String parameter,boolean degree) throws Exception
    {
        Double answer;
        BracketMagnagement a=new BracketMagnagement();
        if(degree)
            answer=1/(Math.sin(Math.toRadians( Double.parseDouble(a.start(parameter)))));
        else
            answer=1/(Math.sin(Double.parseDouble(a.start(parameter))));
        return Double.parseDouble(this.a.calculatorFormat(answer));
    }

    private double solveForSecent(String parameter,boolean degree) throws Exception
    {
        Double answer;
        BracketMagnagement a=new BracketMagnagement();
        if(degree)
            answer=1/(Math.cos(Math.toRadians( Double.parseDouble(a.start(parameter)))));
        else
            answer=1/(Math.cos(Double.parseDouble(a.start(parameter))));
        return Double.parseDouble(this.a.calculatorFormat(answer));
    }

    private double solveForCoTangent(String parameter,boolean degree) throws Exception
    {
        Double answer;
        BracketMagnagement a=new BracketMagnagement();
        if(degree)
            answer=1/(Math.tan(Math.toRadians( Double.parseDouble(a.start(parameter)))));
        else
            answer=1/(Math.tan(Double.parseDouble(a.start(parameter))));
        return Double.parseDouble(this.a.calculatorFormat(answer));
    }

    private double solveForLog(String parameter) throws Exception
    {
        Double answer;
        BracketMagnagement a=new BracketMagnagement();
        answer=Math.log10(Double.parseDouble(a.start(parameter)));
        return answer;
    }

    private double solveForLn(String parameter) throws Exception
    {
        Double answer;
        BracketMagnagement a=new BracketMagnagement();
        answer=Math.log(Double.parseDouble(a.start(parameter)));
        return answer;
    }
}