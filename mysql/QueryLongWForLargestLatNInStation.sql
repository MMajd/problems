/** 

@link https://www.hackerrank.com/challenges/weather-observation-station-15/problem?isFullScreen=true



Query the Western Longitude (LONG_W) for the largest Northern Latitude (LAT_N) in STATION that is less than 137.2345 . Round your answer to  decimal places.
*/ 



/** first solution */
select round(long_w, 4) from station 
where lat_n<137.2345 order by station.lat_n desc limit 1; 


/** second solution */
select round(long_w, 4) from station 
where lat_n = (select max(lat_n) from station where lat_n<137.2345)
