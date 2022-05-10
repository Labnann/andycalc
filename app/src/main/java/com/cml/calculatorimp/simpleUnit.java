package com.cml.calculatorimp;


public class simpleUnit {

    double result;
    String stringResult;
    Term[] term = new Term[100];
    int termCount = 0;
    char[] operationArray;

    public void calculateTerms(String numberString) {

        result = 0;
        numberString = "00+" + numberString + "+";
        //CalculatorProcessor processor = new CalculatorProcessor();
        // char number;

        operationArray = numberString.toCharArray();
        removeSpace();
        calculateOperator();
        int startPoint = 0;
        for (int i = 0; i < operationArray.length; i++) {
            //  System.out.println("calculator.simple.Calculator.stringTranslator()");
            if ((operationArray[i] == '+') || (operationArray[i] == '-') && (i != 0)) {
                if (operationArray[(i - 1)] == '*' || operationArray[i - 1] == '/') {
                    continue;
                }
                {
                    term[termCount] = new Term(operationArray[startPoint]);
                    //     System.out.print("calculateTerms:: operaationArray: "+operationArray[startPoint]+"array:");
                    //    System.out.println(operationArray);
                    term[termCount].setStringValue(Manupulation.charArrayToString(operationArray, startPoint + 1, i - 1));
                    try {
                        term[termCount].operate();
                    } catch (Exception e ) {

                        stringResult = "Wrong syntax";
                        System.out.println(operationArray);

                        System.out.println(e.getMessage());
                        return;
                    }
                    startPoint = i;

                    result += term[termCount].getNumberValue();
                    termCount++;
                }
            }

        }
        termCount = 0;
        stringResult = String.valueOf(result);
    }

    String getResult() {
        return stringResult;
    }

    private void calculateOperator() {
        boolean isChanged = false;
        for (int i = 0; i < operationArray.length - 1; i++) {
            if (operationArray[i] == '\0') {
                continue;
            }
            if (operationArray[i] == '+') {
                if (operationArray[i + 1] == '-' || operationArray[i + 1] == '+') {
                    operationArray[i] = '\0';
                    isChanged = true;
                }
            }
            if (operationArray[i] == '-') {
                if (operationArray[i + 1] == '-') {
                    operationArray[i + 1] = '+';
                    operationArray[i] = '\0';
                    isChanged = true;
                } else if (operationArray[i + 1] == '+') {
                    operationArray[i] = '\0';
                    operationArray[i + 1] = '-';
                    isChanged = true;
                }
            }

        }
        if (isChanged) {
            calculateOperator();
        }

    }
    private void removeSpace()
    {
        String secondArray="";
        for(int i=0;i<operationArray.length;i++)
        {
            if(operationArray[i]==' ')
                continue;
            int j=0;
            secondArray+=operationArray[i];
        }
        operationArray=secondArray.toCharArray();
    }
}

