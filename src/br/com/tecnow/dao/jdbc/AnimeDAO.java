package br.com.tecnow.dao.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.tecnow.dao.DAO;
import br.com.tecnow.models.Anime;
import br.com.tecnow.models.Usuario;

/**
 * DAO para utilizar no Controller de Animes 
 * 
 * @author Nycolas de L. Vieira
 */

@Repository
public class AnimeDAO implements DAO<Anime>{

	// Injetando o Connection Factory na classe
	private ConnectionFactory connection;
	
	public AnimeDAO() {
		connection = new ConnectionFactory();
	}
	
	/**
	 * Método para adicionar um novo elemento na tabela do banco
	 */
	@Override
	public void adicionar(Anime obj) {
		obj.setDataAnime(new java.util.Date());
		
		String sql = "INSERT INTO tb_anime SET id_usuario = ?, nome = ?, dataAnime = ?";
		
		try {
			connection.open();
			
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			stmt.setLong(1, obj.getUsuario().getId());
			stmt.setString(2, obj.getNome());
			stmt.setDate(3, new Date(obj.getDataAnime().getTime()));
						
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
	public void excluir(Anime obj) {
		String sql = "DELETE FROM tb_anime WHERE id = ?";
		
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
	public void alterar(Anime obj) {
		String sql = "UPDATE tb_anime SET nome = ? WHERE id = ?";
		
		try {
			connection.open();
			
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setLong(2, obj.getId());
			
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
	public Anime buscar(Long id) {
		String sql = "SELECT nome, dataAnime FROM tb_anime WHERE id = ?";
		
		try {			
			connection.open();
			
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			stmt.setLong(1, id);
			
			ResultSet result = stmt.executeQuery();
			
			Anime anime = new Anime();
			if (result.next()) 
			{
				anime.setId(id);
				anime.setNome(result.getString("nome"));
				anime.setDataAnime(result.getDate("dataAnime"));
			}
			
			result.close();
			return anime;
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
	public List<Anime> listar() {
		return null;
	}

	/**
	 * Método que lista todos os elementos da tabela a partir do id do usuário
	 */
	public List<Anime> listaAnimeId(Usuario usuario) {	
		String sql = "SELECT id, nome, dataAnime FROM tb_anime WHERE id_usuario = ?";
		
		List<Anime> lista = new ArrayList<>(); 
		
		try {			
			connection.open();
			
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			stmt.setLong(1, usuario.getId());
			
			ResultSet result = stmt.executeQuery();
			
			Anime anime = new Anime();
			while(result.next()) 
			{
				anime.setId(result.getLong("id"));
				anime.setNome(result.getString("nome"));
				anime.setDataAnime(result.getDate("dataAnime"));
				
				lista.add(anime);
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
