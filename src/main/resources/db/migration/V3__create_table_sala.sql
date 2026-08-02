CREATE TABLE IF NOT EXISTS sala (
    id BIGSERIAL PRIMARY KEY,
    localizacao_id BIGINT NOT NULL,
    nome VARCHAR(100) UNIQUE NOT NULL,
    descricao TEXT NOT NULL,
    tipo_sala VARCHAR(10) NOT NULL,
    ativa BOOLEAN NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_sala_localizacao 
        FOREIGN KEY (localizacao_id) 
        REFERENCES localizacao (id)
);