-- ==========================================
-- Migration para adicionar autenticação (users + refresh_tokens)
-- ==========================================

-- Adiciona colunas de autenticação na tabela users
ALTER TABLE users
ADD COLUMN username VARCHAR(100) UNIQUE,
ADD COLUMN password_hash VARCHAR(255) NOT NULL,
ADD COLUMN role VARCHAR(50) DEFAULT 'USER',
ADD COLUMN last_login TIMESTAMP;

-- Cria tabela para refresh tokens (opcional, mas útil em JWT)
CREATE TABLE refresh_tokens (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    token VARCHAR(512) NOT NULL UNIQUE,
    expires_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    revoked_at TIMESTAMP
);
