-- Cadastrando usuarios e perfis
INSERT INTO fluxo.usuarios (id, nome_usuario, senha, ativo)
VALUES (1, 'admin', '$2a$10$Dw/jigEArZUM5tVBH0MEl.Hj.8tOjhePa1ykC3ttYSzICP1AuMS8e', true);
INSERT INTO fluxo.usuario_perfis (usuario_id, perfil_id)
VALUES (1, 2);

INSERT INTO fluxo.usuarios (id, nome_usuario, senha, ativo)
VALUES (2, 'johndoe', '$2a$10$dqCWceQ0Y2Jd77.cnS1OaOvW890bVw716RoX2mqosjh7/PrLyt3Sm', true);
INSERT INTO fluxo.usuario_perfis (usuario_id, perfil_id)
VALUES (2, 4);

INSERT INTO fluxo.usuarios (id, nome_usuario, senha, ativo)
VALUES (3, 'janesmith', '$2a$10$CYNEV2VfaS80PfUtRGycduIOaLo0e4dMYKiRPwPN6qOCbl5ZMHowW', true);
INSERT INTO fluxo.usuario_perfis (usuario_id, perfil_id)
VALUES (3, 3);

INSERT INTO fluxo.usuarios (id, nome_usuario, senha, ativo)
VALUES (4, 'robertbrown', '$2a$10$zdmcx8VVLphBv1JzZfkqVOXWHpxqJBim1TwiBfwaubJrreGZornFS', true);
INSERT INTO fluxo.usuario_perfis (usuario_id, perfil_id)
VALUES (4, 1);

-- Populando a tabela de faturas
INSERT INTO fluxo.fatura (descricao, valor, data_vencimento, data_pagamento, paga)
VALUES ('Pacote de Papéis A4', 15.99, '2024-08-01', '2024-07-31', true),
       ('Caixa de Cartolina', 25.50, '2024-08-02', null, false),
       ('Rolo de Papel Kraft', 35.75, '2024-08-03', '2024-08-02', true),
       ('Papel Sulfite', 12.00, '2024-08-04', null, false),
       ('Envelope Ofício', 8.99, '2024-08-05', '2024-08-04', true),
       ('Papel Fotográfico', 50.00, '2024-08-06', null, false),
       ('Folhas de Papel Vegetal', 18.25, '2024-08-07', '2024-08-06', true),
       ('Caixa de Papelão', 20.75, '2024-08-08', null, false),
       ('Papel de Presente', 5.50, '2024-08-09', '2024-08-08', true),
       ('Papel Chamex', 10.50, '2024-08-10', null, false),
       ('Pacote de Etiquetas', 7.99, '2024-08-11', '2024-08-10', true),
       ('Papel Couchê', 45.00, '2024-08-12', null, false),
       ('Bloco de Notas', 9.50, '2024-08-13', '2024-08-12', true),
       ('Papel Colorido', 22.30, '2024-08-14', null, false),
       ('Rolo de Papel Higiênico', 3.25, '2024-08-15', '2024-08-14', true),
       ('Papel Reciclado', 27.50, '2024-08-16', null, false),
       ('Folhas de Papel Alumínio', 14.75, '2024-08-17', '2024-08-16', true),
       ('Papel de Parede', 55.00, '2024-08-18', null, false),
       ('Caderno de Anotações', 12.99, '2024-08-19', '2024-08-18', true),
       ('Rolo de Papel Toalha', 6.50, '2024-08-20', null, false),
       ('Papel Crepom', 4.75, '2024-08-21', '2024-08-20', true),
       ('Caixa de Papel Seda', 24.00, '2024-08-22', null, false),
       ('Folhas de Papel de Arroz', 16.75, '2024-08-23', '2024-08-22', true),
       ('Rolo de Papel Filme', 13.00, '2024-08-24', null, false),
       ('Papel Cartão', 28.50, '2024-08-25', '2024-08-24', true),
       ('Papel Adesivo', 19.99, '2024-08-26', null, false),
       ('Caixa de Papel Pardo', 32.00, '2024-08-27', '2024-08-26', true),
       ('Pacote de Papel Jornal', 8.75, '2024-08-28', null, false),
       ('Folhas de Papel Metalizado', 25.99, '2024-08-29', '2024-08-28', true),
       ('Papel Contact', 20.50, '2024-08-30', null, false),
       ('Rolo de Papel Craft', 30.00, '2024-08-31', '2024-08-30', true),
       ('Papel Térmico', 15.50, '2024-09-01', null, false),
       ('Folhas de Papel Seda', 22.75, '2024-09-02', '2024-09-01', true),
       ('Pacote de Papel Fotográfico', 55.50, '2024-09-03', null, false),
       ('Caixa de Papel de Arroz', 18.25, '2024-09-04', '2024-09-03', true),
       ('Rolo de Papel Filme', 13.75, '2024-09-05', null, false),
       ('Papel de Desenho', 10.00, '2024-09-06', '2024-09-05', true),
       ('Folhas de Papel Manilha', 24.50, '2024-09-07', null, false),
       ('Rolo de Papel Kraft', 35.00, '2024-09-08', '2024-09-07', true),
       ('Caixa de Papel de Presente', 5.75, '2024-09-09', null, false),
       ('Papel Higiênico Folha Dupla', 4.25, '2024-09-10', '2024-09-09', true),
       ('Bloco de Papel Jornal', 7.50, '2024-09-11', null, false),
       ('Folhas de Papel Colorido', 23.75, '2024-09-12', '2024-09-11', true),
       ('Pacote de Papel Vegetal', 16.50, '2024-09-13', null, false),
       ('Papel Crepom', 4.50, '2024-09-14', '2024-09-13', true),
       ('Rolo de Papel Alumínio', 13.75, '2024-09-15', null, false),
       ('Papel de Impressão', 9.00, '2024-09-16', '2024-09-15', true);

