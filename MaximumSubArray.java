/**
 @link https://leetcode.com/problems/maximum-subarray
 @categories (divide-and-conquer/recursion/dp/segement-tree) 

 Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
A subarray is a contiguous part of an array.

Example 1:
    Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
    Output: 6
    Explanation: [4,-1,2,1] has the largest sum = 6.

Example 2:
    Input: nums = [1]
    Output: 1

Example 3:
    Input: nums = [5,4,-1,7,8]
    Output: 23
 

Constraints:
    1 <= nums.length <= 10^5
    -10^4 <= nums[i] <= 10^4
 

Follow up: 
 If you have figured out the O(n) solution, 
try coding another solution using the divide and conquer approach, which is more subtle.
  
*/ 

// Simple 
class Solution {
    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE,i=0,sum=0;

        for (int i=0; i<nums.length; i++) { 
            sum += nums[i];
            ans = Math.max(ans,sum);

            if(sum<0) sum=0;
        }

        return ans;
    }
}

// Divide-and-Conquer
class Solution {
    public int maxSubArray(int[] nums) {
        return dividAndConquer(nums, 0, nums.length-1); 
    }

    private int dividAndConquer(int[] nums, int left, int right) { 
        if (left == right) return nums[left]; 

        int mid = left + (right-left) >> 1; 
        int lsum = dividAndConquer(nums, left, mid); 
        int rsum = dividAndConquer(nums, mid+1, right); 
        int csum = maxCrossArray(nums, left, right, mid); 
    }

    private int maxCrossArray(int[] nums, int left, int right, int mid) { 
        int currSum = 0; 
        int leftSum = Integer.MIN_VALUE; 

        for (int i=mid; i>=low; --i) {
            currSum = currSum + nums[i]; 
            leftSum = Math.max(currSum, leftSum);
        }

        currSum = 0; 
        int rightSum = Integer.MIN_VALUE; 

        for (int i=mid+1; i<=high; i++) {
            currSum = currSum + nums[i]; 
            rightSum = Math.max(currSum, rightSum);
        }

        return leftSum + rightSum; 
    }
}


// Segment-Tree based solution
class Solution {
    public int maxSubArray(int[] nums) {
        long[] arr = new long[nums.length];

        for(int i=0;i<nums.length;i++) arr[i] = (long)nums[i];

        MaxSegTree maximalSumSegmentTree = new MaxSegTree(arr);

        return (int)maximalSumSegmentTree.segArr[0].maxSum;
    }
}

private class MaxSegTree{
    int dataLen;
    Node[] segArr;

    public MaxSegTree(long[] arr) {
        dataLen = arr.length;

        int segHeight = (int)Math.ceil(Math.log(dataLen)/Math.log(2));
        int segLen = 2*(1<<segHeight)-1;

        segArr = new Node[segLen];

        build(0,dataLen-1,0,arr);
    }

    public Node build(int start, int end, int pos, long[] arr) {
        if(start == end) {
            segArr[pos] = new Node(arr[start], arr[start], arr[start]);
            return segArr[pos]; 
        }

        int mid = start + (end - start) >> 1; 

        Node l = build(start, mid, pos*2+1, arr);
        Node r = build(mid+1, end, pos*2+2, arr);

        segArr[pos] = new Node(
                    Math.max(l.preSum, l.totSum+r.preSum), 
                    Math.max(r.sufSum, r.totSum+l.sufSum), 
                    l.totSum+r.totSum, 
                    Math.max(l.sufSum+r.preSum, Math.max(l.maxSum,r.maxSum))
                );

        return segArr[pos]; 
    }
}

private class Node {
    long preSum;
    long sufSum;
    long totSum;
    long maxSum;

    public Node(long p, long s, long t, long m) {
        preSum = p;
        sufSum = s;
        totSum = t;
        maxSum = m;
    }
}

