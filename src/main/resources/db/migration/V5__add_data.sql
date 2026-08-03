-- =============================================================================
-- 1. CARGA DE LOCALIZAÇÕES
-- =============================================================================
INSERT INTO localizacao (endereco, andar, descricao) VALUES
('Av. Paulista, 1000', '4º Andar - Bloco A', 'Próximo aos elevadores sociais'),
('Av. Paulista, 1000', '4º Andar - Bloco B', 'Ao lado da copa'),
('Av. Paulista, 1000', '10º Andar - Asa Norte', 'Em frente à recepção principal'),
('Rua da Consolação, 500', '2º Andar', 'Anexo ao auditório principal'),
('Rua da Consolação, 500', '1º Andar', 'Acessível para PCD');

-- =============================================================================
-- 2. CARGA DE SALAS 
-- =============================================================================
INSERT INTO sala (nome, descricao, tipo_sala, ativa, localizacao_id) VALUES
('Sala Alpha', 'Sala de reunião executiva com TV 65" e cabo HDMI', 'COLETIVA', true, 1),
('Sala Beta', 'Espaço individual focado em privacidade e chamadas rápidas', 'INDIVIDUAL', true, 2),
('Auditório Principal', 'Auditório completo com projetor 4K, som e 120 lugares', 'AUDITORIO', true, 3),
('Sala Gamma', 'Sala de treinamento com quadro branco e 15 computadores', 'COLETIVA', true, 4),
('Sala Omega (Em Manutenção)', 'Sala indisponível temporariamente para pintura', 'COLETIVA', false, 5);

-- =============================================================================
-- 3. CARGA DE CLIENTES (Para associar às Reservas)
-- =============================================================================
INSERT INTO cliente (nome, email, telefone) VALUES
('Lucas Silva', 'lucas.silva@email.com', '11999998888'),
('Maria Oliveira', 'maria.oliveira@email.com', '11977776666'),
('Empresa Techcorp Ltda', 'contato@techcorp.com.br', '1133334444');

-- =============================================================================
-- 4. CARGA DE RESERVAS (Para testar Conflitos e Consultas de Horário Livre)
-- =============================================================================
-- Data base dos testes: 2026-08-10 (Segunda-feira)

INSERT INTO reserva (sala_id, cliente_id, data_reserva, hora_reserva, data_final , hora_final , reserva_status) VALUES
-- Reserva 1: Sala Alpha ocupada das 09:00 às 11:00
(1, 1, '2026-08-10', '09:00:00', '2026-08-10', '11:00:00', 'RESERVADA'),

-- Reserva 2: Sala Alpha ocupada à tarde das 14:00 às 16:00
(1, 2, '2026-08-10', '14:00:00', '2026-08-10', '16:00:00', 'RESERVADA'),

-- Reserva 3: Auditório ocupado o dia todo
(3, 3, '2026-08-10', '08:00:00', '2026-08-10', '18:00:00', 'RESERVADA'),

-- Reserva 4: Sala Beta com reserva CANCELADA (Essa sala DEVE aparecer como LIVRE!)
(2, 1, '2026-08-10', '10:00:00', '2026-08-10', '12:00:00', 'CANCELADA');