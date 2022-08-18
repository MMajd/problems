#!/bin/bash 

# @link https://www.hackerrank.com/challenges/bash-tutorials---arithmetic-operations/problem?isFullScreen=true

"""
A mathematical expression containing +,-,*,^, / and parenthesis will be provided. Read in the expression, then evaluate it. Display the result rounded to  decimal places.

Constraints
All numeric values are <= 999.

Sample Input 1: 5+50*3/20 + (19*2)/7
Sample Input 2: -105+50*3/20 + (19^2)/7
Sample Input 3: (-105.5*7+50*3)/20 + (19^2)/7

Sample Output 1: 17.929
Sample Output 2: -45.929
Sample Output 3: 22.146
"""



read math 
printf "%.3f\n" `echo "$math" | bc -l`

