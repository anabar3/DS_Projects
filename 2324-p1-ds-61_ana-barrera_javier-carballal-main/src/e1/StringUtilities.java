package e1;

public class StringUtilities {
    private static boolean containsChar(String str, char c){
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == c) return true;
        }
        return false;
    }

    public static boolean isValidString (String inputText, String admissibleChars, int minLength) {
        //Null or empty string
        if(inputText == null || inputText.isEmpty()) return false;
        //Wrong length condition
        if (minLength > inputText.length()) return false;
        //Not valid chars Condition
        for (int i = 0; i < inputText.length(); i++) { //iterate through the first string
            if (!Character.isDigit(inputText.charAt(i))){
                if(!containsChar(admissibleChars, inputText.charAt(i))) return false;
            }
        }
        return true;
    }

    public static String lowercaseFirst (String inputText){
        String lowercase = "", uppercase = "";
        for (int i = 0; i < inputText.length(); i++){
            if (Character.isLowerCase(inputText.charAt(i))){
                lowercase = lowercase.concat(String.valueOf(inputText.charAt(i)));
            }
            else{
                uppercase = uppercase.concat(String.valueOf(inputText.charAt(i)));
            }
        }
        return lowercase.concat(uppercase);
    }

    public static boolean checkTextStats (String inputText, int min, int max){

        if ((min<=0) || (max<=0) ||inputText ==null || inputText.isEmpty()) throw new IllegalArgumentException("Invalid arguments");

        int counterletter=0, counterword=1, maxword=0, temp=0;
        float avg;
        for (int i = 0; i < inputText.length(); i++){
            if(inputText.charAt(i)== ' '){
                if (temp>maxword) maxword=temp;
                temp=0;
                counterword+=1;
            }else {
                temp += 1;
                counterletter += 1;
            }
        }
        avg = (float) counterletter /counterword;
        if ((avg<min)||(avg>max)|| (maxword>avg*2)) return false;
        return true;
    }
}