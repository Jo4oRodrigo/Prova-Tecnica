-- Seed users for tests
-- Assumes table usuarios(id serial primary key, username text unique, password text, perfil text, bloqueado boolean default false, tentativas int default 0);
INSERT INTO usuarios (username, password, perfil, bloqueado, tentativas)
VALUES
('user@example.com', 'validPassword', 'USER', false, 0),
('admin@example.com', 'adminPass', 'ADMIN', false, 0),
('visitor@example.com', 'visitorPass', 'VISITOR', false, 0),
('blocked@example.com', 'any', 'USER', true, 3),
('toBeLocked@example.com', 'correctPassword', 'USER', false, 0);
-- Seed auditoria_login (example)
INSERT INTO auditoria_login (usuario_id, sucesso, data_evento)
SELECT u.id, true, NOW() - interval '1 day'
FROM usuarios u
WHERE u.username = 'admin@example.com'
;
-- create multiple successful logins for admin to satisfy >5 if needed
DO $$
BEGIN
  FOR i IN 1..6 LOOP
    INSERT INTO auditoria_login (usuario_id, sucesso, data_evento)
    SELECT id, true, NOW() - (i || ' minutes')::interval FROM usuarios WHERE username='admin@example.com';
  END LOOP;
END$$;
