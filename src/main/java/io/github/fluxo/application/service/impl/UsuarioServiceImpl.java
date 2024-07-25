package io.github.fluxo.application.service.impl;

import io.github.fluxo.application.dto.UsuarioDTO;
import io.github.fluxo.application.mapper.UsuarioMapper;
import io.github.fluxo.application.service.UsuarioService;
import io.github.fluxo.domain.model.Perfil;
import io.github.fluxo.domain.model.Usuario;
import io.github.fluxo.infrastructure.repository.PerfilRepository;
import io.github.fluxo.infrastructure.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final UsuarioMapper usuarioMapper;


    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PerfilRepository perfilRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    @Transactional
    public UsuarioDTO cadastrarUsuario(UsuarioDTO dto) {
        Perfil perfilUser = perfilRepository.findByNome("USER");

        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario.setPerfis(Collections.singletonList(perfilUser));

        Usuario u = usuarioRepository.save(usuario);

        return usuarioMapper.toDTO(u);
    }

    @Override
    @Transactional
    public UsuarioDTO editarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = getUsuario(id);

        usuario.setNomeUsuario(usuarioDTO.getNomeUsuario());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setAtivo(usuarioDTO.isAtivo());

        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UsuarioDTO> listarUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable)
                .map(usuarioMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioDTO obterUsuarioPorId(Long id) {
        Usuario usuario = getUsuario(id);
        return usuarioMapper.toDTO(usuario);
    }

    @Override
    @Transactional
    public void excluirUsuario(Long id) {
        Usuario usuario = getUsuario(id);
        usuarioRepository.delete(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUsuario(username);
    }

    private Usuario getUsuario(String username) {
        return usuarioRepository.findByNomeUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    private Usuario getUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
