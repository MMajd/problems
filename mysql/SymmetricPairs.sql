/* 

@link https://www.hackerrank.com/challenges/symmetric-pairs/problem?isFullScreen=true

You are given a table, Functions, containing two columns: X and Y.

FUNCTIONS Table
====================
| COULUMN | TYPE   |
|==================|
|    X    |  INT   |
|==================|
|    Y    |  INT   |
====================

Two pairs (X1, Y1) and (X2, Y2) are said to be symmetric pairs if X1 = Y2 and X2 = Y1.

Write a query to output all such symmetric pairs in ascending order by the value of X. List the rows such that X1 ≤ Y1.

Sample Input
===========
|  X |  Y |
=========== 
| 20 | 20 | 
| 20 | 20 | 
| 20 | 20 | 
| 20 | 21 | 
| 21 | 20 | 
| 22 | 23 | 
| 23 | 22 | 
===========

Sample Output
===========
|  X |  Y |
=========== 
| 20 | 20 | 
| 20 | 21 | 
| 22 | 23 | 
===========
*/

SELECT f1.X, f1.Y FROM Functions f1, Functions f2
    WHERE f1.X=f2.Y and f2.X=f1.Y
GROUP BY f1.X, f1.Y
Having COUNT(f1.X)>1 or f1.X<f1.Y
Order BY f1.X
