package io.github.fluxo.application.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nomeUsuario;
    private String senha;
    private boolean ativo = true;
}
