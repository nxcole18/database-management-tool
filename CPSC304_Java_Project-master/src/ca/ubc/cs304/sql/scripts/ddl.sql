CREATE TABLE Venue(
                      Name VARCHAR(50),
                      City VARCHAR(50),
                      Country VARCHAR(50) NOT NULL,
                      Capacity INT,
                      PRIMARY KEY (Name, City)
);

CREATE TABLE Sponsor(
                        ID INT PRIMARY KEY,
                        Name VARCHAR(50) NOT NULL
);

CREATE TABLE Team1(
                      Organization VARCHAR(50) PRIMARY KEY,
                      Country VARCHAR(50) NOT NULL
);

CREATE TABLE Team2(
                      ID INT PRIMARY KEY,
                      Name VARCHAR(50) UNIQUE NOT NULL,
                      Organization VARCHAR(50) NOT NULL,
                      FOREIGN KEY (Organization) REFERENCES Team1(Organization)
                          ON DELETE CASCADE
);

CREATE TABLE Tournament(
                           Name VARCHAR(50),
                           Start_date DATE,
                           End_date DATE,
                           Format VARCHAR(50) NOT NULL,
                           Venue_name VARCHAR(50),
                           Venue_city VARCHAR(50),
                           FOREIGN KEY (Venue_name, Venue_city) REFERENCES Venue(Name, city)
                               ON DELETE SET NULL,
                           Sponsor_ID INT,
                           FOREIGN KEY (Sponsor_ID) REFERENCES Sponsor(ID)
                               ON DELETE SET NULL,
                           Winning_team INT,
                           FOREIGN KEY (Winning_team) REFERENCES Team2(ID)
                               ON DELETE SET NULL,
                           PRIMARY KEY (Name, Start_date),
                           CHECK (End_date >= Start_date)
);

CREATE TABLE Match1(
                       Stage VARCHAR(50) PRIMARY KEY,
                       Rounds INT NOT NULL
);

CREATE TABLE Match2(
                       Match_number INT,
                       Tournament_name VARCHAR(50) NOT NULL,
                       Tournament_start_date DATE NOT NULL,
                       FOREIGN KEY (Tournament_name, Tournament_Start_date) REFERENCES Tournament(Name, Start_date)
                           ON DELETE CASCADE,
                       Start_time TIMESTAMP(0) NOT NULL,
                       Score VARCHAR(50) NOT NULL,
                       Team1 INT,
                       FOREIGN KEY (Team1) REFERENCES Team2(ID)
                           ON DELETE SET NULL,
                       Team2 INT,
                       FOREIGN KEY (Team2) REFERENCES Team2(ID)
                           ON DELETE SET NULL,
                       Winner INT,
                       FOREIGN KEY (Winner) REFERENCES Team2(ID)
                           ON DELETE SET NULL,
                       Stage VARCHAR(50) NOT NULL,
                       FOREIGN KEY (Stage) REFERENCES Match1(Stage),
                       PRIMARY KEY (Match_Number, Tournament_name, Tournament_Start_date)
);

CREATE TABLE Commentator(
                            ID INT PRIMARY KEY,
                            First_name VARCHAR(50) NOT NULL,
                            Last_name VARCHAR(50) NOT NULL,
                            Country VARCHAR(50)
);

CREATE TABLE Commentates(
                            Tournament_name VARCHAR(50),
                            Tournament_start_date DATE,
                            FOREIGN KEY (Tournament_name, Tournament_start_date) REFERENCES Tournament(Name, Start_date)
                                ON DELETE CASCADE,
                            Commentator_ID INT,
                            FOREIGN KEY (Commentator_ID) REFERENCES Commentator(ID)
                                ON DELETE CASCADE,
                            PRIMARY KEY (Tournament_name, Tournament_start_date, Commentator_ID)
);

CREATE TABLE Broadcaster(
                            ID INT PRIMARY KEY,
                            Organization VARCHAR(50) NOT NULL
);

CREATE TABLE Broadcasts(
                           Tournament_name VARCHAR(50),
                           Tournament_start_date DATE,
                           FOREIGN KEY (Tournament_name, Tournament_start_date) REFERENCES Tournament(Name, Start_date)
                               ON DELETE CASCADE,
                           Broadcaster_ID INT,
                           FOREIGN KEY (Broadcaster_ID) REFERENCES Broadcaster(ID)
                               ON DELETE CASCADE,
                           PRIMARY KEY(Tournament_name, Tournament_start_date, Broadcaster_ID)
);

