CREATE TABLE IF NOT EXISTS reserva (
    id BIGSERIAL PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    sala_id BIGINT NOT NULL,
    data_reserva DATE NOT NULL,
    hora_reserva TIME NOT NULL,
    hora_final TIME NOT NULL,
    data_final DATE NOT NULL,
    reserva_status VARCHAR(30)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

    CONSTRAINT fk_reserva_cliente
        FOREIGN KEY (cliente_id) 
        REFERENCES cliente (id)
    CONSTRAINT fk_reserva_sala
        FOREIGN KEY (sala_id) 
        REFERENCES sala (id)
);