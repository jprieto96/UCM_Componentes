INSERT IGNORE INTO cliente (nombre, domicilio, cuenta_bancaria, activo) VALUES
	('Pepito', 'Gran Vía 1', 'ES0000000000000000000000', TRUE),
    ('Pepita', 'Gran Vía 2', 'ES0000000000000000000001', TRUE),
    ('Pepite', 'Gran Vía 3', 'ES0000000000000000000002', FALSE),
    ('Pepiti', 'Gran Vía 4', 'ES0000000000000000000003', TRUE),
    ('Pepitu', 'Gran Vía 5', 'ES0000000000000000000004', TRUE);
    
INSERT IGNORE INTO proveedor (nombre, activo) VALUES
	('Manolito', TRUE),
    ('Manolita', TRUE),
    ('Manolite', FALSE),
    ('Manoliti', FALSE),
    ('Manolitu', TRUE);
    