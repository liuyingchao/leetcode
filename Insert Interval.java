/**
 *
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].

 * 
 * Solution: the key idea is to use a flag "inserted" to mark what state we are in. With that info,
 * we can safely adding existing intervals without change or do the merge as necessary.
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
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        
        // Backup to avoid polluting the input "newInterval"
        Interval copy = new Interval(newInterval.start, newInterval.end);
        boolean inserted = false;
        
        for (Interval interval : intervals) {
        	if (inserted || interval.end < newInterval.start) { // After OR BEFORE reaching the insertion point
        		result.add(interval);
        	} else if (copy.end < interval.start) { // We run into an interval behind the copy ==> insert copy and mark inserted
        		result.add(copy);
        		result.add(interval);
        		inserted = true;
        	} else {    // Overlapping ==> keep merging
        		copy.start = Math.min(interval.start, copy.start);
        		copy.end = Math.max(interval.end, copy.end);
        	}
        }
        if (!inserted) {    // This can be true if the newInterval is behind everybody (as in branch 1) or is eating up every existing intervals(as in branch 3)
        	result.add(copy);
        }
        return result;
    }    
}


//  The vollowing version is ery verbose. Should be refactored.
public class Solution {
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        if (intervals == null || intervals.size() == 0) {
        	result.add(newInterval);        	
        } else {
        	int N = intervals.size();
        	if (newInterval.end < intervals.get(0).start) {
        		result.add(newInterval);
        		result.addAll(intervals);
        	} else if (newInterval.start > intervals.get(N-1).end) {
        		result.addAll(intervals);
        		result.add(newInterval);
        	} else {
        		int i = 0;
        		boolean merged = true;
        		Interval mergedInterval = new Interval();
        		Interval current;
        		while (i < N) {
        			current = intervals.get(i);
        			if (intersect(current, newInterval)) {
        				merged = true;        				
        				mergedInterval.start = Math.min(current.start, newInterval.start);
        				mergedInterval.end = Math.max(current.end, newInterval.end);       				
        				break;
        			} else if (current.start > newInterval.end) {
        				result.add(newInterval);
        				merged = false;
        				break;
        			} else {
        				result.add(current);
        			}
        			i++;
        		}
        		
        		if (merged) {
        			i++;
        			while (i < N && intervals.get(i).start <= mergedInterval.end) {
        				current = intervals.get(i);
        				mergedInterval.end = Math.max(current.end, newInterval.end);
        				i++;
        			}
        			result.add(mergedInterval);
        		}
        		
        		while (i < N) {
    				result.add(intervals.get(i));
    				i++;
    			}
        	}
        }
        return result;
    }
    
    private boolean intersect(Interval a, Interval b) {
    	if ((a.start >= b.start && a.start <= b.end)
    			|| (b.start >= a.start && b.start <= a.end)) {
    		return true;
    	} else {
    		return false;
    	}
    }
}
