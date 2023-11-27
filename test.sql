-- use this to play around with queries and see results
-- so you don't have to type everything again.
CREATE VIEW Teams_with_experienced_coaches AS
SELECT t.ID
FROM Team2 t, Coach c
WHERE t.ID = c.Team_ID AND c.Years_experience > 5;

SELECT Winner, COUNT(*) AS matches_won
FROM Match2
GROUP BY winner
HAVING winner NOT IN (SELECT ID FROM Teams_with_experienced_coaches);