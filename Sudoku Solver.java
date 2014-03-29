/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.

Difficulty: Hard
Solution: Straight DFS, even though I'm using an arrayList, which appears like BFS.
Many online solutions validate against the board on the fly, while I build 3 Lists of
HashSet, which requires certain code, but performes better.

Another version is copied after my code. isValid() function is the muscle. Notice
we can boundle all 3 checks in one loop. However, it's redudant to use int as the loop
index in dfs. My code is better by looping on char, and avoid all those +'0'/-'0'
*/
public class Solution {
    List<Set<Character>> rowHash, colHash, sHash;
    public void solveSudoku(char[][] board) {
        rowHash = new ArrayList<Set<Character>>();
        colHash = new ArrayList<Set<Character>>();
        sHash = new ArrayList<Set<Character>>();
        for (int i = 0; i < 9; i++) {
            rowHash.add(new HashSet<Character>());
            colHash.add(new HashSet<Character>());
            sHash.add(new HashSet<Character>());
        }
        // Fill in the candidates
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    list.add(i*9 + j);
                } else {
                    rowHash.get(i).add(board[i][j]);
                    colHash.get(j).add(board[i][j]);
                    sHash.get(getSIndex(i, j)).add(board[i][j]);
                }
            }
        }
        dfs(board, list, 0, list.size());
    }
    
    private int getSIndex(int row, int col) {
        return 3 * (row/3) + col/3;
    }
    
    private boolean isValid(char c, int row, int col) {
        return !rowHash.get(row).contains(c) &&
            !colHash.get(col).contains(c) &&
            !sHash.get(getSIndex(row, col)).contains(c);
    }
    
    private boolean dfs(char[][] board, List<Integer> list, int cur, int len) {
        if (cur == len) return true;
        int pos = list.get(cur);
        int row = pos / 9;
        int col = pos % 9;
        for (char c = '1'; c <= '9'; c++) {
            if (isValid(c, row, col)) {
                board[row][col] = c;
                fillHash(c, row, col);
                if (dfs(board, list, cur+1, len)) return true;
                board[row][col] = '.';
                resetHash(c, row, col);
            }
        }
        return false;
    }
    
    private void fillHash(char c, int row, int col) {
        rowHash.get(row).add(c);
        colHash.get(col).add(c);
        sHash.get(getSIndex(row, col)).add(c);
    }
    
    private void resetHash(char c, int row, int col) {
        rowHash.get(row).remove(c);
        colHash.get(col).remove(c);
        sHash.get(getSIndex(row, col)).remove(c);
    }
}

// Copied from http://blog.csdn.net/u011095253/article/details/9158497
public class Solution {
    public void solveSudoku(char[][] board) {
        ArrayList<Integer> empty = new ArrayList<Integer>();
        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
                if(board[i][j]=='.'){
                    empty.add(i*9+j);
                }
        int len = empty.size();
        dfs(empty,board,0,len);
    }
    
    public boolean dfs(ArrayList<Integer> empty, char[][] board, int cur, int len){
        if(cur==len) return true;
        int index = empty.get(cur);
        int row = index/9;
        int col = index%9;
        for(int v=1;v<=9;v++){
            if(isValid(v,row,col,board)){
                board[row][col] = (char)(v+'0');
                if(dfs(empty,board,cur+1,len))
                    return true;
                board[row][col] = '.';
            }
        }
        return false;
    }
    
    public boolean isValid(int v, int row, int col, char[][] board){
        for(int i=0;i<9;i++){
            if(board[row][i] - '0'==v) return false;
            if(board[i][col] - '0'==v) return false;
            int row_s = 3*(row/3) + i/3;
            int col_s = 3*(col/3) + i%3;
            if(board[row_s][col_s] - '0'==v) return false;
        }
        return true;
    }
}
