
/**
 * Created by bablu on 18/7/17.
 */


import java.util.ArrayList;
public class calc {



    public double start(String data) throws Exception
    {
        data = polishDataForCharAt0(data);
        data=removeMultiplesigns(data);
        while(!over(data))
        {
            int[] operatorsIndex=operatorIndexList(data);

            int operatorIndex=searchHigherPresedenceOperatorWithLowerIndex(data);
            char operator=data.charAt(operatorIndex);

            double rightOperand=0.0d;
            double leftOperand=0.0d;
            String leftOperandinString=null;
            String rightOperandinString = null;

            if(operator!='√')
            {
                leftOperandinString=findLeftOperand(data, operatorIndex,operatorsIndex);
                leftOperand=Double.parseDouble(leftOperandinString);
            }

            if(operator!='!')		// for single operand operators
            {
                rightOperandinString=findRightOperand(data, operatorIndex,operatorsIndex);
                rightOperand=Double.parseDouble(rightOperandinString);
            }
            double answer=calculate(operator,leftOperand,rightOperand);
            String answerToSet=String.valueOf(answer);
            if(answerToSet.charAt(0)!='-' && operator!='√')
                answerToSet="+"+answerToSet;

            if(operator=='!' && data.length()>operatorIndex+1)     //to convert 3!4 to 3!*4
            {
                boolean flag=false;
                for(int i:operatorsIndex)
                    if(i==operatorIndex+1)
                        flag=true;
                if(!flag)
                {
                    data=data.substring(0,operatorIndex+1)+"*"+data.substring(operatorIndex+1);
                }
            }
            data = placeAnswerWithinData(answerToSet, leftOperandinString, rightOperandinString,data, String.valueOf(operator));
        }

        return Double.parseDouble(data);

    }


    private boolean over(String data)
    {
        int[] operatorsIndex=operatorIndexList(data);
        int lengthofOperatorsIndex=0;
        for(@SuppressWarnings("unused") int i: operatorsIndex)
            lengthofOperatorsIndex++;

        if(operatorsIndex[0]==0)               // ignoring sign symbol at start of string
            return !(lengthofOperatorsIndex>1);
        else
         return !(lengthofOperatorsIndex>0);
    }

    private String polishDataForCharAt0(String data)
    {
        String newData;
        char charAt0=data.charAt(0);
        if(charAt0=='*' || charAt0=='/')
            throw new ArithmeticException("Invalid String");
        else if(charAt0!='+' && charAt0!='-')
            newData="+"+data;
        else
            newData=data;
        return newData;
    }

    private String placeAnswerWithinData(String answerToSet,String leftOperand,String rightOperand,String Data,String operator)
    {
        String newData,partOfDataToRemove;
        if(rightOperand==null)
            partOfDataToRemove=leftOperand+operator;
        else if(leftOperand==null)
            partOfDataToRemove=operator+rightOperand;
        else
            partOfDataToRemove=leftOperand+operator+rightOperand;
        newData=Data.replace(partOfDataToRemove, answerToSet);
        return newData;
    }

    private double calculate(char operator,double operand1,double operand2)                   //Add operator's here
    {
        double result=0.0d;
        if(operator=='+')
            result=operand1+operand2;
        else if(operator=='^')
            result=Math.pow(operand1, operand2);
        else if(operator=='-')
            result=operand1-operand2;
        else if(operator=='*')
            result=operand1*operand2;
        else if(operator=='/') {
            if(operand2==0 && operand1>0)
                result=Double.POSITIVE_INFINITY;
            else if (operand2==0 && operand1<0)
                result=Double.NEGATIVE_INFINITY;
            else
                result = operand1 / operand2;
        }
        else if(operator=='!')
            result=factorial(operand1);
        else if(operator=='p')
            result=(factorial(operand1)/factorial(operand1-operand2));
        else if(operator=='c')
            result=(factorial(operand1)/(factorial(operand2)*factorial(operand1-operand2)));
        else if(operator=='√')
            result=Math.sqrt(operand2);
        return result;
    }


    private double factorial(double operand)
    {
        double result=1;
        double operandPositive=Math.abs(operand);
        if(operandPositive==1 || operandPositive==0)
            result=1;
        else
            for(int i=2;i<=operandPositive;i++)
                result*=i;
        if(operand<0)
            result*=-1;
        return result;
    }

    private String findRightOperand(String data,int operatorIndex,int[] operatorsIndex)
    {
        int rightOperatorIndex=rightOperatorIndexFromGivenIndexExceptInstantRight(operatorIndex,operatorsIndex);
        String rightOperand;
        if( rightOperatorIndex==-1)
            rightOperand=data.substring(operatorIndex+1,data.length());
        else
            rightOperand=data.substring(operatorIndex+1,rightOperatorIndex);
        return rightOperand;
    }


