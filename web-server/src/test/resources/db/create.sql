DROP TABLE IF EXISTS competitions;
DROP TABLE IF EXISTS countries;

CREATE TABLE countries (
  id   UUID PRIMARY KEY,
  name TEXT NOT NULL,
  rank INT  NOT NULL UNIQUE
);

CREATE TABLE competitions (
  id         UUID PRIMARY KEY,
  name       TEXT                           NOT NULL,
  country_id UUID REFERENCES countries (id) NOT NULL,
  rank       INT                            NOT NULL,
  CONSTRAINT unique_country_rank UNIQUE (country_id, rank)
);
