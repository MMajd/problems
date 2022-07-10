/** 
@link https://www.hackerrank.com/challenges/the-blunder/problem?isFullScreen=true

Samantha was tasked with calculating the average monthly salaries for all employees in the EMPLOYEES table, but did not realize her keyboard's  key was broken until after completing the calculation. She wants your help finding the difference between her miscalculation (using salaries with any zeros removed), and the actual average salary.

Write a query calculating the amount of error (i.e.:  average monthly salaries), and round it up to the next integer.

Input Format
The EMPLOYEES table is described as follows:
(id salar, name)


ex input 

actual 
id          name        salary 
1           x           1000
2           y           20002
2           y           23020

error input 
id          name        salary 
1           x           1
2           y           22
2           y           232


*/ 



SELECT CEIL(AVG(SALARY)-AVG(REPLACE(SALARY,"0",""))) FROM EMPLOYEES ; 

