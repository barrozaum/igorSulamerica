package br.com.sulamerica.igor.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import br.com.sulamerica.igor.model.Usuario;

public class UsuarioRowMapper implements RowMapper<Usuario> {

	@Override
	public Usuario mapRow(ResultSet rs, int arg1) throws SQLException {

		Usuario usuario = new Usuario();
		usuario.setId(rs.getLong("id"));
		usuario.setNome(rs.getString("nome"));
		usuario.setCpf(rs.getString("cpf"));
		usuario.setAtivo(rs.getBoolean("ativo"));
		try {
			usuario.setDataNascimento(rs.getDate("data_nascimento"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return usuario;
	}
}