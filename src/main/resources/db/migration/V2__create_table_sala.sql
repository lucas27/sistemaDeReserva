CREATE TABLE IF NOT EXISTS sala (
    id BIGSERIAL PRIMARY KEY,
    localizacao_id BIGINT NOT NULL,
    nome VARCHAR(100),
    capacidade INT,
    tipo_sala VARCHAR(50)
    ativa BOOLEAN,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

    CONSTRAINT fk_sala_localizacao 
        FOREIGN KEY (localizacao_id) 
        REFERENCES localizacao (id)
);