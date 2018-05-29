package br.com.tecnow.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Classe model do Jogo, inserindo os dados requisitados entre banco de dados e Java
 * 
 * @author Nycolas de L. Vieira
 */

public class Jogo {

	// Atributos
	private Long id;
	private Usuario usuario;
	private String nome;
	private CategoriaJogo categoria;
	
	// formatação da data
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataJogo;

	// Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CategoriaJogo getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaJogo categoria) {
		this.categoria = categoria;
	}

	public Date getDataJogo() {
		return dataJogo;
	}

	public void setDataJogo(Date dataJogo) {
		this.dataJogo = dataJogo;
	}
	
}
