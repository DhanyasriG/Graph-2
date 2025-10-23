
//Time Complexity: O(V + E), where V is the number of nodes and E is the number of edges in the graph.
//Space Complexity: O(V) for the colors array and recursion stack in the worst case.

// Did this code successfully run on Leetcode : Yes

//We use DFS to color connected components in the graph.
//Each node is assigned a color representing its connected component.
//we then group nodes by their colors to determine the size of each component.
//Next, we count how many initially infected nodes are present in each component.
//Finally, we determine which node to remove to minimize the spread of malware based on component sizes and infection counts.

class Solution {
    int colors[];
    int n;
    public int minMalwareSpread(int[][] graph, int[] initial) {
        this.n=graph.length;
        this.colors=new int[n];
        Arrays.fill(colors,-1);
        int cl=0;
        //coloring the graph using DFS
        for(int i=0;i<n;i++){
            helper(graph,cl,i);
            cl++;
        }

        int groups[]=new int[cl];
        for(int i=0;i<n;i++){
            groups[colors[i]]++;
        }

        int infected[]=new int[cl];
        for(int node:initial){
            infected[colors[node]]++;
        }
        int result = Integer.MAX_VALUE;

        // Find the best node to remove
        for(int node : initial){
            if(infected[colors[node]] == 1){
                if(result == Integer.MAX_VALUE){
                    result = node;
                }else if(groups[colors[node]] > groups[colors[result]]){
                    result = node;
                }else if(groups[colors[node]] == groups[colors[result]]){
                    result = Math.min(result, node);
                }
            }
        }
        // If no such node found, return the minimum node from initial
        if(result == Integer.MAX_VALUE){
            for(int node: initial){
                result = Math.min(result, node);
            }
        }

        return result;
    }

    void helper(int graph[][],int cl,int i){
        if(colors[i] != -1) return;

        colors[i] = cl;
        for(int j=0; j<n; j++){
            if(i == j) continue;
            if(graph[i][j] == 1){
                helper(graph, cl,j);
            }
        }
    }
}