    private String findLeftOperand(String data,int operatorIndex,int[] operatorsIndex)
    {
        int leftOperatorIndex=leftOperatorIndexfromGivenIndex(operatorIndex,operatorsIndex);
        String leftOperand="";
        if(leftOperatorIndex==-1){
            leftOperand+="+";
            leftOperand+=data.substring(0, operatorIndex);   //if no operator found at left then +
        }
        else
        {
            char symbol=data.charAt(leftOperatorIndex);
            if(leftOperatorIndex>0)
                if((symbol=='+' || symbol=='-') && data.charAt(leftOperatorIndex-1)!='E')
                    leftOperand=data.substring(leftOperatorIndex,operatorIndex);
                else
                    leftOperand=data.substring(leftOperatorIndex+1,operatorIndex);
            else
                leftOperand=data.substring(leftOperatorIndex,operatorIndex);
        }
        return leftOperand;
    }

    private int rightOperatorIndexFromGivenIndexExceptInstantRight(int index,int []operatorsIndexList)
    {
        int rightIndex=-1;
        for(int i:operatorsIndexList)
            if(i>index+1){         //index +1 for not to catch operator of right operand
                rightIndex=i;
                break;
            }
        return rightIndex;
    }

    private int leftOperatorIndexfromGivenIndex(int index,int []operatorsIndexList)
    {
        int leftIndex=-1;     // -1 if given operator's index is for 1st one
        for(int i:operatorsIndexList){
            if(i<index)
                leftIndex=i;
            else
                break;
        }
        return leftIndex;
    }


    private int searchHigherPresedenceOperatorWithLowerIndex(String data)   //Add operator's here
    {
        if(data.contains("^"))
            return data.indexOf("^");
        else if(data.contains("!"))
            return data.indexOf("!");
        else if(data.contains("√"))
            return data.indexOf("√");
        else if(data.contains("p") || data.contains("c"))
        {
            int PIndex=data.indexOf("p");
            int CIndex=data.indexOf("c");
            if(PIndex!=-1 && CIndex!=-1)
                return (Math.min(PIndex, CIndex));
            else if(PIndex==-1)
                return CIndex;
            else
                return PIndex;
        }

        else if(data.contains("*") || data.contains("/"))
        {
            int multIndex=data.indexOf("*");
            int divIndex=data.indexOf("/");
            if(multIndex!=-1 && divIndex!=-1)
                return (Math.min(multIndex, divIndex));
            else if(multIndex==-1)
                return divIndex;
            else
                return multIndex;
        }
        else if(data.contains("+") || data.contains("-"))
        {
            int sumIndex=data.indexOf("+",1);
            int subIndex=data.indexOf("-",1);
            if(subIndex!=-1 && sumIndex!=-1)
                return (Math.min(sumIndex, subIndex));
            else if(subIndex==-1)
                return sumIndex;
            else
                return subIndex;
        }
        else return -1;     ///means error
    }

    private String removeMultiplesigns(String data) throws Exception
    {
        int continuous=0;
        boolean iBefore=false;
        for(int i=0;i<data.length();i++)
        {
            if((data.charAt(i)=='+' || data.charAt(i)=='-' ) && !iBefore)
            {
                continuous++;
                iBefore=true;
            }
            else if((data.charAt(i)=='+' || data.charAt(i)=='-' ) && iBefore)
                continuous++;
            else
            {
                if(iBefore && continuous>2)
                {
                    String toReplace=data.substring(i-continuous,i);
                    String s=String.valueOf(solveSign(toReplace));
                    data=replacePartOfString(i-continuous, i-1, s, data);
                    i=i-continuous+1;
                }
                iBefore=false;
                continuous=0;
            }
        }
        return data;
    }



    private char solveSign(String signs) throws Exception
    {
        String data="";
        int length=signs.length();
        for(int i=1;i<length;i++)
        {
            data=data+String.valueOf(signs.charAt(0))+"1*";
            signs=signs.substring(1,signs.length());
        }
        data=data+data.charAt(0)+"1";

        calc c=new calc();
        double ans=c.start(data);
        if(ans<0.0)
            return '-';
        else
            return '+';
    }

    private String replacePartOfString(int startingIndex,int endingIndex,String dataToReplace,String data) throws Exception
    {
        Additionals additional=new Additionals();
        return additional.replacePartOfString(startingIndex, endingIndex, dataToReplace, data);
    }

    private int[] operatorIndexList(String data)
    {
        ArrayList<Integer> indexList=new ArrayList<Integer>();
        char word;
        for(int i=0;i<data.length();i++)
        {
            word=data.charAt(i);
            if(i>0)
            {
                if((word=='+' && (data.charAt(i-1)!='E')) || (word=='-' && (data.charAt(i-1)!='E')) || word=='*' || word=='/' || word=='^' || word=='!' || word=='p' || word=='c' || word=='√')             //Add operator's here
                    indexList.add(i);
            }
            else
            if(word=='+' || word=='-' || word=='*' || word=='/' || word=='^' || word=='!' || word=='p' || word=='c' || word=='√')                     //Add operator's here
                indexList.add(i);
        }
        int[] indexArr=new int[indexList.size()];
        int index=-1;
        for (int i : indexList)
            indexArr[++index]=i;
        return indexArr;
    }

}