package br.com.sulamerica.igor.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.sulamerica.igor.controller.dto.UsuarioDTO;

public class UsuarioDTORowMapper implements RowMapper<UsuarioDTO>{

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
}
