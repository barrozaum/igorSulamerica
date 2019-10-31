package br.com.sulamerica.igor.controller.config;

public class ErrorDeFormularioDTO {

	private String campo;
	private String erro;

	public ErrorDeFormularioDTO() {
		// TODO Auto-generated constructor stub
	}

	public ErrorDeFormularioDTO(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

}
