ALTER TABLE competitions
  ADD active BOOL DEFAULT TRUE NOT NULL;

UPDATE competitions
SET active = FALSE
WHERE name IN (
  'English Premier League',
  'English FA Cup',
  'English Championship',
  'English League 1',
  'English League 2',
  'English National League',
  'Scottish Premiership',
  'Scottish Championship',
  'Scottish League One',
  'Scottish League Two',
  'Welsh First Division',
  'German Bundesliga',
  'Spanish La Liga Primera',
  'Spanish Segunda Division Femenina',
  'Italian Serie A'
);