package br.com.tecnow.models;

/**
 * Enum para limitar as opções do usuário para a opção Sexo
 * 
 * @author Nycolas de L. Vieira
 */

public enum Sexo {

	// Atributos
	M("Masculino"), F("Feminino"), O("Outro"), P("Prefiro não dizer");
	
	public String sexo;

	private Sexo(String sexo) {
		this.sexo = sexo;
	}
	
}
