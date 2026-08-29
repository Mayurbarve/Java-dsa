package DSAQuestions.Recursion;

public class BasicRecursion {
    public static void main(String[] args) {
        //return back to same function because of recursive stack
        //return back to next line from where it got called
        recursionFromFront(5);
        System.out.println();
        recursionFromBack(5);
        System.out.println();

        System.out.println(recursion(5));
    }

    public static void recursionFromFront(int n){
        if(n==0){
            return;
        }
        recursionFromFront(n-1);
        System.out.print(n + " ");
    }

    public static void recursionFromBack(int n){
        if(n==0){
            return;
        }
        System.out.print(n + " ");
        recursionFromFront(n-1);
    }


    public static int recursion(int n){
        if(n==1){
            return 1;
        }

        return n + recursion(n-1);


    }
}