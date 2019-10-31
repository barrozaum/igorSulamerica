package br.com.sulamerica.igor.controller.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import br.com.sulamerica.igor.controller.dto.UsuarioDTO;
import br.com.sulamerica.igor.model.Usuario;

public interface IUsuarioFacadeDao {

	public void salvar(Usuario u) throws Exception;

	public Usuario findByCode(Long id) throws Exception;

	public void atualizar(Usuario u) throws Exception;

	public void excluir(Long id) throws Exception;

	public void inativarUsuario(Usuario u) throws Exception;

	List<UsuarioDTO> ListarUsuario(JdbcTemplate jdbcTemplate) throws Exception;

	List<UsuarioDTO> buscarUsuariosSexoFeniminoMaiorDe18(JdbcTemplate jdbcTemplate) throws Exception;

	List<UsuarioDTO> buscarCpfIniciadoComZero(JdbcTemplate jdbcTemplate) throws Exception;

	List<UsuarioDTO> buscarUsuarioPorNome(JdbcTemplate jdbcTemplate, String nome) throws Exception;

	List<UsuarioDTO> buscarUsuarioPorCargo(JdbcTemplate jdbcTemplate, String cargo) throws Exception;

	List<UsuarioDTO> buscarUsuarioPorPerfil(JdbcTemplate jdbcTemplate, String perfil) throws Exception;

	List<UsuarioDTO> buscarUsuarioPorCpf(JdbcTemplate jdbcTemplate, String cpf) throws Exception;
}
