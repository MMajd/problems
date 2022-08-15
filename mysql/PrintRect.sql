/*

@link https://www.hackerrank.com/challenges/draw-the-triangle-1/problem?isFullScreen=true

P(R) represents a pattern drawn by Julia in R rows. The following pattern represents P(5):

* * * * * 
* * * * 
* * * 
* * 
*
Write a query to print the pattern P(20).
*/




DELIMITER // 
CREATE PROCEDURE P(IN COLS INT)
BEGIN
    DECLARE RES VARCHAR(1000) DEFAULT ''; 
    DECLARE COUNTER INT; 
    
    WHILE COLS > 0 DO 
        SET COUNTER = COLS; 
        
        WHILE COUNTER > 0 DO  
            SET RES = CONCAT(RES, '*', ' ');
            SET COUNTER = COUNTER-1; 
        END WHILE;
        SELECT RES; 
        SET RES = ''; 
        SET COLS = COLS-1;  

    END WHILE;
END // 
DELIMITER ;  


CALL P(20);
