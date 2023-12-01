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
INSERT INTO Venue VALUES ('Gocheok Sky Dome', 'Seoul', 'South Korea', 16744);

INSERT INTO Sponsor		VALUES (11, 'Michael');
INSERT INTO Sponsor		VALUES (22, 'Nicole');
INSERT INTO Sponsor		VALUES (33, 'Oxford');
INSERT INTO Sponsor		VALUES (44, 'Piper');
INSERT INTO Sponsor		VALUES (55, 'Quann');

INSERT INTO Team1 VALUES ('KSV GEN', 'SK');
INSERT INTO Team1 VALUES ('SK Telecom', 'SK');
INSERT INTO Team1 VALUES ('KT Corp', 'SK');
INSERT INTO Team1 VALUES ('JingDong', 'CN');
INSERT INTO Team1 VALUES ('Bilibili', 'CN');
INSERT INTO Team1 VALUES ('LiNing', 'CN');
INSERT INTO Team1 VALUES ('Weibo', 'CN');
INSERT INTO Team1 VALUES ('NR', 'NA');

INSERT INTO Team2 VALUES (01, 'Gen.G', 'KSV GEN');
INSERT INTO Team2 VALUES (02, 'T1', 'SK Telecom');
INSERT INTO Team2 VALUES (03, 'KT', 'KT Corp');
INSERT INTO Team2 VALUES (04, 'JDG', 'JingDong');
INSERT INTO Team2 VALUES (05, 'BLG', 'Bilibili');
INSERT INTO Team2 VALUES (06, 'LNG', 'LiNing');
INSERT INTO Team2 VALUES (07, 'WBG', 'Weibo');
INSERT INTO Team2 VALUES (08, 'NRG', 'NR');

INSERT INTO PLAYER VALUES (11, 'Doran', 'Choi', 'SK', DATE '2022-01-01', 11, 01);
INSERT INTO PLAYER VALUES (12, 'Peanut', 'Han', 'SK', DATE '2022-01-01', 12, 01);
INSERT INTO PLAYER VALUES (13, 'Chovy', 'Jeong', 'SK', DATE '2022-01-01', 13, 01);
INSERT INTO PLAYER VALUES (14, 'Peyz', 'Kim', 'SK', DATE '2023-01-01', 14, 01);
INSERT INTO PLAYER VALUES (15, 'Delight', 'Yoo', 'SK', DATE '2023-01-01', 15, 01);
INSERT INTO PLAYER VALUES (21, 'Zeus', 'Choi', 'SK', DATE '2020-11-05', 21, 02);
INSERT INTO PLAYER VALUES (22, 'Oner', 'Mun', 'SK', DATE '2021-01-01', 22, 02);
INSERT INTO PLAYER VALUES (23, 'Faker', 'Lee', 'SK', DATE '2013-01-01', 23, 02);
INSERT INTO PLAYER VALUES (24, 'Gumayusi', 'Lee', 'SK', DATE '2021-02-03', 24, 02);
INSERT INTO PLAYER VALUES (25, 'Keria', 'Ryu', 'SK', DATE '2020-11-05', 25, 02);
INSERT INTO PLAYER VALUES (31, 'Kiin', 'Kim', 'SK', DATE '2023-01-01', 31, 03);
INSERT INTO PLAYER VALUES (32, 'Cuzz', 'Moon', 'SK', DATE '2023-01-01', 32, 03);
INSERT INTO PLAYER VALUES (33, 'Bdd', 'Gwak', 'SK', DATE '2023-01-02', 33, 03);
INSERT INTO PLAYER VALUES (34, 'Aiming', 'Kim', 'SK', DATE '2023-01-01', 34, 03);
INSERT INTO PLAYER VALUES (35, 'Lehends', 'Son', 'SK', DATE '2023-01-01', 35, 03);
INSERT INTO PLAYER VALUES (41, '369', 'Bai', 'CN', DATE '2023-01-01', 41, 04);
INSERT INTO PLAYER VALUES (42, 'Kanavi', 'Seo', 'SK', DATE '2022-01-01', 42, 04);
INSERT INTO PLAYER VALUES (43, 'Knight', 'Zhuo', 'CN', DATE '2023-01-01', 43, 04);
INSERT INTO PLAYER VALUES (44, 'Ruler', 'Park', 'SK', DATE '2023-01-01', 44, 04);
INSERT INTO PLAYER VALUES (45, 'Missing', 'Lou', 'CN', DATE '2023-01-01', 45, 04);
INSERT INTO PLAYER VALUES (51, 'Bin', 'Chen', 'CN', DATE '2021-01-01', 51, 05);
INSERT INTO PLAYER VALUES (52, 'Xun', 'Peng', 'CN', DATE '2023-01-01', 52, 05);
INSERT INTO PLAYER VALUES (53, 'Yagao', 'Zeng', 'CN', DATE '2022-01-01', 53, 05);
INSERT INTO PLAYER VALUES (54, 'Elk', 'Zhao', 'CN', DATE '2023-02-01', 54, 05);
INSERT INTO PLAYER VALUES (55, 'On', 'Luo', 'CN', DATE '2023-02-02', 55, 05);
INSERT INTO PLAYER VALUES (61, 'Zika', 'Tang', 'CN', DATE '2023-01-01', 61, 06);
INSERT INTO PLAYER VALUES (62, 'Tarzan', 'Lee', 'SK', DATE '2023-01-01', 62, 06);
INSERT INTO PLAYER VALUES (63, 'Scout', 'Lee', 'SK', DATE '2023-01-01', 63, 06);
INSERT INTO PLAYER VALUES (64, 'LP', 'Li', 'CN', DATE '2022-01-01', 64, 06);
INSERT INTO PLAYER VALUES (65, 'Hang', 'Fu', 'CN', DATE '2023-01-01', 65, 06);
INSERT INTO PLAYER VALUES (71, 'TheShy', 'Kang', 'SK', DATE '2022-01-01', 71, 07);
INSERT INTO PLAYER VALUES (72, 'Karsa', 'Hung', 'PCS', DATE '2023-01-01', 72, 07);
INSERT INTO PLAYER VALUES (73, 'Xiaohu', 'Li', 'CN', DATE '2023-01-01', 73, 07);
INSERT INTO PLAYER VALUES (74, 'Light', 'Wang', 'CN', DATE '2023-01-01', 74, 07);
INSERT INTO PLAYER VALUES (75, 'Crisp', 'Liu', 'CN', DATE '2020-01-01', 75, 07);
INSERT INTO PLAYER VALUES (81, 'Dhokla', 'Doshi', 'USA', DATE '2023-04-06', 81, 08);
INSERT INTO PLAYER VALUES (82, 'Contractz', 'Garcia', 'USA', DATE '2023-04-06', 82, 08);
INSERT INTO PLAYER VALUES (83, 'Palafox', 'Palafox', 'USA', DATE '2023-04-06', 83, 08);
INSERT INTO PLAYER VALUES (84, 'FBI', 'Huang', 'AUS', DATE '2023-05-27', 84, 08);
INSERT INTO PLAYER VALUES (85, 'IgNar', 'Lee', 'SK', DATE '2023-05-27', 85, 08);

