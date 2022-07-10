/*

full description 

@link https://www.hackerrank.com/challenges/contest-leaderboard/problem?isFullScreen=true


*/

SELECT h.hacker_id, h.name, sum(s.score) as score from hackers h, submissions s
where h.hacker_id=s.hacker_id and s.submission_id = (
    select s1.submission_id from submissions s1 
    where s1.hacker_id=s.hacker_id and s1.challenge_id=s.challenge_id
    order by s1.score desc limit 1)
group by h.hacker_id,h.name
having score > 0 
order by score desc, h.hacker_id
