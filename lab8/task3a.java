package lab8;

import java.io.*;
import java.util.*;

public class task3a {
    // Adjacency list representation of the MST (used for path finding)
    static ArrayList<ArrayList<Edge>> graph;

    /**
     * Edge class represents a weighted edge in the graph
     * Implements Comparable to allow sorting edges by weight (for Kruskal's algorithm)
     */
    static class Edge implements Comparable<Edge> {
        int from, to, cost;  // Source node, destination node, and edge weight
        int index;           // Original index of this edge in input (for tracking which edges are in MST)

        Edge(int f, int t, int c, int i) {
            this.from = f;
            this.to = t;
            this.cost = c;
            this.index = i;
        }

        @Override
        public int compareTo(Edge other) {
            // Sort edges by cost in ascending order (needed for Kruskal's algorithm)
            return this.cost - other.cost;
        }
    }

    /**
     * Disjoint Set Union (DSU) / Union-Find data structure
     * Used in Kruskal's algorithm to efficiently detect cycles
     * 
     * Key operations:
     * - find(x): Returns the root/representative of the set containing x
     * - union(x, y): Merges the sets containing x and y
     */
    static class DSU {
        int[] parent;  // parent[i] = parent of node i
        int[] rank;    // rank[i] = approximate depth of tree rooted at i (for optimization)

        DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            // Initially, each node is its own parent (separate sets)
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        /**
         * Find operation with path compression optimization
         * Finds the root of the set containing x
         * Path compression: Makes all nodes on the path point directly to root
         */
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);  // Path compression
            }
            return parent[x];
        }

        /**
         * Union operation with union by rank optimization
         * Merges the sets containing x and y
         * Returns true if union was performed, false if x and y were already in same set
         */
        boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            // Already in same set - adding this edge would create a cycle
            if (rootX == rootY)
                return false;

            // Union by rank: Attach smaller tree under larger tree
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
    }

    /**
     * Kruskal's algorithm to build a Minimum Spanning Tree (MST)
     * 
     * Algorithm:
     * 1. Sort all edges by weight (already done before calling this)
     * 2. Process edges in order, adding each edge if it doesn't create a cycle
     * 3. Stop when we have n-1 edges (complete MST)
     * 
     * @param n Number of nodes
     * @param edges List of all edges (sorted by weight)
     * @param inMST Boolean array to mark which edges are in the MST
     * @param mstEdges List to store the MST edges
     * @return Total cost of the MST
     */
    static long kruskal(int n, List<Edge> edges, boolean[] inMST, List<Edge> mstEdges) {
        DSU dsu = new DSU(n);
        long mstCost = 0;

        // Try each edge in sorted order (greedy approach)
        for (Edge edge : edges) {
            // If adding this edge doesn't create a cycle, include it in MST
            if (dsu.union(edge.from, edge.to)) {
                inMST[edge.index] = true;  // Mark this edge as part of MST
                mstEdges.add(edge);
                mstCost += edge.cost;
                
                // MST has exactly n-1 edges, stop when complete
                if (mstEdges.size() == n - 1)
                    break;
            }
        }

        return mstCost;
    }

    /**
     * DFS to find the path between two nodes in the MST tree
     * Collects all edge weights along the path
     * 
     * Why this works: MST is a tree, so there's exactly one path between any two nodes
     * 
     * @param curr Current node being visited
     * @param target Destination node we're trying to reach
     * @param parent Parent node (to avoid going backwards)
     * @param pathWeights List to collect edge weights along the path
     * @return true if path found, false otherwise
     */
    static boolean findPath(int curr, int target, int parent, ArrayList<Integer> pathWeights) {
        // Base case: reached the target node
        if (curr == target)
            return true;

        // Try all edges connected to current node
        for (Edge edge : graph.get(curr)) {
            // Determine the other end of the edge
            int next = (edge.from == curr) ? edge.to : edge.from;
            
            // Skip parent to avoid going backwards
            if (next == parent)
                continue;

            // Add this edge to path and recursively explore
            pathWeights.add(edge.cost);
            if (findPath(next, target, curr, pathWeights))
                return true;
            
            // Backtrack: remove edge if this path doesn't lead to target
            pathWeights.remove(pathWeights.size() - 1);
        }
        return false;
    }

    /**
     * Find the second-best MST using the cycle property
     * 
     * KEY INSIGHT: Second-best MST is obtained by:
     * 1. Taking the best MST
     * 2. Removing exactly one edge from it
     * 3. Adding exactly one edge not in MST to maintain connectivity
     * 
     * Algorithm:
     * For each edge NOT in the MST:
     *   - Adding this edge to MST creates a cycle
     *   - Find the path in MST between this edge's endpoints (forms cycle when edge added)
     *   - To break the cycle, we must remove one edge from this path
     *   - Try replacing each MST edge on the path with this non-MST edge
     *   - Track the minimum cost among all such valid replacements
     * 
     * @param n Number of nodes
     * @param allEdges All edges in the graph
     * @param inMST Boolean array marking which edges are in the best MST
     * @param minMstCost Cost of the best MST
     * @return Cost of second-best MST, or Long.MAX_VALUE if none exists
     */
    static long findSecondBestMST(int n, List<Edge> allEdges, boolean[] inMST, long minMstCost) {
        long secondBest = Long.MAX_VALUE;

        // Try each edge that is NOT in the best MST
        for (Edge nonMSTEdge : allEdges) {
            if (inMST[nonMSTEdge.index])
                continue;  // Skip edges already in MST

            // Find the unique path in MST between this edge's endpoints
            // This path + the non-MST edge forms a cycle
            ArrayList<Integer> pathWeights = new ArrayList<>();
            findPath(nonMSTEdge.from, nonMSTEdge.to, -1, pathWeights);

            // Try removing each MST edge on this path and adding the non-MST edge instead
            for (int mstEdgeWeight : pathWeights) {
                // Only valid if new tree has strictly greater cost than best MST
                // (ensures we get second-best, not another optimal MST)
                if (nonMSTEdge.cost > mstEdgeWeight) {
                    // New MST cost = old cost - removed edge + added edge
                    long newCost = minMstCost - mstEdgeWeight + nonMSTEdge.cost;
                    secondBest = Math.min(secondBest, newCost);
                }
            }
        }

        return secondBest;
    }

    public static void main(String[] args) throws IOException {
        // Input reading using BufferedReader and StringTokenizer
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // Read number of nodes (N) and number of edges (M)
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // Store all edges
        List<Edge> edges = new ArrayList<>();

        // Read each edge: u_i, v_i, w_i (node u, node v, weight w)
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(u, v, weight, i));
        }

        // Sort edges by weight (required for Kruskal's algorithm)
        Collections.sort(edges);

        // Build the best (minimum cost) MST using Kruskal's algorithm
        boolean[] inMST = new boolean[m];  // Track which edges are in MST
        List<Edge> mstEdges = new ArrayList<>();  // Store MST edges
        long minMstCost = kruskal(n, edges, inMST, mstEdges);

        // Check if we got a complete MST (must have exactly n-1 edges)
        // If not, graph is disconnected - no MST exists
        if (mstEdges.size() != n - 1) {
            System.out.println(-1);
            return;
        }

        // Build adjacency list representation from MST edges
        // This is needed for finding paths between nodes in the MST tree
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());

        // Add each MST edge to adjacency list (bidirectional)
        for (Edge e : mstEdges) {
            graph.get(e.from).add(e);
            graph.get(e.to).add(e);
        }

        // Find the second-best MST cost
        long secondBest = findSecondBestMST(n, edges, inMST, minMstCost);

        // Output result
        if (secondBest == Long.MAX_VALUE) {
            // No second-best MST exists (shouldn't happen for valid input)
            System.out.println(-1);
        } else {
            // Print the cost of second-best MST
            System.out.println(secondBest);
        }
    }
}