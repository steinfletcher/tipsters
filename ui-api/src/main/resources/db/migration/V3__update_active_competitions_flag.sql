UPDATE competitions
SET active = TRUE
WHERE name IN (
  'English Premier League',
  'English FA Cup',
  'English Championship',
  'English League 1',
  'English League 2',
  'English National League',
  'Scottish Premiership',
  'William Hill Scottish Cup',
  'Scottish Championship',
  'Scottish League One',
  'Scottish League Two',
  'Welsh First Division',
  'German Bundesliga',
  'Spanish La Liga Primera',
  'Spanish Segunda Division Femenina',
  'Italian Serie A'
);