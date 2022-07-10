/** 

full description
@link https://www.hackerrank.com/challenges/the-report/problem?isFullScreen=true

*/ 

select if(g.grade>=8, s.name, null) as name, g.grade as grade, s.marks 
    from students s, grades g 
    where s.marks<= g.max_mark and s.marks>=g.min_mark
    order by grade desc, name, s.marks
