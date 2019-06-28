package Graphs.GraphAlgorithmsApplied;

import java.util.LinkedList;
import java.util.Queue;

class ShortestPathInBinaryMatrix {


    static int[][] input =  {{1,0,0}, {1,0,0}, {1, 9, 0}};

    public static int shortestPathInMatrix(int[][] matrix, int numRows, int numCols) {

        Queue<Coordinates> queue = new LinkedList<Coordinates>();
        boolean[][] visited = new boolean[numRows][numCols];
        queue.offer(new Coordinates(0,0, 0));

        while(!queue.isEmpty()) {
            Coordinates curr = queue.poll();
            int row = curr.row;
            int col = curr.col;

            if(matrix[row][col]  == 9) {
                return curr.distance;
            }


            if(!visited[row][col]) {
                visited[row][col] = true;
                //up
                if(row - 1 >= 0 && matrix[row-1][col] != 0 && !visited[row-1][col]) {
                    queue.offer(new Coordinates(row-1, col, curr.distance + 1));
                }
                //left
                if(col-1 >= 0 && matrix[row][col-1] != 0 &&
                        !visited[row][col-1]) {
                    queue.offer(new Coordinates(row, col-1,curr.distance + 1));
                }
                //down
                if(row+1 <= numRows-1 && matrix[row+1][col] != 0 &&
                        !visited[row+1][col]) {
                    queue.offer(new Coordinates(row+1, col,curr.distance +  1));
                }
                //right
                if(col+1 <= numCols-1 && matrix[row][col+1] != 0 &&
                        !visited[row][col+1]) {
                    queue.offer(new Coordinates(row, col+1,curr.distance + 1));
                }
            }
        }

        return -1;

    }



    public static void main(String[] args) {
        System.out.println(shortestPathInMatrix(input, input.length, input[0]
                .length));
    }
}

class Coordinates {

    int row;
    int col;
    int distance;
    public Coordinates(int row, int col, int distance) {
        this.row = row;
        this.col = col;
        this.distance = distance;
    }

}





