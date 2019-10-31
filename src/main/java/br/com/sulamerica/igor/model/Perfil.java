package br.com.sulamerica.igor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity(name = "perfil")
public class Perfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String descricaoPerfil;

	public Perfil() {
		// TODO Auto-generated constructor stub
	}
	
	public Perfil(String descricaoperil) {
		this.descricaoPerfil = descricaoperil;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoPerfil() {
		return descricaoPerfil;
	}

	public void setDescricaoPerfil(String descricaoPerfil) {
		this.descricaoPerfil = descricaoPerfil;
	}

	@Override
	public String toString() {
		return "Perfil [id=" + id + ", descricaoPerfil=" + descricaoPerfil + "]";
	}

}
