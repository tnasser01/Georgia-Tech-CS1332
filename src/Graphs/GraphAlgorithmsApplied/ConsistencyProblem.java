package Graphs.GraphAlgorithmsApplied;

import Graphs.AdjacencyList.Graph;

import java.util.*;

public class ConsistencyProblem {

    //These methods are solutions to the problems from Algorithm Design by
    // Tardos and Kleinberg chapter 3 on Graphs.

    /**
     *
     * Chapter 3 Graphs
     * Problem 4 - Determine if all judgements of specimen similarities are
     * consistent
     *
     * Given: n specimens
     *        m judgements of "same" or "different" for each pair of specimens
     *
     * Output: Returns a boolean true is consistent and false if there is an
     * inconsistency in the judgements
     *
     * Algorithm:
     * -Create a graph G with specimens as nodes and draw an edge between
     * each pair of nodes if there exists a judgement between the two nodes
     * -Arbitrarily choose a start node and label it 'A'. Run BFS from start.
     *      For each node v belonging to edge u,v that is popped from queue:
     *         If judgement of u,v is same, label v same as u.
     *         Else label v opposite of u
     * -For each judgement in m judgements, check if both nodes u and v are
     * labeled correctly.
     *      If they are labeled incorrectly return false
     * -Return true (the judgements are consistent)
     *
     * If G is non connected then must run BFS once from each component.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the ConsistencyVertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */

    public static <T> boolean determineConsistency(ConsistencyVertex<T> start, Graph<T> graph, String[][] judgements) {

        List<ConsistencyVertex<T>> bfs = consistencyBfs(start, graph, judgements);

        for(int i=0; i < judgements.length; i++) {
            for(int j=i+1; j < judgements[0].length; j++) {

                if(judgements[i][j] == "same" &&  bfs.get(i).getLabel() != bfs.get(j).getLabel() ||
                   judgements[i][j] == "diff" && (bfs.get(i)).getLabel() == bfs.get(j).getLabel()) {
                    return false;
                }
            }
        }

        return true;
    }

    public static <T> List<ConsistencyVertex<T>> consistencyBfs(ConsistencyVertex<T> start, Graph<T> graph, String[][] judgements) {

        if (start == null || graph == null) {
            throw new IllegalArgumentException("Start ConsistencyVertex and graph cannot"
                    + "be null");
        }

        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Start ConsistencyVertex is not in the "
                    + "graph");
        }

        Queue<ConsistencyVertex<T>> queue = new LinkedList<ConsistencyVertex<T>>();
        Set<ConsistencyVertex<T>> visited = new HashSet<ConsistencyVertex<T>>();
        List<ConsistencyVertex<T>> bfs = new ArrayList<ConsistencyVertex<T>>();
//        start.setLabel('A');
//        queue.offer(start);
//
//        while (!queue.isEmpty() && visited.size() != graph.getVertices().size()) {
//            ConsistencyVertex<T> curr = queue.poll();
//            if (!visited.contains(curr)) {
//                visited.add(curr);
//                bfs.add(curr);
//                List<Edge<T>> edges = graph.getAdjList().get(curr);
//                for (Edge<T> edge : edges) {
//                    if (!visited.contains(edge.getV())) {
//                        if(judgements[(int)curr.getData()][(int)edge.getV().getData()] == "same") {
//                            ((ConsistencyVertex<T>)edge.getV()).setLabel(curr.getLabel());
//                        } else {
//                            char vLabel = (curr.getLabel() == 'A') ? 'B' : 'A';
//                        }
//                        queue.offer((ConsistencyVertex)edge.getV());
//                    }
//                }
//            }
//        }
//        return bfs;
//    }
        return bfs;
    }

}
