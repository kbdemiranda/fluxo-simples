package io.github.fluxo.application.mapper;

import io.github.fluxo.application.dto.UsuarioDTO;
import io.github.fluxo.domain.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UsuarioMapper {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNomeUsuario(usuario.getNomeUsuario());
        dto.setSenha(usuario.getSenha());
        dto.setAtivo(usuario.isAtivo());
        return dto;
    }

    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .nomeUsuario(usuarioDTO.getNomeUsuario())
                .senha(passwordEncoder.encode(usuarioDTO.getSenha()))
                .ativo(usuarioDTO.isAtivo())
                .build();
    }
}
