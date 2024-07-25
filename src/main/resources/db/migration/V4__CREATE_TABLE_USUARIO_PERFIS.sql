CREATE TABLE fluxo.usuario_perfis(
    usuario_id BIGINT NOT NULL,
    perfil_id  BIGINT NOT NULL,

    CONSTRAINT pk_usuario_perfis PRIMARY KEY (usuario_id, perfil_id),
    CONSTRAINT fk_usuario_perfis_usuario_id FOREIGN KEY (usuario_id) REFERENCES fluxo.usuarios (id) ON DELETE CASCADE,
    CONSTRAINT fk_usuario_perfis_perfil_id FOREIGN KEY (perfil_id) REFERENCES fluxo.perfis (id) ON DELETE CASCADE
);
