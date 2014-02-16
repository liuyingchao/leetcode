/**
 * Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
 * 
 * Solution: sort on the start time. Then merge by scanning the list.
 * Start using the first element as the merge base. The key invariant is:
 * add a result elment when we can no longer merge, but always keep one
 * element as the candidate of merge. Therefore, it's safe to instantiate
 * an interval element outside the lopp with (start, end) pair, regardless of
 * how we reach that state.
 * 
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval a, Interval b) {
            if (a.start < b.start) {
                return -1;
            } else if (a.start > b.start) {
                return 1;
            } else {
                return 0;
            }
        }
    }
    
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        if (intervals == null) {
            return null;
        }
        
        Collections.sort(intervals, new IntervalComparator());
        
        ArrayList<Interval> res = new ArrayList<Interval>();
        if (intervals.size() > 0) {
            Interval head = intervals.get(0);
            int start = head.start;
            int end = head.end;
            Interval current;
            for (int i = 1; i < intervals.size(); i++) {
                Interval later = intervals.get(i);
                if (later.start > end) {    // Now we run into a new interval. Instantiate and add the most recent one into the result list.
                    current = new Interval(start, end);
                    res.add(current);
                    start = later.start;
                    end = later.end;
                } else {    // Keep merging
                    if (later.end > end) {
                        end = later.end;
                    }
                }
            }
            // Handle the last candiate
            current = new Interval(start, end);
            res.add(current);
        }
        return res;
    }
}
