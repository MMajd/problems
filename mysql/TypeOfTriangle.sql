/* 

Write a query identifying the type of each record in the TRIANGLES table using its three side lengths. Output one of the following statements for each record in the table:

Equilateral: It's a triangle with  sides of equal length.
Isosceles: It's a triangle with  sides of equal length.
Scalene: It's a triangle with  sides of differing lengths.
Not A Triangle: The given values of A, B, and C don't form a triangle.
Input Format

The TRIANGLES table is described as follows:

TRIANGLES(A, B, C) all numbers
*/ 



SELECT CASE             
           WHEN A+B<=C or A+C<=B or B+C<=A then "Not A Triangle"
           WHEN A=B and B=C then "Equilateral"
           WHEN A=B or B=C or C=A then "Isosceles"
           ELSE "Scalene"
       END
FROM TRIANGLES;


