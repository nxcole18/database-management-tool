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
	(11, 'Doran', 'Choi', 'SK', '2022-01-01', 11, 01),
	(12, 'Peanut', 'Han', 'SK', '2022-01-01', 12, 01),
	(13, 'Chovy', 'Jeong', 'SK', '2022-01-01', 13, 01), 
	(14, 'Peyz', 'Kim', 'SK', '2023-01-01', 14, 01),
	(15, 'Delight', 'Yoo', 'SK', '2023-01-01', 15, 01),
	(21, 'Zeus', 'Choi', 'SK', '2020-11-05', 21, 02),
	(22, 'Oner', 'Mun', 'SK', '2021-01-01', 22, 02),
	(23, 'Faker', 'Lee', 'SK', '2013-01-01', 23, 02),
	(24, 'Gumayusi', 'Lee', 'SK', '2021-02-03', 24, 02),
	(25, 'Keria', 'Ryu', 'SK', '2020-11-05', 25, 02), 
	(31, 'Kiin', 'Kim', 'SK', '2023-01-01', 31, 03),
	(32, 'Cuzz', 'Moon', 'SK', '2023-01-01', 32, 03),
	(33, 'Bdd', 'Gwak', 'SK', '2023-01-02', 33, 03),
	(34, 'Aiming' 'Kim', 'SK', '2023-01-01', 34, 03),
	(35, 'Lehends', 'Son', 'SK', '2023-01-01', 35, 03),
	(41, '369', 'Bai', 'CN', '2023-01-01', 41, 04),
	(42, 'Kanavi', 'Seo', 'SK', '2022-01-01', 42, 04),
	(43, 'Knight', 'Zhuo', 'CN', '2023-01-01', 43, 04),
	(44, 'Ruler', 'Park', 'SK', '2023-01-01', 44, 04),
	(45, 'Missing', 'Lou', 'CN', '2023-01-01', 45, 04),
	(51,),
	(52,),
	(53,),
	(54,),
	(55,),
	(61,),
	(62),
	(63, 'Scout', '),
	(64),
	(65),
	(71, 'TheShy', 'Kang', 'SK', '2022-01-01', 71, 07),
	(72),
	(73),
	(74),
	(75),
	(81),
	(82),
	(83),
	(84),
	(85);
	
	 
	 
	 
	 
	 
