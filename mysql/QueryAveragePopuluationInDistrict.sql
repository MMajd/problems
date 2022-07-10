/*

Query the average population of all cities in CITY where District is California.

CITY (id, name, district, countrycode, population)





*/ 


SELECT avg(population) FROM CITY WHERE district = "California" limit 1
