-- V2: a primeira evolucao de verdade do banco em producao.
-- Registrar QUANDO cada tarefa foi criada. O Flyway roda este arquivo UMA
-- vez e anota na tabela flyway_schema_history que a versao 2 ja foi aplicada.

ALTER TABLE tarefa ADD COLUMN data_criacao TIMESTAMP;
