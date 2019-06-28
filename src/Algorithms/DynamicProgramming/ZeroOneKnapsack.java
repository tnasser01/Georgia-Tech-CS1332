package DynamicProgramming;

public class ZeroOneKnapsack {

    static class Item {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public int bruteForce(int maxWeight) {
        Item one = new Item(2, 6);
        Item two =  new Item(2, 10);
        Item three = new Item(3, 12);
        Item[] items = new Item[]{one, two,  three};
        return bruteForce(items, maxWeight, 0);
    }

    public static int bruteForce(Item[] items, int maxWeight, int i) {
        if(i == items.length) {
            return 0;
        }

        if (items[i].weight > maxWeight)
            return bruteForce(items, maxWeight, i+1);

        return Math.max(
            bruteForce(items, maxWeight, i+1),
            bruteForce(items, maxWeight - items[i].weight, i+1) + items[i].value);
    }

    public int topDownDP(int maxWeight) {
        Item one = new Item(2, 6);
        Item two =  new Item(2, 10);
        Item three = new Item(3, 12);
        Item[] items = new Item[]{one, two,  three};

        int[][] dp  = new int[items.length][maxWeight];
        for(int i = 0;i < dp.length; i++) {
            for(int j=0; j < dp[0].length; j++) {
                dp[i][j]= -1;
            }

        }
        return bruteForce(items, maxWeight, 0);
    }

    public static int topDownDP(Item[] items, int maxWeight, int i, int[][] dp) {

        if(dp[i][maxWeight] == -1) {
            if (i == items.length) {
                return 0;
            }

            if (items[i].weight > maxWeight)
                dp[i][maxWeight] = bruteForce(items, maxWeight, i + 1);

            dp[i][maxWeight] = Math.max(
                    bruteForce(items, maxWeight, i + 1),
                    bruteForce(items, maxWeight - items[i].weight, i + 1) + items[i].value);

        }

        return dp[i][maxWeight];

    }

    public int bottomUpDP(Item[] items, int W) {
        int[][] dp  = new int[items.length + 1][W + 1];
        for(int i = 1; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                if (items[i-1].weight > j) {   //if item too heavy for knapsack
                    dp[i][j] = dp[i-1][j];    //exclude the item
                } else {
                    int include = dp[i-1][j - items[i-1].weight]
                            + items[i-1].value;
                    dp[i][j] = Math.max(dp[i-1][j], include);
                }
            }
        }

        return dp[items.length][W];
    }

    public static int bottomUpKnapsack(Item[] items, int W) {
        if (items.length == 0 || W == 0) return 0;
        // Initialize cache
        int[][] dp = new int[items.length + 1][W + 1];
        // For each item and weight, compute the max value of the items up to
        // that item that doesn't go over W weight
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (items[i-1].weight > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    int include = dp[i-1][j-items[i-1].weight] + items[i-1].value;
                    dp[i][j] = Math.max(dp[i-1][j], include);
                }
            }
        }

        return dp[items.length][W];
    }

    public static void main(String[] args) {
        Item one = new Item(2, 6);
        Item two =  new Item(2, 10);
        Item three = new Item(3, 12);

        Item[] items = new Item[]{one, two,  three};
        ZeroOneKnapsack knapsack = new ZeroOneKnapsack();
        //System.out.println(knapsack.bruteForce(5));
        //System.out.println(knapsack.topDownDP(5));
        System.out.println(knapsack.bottomUpDP(items, 5));
        //System.out.println(knapsack.bottomUpKnapsack(items, 5));
    }
}



