/*
Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region .

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X

Solution: the idea is described at: http://yucoding.blogspot.com/2013/08/leetcode-question-131-surrounded-regions.html
The key observation is: instead of start BFS randomnly from inside the board, we start BFS from the boarders, which
gives us a clear direction of what to do compared with treating everything 'O' the same and making decision in a messy way.
Mark the result of BFS with temp char 'T', then it's trivial to flip 'O' and restore 'O'
*/

public class Solution {
    private int m, n;
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;
        m = board.length;
        n = board[0].length;
        for (int i = 0; i < m; i++) {
            bfs(board, i, 0);
            bfs(board, i, n-1);
        }
        for (int i = 1; i < n-1; i ++) {
            bfs(board, 0, i);
            bfs(board, m-1, i);
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'T') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    private void bfs(char[][] board, int x, int y) {
        Queue<Integer> queue = new LinkedList<Integer>();
        check(board, x, y, queue);
        while (!queue.isEmpty()) {
            int num = queue.poll();
            x = num / n;
            y = num % n;
            check(board, x + 1, y, queue);
            check(board, x - 1, y, queue);
            check(board, x, y + 1, queue);
            check(board, x, y - 1, queue);
        }
    }
    
    private void check(char[][] board, int x, int y, Queue<Integer> queue) {
        if (x < 0 || x >= m || y < 0 || y >= n || board[x][y] != 'O') return;
        queue.add(x*n + y);
        board[x][y] = 'T';
    }
}

/*
IMPORTANT:
The following code works theoretically, and doesn't maintain an explicit Queue. However,
it runs into stack overflow in practice, because it depends on recursion to go deep on 
big connected regions instead of relying on BFS' checking over queue elements iteratively.

public class Solution {
    private int m, n;
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;
        m = board.length;
        n = board[0].length;
        for (int i = 0; i < m; i++) {
            bfs(board, i, 0);
            bfs(board, i, n-1);
        }
        for (int i = 1; i < n-1; i++) {
            bfs(board, 0, i);
            bfs(board, m-1, i);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                if (board[i][j] == 'T') board[i][j] = 'O';
            }
        }
    }
    
    private boolean isValid(char[][] board, int row, int col) {
        return row >= 0 && row < m && col >= 0 && col < n;
    }
    
    private void bfs(char[][] board, int row, int col) {
        if (isValid(board, row, col) && board[row][col] == 'O') {
            board[row][col] = 'T';
            bfs(board, row-1, col);
            bfs(board, row+1, col);
            bfs(board, row, col-1);
            bfs(board, row, col+1);
        }
    }
}

*/
// BFS--copied from https://github.com/guolinaileen/abc/blob/master/Surrounded%20Regions_BFS.java
public class Solution {
    Queue<Integer> queue=new LinkedList<Integer>();
    public void solve(char[][] board) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(board.length==0) return ; 
        for(int i=0; i<board[0].length; i++)
        {
            process(board, 0, i);
            process(board, board.length-1, i);
        }
        for(int i=0; i<board.length; i++)
        {
            process(board, i, 0);
            process(board, i, board[0].length-1);
        }
        for(int i=0; i<board.length; i++)
        {
            for(int j=0; j<board[0].length; j++)
            {
                if(board[i][j]=='D') board[i][j]='O'; 
                else if(board[i][j]=='O') board[i][j]='X';
            }
        }
    }
    void check(char[][] board, int i, int j)
    {
        if(i<0|| j<0 || i>=board.length||j>=board[0].length||board[i][j]!='O') return ; 
        queue.offer(i*board[0].length+j);
        board[i][j]='D';
    }
    void process(char[][] board, int i, int j)
    {
        check(board, i,  j); 
        while(!queue.isEmpty())
        {
            int num=queue.poll();
            i=num/board[0].length;
            j=num%board[0].length;
            check(board, i+1, j);
            check(board, i-1, j);
            check(board, i, j+1);
            check(board, i, j-1);
        }

    }
}
