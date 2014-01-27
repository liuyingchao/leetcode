/**
 * Very verbose. Should be refactored.
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
