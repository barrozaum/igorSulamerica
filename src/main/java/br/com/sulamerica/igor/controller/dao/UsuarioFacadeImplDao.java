package br.com.sulamerica.igor.controller.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.sulamerica.igor.controller.UsuarioController;
import br.com.sulamerica.igor.controller.dto.UsuarioDTO;
import br.com.sulamerica.igor.model.Usuario;
import br.com.sulamerica.igor.rowmapper.UsuarioRowMapper;
import javassist.NotFoundException;

@Repository
public class UsuarioFacadeImplDao implements IUsuarioFacadeDao {

	private static final Logger logger = LogManager.getLogger(UsuarioFacadeImplDao.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void salvar(Usuario u) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO usuario (ativo, cpf, data_nascimento, nome, sexo, cargo_id, perfil_id)");
		sql.append(" values ");
		sql.append(" (?, ? ,?, ?, ?, ?, ? )");

		jdbcTemplate.update(sql.toString(), new Object[] { u.isAtivo(), u.getCpf(), u.getDataNascimento(), u.getNome(),
				u.getSexo(), u.getCargo().getId(), u.getPerfil().getId() });

	}

	@Override
	public Usuario findByCode(Long id) throws Exception {
		Usuario u = null;
		try {
			String sql = "SELECT * FROM usuario WHERE ID = ?";
			u = (Usuario) jdbcTemplate.queryForObject(sql, new Object[] { id }, new UsuarioRowMapper());
		} catch (Exception e) {
			logger.error("erro ao buscar" + e.getMessage());
		} finally {
			return u;
		}
	}

	@Override
	public void atualizar(Usuario u) throws Exception {

		String sql = "UPDATE usuario SET ativo = ?, cpf = ?, data_nascimento = ? , nome = ?, sexo = ?, cargo_id = ?, perfil_id = ? WHERE id = ?";
		jdbcTemplate.update(sql, new Object[] { u.isAtivo(), u.getCpf(), u.getDataNascimento(), u.getNome(),
				u.getSexo(), u.getCargo().getId(), u.getPerfil().getId(), u.getId() });

	}

	@Override
	public void excluir(Long id) throws Exception {

		String sql = "DELETE FROM usuario WHERE id = ?";
		jdbcTemplate.update(sql, new Object[] { id });
	}

	@Override
	public void inativarUsuario(Usuario u) throws Exception {
		String sql = "UPDATE usuario SET ativo = ? WHERE id = ?";
		jdbcTemplate.update(sql, new Object[] { false, u.getId() });

	}

