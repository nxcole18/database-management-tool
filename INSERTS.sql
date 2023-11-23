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

INSERT INTO Team1(Organization, Country)
VALUES
  ('KSV GEN', 'SK'),
  ('SK Telecom', 'SK'),
  ('KT Corp', 'SK'),
  ('Kia', 'SK'),
  ('JingDong', 'CN'),
  ('Bilibili', 'CN'),
  ('LiNing', 'CN'),
  ('Weibo', 'CN'),
  ('NR', 'NA'), 
  ('C9', 'NA'),
  ('Liquid', 'NA'),
  ('MAD Lions', 'EU'),
  ('Gamers2', 'EU'),
  ('Fnatic', 'EU'),
  ('BDS Gaming', 'EU'),
  ('Giga', 'VN');


