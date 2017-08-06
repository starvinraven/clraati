CREATE TABLE users (
  id       INTEGER PRIMARY KEY,
  username VARCHAR,
  password VARCHAR
);
--;;
CREATE TABLE artists (
  id   INTEGER PRIMARY KEY,
  name VARCHAR
);
--;;
CREATE TABLE albums (
  id     INTEGER PRIMARY KEY,
  name   VARCHAR,
  artist INTEGER REFERENCES artists (id) NOT NULL
);
--;;
CREATE TABLE songs (
  id     INTEGER PRIMARY KEY,
  name   VARCHAR,
  artist INTEGER REFERENCES artists (id) NOT NULL,
  album  INTEGER REFERENCES albums (id)  NOT NULL
);
--;;
CREATE TABLE ratings (
  id         INTEGER PRIMARY KEY,
  comment    TEXT,
  song       INTEGER REFERENCES songs (id),
  rating     INTEGER NOT NULL,
  normalized NUMERIC
);
