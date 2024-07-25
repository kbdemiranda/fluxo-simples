package io.github.fluxo.application.service.impl;

import io.github.fluxo.application.dto.UsuarioDTO;
import io.github.fluxo.application.mapper.UsuarioMapper;
import io.github.fluxo.domain.model.Perfil;
import io.github.fluxo.domain.model.Usuario;
import io.github.fluxo.infrastructure.repository.PerfilRepository;
import io.github.fluxo.infrastructure.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PerfilRepository perfilRepository;

    @Mock
    private UsuarioMapper usuarioMapper = new UsuarioMapper();

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    public void init() {
        usuarioService = new UsuarioServiceImpl(usuarioRepository, perfilRepository, usuarioMapper);
    }

    @Test
    void cadastrarUsuario() {
        UsuarioDTO usuarioDTO = getUsuarioDTO();
        Usuario usuario = getUsuario();
        Perfil perfilUser = getPerfil();

        when(usuarioMapper.toEntity(usuarioDTO)).thenReturn(usuario);
        when(usuarioMapper.toDTO(usuario)).thenReturn(usuarioDTO);
        when(perfilRepository.findByNome("USER")).thenReturn(perfilUser);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        UsuarioDTO usuarioDTOResult = usuarioService.cadastrarUsuario(usuarioDTO);

        assertEquals(usuarioDTO.getNomeUsuario(), usuarioDTOResult.getNomeUsuario());
    }

    @Test
    void editarUsuario() {
        Long id = 1L;
        UsuarioDTO usuarioDTO = getUsuarioDTO();
        Usuario usuario = getUsuario();
        usuario.setId(id);

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        when(usuarioMapper.toDTO(usuario)).thenReturn(usuarioDTO);

        UsuarioDTO usuarioDTOResult = usuarioService.editarUsuario(id, usuarioDTO);

        assertEquals(usuarioDTO.getNomeUsuario(), usuarioDTOResult.getNomeUsuario());
    }

    @Test
    void listarUsuarios() {
        UsuarioDTO usuarioDTO = getUsuarioDTO();
        Usuario usuario = getUsuario();

        List<Usuario> usuarios = List.of(usuario);
        Page<Usuario> usuarioPage = new PageImpl<>(usuarios);

        when(usuarioRepository.findAll(any(PageRequest.class))).thenReturn(usuarioPage);
        when(usuarioMapper.toDTO(usuario)).thenReturn(usuarioDTO);

        Page<UsuarioDTO> usuarioDTOResult = usuarioService.listarUsuarios(PageRequest.of(0, 10));

        assertEquals(usuarioDTO.getNomeUsuario(), usuarioDTOResult.getContent().get(0).getNomeUsuario());
    }

    @Test
    void obterUsuarioPorId() {
        UsuarioDTO usuarioDTO = getUsuarioDTO();
        Usuario usuario = getUsuario();

        when(usuarioRepository.findById(any())).thenReturn(Optional.of(usuario));
        when(usuarioMapper.toDTO(usuario)).thenReturn(usuarioDTO);

        UsuarioDTO usuarioDTOResult = usuarioService.obterUsuarioPorId(1L);

        assertEquals(usuarioDTO.getNomeUsuario(), usuarioDTOResult.getNomeUsuario());
    }

    @Test
    void excluirUsuario() {
        Long id = 1L;
        Usuario usuario = getUsuario();
        usuario.setId(id);

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        usuarioService.excluirUsuario(id);

        verify(usuarioRepository, times(1)).delete(usuario);
    }

    @Test
    void loadUserByUsername() {
        String username = "testuser";
        Usuario usuario = getUsuario();
        usuario.setNomeUsuario(username);

        when(usuarioRepository.findByNomeUsuario(username)).thenReturn(Optional.of(usuario));

        UserDetails userDetails = usuarioService.loadUserByUsername(username);

        assertEquals(username, userDetails.getUsername());
    }

    @Test
    void loadUserByUsernameNotFound() {
        String username = "nonexistentuser";

        when(usuarioRepository.findByNomeUsuario(username)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> usuarioService.loadUserByUsername(username));
    }

    private static UsuarioDTO getUsuarioDTO() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNomeUsuario("Usuário de teste");
        usuarioDTO.setSenha("senha123");
        return usuarioDTO;
    }

    private static Usuario getUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario("Usuário de teste");
        usuario.setSenha(new BCryptPasswordEncoder().encode("senha123"));
        return usuario;
    }

    private static Perfil getPerfil() {
        Perfil perfil = new Perfil();
        perfil.setNome("ROLE_ADMIN");
        return perfil;
    }
}
