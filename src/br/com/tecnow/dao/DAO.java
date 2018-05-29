package br.com.tecnow.dao;

import java.util.List;

/**
 * DAO Gen�rica para criar m�todos obrigat�rios em todos os outros DAOs
 * 
 * @author Nycolas de L. Vieira
 */

public interface DAO<T> {

	public void adicionar(T obj);
	
	public void excluir(T obj);
	
	public void alterar(T obj);
	
	public T buscar(Long id);
	
	public List<T> listar();
	
}
