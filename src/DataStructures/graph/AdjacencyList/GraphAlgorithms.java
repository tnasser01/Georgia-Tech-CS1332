package Graphs.AdjacencyList;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * My implementation of 4 graph algorithms:
 * BFS
 * DFS
 * Dijkstra's Shortest Path
 * Prim's Minimum Spanning Tree
 *
 * @author Tania Nasser
 * @userid tnasser3
 * @GTID 903397126
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When exploring a vertex, make sure to explore in the order that the
     * adjacency list returns the neighbors to you. Failure to do so may cause
     * you to lose points.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List},
     * {@code java.util.Queue}, and any classes that implement the
     * aforementioned interfaces, as long as it is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start,
                                                         Graph<T> graph) {

        if (start == null || graph == null) {
            throw new IllegalArgumentException("Start vertex and graph cannot"
                    + "be null");
        }

        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Start vertex is not in the "
                   + "graph");
        }

        Queue<Vertex<T>> queue = new LinkedList<Vertex<T>>();
        Set<Vertex<T>> visited = new HashSet<Vertex<T>>();
        List<Vertex<T>> bfs = new ArrayList<Vertex<T>>();
        queue.offer(start);

        while (!queue.isEmpty() && visited.size() != graph.getVertices().size()) {
            Vertex<T> curr = queue.poll();
            if (!visited.contains(curr)) {
                visited.add(curr);
                bfs.add(curr);
                List<Edge<T>> edges = graph.getAdjList().get(curr);
                for (Edge<T> edge : edges) {
                    if (!visited.contains(edge.getV())) {
                        queue.offer(edge.getV());
                    }
                }
            }
        }
        return bfs;
    }




    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When deciding which neighbors to visit next from a vertex, visit the
     * vertices in the order presented in that entry of the adjacency list.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * most if not all points for this method.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List}, and
     * any classes that implement the aforementioned interfaces, as long as it
     * is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start, Graph<T> graph) {

        if (start == null || graph == null)  {
            throw new IllegalArgumentException("Start vertex and graph cannot be null");
        }

        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Start vertex is not in the graph");
        }

        Set<Vertex<T>> visited = new HashSet<Vertex<T>>();
        List<Vertex<T>> dfs = new ArrayList<Vertex<T>>();

        dfsRecursive(start, graph, visited, dfs);

        return dfs;
    }

    /**
     *
     * Private recursive helper method dfsRecursive.  The depth first search
     * traversal is completed by recursive calls to dfsRecursive.
     *
     * The recursive calls act as the stack for dfs.
     *
     * @param node current node of traversal
     * @param graph graph to traverse
     * @param visited set of visited nodes
     * @param dfs the depth first search traversal list
     * @param <T> this method uses the generic type T
     */
    private static <T> void dfsRecursive(Vertex<T> node, Graph<T> graph, Set<Vertex<T>> visited, List<Vertex<T>> dfs) {

        if (!visited.contains(node)) {
            visited.add(node);
            dfs.add(node);

            for (Edge<T> edge : graph.getAdjList().get(node)) {
                if (!visited.contains(edge.getV())) {
                    dfsRecursive(edge.getV(), graph, visited, dfs);
                }
            }
        }

    }


    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and {@code java.util.Set} and any class that
     * implements the aforementioned interfaces, as long as it's efficient.
     *
     * You should implement the version of Dijkstra's where you terminate the
     * algorithm once either all vertices have been visited or the PQ becomes
     * empty.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input is null, or if start
     *  doesn't exist in the graph.
     * @param <T> the generic typing of the data
     * @param start index representing which vertex to start at (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every other node
     *         in the graph
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {

        if (start == null || graph == null)  {throw new IllegalArgumentException
                ("Start vertex and graph cannot be null"); }
        if (!graph.getVertices().contains(start)) {throw new IllegalArgumentException("Start vertex is not in the graph"); }

        Map<Vertex<T>, Integer> costs = new HashMap<>();
        Set<Vertex<T>> visited = new HashSet<>();
        PriorityQueue<Edge<T>> pq = new PriorityQueue<>();

        for (Vertex<T> v : graph.getVertices()) {
            costs.put(v, Integer.MAX_VALUE);
        }
        pq.add(new Edge<T>(start, start, 0));

        while (!(pq.isEmpty()) && visited.size() < graph.getVertices().size()) {
            Edge<T> e = pq.poll();
            visited.add(e.getU());  //we cant check if visited contains the vertex before adding to visited because our start edge is a fake edge that has the same vertex for U and V

            if (e.getWeight() < costs.get(e.getV())) {
                costs.put(e.getV(), e.getWeight());
                for (Edge<T> incidentEdge : graph.getAdjList().get(e.getV())) {//Since adding to a PQ is costly (logn) we SHOULD check if visited contains vertex v of the current edge.  If not, the worst case runtime increases. For BFS and DFS, that check is not required because adding to a queue or stack is O(1), but it is still slightly more efficient
                    if (!visited.contains(incidentEdge.getV())) {
                        pq.add(new Edge<T>(incidentEdge.getU(), incidentEdge.getV(), incidentEdge.getWeight() + e.getWeight())); //add incident edge with updated weight
                    }
                }
            }
        }

        return costs;

    }

    /**
     * Runs Prim's algorithm on the given graph and return the Minimal
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Graphs.AdjacencyList.Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * opposite edge to the set as well. This is for testing purposes.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops into the MST.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface, as long as it's efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for this method (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {

        if (start == null || graph == null)  {
            throw new IllegalArgumentException("Start vertex and graph cannot be null"); }

        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Start vertex is not in the graph"); }

        Set<Edge<T>> mst = new HashSet<Edge<T>>();
        Set<Vertex<T>> visitedVertices = new HashSet<Vertex<T>>();
        Queue<Edge<T>> pq = new PriorityQueue<Edge<T>>();

        visitedVertices.add(start);
        for (Edge<T> edge : graph.getAdjList().get(start)) {
            pq.add(edge);
        }

        //Start traversal
        while (!pq.isEmpty() && visitedVertices.size() != graph.getVertices().size()) {
            Edge<T> currEdge = pq.poll();

            if (!visitedVertices.contains(currEdge.getV())) {
                mst.add(currEdge);
                mst.add(new Edge(currEdge.getV(), currEdge.getU(), currEdge.getWeight()));
                for (Edge<T> edge : graph.getAdjList().get(currEdge.getV())) {
                    if (!visitedVertices.contains(edge.getV())) {
                        pq.add(edge);
                    }
                }
            }
            visitedVertices.add(currEdge.getV());
        }

        //check if MST is valid (# edges = V - 1)
        if (mst.size() != (graph.getVertices().size() - 1) * 2) {
            return null;
        }

        return mst;

    }
}