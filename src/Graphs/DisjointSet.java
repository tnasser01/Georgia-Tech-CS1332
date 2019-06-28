package Graphs;

public class DisjointSet {

    int[] parent;
    int[] rank;

    public DisjointSet(int n) {
        rank = new int[n];
        parent = new int[n];
        for(int i=0; i < n; i++) {
            parent[i] = i;
        }
    }

    int find(int i) {
        if (parent[i] != i)
            parent[i] = find(parent[i]);
        return parent[i];
    }

    void union(int x, int y) {

        int x_root = find(x);
        int y_root = find(y);

        if(x_root != y_root) {
            if(rank[x_root] < rank[y_root]) {
                parent[x_root] = y_root;
            } else if (rank[x_root] > rank[y_root]) {
                parent[x_root] = x_root;
            } else {
                parent[y_root] = x_root;
                rank[x_root]++;
            }
        }
    }
}
