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
