package br.com.sulamerica.igor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity(name = "cargo")
public class Cargo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String descricaoCargo;

	public Cargo() {
		
	}
	public Cargo(String descricaocargo) {
		this.descricaoCargo = descricaocargo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaocargo() {
		return descricaoCargo;
	}

	public void setDescricaocargo(String descricaocargo) {
		this.descricaoCargo = descricaocargo;
	}

	@Override
	public String toString() {
		return "Cargo [id=" + id + ", descricaocargo=" + descricaoCargo + "]";
	}

}
