#!/bin/bash

"""
Given  integers, compute their average, rounded to three decimal places.

Input Format
The first line contains an integer, .
Each of the following  lines contains a single integer.

Output Format
Display the average of the  integers, rounded off to three decimal places.


Sample Input: 4 1 2 9 8
Sample Output: 5.000
"""




# @link https://www.hackerrank.com/challenges/bash-tutorials---compute-the-average/problem?isFullScreen=true&h_r=next-challenge&h_v=zen

# My solution 
# read len 
# counter=0
# math=0

# while (( $counter < $len ))
# do 
#     read curr
#     math=$((math + curr))
#     counter=$((counter + 1))
# done 

# printf "%.3f" `echo $math/$len | bc -l`


read N
s=0

for i in $(cat)
do
    s=$((s+i))
done

printf "%.3f" `echo $s/$N | bc -l`

