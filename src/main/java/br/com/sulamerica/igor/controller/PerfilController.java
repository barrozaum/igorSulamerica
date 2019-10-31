package br.com.sulamerica.igor.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sulamerica.igor.controller.form.PerfilForm;
import br.com.sulamerica.igor.model.Perfil;
import br.com.sulamerica.igor.repository.IPerfilRepository;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	private IPerfilRepository perilRepository;

	@GetMapping
	public List<Perfil> lista() {
		List<Perfil> listaCargos = perilRepository.findAll();
		return listaCargos;
	}

	@PostMapping
	public ResponseEntity<Perfil> cadastra(@RequestBody PerfilForm form, UriComponentsBuilder uriBuilder) {
		Perfil perfil = form.converter();
		perilRepository.save(perfil);
		URI uri = uriBuilder.path("/perfil/{id}").buildAndExpand(perfil.getId()).toUri();
		return ResponseEntity.created(uri).body(perfil);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Perfil> optional = perilRepository.findById(id);
		if (optional.isPresent()) {
			perilRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}