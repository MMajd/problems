/*

 @link https://www.hackerrank.com/challenges/15-days-of-learning-sql/problem?isFullScreen=true

 Julia conducted a  days of learning SQL contest. The start date of the contest was March 01, 2016 and the end date was March 15, 2016.

Write a query to print total number of unique hackers who made at least  submission each day (starting on the first day of the contest), and find the hacker_id and name of the hacker who made maximum number of submissions each day. If more than one such hacker has a maximum number of submissions, print the lowest hacker_id. The query should print this information for each day of the contest, sorted by the date.

Input Format

The following tables hold contest data:
Hackers: 
*************************
*  COLUMN   |    TYPE   *
*************************
* HACKER_ID |  INTEGER  *
*************************
* NAME      |  STRING   *
*************************

Submissions: 
*********************************
*  COLUMN         |  TYPE       *
*********************************
* SUBMISSION_ID   |  INTEGER    *
*********************************
* SUBMISSION_DATE |  STRING     * 
*********************************
* HACKER_ID       |  INTEGER    *
*********************************
* SCORE           |  INTEGER    * 
*********************************

Sample Input

For the following sample input, 
assume that the end date of the contest was March 06, 2016.

CREATE TABLE submissions (submission_date date, submission_id integer, hacker_id integer, score integer);
INSERT INTO "submissions" VALUES('2016-03-01',8494,20703,0);
INSERT INTO "submissions" VALUES('2016-03-01',22403,53473,15);
INSERT INTO "submissions" VALUES('2016-03-01',23965,79722,60);
INSERT INTO "submissions" VALUES('2016-03-01',30173,36396,70);
INSERT INTO "submissions" VALUES('2016-03-02',34928,20703,0);
INSERT INTO "submissions" VALUES('2016-03-02',38740,15758,60);
INSERT INTO "submissions" VALUES('2016-03-02',42769,79722,60);
INSERT INTO "submissions" VALUES('2016-03-02',44364,79722,60);
INSERT INTO "submissions" VALUES('2016-03-03',45440,20703,0);
INSERT INTO "submissions" VALUES('2016-03-03',49050,36396,70);
INSERT INTO "submissions" VALUES('2016-03-03',50273,79722,5);
INSERT INTO "submissions" VALUES('2016-03-04',50344,20703,0);
INSERT INTO "submissions" VALUES('2016-03-04',51360,44065,90);
INSERT INTO "submissions" VALUES('2016-03-04',54404,53473,65);
INSERT INTO "submissions" VALUES('2016-03-04',61533,79722,45);
INSERT INTO "submissions" VALUES('2016-03-05',72852,20703,0);
INSERT INTO "submissions" VALUES('2016-03-05',74546,38289,0);
INSERT INTO "submissions" VALUES('2016-03-05',76487,62529,0);
INSERT INTO "submissions" VALUES('2016-03-05',82439,36396,10);
INSERT INTO "submissions" VALUES('2016-03-05',9006,36396,40);
INSERT INTO "submissions" VALUES('2016-03-06',90404,20703,0);
CREATE TABLE hackers (hacker_id integer, name string);
INSERT INTO "hackers" VALUES(15758,'Rose');
INSERT INTO "hackers" VALUES(20703,'Angela');
INSERT INTO "hackers" VALUES(36396,'Frank');
INSERT INTO "hackers" VALUES(38289,'Patrick');
INSERT INTO "hackers" VALUES(44065,'Lisa');
INSERT INTO "hackers" VALUES(53473,'Kimberly');
INSERT INTO "hackers" VALUES(62529,'Bonnie');
INSERT INTO "hackers" VALUES(79722,'Michael');

Sample Output

2016-03-01 4 20703 Angela
2016-03-02 2 79722 Michael
2016-03-03 2 20703 Angela
2016-03-04 2 20703 Angela
2016-03-05 1 36396 Frank
2016-03-06 1 20703 Angela


*/





SELECT SUBMISSION_DATE, (
        SELECT COUNT(DISTINCT S2.HACKER_ID) FROM SUBMISSIONS S2
        WHERE S2.SUBMISSION_DATE = S.SUBMISSION_DATE AND (
            SELECT COUNT(DISTINCT S3.SUBMISSION_DATE) 
                FROM SUBMISSIONS S3 
                WHERE S2.HACKER_ID = S3.HACKER_ID 
                AND S3.SUBMISSION_DATE < S.SUBMISSION_DATE
        ) = DATEDIFF(S.SUBMISSION_DATE, '2016-03-01')
    ), 
    (
        SELECT HACKER_ID FROM SUBMISSIONS S2 
        WHERE S2.SUBMISSION_DATE = S.SUBMISSION_DATE 
        GROUP BY HACKER_ID
        ORDER BY COUNT(SUBMISSION_DATE) DESC, HACKER_ID LIMIT 1
    ) as BEST, 
    (
        SELECT NAME FROM HACKERS WHERE HACKER_ID = BEST
    )
FROM (SELECT DISTINCT SUBMISSION_DATE FROM SUBMISSIONS) S 
GROUP BY SUBMISSION_DATE
