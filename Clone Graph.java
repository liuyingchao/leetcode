/**
 * 
 *  OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    	if (node == null) {
            return null;
		}
		UndirectedGraphNode result = new UndirectedGraphNode(node.label);
		// It's important to use HashMap for storing Graph "visited array" 
		HashMap<Integer, UndirectedGraphNode> visited = new 
		                HashMap<Integer, UndirectedGraphNode>();
		visited.put(node.label, result);
		dfs(node, result, visited);
		return result;
    }
    
    private void dfs(UndirectedGraphNode node, UndirectedGraphNode result, 
	            HashMap<Integer, UndirectedGraphNode> visited) {
		for (UndirectedGraphNode neighbor : node.neighbors) {
			if (visited.containsKey(neighbor.label)) {
			    result.neighbors.add(visited.get(neighbor.label));
			} else {
			    UndirectedGraphNode result_neighbor = 
			                    new UndirectedGraphNode(neighbor.label);
			    result.neighbors.add(result_neighbor);
			    visited.put(neighbor.label, result_neighbor);
			    dfs(neighbor, result_neighbor, visited);
			}
		}
	}
}
