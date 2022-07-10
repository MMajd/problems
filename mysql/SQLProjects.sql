/** 

full description 
@link https://www.hackerrank.com/challenges/sql-projects/problem?isFullScreen=true

*/ 

/** deactivate full group by mode */
set sql_mode = ''; 
SELECT Start_Date, End_Date
FROM (
        /** select start date that has not appeard as an end date for some task, true start date of a project */ 
    (SELECT Start_Date FROM Projects WHERE Start_Date NOT IN (SELECT End_Date FROM Projects)) a,
    /** select end date that has not appeard as a start date for some task, true end date of a project*/
    (SELECT End_Date FROM Projects WHERE End_Date NOT IN (SELECT Start_Date FROM Projects)) b 
)
/** make sure that dates are valid */
WHERE Start_Date < End_Date
GROUP BY Start_Date 
/** order by days taken asc, if equal order by early started project */
ORDER BY DATEDIFF(End_Date, Start_Date), Start_Date

