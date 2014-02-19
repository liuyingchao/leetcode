/*
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.

Solution: First produce the gap array to see what's the net amount we have from each station. If the sum of the total gas
is negative, we know for sure we should return -1. Otherwise, this turns into Kadane's Largest Sum Contiguous Subarray
algorithm. We run that algorithm and capture the moment where the running sum turns negative. Since we are guaranteed to
have a unique solution, the next station after the negative running sum should be the index we are looking for. That's
why we store prevIndex. If prevIndex remains negative, we know we never run into a negative running sum, which means
the last element must be negative, so we return n-1 in that case.
*/
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null) return -1;
        int n = gas.length;
        int[] rem = new int[n];
        int remTotal = 0;
        for (int i = 0; i < n; i++) {
            rem[i] = gas[i] - cost[i];
            remTotal += rem[i];
        }
        
        if (remTotal < 0) {
            return -1;
        }
        
        int maxEndingHere = 0;
        int maxSofar = 0;
        int prevIndex = -1;
        for (int i = 0; i < n; i++) {
            maxEndingHere = maxEndingHere + rem[i];
            if (maxEndingHere < 0) {
                maxEndingHere = 0;
                prevIndex = i;
            }
            if (maxSofar < maxEndingHere) {
                maxSofar = maxEndingHere;
            }
        }
        
        if (prevIndex < 0) {
            return n-1;
        } else {
            return prevIndex + 1;
        }
    }
}
