package DSAQuestions.Recursion;

public class ReverseString {
        public static void main(String[] args) {
            System.out.println(factorial("mayur"));
        }

        public static String factorial(String str){
            if(str.length() == 1){
                return str;
            }
            return factorial(str.substring(1)) + str.charAt(0);
        }
    }
