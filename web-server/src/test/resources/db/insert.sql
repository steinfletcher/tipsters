
INSERT INTO countries(id, name) VALUES
  ('720efe16-0517-47bf-8af0-e6fcca8baa6c', 'England');

INSERT INTO leagues(id, country_id, name) VALUES
  ('05c6dd65-8141-4093-827a-b05372bcbbe5', '720efe16-0517-47bf-8af0-e6fcca8baa6c', 'Premier League');

INSERT INTO teams(id, league_id, name) VALUES
  ('16b0c1a9-91b3-470f-b2c4-47d304ba4fb2', '05c6dd65-8141-4093-827a-b05372bcbbe5', 'Arsenal'),
  ('e25f6cdd-40cd-4be8-927b-cc80f66a741f', '05c6dd65-8141-4093-827a-b05372bcbbe5', 'Chelsea'),
  ('1b619ed9-d9d0-4e06-8946-c6835f01a0da', '05c6dd65-8141-4093-827a-b05372bcbbe5', 'Man Utd'),
  ('2e730584-1c52-4339-b66c-0a790737cc2e', '05c6dd65-8141-4093-827a-b05372bcbbe5', 'Leicester City');
