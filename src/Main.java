//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    System.out.println("This Repository belong to My Java Learning Phase ");

    int[] nums = {1,3, 3, 4,1, 1,};

    System.out.println(numIdenticalPairs(nums));

}

    public int numIdenticalPairs(int[] nums) {

        int[] freq = new int[101];
        int count = 0;

        for (int num : nums) {
            if (freq[num] > 0) {
                count += freq[num];
                freq[num]++;

            } else {
                freq[num] = 1;
            }
        }
        return count;
    }
