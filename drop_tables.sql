-- generates the required statemnts: SELECT 'drop table '||table_name||' cascade constraints;' from user_tables;

'DROPTABLE'||TABLE_NAME||'CASCADECONSTRAINTS;'
--------------------------------------------------------------------------------
drop table BROADCASTER cascade constraints;
drop table BROADCASTS cascade constraints;
drop table BUSINESSMEMBER cascade constraints;
drop table COACH cascade constraints;
drop table COMMENTATES cascade constraints;
drop table COMMENTATOR cascade constraints;
drop table MATCH1 cascade constraints;
drop table MATCH2 cascade constraints;
drop table PLAYER cascade constraints;
drop table SPONSOR cascade constraints;
drop table TEAM1 cascade constraints;

'DROPTABLE'||TABLE_NAME||'CASCADECONSTRAINTS;'
--------------------------------------------------------------------------------
drop table TEAM2 cascade constraints;
drop table TOURNAMENT cascade constraints;
drop table VENUE cascade constraints;