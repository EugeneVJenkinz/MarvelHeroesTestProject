USE marvelheroes_db;

CREATE TABLE characters (
  character_id int PRIMARY KEY AUTO_INCREMENT,
  character_name VARCHAR(50)
) ;

CREATE TABLE comics (
  comic_id int PRIMARY KEY AUTO_INCREMENT,
  comic_name VARCHAR(50)
) ;

CREATE TABLE pictures (
  picture_id int PRIMARY KEY AUTO_INCREMENT,
  picture_array LONGBLOB,
  character_id int,
  comic_id int,
  FOREIGN KEY (character_id) REFERENCES characters(character_id),
  FOREIGN KEY (comic_id) REFERENCES comics(comic_id)
) ;

CREATE TABLE characters_comics (
  character_id int NOT NULL,
  comic_id int NOT NULL,
  FOREIGN KEY (character_id) REFERENCES characters(character_id),
  FOREIGN KEY (comic_id) REFERENCES comics(comic_id)
) ;