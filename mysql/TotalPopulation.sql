/* 

Query the total population of all cities in CITY where District is California.

Input Format

The CITY table is described as follows: 

id - number
name - varchar 
countrycode - varchar 
district - varchar 
popluation number

*/ 


SELECT sum(population) FROM CITY WHERE district = "California" limit 1