CREATE TABLE Coach(
                      ID INT PRIMARY KEY,
                      First_name VARCHAR(50) NOT NULL,
                      Last_name VARCHAR(50) NOT NULL,
                      Country VARCHAR(50) NOT NULL,
                      Join_date DATE NOT NULL,
                      Years_experience INT NOT NULL,
                      Team_ID INT NOT NULL,
                      FOREIGN KEY (Team_ID) REFERENCES Team2(ID)
                          ON DELETE CASCADE
);

CREATE TABLE BusinessMember(
                               ID INT PRIMARY KEY,
                               First_name VARCHAR(50) NOT NULL,
                               Last_name VARCHAR(50) NOT NULL,
                               Country VARCHAR(50) NOT NULL,
                               Join_date DATE NOT NULL,
                               Role VARCHAR(50) NOT NULL,
                               Team_ID INT NOT NULL,
                               FOREIGN KEY (Team_ID) REFERENCES Team2(ID)
                                   ON DELETE CASCADE
);

CREATE TABLE Player(
                       ID INT PRIMARY KEY,
                       First_name VARCHAR(50) NOT NULL,
                       Last_name VARCHAR(50) NOT NULL,
                       Country VARCHAR(50) NOT NULL,
                       Join_date DATE NOT NULL,
                       Ranking INT UNIQUE NOT NULL,
                       Team_ID INT NOT NULL,
                       FOREIGN KEY (Team_ID) REFERENCES Team2(ID)
                           ON DELETE CASCADE
);

-- Insert statements
INSERT INTO Venue		VALUES ('VenueA', 'Shanghai', 'China', 100000);
INSERT INTO Venue		VALUES ('VenueB', 'Vancouver', 'Canada', 100000);
INSERT INTO Venue		VALUES ('VenueC', 'Helsinki', 'Finland', 100000);
INSERT INTO Venue		VALUES ('VenueD', 'Incheon', 'South Korea', 50000);
INSERT INTO Venue		VALUES ('VenueE', 'PyeongChang', 'South Korea', 100000);

INSERT INTO Sponsor		VALUES (11, 'Michael');
INSERT INTO Sponsor		VALUES (22, 'Nicole');
INSERT INTO Sponsor		VALUES (33, 'Oxford');
INSERT INTO Sponsor		VALUES (44, 'Piper');
INSERT INTO Sponsor		VALUES (55, 'Quann');

INSERT INTO Team1		VALUES ('OrgA', 'Canada');
INSERT INTO Team1		VALUES ('OrgB', 'USA');
INSERT INTO Team1		VALUES ('OrgC', 'Mexico');
INSERT INTO Team1		VALUES ('OrgD', 'Japan');
INSERT INTO Team1		VALUES ('OrgE', 'Finland');

INSERT INTO Team2		VALUES (101, 'TeamA', 'OrgA');
INSERT INTO Team2		VALUES (102, 'TeamB', 'OrgB');
INSERT INTO Team2		VALUES (103, 'TeamC', 'OrgC');
INSERT INTO Team2		VALUES (104, 'TeamD', 'OrgD');
INSERT INTO Team2		VALUES (105, 'TeamE', 'OrgE');

INSERT INTO Player		VALUES (666, 'Patrick', 'Parker', 'Canada', DATE '2018-02-05', 6, 101);
INSERT INTO Player		VALUES (777, 'Quinna', 'Quarles', 'USA', DATE '2020-12-31', 2, 102);
INSERT INTO Player		VALUES (888, 'Rachel', 'Rayon', 'Mexico', DATE '2023-01-01', 8, 103);
INSERT INTO Player		VALUES (999, 'Sylvia', 'Sin', 'Japan', DATE '2019-07-31', 7, 104);
INSERT INTO Player		VALUES (1010, 'Tim', 'Thomas', 'Finland', DATE '2022-10-31', 1, 105);

INSERT INTO Tournament	VALUES ('TournamentA', DATE '2012-04-08', DATE '2012-04-12', 'Progression', 'VenueA', 'Shanghai', 11, 101);
INSERT INTO Tournament	VALUES ('TournamentB', DATE '2020-12-07', DATE '2020-12-21', 'Elimination', 'VenueB', 'Vancouver', 55, 101);
INSERT INTO Tournament	VALUES ('TournamentC', DATE '2016-11-03', DATE '2016-11-09', 'Elimination', 'VenueC', 'Helsinki', 22, 102);
INSERT INTO Tournament	VALUES ('LOL', DATE '2018-11-05', DATE '2018-11-05', 'Elimination', 'VenueD', 'Incheon', 33,103);
INSERT INTO Tournament	VALUES ('2018 Winter Olympics', DATE '2018-02-09', DATE '2018-02-25', 'Progression', 'VenueE', 'PyeongChang', 44, 104);

