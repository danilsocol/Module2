import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Main {

    public static void main(String[] args) throws ScriptException {
        String nums = "98765432";
        char[] exArr = nums.toCharArray();

        String[] operation = {"_","_","_","_","_","_","_"};
        int count = operation.length-1;

        for(int i = 0; i < Math.pow(operation.length,5);i++) {
            String ex = merge(exArr,operation);
            output(ex);
            count = enumerationOperation(exArr,operation,count);
        }
    }

    static int enumerationOperation(char[] exArr,String[] operation,int count){

        switch (operation[count]){
            case "_":
                operation[count] = "+";
                break;
            case "+":
                operation[count] = "-";
                break;
            case "-":
                operation[count] = "/";
                break;
            case "/":
                operation[count] = "*";
                break;
            case "*":
                operation[count] = "_";
                count--;
                return count;
        }
        if(count != operation.length-1 && operation[count+1] == "_")
            count++;
        return count;

    }

    static String merge(char[] exArr,String[] operation){
        String result = "";
        for (int i = 0; i< exArr.length;i++)
        {
            result += exArr[i];
            if(i < operation.length && operation[i] != "_")
                result += operation[i];
        }
        return result;
    }

    static void output(String ex) throws ScriptException {
        if (100 == Math.round(calc(ex))) {
            System.out.print(ex);
            System.out.println("=100");
        }
    }

    public static double calc(String ex){
        try {
            return Double.parseDouble(new ScriptEngineManager().getEngineByName("JavaScript").eval(ex).toString());
        } catch (ScriptException e) {
            return Double.NaN;
        }
    }

}
