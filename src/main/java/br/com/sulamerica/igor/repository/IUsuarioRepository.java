package br.com.sulamerica.igor.repository;

import java.util.Optional;

import br.com.sulamerica.igor.model.Usuario;

public interface IUsuarioRepository {

	Optional<Usuario> findById(Long id);

}
