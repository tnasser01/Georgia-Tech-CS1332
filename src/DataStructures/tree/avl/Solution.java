package DataStructures.tree.avl;

import java.io.*;
import java.util.*;

public class Solution {

    static long uniqueRecycledPairs(int[] A) {

        if (A.length < 2) {
            return 0;
        }

        long count = 0;

        for (int i = 0; i < A.length-1; i++) {

        int curr = A[i];

        while(true) {
            if(curr % 10 == 0){
                break;
            }
            int temp = curr;
            int power = 1;


            while (curr >= 10) {
                power *= 10;
                curr /= 10;
            }
            curr = (temp % power * (power / 10) + curr);
            if(curr == A[i]){
                break;
            }
            for (int j = i + 1; j < A.length; j++) {
                if (curr == A[j]) {
                    count++;
                    break;
                }
            }

        }

        }
        return count;
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv
           //     ("OUTPUT_PATH")));

        int n = Integer.parseInt(scan.nextLine().trim());

        int[] A = new int[n];

        String[] AItems = scan.nextLine().split(" ");

        for (int AItr = 0; AItr < n; AItr++) {
            int AItem = Integer.parseInt(AItems[AItr].trim());
            A[AItr] = AItem;
        }

        long result = uniqueRecycledPairs(A);
        System.out.println("Result is: " + result);
        //bw.write(String.valueOf(result));
        //bw.newLine();

        //bw.close();
    }
}
