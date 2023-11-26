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
	Match_Number INT,
	Tournament_name VARCHAR(50) NOT NULL,
	Tournament_Start_date DATE NOT NULL,
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

CREATE TABLE PLAYER(
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

INSERT INTO Tournament	VALUES ('TournamentA', DATE '2012-04-08', DATE '2012-04-12', 'Progression', 
'VenueA', 'Shanghai', 123, 1);
INSERT INTO Tournament	VALUES ('TournamentB', DATE '2020-12-07', DATE '2020-12-21', 'Elimination', 
'VenueB', 'Vancouver', 128, 5);
INSERT INTO Tournament	VALUES ('TournamentC', DATE '2016-11-03', DATE '2016-11-09', 'Elimination', 
'VenueC', 'Helsinki', 21, 4);
INSERT INTO Tournament	VALUES ('LOL', DATE '2018-11-05', DATE '2018-11-05', 'Elimination', 'VenueD', 
'Incheon', 1,1);
INSERT INTO Tournament	VALUES (DATE '2018 Winter Olympics', DATE '2018-02-09', DATE '2018-02-25', 
'Progression', 'VenueE', 'PyeongChang', 127, 23);

INSERT INTO Match1		VALUES ('Finals', 5);
INSERT INTO Match1		VALUES ('Semifinals', 5);
INSERT INTO Match1		VALUES ('Qualification', 3);
INSERT INTO Match1		VALUES ('Quarterfinals', 3);
INSERT INTO Match1		VALUES ('Group', 3);

INSERT INTO Match2		VALUES (1, 'LOL', DATE '2018-11-05', '17:00:00', '3-2', 101, 102, 101, 'Finals');
INSERT INTO Match2		VALUES (2, 'LOL', DATE '2018-11-05', '15:00:00', '3-0', 101, 104, 101, 'Semifinals');
INSERT INTO Match2		VALUES (3, 'LOL', DATE '2018-11-05', '15:00:00', '3-1', 102, 103, 102, 'Semifinals');
INSERT INTO Match2		VALUES (4, 'TournamentA', DATE '2012-04-08', '12:30:00', '1-2', 101, 105, 105, 'Qualification');
INSERT INTO Match2		VALUES (5, 'TournamentB', DATE '2020-12-07', '09:45:00', '0-2', 102, 104, 104, 'Quarterfinals');

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

INSERT INTO Broadcasts		VALUES ('TournamentA', DATE '2012-04-08', 1);
INSERT INTO Broadcasts		VALUES ('TournamentB', DATE '2020-12-07', 2);
INSERT INTO Broadcasts		VALUES ('TournamentC', DATE '2016-11-03', 3);
INSERT INTO Broadcasts		VALUES ('LOL', DATE '2018-11-05', 4);
INSERT INTO Broadcasts		VALUES ('2018 Winter Olympics', DATE '2018-02-09', 5);

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

INSERT INTO Coach		VALUES (1010, 'Frank', 'Freeman', 'France', DATE '2021-10-02', 2, 101);
INSERT INTO Coach		VALUES (1020, 'Gary', 'Green', 'Germany', DATE '2015-02-12', 8, 102);
INSERT INTO Coach		VALUES (1030, 'Helen', 'Hall', 'Hungary', DATE '2018-11-06', 5, 103);
INSERT INTO Coach		VALUES (1040, 'Irene', 'Idd', 'Iceland', DATE '2000-09-21', 23, 104);
INSERT INTO Coach		VALUES (1040, 'Jackson', 'Johnson', 'Japan', DATE '2008-12-31', 15, 105);

INSERT INTO BusinessMember	VALUES (111, 'Kelly', 'Kwan', 'Korea', DATE '2023-02-01', 'Manager', 101);
INSERT INTO BusinessMember	VALUES (222, 'Lesley', 'Lee', 'Luxembourg', DATE '2020-01-01', 'Director', 102);
INSERT INTO BusinessMember	VALUES (333, 'Michelle', 'Miller', 'Morocco', DATE '2018-02-17', 'Doctor', 103);
INSERT INTO BusinessMember	VALUES (444, 'Nelson', 'Nelson', 'New Zealand', DATE '2019-12-31', 'Doctor', 104);
INSERT INTO BusinessMember	VALUES (555, 'Olivia', 'OBrien', 'Oman', DATE '2017-05-31', 'Manager', 105);

INSERT INTO Player		VALUES (666, 'Patrick', 'Parker', 'Canada', DATE '2018-02-05', 6, 101);
INSERT INTO Player		VALUES (777, 'Quinna', 'Quarles', 'USA', DATE '2020-12-31', 2, 102);
INSERT INTO Player		VALUES (888, 'Rachel', 'Rayon', 'Mexico', DATE '2023-01-01', 8, 103);
INSERT INTO Player		VALUES (999, 'Sylvia', 'Sin', 'Japan', DATE '2019-07-31', 7, 104); 
INSERT INTO Player		VALUES (1010, 'Tim', 'Thomas', 'Finland', DATE '2022-10-31', 1, 105);

