package br.com.tecnow.models;

/**
 * Enum para limitar as op��es do usu�rio para a op��o Sexo
 * 
 * @author Nycolas de L. Vieira
 */

public enum Sexo {

	// Atributos
	M("Masculino"), F("Feminino"), O("Outro"), P("Prefiro n�o dizer");
	
	public String sexo;

	private Sexo(String sexo) {
		this.sexo = sexo;
	}
	
}
