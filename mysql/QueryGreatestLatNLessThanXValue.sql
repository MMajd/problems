/** 


@link https://www.hackerrank.com/challenges/weather-observation-station-14/problem?isFullScreen=true


    Query the greatest value of the Northern Latitudes (LAT_N) from STATION that is less than 137.7880. Truncate your answer to  decimal places.





*/ 


select round(lat_n, 4) from station where lat_n<137.2345 order by 1 desc limit 1