INSERT INTO Match1		VALUES ('Final', 5);
INSERT INTO Match1		VALUES ('Semifinals', 5);
INSERT INTO Match1		VALUES ('Qualification', 3);
INSERT INTO Match1		VALUES ('Quarterfinals', 3);
INSERT INTO Match1		VALUES ('Group', 3);

INSERT INTO Commentator	VALUES (1, 'Amber', 'Abbott', 'USA');
INSERT INTO Commentator	VALUES (2, 'Bob', 'Brown', 'Canada');
INSERT INTO Commentator	VALUES (3, 'Catherine', 'Campbell', 'UK');
INSERT INTO Commentator	VALUES (4, 'David', 'Dan', 'China');
INSERT INTO Commentator	VALUES (5, 'Elizabeth', 'Emery', 'France');

INSERT INTO Commentates	VALUES ('TournamentA', DATE '2012-04-08', 1);
INSERT INTO Commentates	VALUES ('TournamentB', DATE '2020-12-07', 2);
INSERT INTO Commentates	VALUES ('TournamentC', DATE '2016-11-03', 3);
INSERT INTO Commentates	VALUES ('LOL', DATE '2018-11-05', 4);
INSERT INTO Commentates	VALUES ('2018 Winter Olympics', DATE '2018-02-09', 5);

INSERT INTO Broadcaster	VALUES (101, 'NBC');
INSERT INTO Broadcaster	VALUES (202, 'CBC');
INSERT INTO Broadcaster	VALUES (303, 'BBC');
INSERT INTO Broadcaster	VALUES (404, 'CTV');
INSERT INTO Broadcaster	VALUES (505, 'BTV');

INSERT INTO Coach VALUES (1010, 'Frank', 'Freeman', 'France', DATE '2021-10-02', 2, 101);
INSERT INTO Coach VALUES (1020, 'Gary', 'Green', 'Germany', DATE '2015-02-12', 8, 102);
INSERT INTO Coach VALUES (1030, 'Helen', 'Hall', 'Hungary', DATE '2018-11-06', 5, 103);
INSERT INTO Coach VALUES (1040, 'Irene', 'Idd', 'Iceland', DATE '2000-09-21', 23, 104);
INSERT INTO Coach VALUES (1050, 'Jackson', 'Johnson', 'Japan', DATE '2008-12-31', 15, 105);

INSERT INTO BusinessMember	VALUES (111, 'Kelly', 'Kwan', 'Korea', DATE '2023-02-01', 'Manager', 101);
INSERT INTO BusinessMember	VALUES (222, 'Lesley', 'Lee', 'Luxembourg', DATE '2020-01-01', 'Director', 102);
INSERT INTO BusinessMember	VALUES (333, 'Michelle', 'Miller', 'Morocco', DATE '2018-02-17', 'Doctor', 103);
INSERT INTO BusinessMember	VALUES (444, 'Nelson', 'Nelson', 'New Zealand', DATE '2019-12-31', 'Doctor', 104);
INSERT INTO BusinessMember	VALUES (555, 'Olivia', 'OBrien', 'Oman', DATE '2017-05-31', 'Manager', 105);



--new inserts for milestone5
--based on LOL 2023 eliminiation rounds data
INSERT INTO Team1 VALUES ('KSV GEN', 'South Korea');
INSERT INTO Team1 VALUES ('SK Telecom', 'South Korea');
INSERT INTO Team1 VALUES ('KT Corp', 'South Korea');
INSERT INTO Team1 VALUES ('JingDong', 'China');
INSERT INTO Team1 VALUES ('Bilibili', 'China');
INSERT INTO Team1 VALUES ('LiNing', 'China');
INSERT INTO Team1 VALUES ('Weibo', 'China');
INSERT INTO Team1 VALUES ('NR', 'Canada');

INSERT INTO Team2 VALUES (01, 'Gen.G', 'KSV GEN');
INSERT INTO Team2 VALUES (02, 'T1', 'SK Telecom');
INSERT INTO Team2 VALUES (03, 'KT', 'KT Corp');
INSERT INTO Team2 VALUES (04, 'JDG', 'JingDong');
INSERT INTO Team2 VALUES (05, 'BLG', 'Bilibili');
INSERT INTO Team2 VALUES (06, 'LNG', 'LiNing');
INSERT INTO Team2 VALUES (07, 'WBG', 'Weibo');
INSERT INTO Team2 VALUES (08, 'NRG', 'NR');

