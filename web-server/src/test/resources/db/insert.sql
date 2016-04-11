INSERT INTO countries (id, name, priority) VALUES
  ('720efe16-0517-47bf-8af0-e6fcca8baa6c', 'England', 1),
  ('d8e226b2-f78f-4448-b94c-2e94f12108a7', 'Spain', 2),
  ('88a7559e-0e18-4e84-952e-67db32e51bb9', 'Germany', 3),
  ('d495cfbe-257e-453c-8742-8034e0e9c49a', 'France', 4);

INSERT INTO competitions (id, country_id, name, priority) VALUES
  -- england
  ('05c6dd65-8141-4093-827a-b05372bcbbe5', '720efe16-0517-47bf-8af0-e6fcca8baa6c', 'Premier League', 1),
  ('5070aa50-e5fe-4f3b-84a6-7b304c5533fb', '720efe16-0517-47bf-8af0-e6fcca8baa6c', 'Championship', 2),
  ('d00825a2-c389-47b6-8e89-2e37086f5fd7', '720efe16-0517-47bf-8af0-e6fcca8baa6c', 'League One', 3),
  ('b0a6f611-116a-4e24-8d1c-8e7a7cbd7041', '720efe16-0517-47bf-8af0-e6fcca8baa6c', 'League Two', 4),

  -- spain
  ('245c927b-b02e-4a6f-965e-7930559382ae', 'd8e226b2-f78f-4448-b94c-2e94f12108a7', 'Primera Division', 1),
  ('87c9af63-6931-4ac5-a29f-7a07ceae873e', 'd8e226b2-f78f-4448-b94c-2e94f12108a7', 'Segunda Division', 2),

  -- germany
  ('d40bd216-c6aa-416f-ab35-50ea5ece5ad2', '88a7559e-0e18-4e84-952e-67db32e51bb9', 'Bundesliga 1', 1),
  ('c534edcf-4f6f-4b9c-9da4-3629455f7623', '88a7559e-0e18-4e84-952e-67db32e51bb9', 'Bundesliga 2', 2),

  -- france
  ('21e864fd-2ebc-457a-b510-ba8d34479280', 'd495cfbe-257e-453c-8742-8034e0e9c49a', 'Ligue 1', 1),
  ('6313bed5-ed47-40ec-b850-3c1677127fe4', 'd495cfbe-257e-453c-8742-8034e0e9c49a', 'Ligue 2', 2);
