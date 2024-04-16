USE pingu;

INSERT INTO Users (id, name, email, password, role)
VALUES 
    (2, 'Juan Pérez', 'juan@example.com', 'password123', 'ADMINISTRATOR'),
    (3, 'María Gómez', 'maria@example.com', 'securepass', 'READER'),
    (4, 'Pedro Ramirez', 'pedro@example.com', 'strongpassword', 'READER'),
    (5, 'Luisa Martínez', 'luisa@example.com', 'luisapass', 'EMPLOYEE'),
    (6, 'Andrés López', 'andres@example.com', 'andrespass', 'READER');

INSERT INTO Books (id, title, author, copies, copies_borrowed, field, pages)
VALUES 
    (1, 'Cien años de soledad', 'Gabriel García Márquez', 6, 1, 'Ficción', 432),
    (2, 'El amor en los tiempos del cólera', 'Gabriel García Márquez', 1, 1, 'Ficción', 368), -- unavailable copies
    (3, 'Crónica de una muerte anunciada', 'Gabriel García Márquez', 2, 0, 'Ficción', 128),
    (4, 'La sombra del viento', 'Carlos Ruiz Zafón', 4, 0, 'Ficción', 576),
    (5, 'Cien años de soledad', 'Gabriel García Márquez', 10, 0, 'Ficción', 434),
    (6, 'Rayuela', 'Julio Cortázar', 5, 1, 'Ficción', 700);

INSERT INTO Novels (id, title, author, copies, copies_borrowed, genre, recommended_age)
VALUES 
    (1, 'La casa de los espíritus', 'Isabel Allende', 3, 0, 'Realismo mágico', 16),
    (2, 'El coronel no tiene quien le escriba', 'Gabriel García Márquez', 4, 0, 'Ficción', 18),
    (3, 'La ciudad y los perros', 'Mario Vargas Llosa', 1, 0, 'Novela', 16),
    (4, 'Cien años de soledad', 'Gabriel García Márquez', 2, 0, 'Realismo mágico', 18),
    (5, 'La fiesta del chivo', 'Mario Vargas Llosa', 5, 0, 'Histórica', 14),
    (6, 'La ciudad y los perros', 'Mario Vargas Llosa', 3, 1, 'Novela', 14);

INSERT INTO Borrowings (id, user_id, requested_date, returned_date, status)
VALUES 
    (1, 2, '2024-04-03', '2024-04-17', 'BORROWED'),
    (2, 3, '2024-04-05', '2024-04-19', 'REQUESTED'),
    (3, 4, '2024-04-07', '2024-04-21', 'REQUESTED'),
    (4, 5, '2024-04-09', '2024-04-23', 'FINALIZED'),
    (5, 6, '2024-04-01', '2024-04-11', 'BORROWED'); -- Late for return

INSERT INTO borrowings_books (borrowing_id, book_id)
VALUES 
    (1, 1),
    (1, 2),
    (2, 3),
    (3, 4),
    (4, 5),
    (5, 6);

INSERT INTO borrowings_novels (borrowing_id, novel_id)
VALUES 
    (2, 1),
    (3, 3),
    (4, 5),
    (5, 6);
