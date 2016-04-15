INSERT INTO regions (id, name, rank) VALUES
  ('70d525cb-2845-4ae0-9cc9-5d189f847e28', 'UK', 1),
  ('0d349532-6b22-45c3-9894-f85b53f0d90e', 'European Major Leagues', 2);

INSERT INTO countries (id, name, region_id, rank) VALUES
  ('720efe16-0517-47bf-8af0-e6fcca8baa6c', 'England', '70d525cb-2845-4ae0-9cc9-5d189f847e28', 1),
  ('3a99b0a1-f8dc-44f9-97d0-fca1a984c8a9', 'Spain', '0d349532-6b22-45c3-9894-f85b53f0d90e', 2),
  ('2464dd3b-4ce1-4d7c-8c65-3f69e6f55a2a', 'Germany', '0d349532-6b22-45c3-9894-f85b53f0d90e', 3),
  ('3d36f907-3ea1-405e-9f38-07b83047735d', 'Italy', '0d349532-6b22-45c3-9894-f85b53f0d90e', 4),
  ('f1ab9786-938e-4ff4-b826-c93a58cea7ac', 'Scotland', '70d525cb-2845-4ae0-9cc9-5d189f847e28', 5),
  ('e882a456-d703-498b-a63d-4512fe3645b9', 'Wales', '70d525cb-2845-4ae0-9cc9-5d189f847e28', 6);

INSERT INTO competitions (id, country_id, name, rank) VALUES
  -- england
  ('05c6dd65-8141-4093-827a-b05372bcbbe5', '720efe16-0517-47bf-8af0-e6fcca8baa6c', 'English Premier League', 1),
  ('5070aa50-e5fe-4f3b-84a6-7b304c5533fb', '720efe16-0517-47bf-8af0-e6fcca8baa6c', 'English FA Cup', 2),
  ('245c927b-b02e-4a6f-965e-7930559382ae', '720efe16-0517-47bf-8af0-e6fcca8baa6c', 'English Championship', 3),
  ('d00825a2-c389-47b6-8e89-2e37086f5fd7', '720efe16-0517-47bf-8af0-e6fcca8baa6c', 'English League 1', 4),
  ('b0a6f611-116a-4e24-8d1c-8e7a7cbd7041', '720efe16-0517-47bf-8af0-e6fcca8baa6c', 'English League 2', 5),
  ('c534edcf-4f6f-4b9c-9da4-3629455f7623', '720efe16-0517-47bf-8af0-e6fcca8baa6c', 'English National League', 6),
  ('a0120fe1-3e4d-4035-bfec-62f5779bf261', 'f1ab9786-938e-4ff4-b826-c93a58cea7ac', 'Scottish Premiership', 1),
  ('026f0c41-8c0e-42ca-92c8-b834fdbb71a2', 'f1ab9786-938e-4ff4-b826-c93a58cea7ac', 'William Hill Scottish Cup', 2),
  ('fc136480-04e3-4224-aaad-8aa2d677a521', 'f1ab9786-938e-4ff4-b826-c93a58cea7ac', 'Scottish Championship', 3),
  ('d59c868d-1a6d-40ba-a318-2bb0f500d8ee', 'f1ab9786-938e-4ff4-b826-c93a58cea7ac', 'Scottish League One', 4),
  ('52abc101-78bd-447a-b5c8-299b46c41da5', 'f1ab9786-938e-4ff4-b826-c93a58cea7ac', 'Scottish League Two', 5),
  ('913b2b29-eba0-4a13-a884-4be967ef3064', 'e882a456-d703-498b-a63d-4512fe3645b9', 'Welsh First Division', 1),
  ('5b6854e1-5fc9-4c9f-b309-9084a57a8316', '2464dd3b-4ce1-4d7c-8c65-3f69e6f55a2a', 'German Bundesliga', 1),
  ('e8bd899c-8c7c-4ab7-a439-36b7b042df4d', '3a99b0a1-f8dc-44f9-97d0-fca1a984c8a9', 'Spanish La Liga Primera', 1),
  ('b6bb1282-c5dd-4e64-a0ad-5060c417d62d', '3a99b0a1-f8dc-44f9-97d0-fca1a984c8a9', 'Spanish Segunda Division Femenina', 2),
  ('02d3a983-ae9d-4163-a5b9-0564b01e1c41', '3d36f907-3ea1-405e-9f38-07b83047735d', 'Italian Serie A', 1);
