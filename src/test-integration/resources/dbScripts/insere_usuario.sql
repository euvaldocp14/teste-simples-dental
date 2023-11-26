INSERT INTO pessoa(id, nome, data_nascimento, genero)
VALUES ('e0cf5e78-8c88-11ee-b9d1-0242ac120002', 'Euvaldo Costa Parente', '1991-05-17', 'MASCULINO');

INSERT INTO usuario(id, login, senha, pessoa_id)
VALUES ('f43d0668-8c88-11ee-b9d1-0242ac120002', 'teste123', '$2a$12$MSJfuXuuoyuoxhAz5Y7oYeyKxxR6jKjDsam7C6DRBN4eaflmjOeGq', 'e0cf5e78-8c88-11ee-b9d1-0242ac120002');