INSERT INTO Team1(Organization, Country)
VALUES
	('KSV GEN', 'SK'),
	('SK Telecom', 'SK')
	('KT Corp', 'SK'),
	('Kia', 'SK'),
	('JingDong', 'CN'),
	('Bilibili', 'CN'),
	('LiNing', 'CN'),
	('Weibo', 'CN'),
	('NR', 'NA'), 
	('Cloud9', 'NA'),
	('Liquid', 'NA'),
	('MAD Lions', 'EU'),
	('Gamers2', 'EU'),
	('Fnatic', 'EU'),
	('BDS Gaming', 'EU'),
	('Giga', 'VN');


INSERT INTO Team2(ID, Name, Organization)
VALUES
	(01, 'Gen.G', 'KSV GEN'),
	(02, 'T1', 'SK Telecom'),
	(03, 'KT', 'KT Corp'),
	(04, 'DK', 'Kia'),
	(05, 'JDG', 'JingDong'),
	(06, 'BLG', 'Bilibili'),
	(07, 'LNG', 'LiNing'),
	(08, 'WBG', 'Weibo'),
	(09, 'NRG', 'NR'),
	(10, 'C9', 'Cloud9'),
	(11, 'TL', 'Liquid'),
	(12, 'MAD', 'MAD Lions'),
	(13, 'G2', 'Gamers2'),
	(14, 'FNC', 'Fnatic'),
	(15, 'BDS', 'BDS Gaming'),
	(16, 'GAM', 'Giga');

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
	(),
