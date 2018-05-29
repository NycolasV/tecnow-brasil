package br.com.tecnow.dao.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.tecnow.dao.DAO;
import br.com.tecnow.models.CategoriaJogo;
import br.com.tecnow.models.Jogo;
import br.com.tecnow.models.Usuario;

/**
 * DAO para utilizar no Controller de Jogos
 * 
 * @author Nycolas de L. Vieira
 */

@Repository
public class JogoDAO implements DAO<Jogo>{

	// Injetando o Connection Factory na classe
	private ConnectionFactory connection;
	
	public JogoDAO() {
		connection = new ConnectionFactory();
	}

	/**
	 * Método para adicionar o elemento na tabela
	 */
	@Override
	public void adicionar(Jogo obj) {
		obj.setDataJogo(new java.util.Date());
		
		String sql = "INSERT INTO tb_jogo SET id_usuario = ?, nome = ?, "
				+ "categoria = ?, dataJogo = ?";
		
		try {
			connection.open();
			
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			stmt.setLong(1, obj.getUsuario().getId());
			stmt.setString(2, obj.getNome());
			stmt.setString(3, obj.getCategoria().toString());
			stmt.setDate(4, new Date(obj.getDataJogo().getTime()));
						
			stmt.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
		}
	}
	/**
	 * Método para excluir um elemento da tabela 
	 */
	@Override
	public void excluir(Jogo obj) {
		String sql = "DELETE FROM tb_jogo WHERE id = ?";
		
		try {
			connection.open();
			
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			stmt.setLong(1, obj.getId());
			
			stmt.execute();			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
		}
	}

	/**
	 * Método para alterar o elemento já adicionado da tabela
	 */
	@Override
	public void alterar(Jogo obj) {
		String sql = "UPDATE tb_jogo SET nome = ?, categoria = ? WHERE id = ?";
		
		try {
			connection.open();
			
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getCategoria().toString());
			stmt.setLong(3, obj.getId());
			
			stmt.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
		}
	}

	/**
	 * Método para buscar um elemento da tabela a partir do ID
	 */
	@Override
	public Jogo buscar(Long id) {
		String sql = "SELECT nome, categoria, dataJogo FROM tb_jogo WHERE id = ?";
		
		try {			
			connection.open();
			
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			stmt.setLong(1, id);
			
			ResultSet result = stmt.executeQuery();
			
			Jogo jogo = new Jogo();
			if (result.next()) 
			{
				jogo.setId(id);
				jogo.setNome(result.getString("nome"));
				
				CategoriaJogo categoria = CategoriaJogo.valueOf(result.getString("categoria"));
				jogo.setCategoria(categoria);
				
				jogo.setDataJogo(result.getDate("dataJogo"));
			}
			
			result.close();
			return jogo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally { 
			connection.close(); 
		}
	}

	/**
	 * Método que lista todos os elementos da tabela
	 */
	@Override
	public List<Jogo> listar() {
		return null;
	}
	
	/**
	 * Método que lista todos os elementos da tabela a partir do id do usuário
	 */
	public List<Jogo> listarJogoId(Usuario usuario) {
		String sql = "SELECT id, nome, categoria, dataJogo FROM tb_jogo WHERE id_usuario = ?";
		
		List<Jogo> lista = new ArrayList<>(); 
		
		try {			
			connection.open();
			
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			stmt.setLong(1, usuario.getId());
			
			ResultSet result = stmt.executeQuery();
			
			Jogo jogo = new Jogo();
			while(result.next()) 
			{
				jogo.setId(result.getLong("id"));
				jogo.setNome(result.getString("nome"));
				
				CategoriaJogo categoria = CategoriaJogo.valueOf(result.getString("categoria"));
				jogo.setCategoria(categoria);
				
				jogo.setDataJogo(result.getDate("dataJogo"));
				
				lista.add(jogo);
			}
			
			result.close();
			return lista;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally { 
			connection.close(); 
		}
	}

}
