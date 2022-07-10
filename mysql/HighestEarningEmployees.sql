/** 

We define an employee's total earnings to be their monthly (salary*months) worked, and the maximum total earnings to be the maximum total earnings for any employee in the Employee table. Write a query to find the maximum total earnings for all employees as well as the total number of employees who have maximum total earnings. Then print these values as  space-separated integers.


*/ 



/** first solution */ 
SELECT salary*months as earnings, 
    count(distinct(employee_id)) 
    from employee 
    where salary*months in (SELECT MAX(salary*months) from employee e)
group by earnings


/** second solution */ 
SELECT salary*months as earnings, count(distinct(employee_id))
FROM Employee E 
GROUP BY earnings
ORDER BY earnings desc 
LIMIT 1 
