CREATE TABLE users (
  id         INTEGER PRIMARY KEY,
  username   VARCHAR,
  name       VARCHAR,
  fb_id      VARCHAR,
  fb_user    JSON,
  last_login TIMESTAMP WITH TIME ZONE,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);
--;;
CREATE TABLE logins (
  id        INTEGER PRIMARY KEY,
  login_key VARCHAR,
  user      INTEGER REFERENCES users (id),
  expires   TIMESTAMP WITH TIME ZONE
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
  album  INTEGER REFERENCES albums (id)  NOT NULL,
  url    VARCHAR
);
--;;
CREATE TABLE ratings (
  id         INTEGER PRIMARY KEY,
  comment    TEXT,
  song       INTEGER REFERENCES songs (id),
  rating     INTEGER NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
  normalized NUMERIC
);

