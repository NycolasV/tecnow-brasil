package br.com.tecnow.models;

/**
 * Enum para limitar as op��es do usu�rio para a categoriza��o dos jogos cadastrados
 * 
 * @author Nycolas de L. Vieira
 */

public enum CategoriaJogo {

	// Atributos
	T("Tiro"), R("RPG"), P("Plataforma"), E("Esporte"), H("Hack_and_Slash"), O("Outro");
	
	public String categoria;

	private CategoriaJogo(String categoria) {
		this.categoria = categoria;
	}
	
}
