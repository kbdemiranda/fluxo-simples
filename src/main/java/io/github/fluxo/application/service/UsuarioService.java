package io.github.fluxo.application.service;

import io.github.fluxo.application.dto.UsuarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {
    UsuarioDTO cadastrarUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO editarUsuario(Long id, UsuarioDTO usuarioDTO);
    Page<UsuarioDTO> listarUsuarios(Pageable pageable);
    UsuarioDTO obterUsuarioPorId(Long id);
    void excluirUsuario(Long id);
}
