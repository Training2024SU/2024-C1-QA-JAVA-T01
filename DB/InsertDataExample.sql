-- Insertar autores
INSERT INTO author (id, name) VALUES 
('1', 'Gabriel García Márquez'),
('2', 'J.K. Rowling'),
('3', 'Stephen King'),
('4', 'Haruki Murakami'),
('5', 'Agatha Christie'),
('6', 'Ernest Hemingway');

-- Insertar usuarios
INSERT INTO user (id, name, email, password, role) VALUES 
('1', 'John Doe', 'administrador@pingu.com.co', 'contrasenasegura123456', 'ADMINISTRATOR'),
('2', 'Reader Johnson', 'reader1@example.com', 'reader1pass', 'READER'),
('3', 'Assistant Williams', 'assistant1@example.com', 'assistant1pass', 'ASSISTANT'),
('5', 'Reader Davis', 'reader2@example.com', 'reader2pass', 'READER'),
('6', 'Assistant Wilson', 'assistant2@example.com', 'assistant2pass', 'ASSISTANT');

-- Insertar libros
INSERT INTO book (book_id, title, quantity, quantity_loaned, category, author_id) VALUES 
('1', 'Cien annos de soledad', 10, 1, 'Ficcion', '1'),
('2', 'Harry Potter y la piedra filosofal', 8, 1, 'Fantasia', '2'),
('3', 'El resplandor', 15, 1, 'Terror', '3'),
('4', 'Tokio blues', 20, 1, 'Drama', '4'),
('5', 'Asesinato en el Orient Express', 12, 1, 'Misterio', '5');

-- Insertar novelas
INSERT INTO novel (novel_id, title, recommend_age, quantity, quantity_loaned, gender, author_id) VALUES 
('6', 'Cien annos de soledad (versión juvenil)', 14, 10, 1, 'Ficcion', '1'),
('7', 'Harry Potter y la camara secreta', 16, 8, 1, 'Fantasia', '2'),
('8', 'It', 18, 15, 1, 'Terror', '3'),
('9', 'Norwegian Wood', 12, 20, 1, 'Drama', '4'),
('10', 'Asesinato en el Nilo', 14, 12, 1, 'Misterio', '5');

-- Insertar préstamos de libros
INSERT INTO book_loan (book_loan_id, user_id, book_id, loan_date, return_date, status) VALUES 
('1', '2', '1', '2024-04-15', DATE_ADD('2024-04-15', INTERVAL 15 DAY), 'REQUESTED'),
('2', '5', '2', '2024-04-16', DATE_ADD('2024-04-16', INTERVAL 15 DAY), 'COMPLETED'),
('3', '2', '3', '2024-04-17', DATE_ADD('2024-04-17', INTERVAL 15 DAY), 'FINISHED'),
('4', '5', '4', '2024-04-18', DATE_ADD('2024-04-18', INTERVAL 15 DAY), 'REQUESTED'),
('5', '2', '5', '2024-04-19', DATE_ADD('2024-04-19', INTERVAL 15 DAY), 'FINISHED');

-- Insertar préstamos de novelas
INSERT INTO novel_loan (novel_loan_id, user_id, novel_id, loan_date, return_date, status) VALUES 
('6', '2', '6', '2024-04-15', DATE_ADD('2024-04-15', INTERVAL 15 DAY), 'REQUESTED'),
('7', '5', '7', '2024-04-16', DATE_ADD('2024-04-16', INTERVAL 15 DAY), 'COMPLETED'),
('8', '2', '8', '2024-04-17', DATE_ADD('2024-04-17', INTERVAL 15 DAY), 'FINISHED'),
('9', '5', '9', '2024-04-18', DATE_ADD('2024-04-18', INTERVAL 15 DAY), 'REQUESTED'),
('10', '2', '10', '2024-04-19', DATE_ADD('2024-04-19', INTERVAL 15 DAY), 'FINISHED');
