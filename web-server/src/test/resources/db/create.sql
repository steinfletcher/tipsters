DROP TABLE IF EXISTS competitions;
DROP TABLE IF EXISTS countries;
DROP TABLE IF EXISTS regions;

CREATE TABLE regions (
  id   UUID PRIMARY KEY,
  name TEXT NOT NULL,
  rank INT  NOT NULL UNIQUE
);

CREATE TABLE countries (
  id        UUID PRIMARY KEY,
  name      TEXT                         NOT NULL,
  region_id UUID REFERENCES regions (id) NOT NULL,
  rank      INT                          NOT NULL UNIQUE
);

CREATE TABLE competitions (
  id         UUID PRIMARY KEY,
  name       TEXT                           NOT NULL,
  country_id UUID REFERENCES countries (id) NOT NULL,
  rank       INT                            NOT NULL,
  CONSTRAINT unique_country_rank UNIQUE (country_id, rank)
);
