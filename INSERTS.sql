INSERT INTO Team1(Organization, Country)
VALUES
	('KSV GEN', 'SK'),
	('SK Telecom', 'SK')
	('KT Corp', 'SK'),
	('JingDong', 'CN'),
	('Bilibili', 'CN'),
	('LiNing', 'CN'),
	('Weibo', 'CN'),
	('NR', 'NA');

INSERT INTO Team2(ID, Name, Organization)
VALUES
	(01, 'Gen.G', 'KSV GEN'),
	(02, 'T1', 'SK Telecom'),
	(03, 'KT', 'KT Corp'),
	(04, 'JDG', 'JingDong'),
	(05, 'BLG', 'Bilibili'),
	(06, 'LNG', 'LiNing'),
	(07, 'WBG', 'Weibo'),
	(08, 'NRG', 'NR');

CREATE TABLE PLAYER(
	ID INT PRIMARY KEY,
	First_name VARCHAR NOT NULL,
	Last_name VARCHAR NOT NULL,
	Country VARCHAR NOT NULL,
	Join_date DATE NOT NULL,
	Ranking INT UNIQUE NOT NULL,
	Team_ID INT NOT NULL,
FOREIGN KEY (Team_ID) REFERENCES Team(ID) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Player(ID, First_name, Last_name, Country, Join_date, Ranking, Team_ID)
VALUES
	(11, 'Doran', ),
	(12, 'Peanut', 'Han', 'SK', '2022-01-01', 12, 01),
	(13, 'Chovy', 'Jeong', 'SK', '2022-01-01', 13, 01), 
	(14, 'Peyz', ''),
	(15, 'Delight', ''),
	(21, 'Zeus', 'Choi', 'SK', '2020-11-05', 21, 02),
	(22, 'Oner', ''),
	(23, 'Faker', 'Lee', 'SK', '2013-01-01', 23, 02),
	(24, 'Gumayusi', 'Lee', 'SK', '2021-02-03', 24, 02),
	(25, 'Keria', 'Ryu', 'SK', '2020-11-05', 25, 02), 
	(31),
	(32),
	(33),
	(34),
	(35),
	(41),
	(42),
	(43),
	(44),
	(45),
	(51),
	(52),
	(53),
	(54),
	(55),
	(61),
	(62),
	(63),
	(64),
	(65),
	(71),
	(72),
	(73),
	(74),
	(75),
	(81),
	(82),
	(83),
	(84),
	(85);
	
	 
	 
	 
	 
	 