INSERT INTO Player VALUES (11, 'Doran', 'Choi', 'South Korea', DATE '2022-01-01', 11, 01);
INSERT INTO Player VALUES (12, 'Peanut', 'Han', 'South Korea', DATE '2022-01-01', 12, 01);
INSERT INTO Player VALUES (13, 'Chovy', 'Jeong', 'South Korea', DATE '2022-01-01', 13, 01);
INSERT INTO Player VALUES (14, 'Peyz', 'Kim', 'South Korea', DATE '2023-01-01', 14, 01);
INSERT INTO Player VALUES (15, 'Delight', 'Yoo', 'South Korea', DATE '2023-01-01', 15, 01);
INSERT INTO Player VALUES (21, 'Zeus', 'Choi', 'South Korea', DATE '2020-11-05', 21, 02);
INSERT INTO Player VALUES (22, 'Oner', 'Mun', 'South Korea', DATE '2021-01-01', 22, 02);
INSERT INTO Player VALUES (23, 'Faker', 'Lee', 'South Korea', DATE '2013-01-01', 23, 02);
INSERT INTO Player VALUES (24, 'Gumayusi', 'Lee', 'South Korea', DATE '2021-02-03', 24, 02);
INSERT INTO Player VALUES (25, 'Keria', 'Ryu', 'South Korea', DATE '2020-11-05', 25, 02);
INSERT INTO Player VALUES (31, 'Kiin', 'Kim', 'South Korea', DATE '2023-01-01', 31, 03);
INSERT INTO Player VALUES (32, 'Cuzz', 'Moon', 'South Korea', DATE '2023-01-01', 32, 03);
INSERT INTO Player VALUES (33, 'Bdd', 'Gwak', 'South Korea', DATE '2023-01-02', 33, 03);
INSERT INTO Player VALUES (34, 'Aiming', 'Kim', 'South Korea', DATE '2023-01-01', 34, 03);
INSERT INTO Player VALUES (35, 'Lehends', 'Son', 'South Korea', DATE '2023-01-01', 35, 03);
INSERT INTO Player VALUES (41, '369', 'Bai', 'China', DATE '2023-01-01', 41, 04);
INSERT INTO Player VALUES (42, 'Kanavi', 'Seo', 'South Korea', DATE '2022-01-01', 42, 04);
INSERT INTO Player VALUES (43, 'Knight', 'Zhuo', 'China', DATE '2023-01-01', 43, 04);
INSERT INTO Player VALUES (44, 'Ruler', 'Park', 'South Korea', DATE '2023-01-01', 44, 04);
INSERT INTO Player VALUES (45, 'Missing', 'Lou', 'China', DATE '2023-01-01', 45, 04);
INSERT INTO Player VALUES (51, 'Bin', 'Chen', 'China', DATE '2021-01-01', 51, 05);
INSERT INTO Player VALUES (52, 'Xun', 'Peng', 'China', DATE '2023-01-01', 52, 05);
INSERT INTO Player VALUES (53, 'Yagao', 'Zeng', 'China', DATE '2022-01-01', 53, 05);
INSERT INTO Player VALUES (54, 'Elk', 'Zhao', 'China', DATE '2023-02-01', 54, 05);
INSERT INTO Player VALUES (55, 'On', 'Luo', 'China', DATE '2023-02-02', 55, 05);
INSERT INTO Player VALUES (61, 'Zika', 'Tang', 'China', DATE '2023-01-01', 61, 06);
INSERT INTO Player VALUES (62, 'Tarzan', 'Lee', 'South Korea', DATE '2023-01-01', 62, 06);
INSERT INTO Player VALUES (63, 'Scout', 'Lee', 'South Korea', DATE '2023-01-01', 63, 06);
INSERT INTO Player VALUES (64, 'LP', 'Li', 'China', DATE '2022-01-01', 64, 06);
INSERT INTO Player VALUES (65, 'Hang', 'Fu', 'China', DATE '2023-01-01', 65, 06);
INSERT INTO Player VALUES (71, 'TheShy', 'Kang', 'South Korea', DATE '2022-01-01', 71, 07);
INSERT INTO Player VALUES (72, 'Karsa', 'Hung', 'PCS', DATE '2023-01-01', 72, 07);
INSERT INTO Player VALUES (73, 'Xiaohu', 'Li', 'China', DATE '2023-01-01', 73, 07);
INSERT INTO Player VALUES (74, 'Light', 'Wang', 'China', DATE '2023-01-01', 74, 07);
INSERT INTO Player VALUES (75, 'Crisp', 'Liu', 'China', DATE '2020-01-01', 75, 07);
INSERT INTO Player VALUES (81, 'Dhokla', 'Doshi', 'USA', DATE '2023-04-06', 81, 08);
INSERT INTO Player VALUES (82, 'Contractz', 'Garcia', 'USA', DATE '2023-04-06', 82, 08);
INSERT INTO Player VALUES (83, 'Palafox', 'Palafox', 'USA', DATE '2023-04-06', 83, 08);
INSERT INTO Player VALUES (84, 'FBI', 'Huang', 'Australia', DATE '2023-05-27', 84, 08);
INSERT INTO Player VALUES (85, 'IgNar', 'Lee', 'South Korea', DATE '2023-05-27', 85, 08);

