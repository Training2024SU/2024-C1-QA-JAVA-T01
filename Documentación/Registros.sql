INSERT INTO `Usuario` (`id`, `nombre`, `correo`, `contrasenia`, `rol`)
VALUES
    ('1', 'John Doe', 'administrador@pingu.com.co', 'contraseñasegura123456', 'ADMIN'),
    ('2', 'Asistente Usuario', 'asistente@example.com', 'asistente123', 'ASISTENTE'),
    ('3', 'Lector Usuario', 'lector@example.com', 'lector123', 'LECTOR'),
    ('4', 'Usuario Cuatro', 'usuario4@example.com', 'usuario4123', 'LECTOR'),
    ('5', 'Usuario Cinco', 'usuario5@example.com', 'usuario5123', 'ASISTENTE');


INSERT INTO `Autor` (`id`, `nombre`)
VALUES
    ('1', 'Gabriel García Márquez'),
    ('2', 'J.K. Rowling'),
    ('3', 'Stephen King'),
    ('4', 'Jane Austen'),
    ('5', 'Fyodor Dostoevsky'),
    ('6', 'Carl Sagan'),
    ('7', 'Stephe Hawking'),
    ('8', 'Michio Kaku')
    ;


INSERT INTO `Publicacion` (`id`, `titulo`, `nEjemplares`, `nPrestados`, `autor_id`)
VALUES
    ('1', 'Cien años de soledad', 100, 90, '1'),
    ('2', 'El amor en los tiempos del cólera', 90, 0, '1'),
    ('3', 'Crónica de una muerte anunciada', 90, 2, '1'),
    ('4', 'Harry Potter y la Piedra Filosofal', 150, 120, '2'),
    ('5', 'Orgullo y Prejuicio', 120, 110, '4'),
    ('6', 'Crime and Punishment', 90, 80, '5'),
    ('7', 'Los Hermanos Karamazov', 90, 25, '5')
    ;


INSERT INTO `Prestamo` (`id`, `fechaRealizado`, `fechaExpiracion`, `usuario_id`, `estado`)
VALUES
    ('1', '2024-04-01', '2024-04-15', '2', 'SOLICITADO'),
    ('2', '2024-04-03', '2024-04-17', '3', 'REALIZADO'),
    ('3', '2024-04-05', '2024-04-19', '1', 'FINALIZADO'),
    ('4', '2024-04-07', '2024-04-21', '4', 'SOLICITADO'),
    ('5', '2024-04-07', '2024-04-21', '4', 'SOLICITADO'),
    ('6', '2024-04-07', '2024-04-21', '4', 'SOLICITADO'),
    ('7', '2024-04-09', '2024-04-23', '5', 'REALIZADO');


INSERT INTO `Publicacion x Prestamo` (`prestamo_id`, `publicacion_id`)
VALUES
    ('1', '1'),
    ('2', '2'),
    ('3', '3'),
    ('4', '4'),
    ('5', '5'),
    ('6', '6'),
    ('7', '7');


INSERT INTO `Novela` (`id`, `edadLectura`, `genero`)
VALUES
    ('1', 18, 'HISTORICA'),
    ('2', 21, 'TERROR'),
    ('3', 16, 'ROMANTICA'),
    ('4', 20, 'CIENCIA_FICCION');

INSERT INTO `Libro` (`id`, `paginas`, `area`)
VALUES
	('5', 22, 'HISTORIA'),
    ('6', 22, 'HISTORIA'),
    ('7', 300, 'CIENCIA_POPULAR');





-- Ignorar lo siguiente



-- SELECT l.id AS 'libro_id', p.titulo, l.paginas, l.area, p.nEjemplares,
-- 	p.nDisponibles, p.nPrestados, p.autor_id, a.nombre AS 'nombre_autor'
-- FROM libro l
-- JOIN publicacion p ON l.id = p.id
-- JOIN autor a ON p.autor_id = a.id;

-- SELECT n.id AS 'novela_id', p.titulo, n.edadLectura, n.genero, p.nEjemplares,
-- 	 p.nPrestados, p.nDisponibles, p.autor_id, a.nombre AS 'nombre_autor'
-- FROM novela n
-- JOIN publicacion p ON n.id = p.id
-- JOIN autor a ON p.autor_id = a.id
-- ;

-- select * from publicacion
-- ;

-- select * from autor where id = '100';
-- select * from libro;
-- update publicacion set autor_id = '1' where id = '5';
-- select * from publicacion where id = 91764;
