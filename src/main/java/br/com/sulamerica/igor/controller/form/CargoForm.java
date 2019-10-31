package br.com.sulamerica.igor.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.sulamerica.igor.model.Cargo;

public class CargoForm {

	@NotNull
	@NotEmpty
	@Length(min=1)
	private String descricaocargo;
	
	public String getDescricaocargo() {
		return descricaocargo;
	}

	public void setDescricaocargo(String descricaocargo) {
		this.descricaocargo = descricaocargo;
	}

	public Cargo converter() {
		Cargo cargo = new Cargo(this.descricaocargo);
		return cargo;
	}

	
	
	
}
