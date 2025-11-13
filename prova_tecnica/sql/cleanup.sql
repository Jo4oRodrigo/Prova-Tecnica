-- Cleanup seed data (use with caution)
DELETE FROM auditoria_login WHERE usuario_id IN (SELECT id FROM usuarios WHERE username LIKE '%@example.com');
DELETE FROM usuarios WHERE username LIKE '%@example.com';
