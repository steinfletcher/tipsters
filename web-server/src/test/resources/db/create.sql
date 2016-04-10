DROP TABLE IF EXISTS teams;
DROP TABLE IF EXISTS leagues;
DROP TABLE IF EXISTS countries;

CREATE TABLE countries (
  id   UUID PRIMARY KEY,
  name TEXT NOT NULL
);

CREATE TABLE leagues (
  id        UUID PRIMARY KEY,
  name      TEXT NOT NULL,
  country_id UUID REFERENCES countries (id) NOT NULL
);

CREATE TABLE teams (
  id        UUID PRIMARY KEY,
  name      TEXT                         NOT NULL,
  league_id UUID REFERENCES leagues (id) NOT NULL
);
