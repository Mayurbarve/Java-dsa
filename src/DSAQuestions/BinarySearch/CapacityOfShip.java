/*
1011. Capacity To Ship Packages Within D Days
Solved
Medium

Topics
premium lock icon
Companies

Hint
A conveyor belt has packages that must be shipped from one port to another within days days.

The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.



Example 1:

Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Output: 15
Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
Example 2:

Input: weights = [3,2,2,4,1,4], days = 3
Output: 6
Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4
 */


//Complexity O(n log n)


    public boolean isTrue(int[] weights, int days, int cap){
        int dayCount = 1;  // Initial Day Count
        int tempSum = 0;   // Temporary Sum Change after each capacity

        for(int w : weights){
            if(tempSum + w > cap){ // Check temporary Sum + current element is lesser or not then capacity
                dayCount++;  // if lesser than Day count ++
                tempSum = 0;  // and make it temp sum 0
            }

            tempSum += w; // keep adding next element until sum till capacity
        }

        return dayCount <= days;  //return true if dayCount lesser or equal than days else false
    }



    public int shipWithinDays(int[] weights, int days) {


        int left = 0;  // Maximum among elements.
        int right = 0; // Sum of all elements.

        for(int ele : weights){
            left = Math.max(left, ele);
            right += ele;
        }

        int ans = right;

        while(left <= right){
            int capacity = left + (right - left) / 2; // Taking mid

            if(isTrue(weights, days, capacity)){ // check if it is valid or not else move to other element
                ans = capacity;
                right = capacity - 1;
            }
            else{
                left = capacity + 1;
            }
        }

        return ans;
    }


    void main() {

        int[] weights = {1,2,3,4,5,6,7,8,9,10};
        int days = 5;

        System.out.println(shipWithinDays(weights, days));
    }