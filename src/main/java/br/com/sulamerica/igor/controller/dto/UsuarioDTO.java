package br.com.sulamerica.igor.controller.dto;

public class UsuarioDTO {

	private String ativo;
	private String cpf;
	private String dataNascimento;
	private String nome;
	private String sexo;
	private String perfil;
	private String cargo;

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [ativo=" + ativo + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + ", nome=" + nome
				+ ", sexo=" + sexo + ", perfil=" + perfil + ", cargo=" + cargo + "]";
	}

}
