import java.util.ArrayList;

public class Solution {
	private static int m, n;
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
        if (matrix != null && matrix.length > 0) {
        	m = matrix.length;
        	int[] firstRow = matrix[0];
        	if (firstRow != null && firstRow.length > 0) {
        		n = firstRow.length;
        		appendOutput(list, matrix, 0);
        	}	
        }
    	m = 0;
    	n = 0;
    	return list;
    }
    
    private void appendOutput(ArrayList<Integer> list, int[][] matrix, int topLeft) {
    	int width = n - topLeft * 2;
    	int height = m - topLeft * 2;
    	// The following 2 branches can be merged into the last else branch, but that'll
    	// be harder to read
    	if (width == 1) {
    		for (int i = topLeft; i < topLeft + height; i++) {
    			list.add(matrix[i][topLeft]);
    		}
    	} else if (height == 1) {
    		for (int i = topLeft; i < topLeft + width; i++) {
    			list.add(matrix[topLeft][i]);
    		}
    	} else {	// width  > 1 && height > 1;
    		// These 2 values are inclusive
    		int rightEdge = topLeft + width - 1;
    		int bottomEdge = topLeft + height - 1;
    		for (int i = topLeft; i <= rightEdge; i++) {
    			list.add(matrix[topLeft][i]);
    		}
    		for (int i = topLeft + 1; i <= bottomEdge; i++) {
    			list.add(matrix[i][rightEdge]);
    		}
    		for (int i = rightEdge - 1; i >= topLeft; i--) {
    			list.add(matrix[bottomEdge][i]);
    		}
    		for (int i = bottomEdge - 1; i > topLeft; i--) {
    			list.add(matrix[i][topLeft]);
    		}
    		topLeft++;
    		int size = Math.min(m, n);
    		if (topLeft < (size + 1)/2) {
    			appendOutput(list, matrix, topLeft);	
    		}
    	}
    }
}
