/**  
@link https://www.hackerrank.com/challenges/sql-projects/problem?isFullScreen=true

You are given a table, Projects, containing three 
columns: Task_ID, Start_Date and End_Date. 
It is guaranteed that the difference between the End_Date and the 
Start_Date is equal to 1 day for each row in the table.

If the End_Date of the tasks are consecutive, then they are part of the same project. 
Samantha is interested in finding the total number of different projects completed.
Write a query to output the start and end dates of projects listed by the number of days it took to complete the project in ascending order. 
If there is more than one project that have the same number of completion days, 
then order by the start date of the project.


Sample Output: 

Sample Output: 
    2015-10-28 2015-10-29
    2015-10-30 2015-10-31
    2015-10-13 2015-10-15
    2015-10-01 2015-10-04


Explanation
    The example describes following four projects:
    Project 1: Tasks 1, 2 and 3 are completed on consecutive days, so these are part of the project. Thus start date of project is 2015-10-01 and end date is 2015-10-04, so it took 3 days to complete the project.
    Project 2: Tasks 4 and 5 are completed on consecutive days, so these are part of the project. Thus, the start date of project is 2015-10-13 and end date is 2015-10-15, so it took 2 days to complete the project.
    Project 3: Only task 6 is part of the project. Thus, the start date of project is 2015-10-28 and end date is 2015-10-29, so it took 1 day to complete the project.
    Project 4: Only task 7 is part of the project. Thus, the start date of project is 2015-10-30 and end date is 2015-10-31, so it took 1 day to complete the project.
*/ 

/** deactivate full group by mode */
set sql_mode = ''; 
SELECT Start_Date, End_Date
    FROM (
            /** select start date that has not appeard as an end date for some task, true start date of a project */ 
        (SELECT Start_Date FROM Projects WHERE Start_Date NOT IN (SELECT End_Date FROM Projects)) AS A,
        /** select end date that has not appeard as a start date for some task, true end date of a project*/
        (SELECT End_Date FROM Projects WHERE End_Date NOT IN (SELECT Start_Date FROM Projects)) AS B 
    )
/** make sure that dates are valid */
WHERE Start_Date < End_Date
GROUP BY Start_Date
/** order by days taken asc, if equal order by early started project */
ORDER BY DATEDIFF(End_Date, Start_Date), Start_Date
