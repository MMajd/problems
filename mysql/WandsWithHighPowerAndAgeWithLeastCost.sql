/** 
full description @
@link https://www.hackerrank.com/challenges/harry-potter-and-wands/problem?isFullScreen=true

*/ 


select w.id, wp.age, w.coins_needed, w.power 
    from Wands w, Wands_Property wp 
    where w.code = wp.code and wp.is_evil = 0 and w.coins_needed = 
        (
            select min(coins_needed) 
                from Wands w1, Wands_Property wp1 
                where w1.code = wp1.code and w1.power = w.power 
                and wp1.age = wp.age
        )
order by w.power desc, wp.age desc

