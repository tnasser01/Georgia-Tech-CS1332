package Graphs.AdjacencyMatrix;

import java.util.*;

public class UnweightedGraphAdjacencyMatrix {

    int[][] adjMatrix;

    public UnweightedGraphAdjacencyMatrix(int[][] adjMatrix){
        this.adjMatrix = adjMatrix;
    }

    public int bfsShortestPathAdjMatrix(int start, int finish) {
        int n = adjMatrix.length;
        Queue<Integer> queue = new LinkedList<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        List<Integer> bfs = new ArrayList<Integer>();

        queue.offer(start);
        while(!queue.isEmpty() && visited.size() != 5) {
            Integer curr = queue.poll();

            if (curr == finish)
                return bfs.size();

            if(!visited.contains(curr)) {
                visited.add(curr);
                bfs.add(curr);

                for (int i = 0; i < adjMatrix.length; i++) {
                    if (adjMatrix[curr][i] == 1 && !visited.contains(i)) {
                        queue.offer(i);
                    }
                }
            }
        }

        return -1;

    }

    public List<Integer> bfsTraversal(int start) {

        int n = adjMatrix.length;
        Queue<Integer> queue = new LinkedList<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        List<Integer> bfs = new ArrayList<Integer>();

        queue.offer(start);
        while(!queue.isEmpty() && visited.size() != n) {
            Integer curr = queue.poll();

            if(!visited.contains(curr)) {
                visited.add(curr);
                bfs.add(curr);

                for (int i = 0; i < adjMatrix.length; i++) {
                    if (adjMatrix[curr][i] == 1 && !visited.contains(i)) {
                        queue.offer(i);
                    }
                }
            }
        }

        return bfs;

    }

    public List<Integer> dfsTraversal(int[][] adjMatrix, int start) {

        Set<Integer> visited = new HashSet<Integer>();
        List<Integer> dfs = new ArrayList<Integer>();

        dfsHelper(adjMatrix, start, visited, dfs);
        
        return dfs;
    }

    public void dfsHelper(int[][] adjMatrix, int curr, Set<Integer>
            visited,  List<Integer> dfs) {

        if(!visited.contains(curr)) {
            visited.add(curr);
            dfs.add(curr);

            for(int i=0; i < adjMatrix.length; i++) {
                if(adjMatrix[curr][i] == 1 && !visited.contains(i)) {
                    dfsHelper(adjMatrix, i, visited, dfs);
                }
            }
        }

    }

    public static void main(String[] args) {

        int[][] matrix= new int[5][5];
        matrix[0][1] = 1;
        matrix[1][0] = 1;
        matrix[0][2] = 1;
        matrix[2][0] = 1;
        matrix[1][2] = 1;
        matrix[2][1] = 1;
        matrix[1][3] = 1;
        matrix[3][1] = 1;
        matrix[2][3] = 1;
        matrix[3][2] = 1;
        matrix[3][4] = 1;
        matrix[4][3] = 1;

        UnweightedGraphAdjacencyMatrix a = new UnweightedGraphAdjacencyMatrix
                (matrix);
       // int i = a.shortestPathAdjMatrix(0, 4);
       // System.out.println(i);
       // List<Integer> list = a.bfsTraversal(0);
        List<Integer> dfs = a.dfsTraversal(matrix,  0);
       // for(Integer b: list)
        //    System.out.print(b + " ");

        for(Integer c: dfs){
            System.out.print(c + " ");
        }
    }
}
