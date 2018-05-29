package br.com.tecnow.models;

/**
 * Enum para limitar as opções do usuário para a categorização dos jogos cadastrados
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
