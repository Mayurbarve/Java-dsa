/*
875. Koko Eating Bananas

Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

Example 1:

Input: piles = [3,6,7,11], h = 8
Output: 4
Example 2:

Input: piles = [30,11,23,4,20], h = 5
Output: 30
Example 3:

Input: piles = [30,11,23,4,20], h = 6
Output: 23
 */

//Complexity O(n log n)

public int minEatingSpeed(int[] piles, int h) {
    int left = 1;
    int right = 0;

    for (int j : piles) {
        right = Math.max(right, j);
    }

    int ans = -1;
    while(left <= right){
        int mid = left + (right - left) / 2; // Taking mid

        int hour = 0; // Inital Hour count to eat banana

        for(int pile : piles){ // traverse the array [3, 6, 7, 11]
            hour += (int) Math.ceil((double)pile / mid ); // check total hour to eat banana in each pile and add to hour
        }

        if(hour <= h){ //if hour lesser or equal to given hours then ans = mid
            ans = mid;
            right = mid - 1;
        }
        else{
            left = mid + 1;
        }
    }

    return ans;
}


void main() {

    int[] piles = {3, 6, 7, 11};
    int hour = 8;

    System.out.println(minEatingSpeed(piles, hour));
}