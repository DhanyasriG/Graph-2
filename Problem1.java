
//Time Complexity: O(V+E) where V is the number of vertices and E is the number of edges.
//Space Complexity: O(V+E) for storing the graph and auxiliary arrays.

// Did this code successfully run on Leetcode : Yes

//we use tarjan's algorithm to find critical connections in a network.
//We maintain discovery and lowest arrays to track discovery times and the lowest reachable vertices.
//If a neighbor's lowest time is greater than the current node's discovery time, it's a critical edge.

//we have build the graph using adjacency list representation and then perform DFS to update the discovery and lowest arrays.
// If we find an edge that satisfies the critical connection condition, we add it to the result list.
//and update the lowest time of the current node based on its neighbors.

class Solution {
    int discovery[];
    int lowest[];
    int time;
    List<List<Integer>> res;
    HashMap<Integer,List<Integer>> map;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.discovery=new int[n];
        this.lowest=new int[n];
        this.time=0;
        this.res=new ArrayList<>();
        this.map=new HashMap<>();

        Arrays.fill(discovery,-1);

        for(int i=0;i<n;i++)
            map.put(i,new ArrayList<>());

        for(List<Integer> con:connections){
            int u=con.get(0);
            int v=con.get(1);
            map.get(u).add(v);
            map.get(v).add(u);
        }
        helper(0,-1);
        return res;
    }
    void helper(int u,int v){
        if(discovery[u]!=-1)
            return;
        discovery[u]=time;
        lowest[u]=time;
        time++;

        for(int ne:map.get(u)){
            if(ne==v) continue;
            helper(ne,u);
            if(lowest[ne]>discovery[u]){
                res.add(Arrays.asList(ne,u));
            }
            lowest[u]=Math.min(lowest[ne],lowest[u]);
        }
    }
}