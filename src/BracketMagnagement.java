

/**
 * Created by bablu on 18/7/17.
 */

import java.util.ArrayList;


public class BracketMagnagement {

    public String start(String data)
    {
        try{
            data=manageBrackets(data);
            data=polishDataForSignsBeforeBrackets(data);
            return solve(data);
        }
        catch(Exception e){
            throw new ArithmeticException();
        }
    }

    private String solve(String data) throws Exception
    {
        calc calc=new calc();
        while(true)
        {
            int[] BracketIndexes=getBracketIndexes(data);
            if(BracketIndexes.length==0)
                break;
            int leastOpeningBracesIndex=BracketIndexes[BracketIndexes.length/2-1];
            int firstClosingBracesIndex=-1;
            for(int i=BracketIndexes.length/2;i<BracketIndexes.length;i++)
            {
                int val=BracketIndexes[i];
                if(val>leastOpeningBracesIndex)
                {
                    firstClosingBracesIndex=val;
                    break;
                }
            }

            String soluteDataPart=data.substring(leastOpeningBracesIndex+1,firstClosingBracesIndex);

            String answerToreplace=String.valueOf(calc.start(soluteDataPart));
            data=replacePartOfString(leastOpeningBracesIndex, firstClosingBracesIndex, answerToreplace, data);
            //System.out.println(data);

        }
        return data;
    }


    private String replacePartOfString(int startingIndex,int endingIndex,String dataToReplace,String data)
    {
        String newData;
        String leftPart=data.substring(0,startingIndex);
        String rightPart=data.substring(endingIndex+1);
        newData=leftPart+dataToReplace+rightPart;
        return newData;
    }

    private int[] getBracketIndexes(String data)
    {
        ArrayList<Integer> list=new ArrayList<Integer>();
        int index=-1;
        for(char i:data.toCharArray())
        {
            index++;
            if(i=='(')
                list.add(index);
        }
        index=-1;
        for(char i:data.toCharArray())
        {
            index++;
            if(i==')')
                list.add(index);
        }
        int[] bracketList=new int[list.size()];
        index=0;
        for(int i: list)
            bracketList[index++]=i;
        return bracketList;
    }

    private String polishDataForSignsBeforeBrackets(String data)
    {
        int[] bracketIndexes=getBracketIndexes(data);
        char symbol,symbolAti;
        String newData=data;
        for(int j=0;j<bracketIndexes.length;j++)
        {
            int i=bracketIndexes[j];
            if(i>0)
            {
                symbol=data.charAt(i-1);
                symbolAti=data.charAt(i);
                if( symbolAti=='(' && (symbol!='+' && symbol!='-' && symbol!='/' && symbol!='*' && symbol!='(' && symbol!='^' && symbol!='!' && symbol!='√' && symbol!='c' && symbol!='p'))
                    newData=replacePartOfString(i, i-1, "*", data);
                else if(symbolAti==')')
                {
                    if(i+1!=data.length())
                    {
                        symbol=data.charAt(i+1);
                        if(symbol!='+' && symbol!='-' && symbol!='/' && symbol!='*' && symbol!=')' && symbol!='^' && symbol!='!' && symbol!='√' && symbol!='c' && symbol!='p' )
                            newData=replacePartOfString(i+1, i, "*", data);
                    }
                }
            }
        }
        return newData;
    }

    private String manageBrackets(String data)
    {
        if(data.charAt(data.length()-1)!=')')
            data='('+data+')';
        if(data.charAt(0)!='(')
            data="("+data;

        String newData;
        int openingParanthesisNo=counterofSameCharWithinString(data,"(");
        int closingParanthesisNo=counterofSameCharWithinString(data,")");
        if(openingParanthesisNo==closingParanthesisNo)
            newData=data;
        else if(openingParanthesisNo>closingParanthesisNo)
        {
            String closingP="";
            for(int i=0;i<openingParanthesisNo-closingParanthesisNo;i++)
                closingP+=")";
            newData=data+closingP;
        }
        else
            throw new ArithmeticException("Closing brackets are more than opening");
        return newData;
    }

    private int counterofSameCharWithinString(String data,String toCount)
    {
        int count=0,index=0;
        do{
            index=data.indexOf(toCount,index)+1;
            if(index!=0)
                count++;
        }
        while(index!=0);
        return count;
    }
}