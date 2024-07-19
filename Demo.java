//Return how many of them contain an even number of digits

public class Demo {
    public static void main(String[] args) {
        int[] nums = {12, 345, 2, 6, 7896};
        System.out.println(findNumber(nums));

        System.out.println(digits(345));
    }

    static int findNumber(int[] nums) {
        int count = 0;
        for (int num : nums) {
            if (even(num)) {
                count++;
            }
        }
        return count;
    }

    // function to check whether a number contains even digit or not
    static boolean even(int num) {
        int numberOfDigits = digits(num);
        if(numberOfDigits % 2 == 0){
            return true;
        }
        return false;
        // return numberOfDigits % 2 ==0;
    }

    // function to check the digit of a number
    static int digits(int num) {
        if(num <0){
            num = num * -1;
        }

        // if(num == 0){
        //     return 1;
        // }

        // int count = 0;
        // while (num > 0) {
        //     count++;
        //     num /= 10;
        // }

        // return count;

        return (int)(Math.log10(num) + 1);
    }

}
