package com.cml.calculatorimp;


public class Expression {

    String expression;
    // private char[] expressionArray;
    char leftData,rightData;
    //  String result;
    simpleUnit termCalculator = new simpleUnit();

    public String getResult() {
        return termCalculator.getResult();
    }

    public void setExpression(String expression) {
        this.expression = "(" + expression + ")";
        //this.expression=expression;
        // expressionArray=expression.toCharArray();
    }

    public void calculate() {
        // while(keepSimplifying());

//        for(int i=0;i<7;i++){
//            calculateOperator(expression);
//        }
        keepSimplifying();
    }

    private void keepSimplifying() {
        // calculateOperator(expression);
        System.out.println(expression);
        String shortenedExpression;
        int rightBracePosition = findRightBrace();
        if (rightBracePosition == -1) {
            return;
        }

        int leftBracePosition = findOppositeLeftBrace(rightBracePosition);
        if (leftBracePosition != -1) {
            shortenedExpression = Manupulation.cutFromString(expression, leftBracePosition + 1, rightBracePosition - 1);
            expression = Manupulation.removeFromString(expression, leftBracePosition, rightBracePosition);
            termCalculator.calculateTerms(shortenedExpression);
            shortenedExpression = termCalculator.getResult();
            expression = Manupulation.addToString(expression, leftData+shortenedExpression+rightData, leftBracePosition - 1);
        } else {
            return;
        }
        keepSimplifying();

        //   return true;
    }

    private int findRightBrace() {
        char[] expressionArray = expression.toCharArray();
        int length = expression.length();
        for (int i = 0; i < length; i++) {
            if (expressionArray[i] == ')') {
                if(i<length-1)
                    rightData=setRightData(expressionArray[i+1]);
                return i;
            }
        }
        return -1;
    }
//     private int findLeftBrace() {
//       // char[] expressionArray = expression.toCharArray();
//        for (int i = 0; i < expression.length(); i++) {
//            if (expressionArray[i] == '(') {
//                return i;
//            }
//        }
//        return -1;
//    }

    private int findOppositeLeftBrace(int rightBracePosition) {
        char[] expressionArray = expression.toCharArray();
        for (int i = rightBracePosition; i >= 0; i--) {
            if (expressionArray[i] == '(') {
                if(i!=0)
                    leftData = setLeftData(expressionArray[i-1]);
                return i;
            }
        }
        return -1;
    }
    //    private int findOppositRightBrace(int leftBracePosition){
//        for (int i =leftBracePosition;i< expressionArray.length; i++) {
//            if (expressionArray[i] == ')') {
//
//                return i;
//            }
//        }
//        return -1;
//    }
//    private void fixBrace()
//    {
//        int rightBrace= findRightBrace();
//        int leftBrace = findOppositeLeftBrace(rightBrace);
//        //boolean isChanged = false;
//        switch(expressionArray[leftBrace-1]){
//            case '+':
//            case '-':
//            case '(':
//                break;
//            default:
//            {
//                expressionArray[leftBrace]='*';
//                expressionArray[rightBrace]=' ';
//                fixBrace();;
//            }
//            //leftBrace=findLeftBrace();
//            //rightBrace=findOppositRightBrace(leftBrace);
//
//
//        }
//
//
//    }
    private char setLeftData(char data){
        switch (data){
            case '+':
            case '-':
            case '/':
            case '*':
            case '(':
                return ' ';
            default:
                return '*';

        }
    }
    private char setRightData(char data){
        switch (data){
            case '+':
            case '-':
            case '/':
            case '*':
            case ')':
                return ' ';
            default:
                return '*';

        }
    }
}