INSERT INTO Tournament VALUES ('2023 LOL Worlds', DATE '2023-11-02', DATE '2023-11-19', 'Elimination', 'Gocheok Sky Dome', 'Seoul', 22, 02);

INSERT INTO Match1 VALUES ('Finals', 5);
INSERT INTO Match1 VALUES ('Semifinals1', 5);
INSERT INTO Match1 VALUES ('Semifinals2', 5);
INSERT INTO Match1 VALUES ('Quarterfinals1', 5);
INSERT INTO Match1 VALUES ('Quarterfinals2', 5);
INSERT INTO Match1 VALUES ('Quarterfinals3', 5);
INSERT INTO Match1 VALUES ('Quarterfinals4', 5);

INSERT INTO Match2 VALUES (101, '2023 LOL Worlds', DATE '2023-11-02', TO_TIMESTAMP('2023-11-02 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), '3-0', 07, 08, 07, 'Quarterfinals1');
INSERT INTO Match2 VALUES (102, '2023 LOL Worlds', DATE '2023-11-03', TO_TIMESTAMP('2023-11-02 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), '3-2', 01, 05, 05, 'Quarterfinals2');
INSERT INTO Match2 VALUES (103, '2023 LOL Worlds', DATE '2023-11-04', TO_TIMESTAMP('2023-11-02 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), '3-1', 03, 04, 04, 'Quarterfinals3');
INSERT INTO Match2 VALUES (104, '2023 LOL Worlds', DATE '2023-11-05', TO_TIMESTAMP('2023-11-02 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), '3-0', 02, 06, 02, 'Quarterfinals4');
INSERT INTO Match2 VALUES (201, '2023 LOL Worlds', DATE '2023-11-11', TO_TIMESTAMP('2023-11-02 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), '3-2', 07, 05, 07, 'Semifinals1');
INSERT INTO Match2 VALUES (202, '2023 LOL Worlds', DATE '2023-11-12', TO_TIMESTAMP('2023-11-02 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), '3-1', 02, 04, 02, 'Semifinals1');
INSERT INTO Match2 VALUES (301, '2023 LOL Worlds', DATE '2023-11-19', TO_TIMESTAMP('2023-11-02 19:00:00', 'YYYY-MM-DD HH24:MI:SS'), '3-0', 02, 07, 02, 'Finals'); Worlds', DATE '2023-11-02', TO_TIMESTAMP('2023-11-02 19:00:00', 'YYYY-MM-DD HH24:MI:SS'), '3-0', 02, 07, 02, 'Finals');