	@Override
	public List<UsuarioDTO> ListarUsuario(JdbcTemplate jdbcTemplate) throws Exception {

		String sql = " SELECT ativo, cpf, data_nascimento , nome , sexo, descricao_perfil, descricao_cargo\r\n"
				+ "FROM USUARIO u\r\n" + "JOIN PERFIL p \r\n" + "ON  U.perfil_id = p.id\r\n" + "JOIN CARGO c\r\n"
				+ "on u.cargo_id = c.id ";
		List<UsuarioDTO> usuarios = new ArrayList<>();
		usuarios = (List<UsuarioDTO>) jdbcTemplate.query(sql, new RowMapper<UsuarioDTO>() {

			@Override
			public UsuarioDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

				UsuarioDTO usuario = new UsuarioDTO();
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setAtivo(rs.getString("ativo").equalsIgnoreCase("true") ? "Ativo" : "Inativo");
				usuario.setDataNascimento(rs.getDate("data_nascimento").toLocaleString());
				usuario.setPerfil(rs.getString("descricao_perfil"));
				usuario.setCargo(rs.getString("descricao_cargo"));
				usuario.setSexo(rs.getString("sexo"));

				return usuario;
			}

		});
		return usuarios;
	}

	@Override
	public List<UsuarioDTO> buscarUsuariosSexoFeniminoMaiorDe18(JdbcTemplate jdbcTemplate) throws Exception {
		String sql = "select * \r\n"
				+ "   from ( select u.ativo, u.cpf, u.data_nascimento , u.nome , u.sexo, p.descricao_perfil, c.descricao_cargo,   datediff(year,u.data_nascimento,now()) as idade \r\n"
				+ " FROM USUARIO u\r\n" + " JOIN PERFIL p \r\n" + " ON  U.perfil_id = p.id\r\n" + " JOIN CARGO c\r\n"
				+ " on u.cargo_id = c.id " + "    where u.sexo = 'F' \r\n" + "    and u.data_nascimento is not null\r\n"
				+ ") t1\r\n" + "where t1.idade > 18";

		List<UsuarioDTO> usuarios = new ArrayList<>();
		usuarios = (List<UsuarioDTO>) jdbcTemplate.query(sql, new RowMapper<UsuarioDTO>() {

			@Override
			public UsuarioDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

				UsuarioDTO usuario = new UsuarioDTO();
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setAtivo(rs.getString("ativo").equalsIgnoreCase("true") ? "Ativo" : "Inativo");
				usuario.setDataNascimento(rs.getDate("data_nascimento").toLocaleString());
				usuario.setPerfil(rs.getString("descricao_perfil"));
				usuario.setCargo(rs.getString("descricao_cargo"));
				usuario.setSexo(rs.getString("sexo"));

				return usuario;
			}

		});
		return usuarios;
	}

	@Override
	public List<UsuarioDTO> buscarCpfIniciadoComZero(JdbcTemplate jdbcTemplate) throws Exception {
		String sql = " SELECT ativo, cpf, data_nascimento , nome , sexo, descricao_perfil, descricao_cargo\r\n"
				+ " FROM USUARIO u\r\n" + " JOIN PERFIL p \r\n" + " ON  U.perfil_id = p.id\r\n" + " JOIN CARGO c\r\n"
				+ " on u.cargo_id = c.id" + " where SUBSTR(cpf, 1, 1) = 0 ";

		List<UsuarioDTO> usuarios = new ArrayList<>();
		usuarios = (List<UsuarioDTO>) jdbcTemplate.query(sql, new RowMapper<UsuarioDTO>() {

			@Override
			public UsuarioDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

				UsuarioDTO usuario = new UsuarioDTO();
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setAtivo(rs.getString("ativo").equalsIgnoreCase("true") ? "Ativo" : "Inativo");
				usuario.setDataNascimento(rs.getDate("data_nascimento").toLocaleString());
				usuario.setPerfil(rs.getString("descricao_perfil"));
				usuario.setCargo(rs.getString("descricao_cargo"));
				usuario.setSexo(rs.getString("sexo"));

				return usuario;
			}

		});
		return usuarios;

	}

	public List<UsuarioDTO> buscarUsuarioPorNome(JdbcTemplate jdbcTemplate, String nome) throws Exception {

		String sql = " SELECT ativo, cpf, data_nascimento , nome , sexo, descricao_perfil, descricao_cargo\r\n"
				+ "FROM USUARIO u\r\n" 
				+ "JOIN PERFIL p \r\n" 
				+ "ON  U.perfil_id = p.id\r\n" 
				+ "JOIN CARGO c\r\n"
				+ "on u.cargo_id = c.id "
				+ " where lower(u.nome) like ?";
		List<UsuarioDTO> usuarios = new ArrayList<>();
		usuarios = (List<UsuarioDTO>) jdbcTemplate.query(sql, new Object[] { "%"+nome.toLowerCase()+"%" }, new RowMapper<UsuarioDTO>() {

			@Override
			public UsuarioDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

				UsuarioDTO usuario = new UsuarioDTO();
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setAtivo(rs.getString("ativo").equalsIgnoreCase("true") ? "Ativo" : "Inativo");
				usuario.setDataNascimento(rs.getDate("data_nascimento").toLocaleString());
				usuario.setPerfil(rs.getString("descricao_perfil"));
				usuario.setCargo(rs.getString("descricao_cargo"));
				usuario.setSexo(rs.getString("sexo"));

				return usuario;
			}

		});
		return usuarios;
	}

	public List<UsuarioDTO> buscarUsuarioPorCpf(JdbcTemplate jdbcTemplate, String cpf) throws Exception {

		String sql = " SELECT ativo, cpf, data_nascimento , nome , sexo, descricao_perfil, descricao_cargo\r\n"
				+ "FROM USUARIO u\r\n" 
				+ "JOIN PERFIL p \r\n" 
				+ "ON  U.perfil_id = p.id\r\n" 
				+ "JOIN CARGO c\r\n"
				+ "on u.cargo_id = c.id "
				+ " where lower(u.cpf) = ?";
		List<UsuarioDTO> usuarios = new ArrayList<>();
		usuarios = (List<UsuarioDTO>) jdbcTemplate.query(sql, new Object[] { cpf }, new RowMapper<UsuarioDTO>() {

			@Override
			public UsuarioDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

				UsuarioDTO usuario = new UsuarioDTO();
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setAtivo(rs.getString("ativo").equalsIgnoreCase("true") ? "Ativo" : "Inativo");
				usuario.setDataNascimento(rs.getDate("data_nascimento").toLocaleString());
				usuario.setPerfil(rs.getString("descricao_perfil"));
				usuario.setCargo(rs.getString("descricao_cargo"));
				usuario.setSexo(rs.getString("sexo"));

				return usuario;
			}

		});
		return usuarios;
	}

	public List<UsuarioDTO> buscarUsuarioPorCargo(JdbcTemplate jdbcTemplate2, String cargo) throws Exception {

		String sql = " SELECT ativo, cpf, data_nascimento , nome , sexo, descricao_perfil, descricao_cargo\r\n"
				+ "FROM USUARIO u\r\n" 
				+ "JOIN PERFIL p \r\n" 
				+ "ON  U.perfil_id = p.id\r\n" 
				+ "JOIN CARGO c\r\n"
				+ "on u.cargo_id = c.id "
				+ " where lower(c.descricao_cargo) like ?";
		List<UsuarioDTO> usuarios = new ArrayList<>();
		usuarios = (List<UsuarioDTO>) jdbcTemplate.query(sql, new Object[] { "%"+cargo.toLowerCase()+"%" }, new RowMapper<UsuarioDTO>() {

			@Override
			public UsuarioDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

				UsuarioDTO usuario = new UsuarioDTO();
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setAtivo(rs.getString("ativo").equalsIgnoreCase("true") ? "Ativo" : "Inativo");
				usuario.setDataNascimento(rs.getDate("data_nascimento").toLocaleString());
				usuario.setPerfil(rs.getString("descricao_perfil"));
				usuario.setCargo(rs.getString("descricao_cargo"));
				usuario.setSexo(rs.getString("sexo"));

				return usuario;
			}

		});
		return usuarios;
	}

	public List<UsuarioDTO> buscarUsuarioPorPerfil(JdbcTemplate jdbcTemplate2, String perfil) throws Exception {

		String sql = " SELECT ativo, cpf, data_nascimento , nome , sexo, descricao_perfil, descricao_cargo\r\n"
				+ "FROM USUARIO u\r\n" 
				+ "JOIN PERFIL p \r\n" 
				+ "ON  U.perfil_id = p.id\r\n" 
				+ "JOIN CARGO c\r\n"
				+ "on u.cargo_id = c.id "
				+ " where lower(p.descricao_perfil) like ?";
		List<UsuarioDTO> usuarios = new ArrayList<>();
		usuarios = (List<UsuarioDTO>) jdbcTemplate.query(sql, new Object[] { "%"+perfil.toLowerCase()+"%" }, new RowMapper<UsuarioDTO>() {

			@Override
			public UsuarioDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

				UsuarioDTO usuario = new UsuarioDTO();
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setAtivo(rs.getString("ativo").equalsIgnoreCase("true") ? "Ativo" : "Inativo");
				usuario.setDataNascimento(rs.getDate("data_nascimento").toLocaleString());
				usuario.setPerfil(rs.getString("descricao_perfil"));
				usuario.setCargo(rs.getString("descricao_cargo"));
				usuario.setSexo(rs.getString("sexo"));

				return usuario;
			}

		});
		return usuarios;
	}

}
