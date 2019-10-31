package br.com.sulamerica.igor.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.sulamerica.igor.model.Cargo;
import br.com.sulamerica.igor.model.Perfil;

public class PerfilForm {
	@NotNull
	@NotEmpty
	@Length(min = 1)
	private String descricaoperil;

	public String getDescricaoperil() {
		return descricaoperil;
	}

	public void setDescricaoperil(String descricaoperil) {
		this.descricaoperil = descricaoperil;
	}

	public Perfil converter() {
		Perfil perfil = new Perfil(this.descricaoperil);
		return perfil;
	}

}
