package DynamicProgramming;

public class LongestIncreasingSubsequence {


    int[] sequence = {-1,3,4,5,2,2,2,2};

    public LongestIncreasingSubsequence() {
    }

    public int lis() {

       int lis [] = new int[sequence.length];

       for( int i=0; i < sequence.length; i++) {
           lis[i] = 1;
       }

       for(int i=1; i < sequence.length; i++) {
           for (int j=0; j < i; j++) {

               if(sequence[i] >= sequence[j] && lis[i] < lis[j] + 1) {
                   lis[i] = lis[j]+1;
               }
           }
       }

       int max = lis[0];
       for(int i=1; i < lis.length; i++) {
           if(max < lis[i]) {
               max = lis[i];
           }
       }

       return max;

    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        System.out.println(lis.lis());
    }



}
