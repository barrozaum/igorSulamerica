package br.com.sulamerica.igor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sulamerica.igor.model.Cargo;

public interface ICargoRepository extends JpaRepository<Cargo, Long>{

}
