/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
*/
public class Solution {
    public String convert(String s, int nRows) {
        if (nRows <= 0) { throw new IllegalArgumentException("nRows must be greater tthan 0"); }
        if (s == null || s.length() == 0 || nRows == 1) return s;
        
        StringBuffer[] bufs = new StringBuffer[nRows];
        // Important to initialize in this loop
        for (int i = 0; i < nRows; i++) {
            bufs[i] = new StringBuffer();
        }
        int len = s.length(); 
        int i = 0;
        for (i = 0; i < Math.min(len, nRows); i++) {
            bufs[i].append(s.charAt(i));
        }
        if (len > nRows) {
            while (i < len) {
                int offset = i - nRows;
                int batch = offset / (nRows - 1);
                int mod = offset % (nRows - 1);
                if (batch % 2 == 0) {
                    bufs[nRows - mod - 2].append(s.charAt(i));
                } else {
                    bufs[mod + 1].append(s.charAt(i));
                }
                i++;    
            }
        }
        
        StringBuffer sb = new StringBuffer();
        for (i = 0; i < nRows; i++) {
            sb.append(bufs[i].toString());
        }
        return sb.toString();
    }
}
