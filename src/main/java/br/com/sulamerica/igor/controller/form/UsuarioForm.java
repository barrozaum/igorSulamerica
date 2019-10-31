package br.com.sulamerica.igor.controller.form;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import br.com.sulamerica.igor.model.Cargo;
import br.com.sulamerica.igor.model.Perfil;
import br.com.sulamerica.igor.model.Usuario;
import br.com.sulamerica.igor.repository.ICargoRepository;
import br.com.sulamerica.igor.repository.IPerfilRepository;

public class UsuarioForm {

	@NotNull
	@NotEmpty
	@Length(min = 1)
	private String nome;

	@NotNull
	@NotEmpty
	@CPF
	private String cpf;

	@NotNull
	@NotEmpty
	private String sexo;

	@NotNull
	@NotEmpty
	private String dataNascimento;

	private boolean ativo = true;

	@NotNull
	@NotEmpty
	private String cargo;

	@NotNull
	@NotEmpty
	private String perfil;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public Usuario converter(ICargoRepository cargoRepository, IPerfilRepository perilRepository) {
		Usuario u = new Usuario();
		
		

		// Verificando se o cargo existe
		Optional<Cargo> cargo = cargoRepository.findById(new Long(this.cargo));
		if (cargo.isPresent()) {
			u.setCargo(cargo.get());
		}

		// Verificando se o cargo existe
		Optional<Perfil> perfil = perilRepository.findById(new Long(this.perfil));
		if (perfil.isPresent()) {
			u.setPerfil(perfil.get());
		}

		u.setAtivo(this.ativo);
		u.setCpf(this.cpf);
		u.setSexo(this.sexo);
		u.setNome(this.nome);
		
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date data = formato.parse(this.dataNascimento);
			u.setDataNascimento(data);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return u;

	}
}
