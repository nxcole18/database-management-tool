-- sqlplus ora_ethanz01@stu

-- insert
INSERT INTO Player
VALUES (998, 'Jack', 'Zhen', 'Canada', DATE '2023-09-05', 10300, 101);

-- Should trigger error because team doesn't exist. Should be able to handle that
VALUES (998, 'Nave', 'Ecatz', 'Canada', DATE '2023-04-05', 10400, 109);

-- use function: insertPlayer


-- update
UPDATE Player
SET ranking = 10020
WHERE ID = 998;
-- can set any column you want

-- use set of functions: updatePlayerId, updatePlayerFirstName etc.

-- delete
DELETE FROM Team2
where ID = 102;
-- cascades and deletes players and sets tournament winner to null
-- use function: deletePlayer

-- select (relational algebra select NOT SELECT statement)
SELECT *
FROM Player
WHERE ID = 998;

-- project
SELECT First_name
FROM Player;
-- both columns and tables have to be dynamic (can be chosen by user even with randomly added tables)
-- use functions: getTables, getTableColumns, project

-- join
SELECT Name, t1.Organization
FROM Team1 t1, Team2 t2
WHERE t1.Organization = t2.Organization AND t1.= "China";
-- find teams which are based in China
-- user can specify which country to choose

-- aggregation group by
SELECT AVG(ranking) AS Average_Team_Ranking
FROM Player
GROUP BY Team_ID;
-- average ranking of a team

-- having aggregation
SELECT v.City, AVG(Capacity) AS avg_capacity
FROM Tournament t, Venue v
WHERE t.Venue_name = v.Name AND t.Venue_city = v.City
GROUP BY v.City
HAVING COUNT(*) < 2;
-- might change later to > 5 or something to get bigger venues if we have more entries

-- nested aggregation group by

CREATE VIEW Teams_with_experienced_coaches AS
SELECT t.ID
FROM Team2 t, Coach c
WHERE t.ID = c.Team_ID AND c.Years_experience > 5;

SELECT Winner, COUNT(*) AS matches_won
FROM Match2
GROUP BY winner
HAVING winner NOT IN (SELECT ID FROM Teams_with_experienced_coaches);
-- finding how many matches each team with an unexperienced coach
-- (defined by having 5 or less years experience) has won


-- division
-- Find all broadcasters that have broadcasted all the tournaments, name the ID and organization
SELECT DISTINCT B.ID, B.Organization
FROM Broadcaster B 
WHERE NOT EXISTS (
    SELECT T.Name, T.Start_date
    FROM Tournament T 
    WHERE NOT EXISTS (
        SELECT *
        FROM Broadcasts R 
        WHERE R.Tournament_name=T.Name AND R.Tournament_start_date=T.Start_date AND R.Broadcaster_ID = B.ID
    )
);
