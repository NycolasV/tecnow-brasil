package br.com.tecnow.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Classe model do Anime, inserindo os dados requisitados entre banco de dados e Java
 * 
 * @author Nycolas de L. Vieira
 */

public class Anime {

	// Atributos
	private Long id;
	private Usuario usuario;
	private String nome;
	
	// formatação da data
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataAnime;
	
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
	public Date getDataAnime() {
		return dataAnime;
	}
	public void setDataAnime(Date dataAnime) {
		this.dataAnime = dataAnime;
	}
	
}
