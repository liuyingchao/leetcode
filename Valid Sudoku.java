/*
Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.

Difficulty: Medium

Solution: The key is to consolidate on the "process" funciton to make the code way much cleaner than big inner loop body
*/
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0] == null || board[0].length != 9) return false;
        Set<Character> hash = new HashSet<Character>();
        char c;
        boolean valid = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                valid = process(board[i][j], hash);
                if (!valid) return false;    
            }
            hash.clear();
        }
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                valid = process(board[j][i], hash);
                if (!valid) return false;
            }
            hash.clear();
        }
        
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                for (int a = i; a < i+3; a++) {
                    for (int b = j; b < j+3; b++) {
                        valid = process(board[a][b], hash);
                        if (!valid) return false;
                    }
                }
                hash.clear();
            }
        }
        
        return true;
    }
    
    private boolean process(char c, Set<Character> hash) {
        if (c >= '0' && c <= '9') {
            if (hash.contains(c)) {
                return false;
            } else {
                hash.add(c);
                return true;
            }
        } else {
            return c == '.';
        }
    }
}
