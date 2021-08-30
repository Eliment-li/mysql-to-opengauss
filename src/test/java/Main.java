import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner src = new Scanner(System.in);

        String l1 = src.nextLine();
        String l3 = src.nextLine();

        String[] s1 = l1.split(" ");
        int n = Integer.parseInt(s1[0]);
        int[] nums = new int[n];
        int L = Integer.parseInt(s1[1]);
        int R = Integer.parseInt(s1[2]);


        String[] s3 = l3.split(" ");
        int i = 0;
        for (String s : s3) {
            nums[i] = Integer.parseInt(s);
            i++;
        }

        Arrays.sort(nums);

        int count = 0;
        for (int k = 0; k < n; k++) {
            if (nums[k] > R) continue;
            for (int m = 0; m < k; m++) {
                if (m == k) continue;
                if (nums[m] > R) continue;
                int temp = nums[k] + nums[m];
                if (temp <= R && temp >= L) count++;
            }

        }
        System.out.println(count);
    }
}
