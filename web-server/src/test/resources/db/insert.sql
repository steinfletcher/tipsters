INSERT INTO countries (id, name, rank) VALUES
  ('720efe16-0517-47bf-8af0-e6fcca8baa6c', 'England', 1),
  ('f1ab9786-938e-4ff4-b826-c93a58cea7ac', 'Scotland', 2),
  ('e882a456-d703-498b-a63d-4512fe3645b9', 'Wales', 3);

INSERT INTO competitions (id, country_id, name, rank) VALUES
  -- england
  ('05c6dd65-8141-4093-827a-b05372bcbbe5', '720efe16-0517-47bf-8af0-e6fcca8baa6c', 'English Premier League', 1),
  ('5070aa50-e5fe-4f3b-84a6-7b304c5533fb', '720efe16-0517-47bf-8af0-e6fcca8baa6c', 'English FA Cup', 2),
  ('245c927b-b02e-4a6f-965e-7930559382ae', '720efe16-0517-47bf-8af0-e6fcca8baa6c', 'English Championship', 3),
  ('d00825a2-c389-47b6-8e89-2e37086f5fd7', '720efe16-0517-47bf-8af0-e6fcca8baa6c', 'English League 1', 4),
  ('b0a6f611-116a-4e24-8d1c-8e7a7cbd7041', '720efe16-0517-47bf-8af0-e6fcca8baa6c', 'English League 2', 5),
  ('c534edcf-4f6f-4b9c-9da4-3629455f7623', '720efe16-0517-47bf-8af0-e6fcca8baa6c', 'English National League', 6),

  -- scotland
  ('a0120fe1-3e4d-4035-bfec-62f5779bf261', 'f1ab9786-938e-4ff4-b826-c93a58cea7ac', 'Scottish Premiership', 1),
  ('026f0c41-8c0e-42ca-92c8-b834fdbb71a2', 'f1ab9786-938e-4ff4-b826-c93a58cea7ac', 'William Hill Scottish Cup', 2),
  ('fc136480-04e3-4224-aaad-8aa2d677a521', 'f1ab9786-938e-4ff4-b826-c93a58cea7ac', 'Scottish Championship', 3),
  ('d59c868d-1a6d-40ba-a318-2bb0f500d8ee', 'f1ab9786-938e-4ff4-b826-c93a58cea7ac', 'Scottish League One', 4),
  ('52abc101-78bd-447a-b5c8-299b46c41da5', 'f1ab9786-938e-4ff4-b826-c93a58cea7ac', 'Scottish League Two', 5),

  -- wales
  ('913b2b29-eba0-4a13-a884-4be967ef3064', 'e882a456-d703-498b-a63d-4512fe3645b9', 'Welsh First Division', 1);
