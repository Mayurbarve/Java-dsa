void main() {
    
}


class Solution {

    public boolean isTrue(int[] weights, int days, int cap){
        int dayCount = 1;
        int tempSum = 0;

        for(int w : weights){
            if(tempSum + w > cap){
                dayCount++;
                tempSum = 0;
            }

            tempSum += w;
        }

        return dayCount <= days;
    }



    public int shipWithinDays(int[] weights, int days) {
        int left = 0;
        int right = 0;

        for(int ele : weights){
            left = Math.max(left, ele);
            right += ele;
        }

        int ans = right;

        while(left <= right){
            int capacity = left + (right - left) / 2;

            if(isTrue(weights, days, capacity)){
                ans = capacity;
                right = capacity - 1;
            }
            else{
                left = capacity + 1;
            }
        }

        return ans;
    }
}