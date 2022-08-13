/*
@link https://www.hackerrank.com/challenges/the-company/problem?isFullScreen=true
*/

SELECT c.company_code, c.founder, 
        COUNT(DISTINCT lm.lead_manager_code),
        COUNT(DISTINCT sm.senior_manager_code),
        COUNT(DISTINCT m.manager_code), 
        COUNT(DISTINCT e.employee_code)
    FROM Company c 
    JOIN Lead_Manager lm ON c.company_code=lm.company_code
    JOIN Senior_Manager sm ON lm.company_code=sm.company_code
    JOIN Manager m ON sm.company_code=m.company_code 
    JOIN Employee e ON m.company_code=e.company_code 
GROUP BY c.company_code, c.founder
ORDER BY c.company_code ASC 

