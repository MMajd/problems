/*


Query the list of CITY names from STATION which have vowels (i.e., a, e, i, o, and u) as both their first and last characters. Your result cannot contain duplicates.

Input Format

The STATION table is described as follows:

station(id, city, state, lat_n, long_w) 

*/

SELECT DISTINCT(CITY) FROM STATION s 
    WHERE lower(s.CITY) REGEXP "^(a|e|i|o|u).*(a|e|i|o|u)$";
