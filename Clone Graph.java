/**
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