INSERT INTO Venue VALUES ('Gocheok Sky Dome', 'Seoul', 'South Korea', 16744);

INSERT INTO Tournament VALUES ('2023 LOL Worlds', DATE '2023-11-02', DATE '2023-11-19', 'Elimination', 'Gocheok Sky Dome', 'Seoul', 22, 02);

INSERT INTO Match1 VALUES ('Finals', 5);
INSERT INTO Match1 VALUES ('Semifinals1', 5);
INSERT INTO Match1 VALUES ('Semifinals2', 5);
INSERT INTO Match1 VALUES ('Quarterfinals1', 5);
INSERT INTO Match1 VALUES ('Quarterfinals2', 5);
INSERT INTO Match1 VALUES ('Quarterfinals3', 5);
INSERT INTO Match1 VALUES ('Quarterfinals4', 5);

INSERT INTO Match2 VALUES (101, '2023 LOL Worlds', DATE '2023-11-02', TO_TIMESTAMP('2023-11-02 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), '3-0', 07, 08, 07, 'Quarterfinals1');
INSERT INTO Match2 VALUES (102, '2023 LOL Worlds', DATE '2023-11-02', TO_TIMESTAMP('2023-11-02 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), '3-2', 01, 05, 05, 'Quarterfinals2');
INSERT INTO Match2 VALUES (103, '2023 LOL Worlds', DATE '2023-11-02', TO_TIMESTAMP('2023-11-02 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), '3-1', 03, 04, 04, 'Quarterfinals3');
INSERT INTO Match2 VALUES (104, '2023 LOL Worlds', DATE '2023-11-02', TO_TIMESTAMP('2023-11-02 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), '3-0', 02, 06, 02, 'Quarterfinals4');
INSERT INTO Match2 VALUES (201, '2023 LOL Worlds', DATE '2023-11-02', TO_TIMESTAMP('2023-11-02 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), '3-2', 07, 05, 07, 'Semifinals1');
INSERT INTO Match2 VALUES (202, '2023 LOL Worlds', DATE '2023-11-02', TO_TIMESTAMP('2023-11-02 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), '3-1', 02, 04, 02, 'Semifinals1');
INSERT INTO Match2 VALUES (301, '2023 LOL Worlds', DATE '2023-11-02', TO_TIMESTAMP('2023-11-02 19:00:00', 'YYYY-MM-DD HH24:MI:SS'), '3-0', 02, 07, 02, 'Finals');


--Inserts for division
--Broadcaster that Broadcasted all Tournaments
INSERT INTO Broadcasts VALUES ('TournamentA', DATE '2012-04-08', 101);
INSERT INTO Broadcasts VALUES ('TournamentB', DATE '2020-12-07', 101);
INSERT INTO Broadcasts VALUES ('TournamentC', DATE '2016-11-03', 101);
INSERT INTO Broadcasts VALUES ('LOL', DATE '2018-11-05', 101);
INSERT INTO Broadcasts VALUES ('2018 Winter Olympics', DATE '2018-02-09', 101);
INSERT INTO Broadcasts VALUES ('2023 LOL Worlds', DATE '2023-11-02', 101);
INSERT INTO Broadcasts VALUES ('TournamentA', DATE '2012-04-08', 303);
INSERT INTO Broadcasts VALUES ('TournamentB', DATE '2020-12-07', 303);
INSERT INTO Broadcasts VALUES ('TournamentC', DATE '2016-11-03', 303);
INSERT INTO Broadcasts VALUES ('LOL', DATE '2018-11-05', 303);
INSERT INTO Broadcasts VALUES ('2018 Winter Olympics', DATE '2018-02-09', 303);
INSERT INTO Broadcasts VALUES ('2023 LOL Worlds', DATE '2023-11-02', 303);