package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExtremeSin implements ExtremeFunction {
    private BigDecimal x;
    private int numDigit;
    private int maxNumBlock;
    private boolean successful;

    @Override
    public boolean isSuccessful() {
        return successful;
    }

    public ExtremeSin(String value, int numDigit, int maxNumBlock){
        x=new BigDecimal(value);
        this.numDigit =numDigit;
        this.maxNumBlock = maxNumBlock;
    }
    @Override
    public BigDecimal execute() {
        successful=false;

        BigDecimal nowX=x;
        nowX.setScale(numDigit,RoundingMode.HALF_EVEN);
        BigDecimal factor=BigDecimal.ONE;//k!の数値
        factor.setScale(numDigit,RoundingMode.HALF_EVEN);
        BigDecimal answer=BigDecimal.ZERO;
        answer.setScale(numDigit, RoundingMode.HALF_EVEN);

        String prevAnswer="";

        for(int i = 0; i< maxNumBlock; i++){
            System.out.print(i+" ");
            int k=i*2+1;
            //x^k
            if(k!=1){
                nowX=nowX.multiply(nowX);
                nowX=nowX.multiply(nowX);
            }
            //k!
            int multiplyInt=k*(k-1);
            if(multiplyInt==0)multiplyInt=1;
            BigDecimal multiply=BigDecimal.valueOf(Integer.toUnsignedLong(multiplyInt));
            multiply.setScale(numDigit);
            factor=factor.multiply(multiply);

            //dx^k/d^ky(x=0)
            boolean isNegative=k%4==3;

            BigDecimal newPart = nowX.divide(factor,numDigit,RoundingMode.HALF_EVEN);
            if(isNegative)newPart=newPart.negate();

            answer=answer.add(newPart);
            String answerStr=answer.toString();
            System.out.println(answerStr);
            if(prevAnswer.equals(answerStr)){
                successful=true;
                break;
            }
            prevAnswer=answerStr;
        }
        if(successful){
            System.out.println("答えが収束しました");
        }else{
            System.out.println("答えが収束しませんでした");
        }
        return answer;
    }
}
