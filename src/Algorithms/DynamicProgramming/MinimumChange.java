package DynamicProgramming;

public class MinimumChange {

    static int change = 12;
    static int[] coins = {1, 6, 10};


    //Time:  O(n^c) (O(depth^branching factor))
    //Space:  O(n)
    public int bruteForce(int chg ) {

        //base case - if change is 0 return 0
        if (chg == 0) {
            return 0;
        }

        int minCoins = Integer.MAX_VALUE;

        //if the coin is less than the change, recurse
        for(int coin: coins) {
            if(chg - coin >=0) {

                //recursing returns the curr min of coins needs
                int currMin = bruteForce(chg - coin);
                //track the min so far
                minCoins  = Math.min(currMin, minCoins);
            }
        }
        //add 1 to the min of coins needed
        return minCoins + 1;
    }

    //Time: O(n*c)
    //Space: O(n)
    public int topDownDP(int n, int[] coins){
        int[] dp = new int[n+1];
        return topDownDP(n, coins, dp);

    }

    public int topDownDP(int n, int[] coins, int[] dp) {

        if(dp[n] == 0) {
            if (n == 0) return 0;
            int minCoins = Integer.MAX_VALUE;

            for (int coin : coins) {
                if (n - coin >= 0) {
                    int currMin = topDownDP(n - coin, coins, dp);
                    minCoins = Math.min(currMin, minCoins);
                }
            }
            dp[n] = minCoins + 1;
        }
        return dp[n];
    }


    //Worst case runtime is O(n*c)
    //Space complexity isO(n)
    public int bottomUpDP(int n, int[] coins) {

        int[] dp = new int[n+1];

        for(int i=1; i <= n; i++) {
            int  minCoins = Integer.MAX_VALUE;

            for (int coin: coins) {
                if (i - coin >= 0) {
                    minCoins = Math.min(minCoins, dp[i - coin]);
                }
                dp[i] = minCoins + 1;
            }
        }

        return dp[n];
    }



    public static void main(String[] args) {
            MinimumChange mc = new MinimumChange();
           // System.out.println(mc.bruteForce(change));
           //System.out.println(mc.topDownDP(change, coins));
            System.out.println(mc.bottomUpDP(change, coins));
        }


}
