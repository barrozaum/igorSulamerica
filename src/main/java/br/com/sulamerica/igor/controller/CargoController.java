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

import br.com.sulamerica.igor.controller.form.CargoForm;
import br.com.sulamerica.igor.model.Cargo;
import br.com.sulamerica.igor.repository.ICargoRepository;

@RestController
@RequestMapping("/cargo")
public class CargoController {
	@Autowired
	private ICargoRepository cargoRepository;

	@GetMapping
	public List<Cargo> lista() {
		List<Cargo> listaCargos = cargoRepository.findAll();
		return listaCargos;
	}

	// Method : Post
	// url http://localhost:8080/cargo

	@PostMapping
	public ResponseEntity<Cargo> cadastra(@RequestBody CargoForm form, UriComponentsBuilder uriBuilder) {
		Cargo cargo = form.converter();

		cargoRepository.save(cargo);

		URI uri = uriBuilder.path("/cargo/{id}").buildAndExpand(cargo.getId()).toUri();
		return ResponseEntity.created(uri).body(cargo);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Cargo> optional = cargoRepository.findById(id);
		if (optional.isPresent()) {
			cargoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
