package br.com.tecnow.dao.jdbc;

import java.util.List;

import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.tecnow.dao.DAO;
import br.com.tecnow.models.Sexo;
import br.com.tecnow.models.Usuario;

/**
 * DAO para utilizar no Controller do usuário
 * 
 * @author Nycolas de L. Vieira
 */

@Repository
public class UsuarioDAO implements DAO<Usuario>{

	// Injetando o Connection Factory na classe
	private ConnectionFactory connection;
	
	public UsuarioDAO() {
		connection = new ConnectionFactory();
	}

	/**
	 * Método para adicionar o elemento na tabela
	 */
	@Override
	public void adicionar(Usuario obj) {
		String sql = "INSERT INTO tb_usuario SET "
				+ "nome = ?, email = ?, senha = ?, dataNascimento = ?, sexo = ?";
		
		try {
			connection.open();
			
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getEmail());
			stmt.setString(3, obj.getSenha());
			stmt.setDate(4, new Date(obj.getDataNascimento().getTime()));
			stmt.setString(5, obj.getSexo().toString());
			
			stmt.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
		}
	}
	/**
	 * Método para autenticar o usuário a partir do Interceptor 
	 */
	public Usuario logarUsuario(Usuario usuario) {
		String sql = "SELECT id, nome, dataNascimento, sexo FROM tb_usuario "
				+ "WHERE email = ? AND senha = ?";
		
		try {
			connection.open();
			
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			
			ResultSet result = stmt.executeQuery();
			
			Usuario usuarioLogado = null;
			
			if (result.next()) 
			{
				usuarioLogado = new Usuario();
				usuarioLogado.setId(result.getLong("id"));
				usuarioLogado.setNome(result.getString("nome"));
				usuarioLogado.setDataNascimento(result.getDate("dataNascimento"));
				
				Sexo sexo = Sexo.valueOf(result.getString("sexo"));
				usuarioLogado.setSexo(sexo);
				
				usuarioLogado.setEmail(usuario.getEmail());
				usuarioLogado.setSenha(usuario.getSenha());
			}
			
			result.close();
			return usuarioLogado;
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
	public void excluir(Usuario obj) {
		String sql = "DELETE FROM tb_usuario WHERE id = ?";
		
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
	public void alterar(Usuario obj) {
		String sql = "UPDATE tb_usuario SET nome = ?, email = ?, senha = ?, "
				+ "dataNascimento = ?, sexo = ? WHERE id = ?";
		
		try {
			connection.open();
			
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getEmail());
			stmt.setString(3, obj.getSenha());
			stmt.setDate(4, new Date (obj.getDataNascimento().getTime()));
			stmt.setString(5, obj.getSexo().toString());
			stmt.setLong(6, obj.getId());
			
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
	public Usuario buscar(Long id) {
		String sql = "SELECT nome, email, senha, dataNascimento, sexo "
				+ "FROM tb_usuario WHERE id = ?";
		
		try {			
			connection.open();
			
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			
			stmt.setLong(1, id);
			
			ResultSet result = stmt.executeQuery();
			
			Usuario usuario = new Usuario();
			if (result.next()) 
			{
				usuario.setId(id);
				usuario.setNome(result.getString("nome"));
				usuario.setEmail(result.getString("email"));
				usuario.setSenha(result.getString("senha"));
				usuario.setDataNascimento(result.getDate("dataNascimento"));
			
				Sexo sexo = Sexo.valueOf(result.getString("sexo"));
				usuario.setSexo(sexo);
			}
			
			result.close();
			return usuario;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally { 
			connection.close(); 
		}
	}
	
	/**
	 * Método para autenticar se o email já está no banco de dados
	 */
	public Usuario buscarEmail(String email) {
		String sql = "SELECT * FROM tb_usuario WHERE email = ?";
		
		try {
			connection.open();
			
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet result = stmt.executeQuery();
			
			Usuario usuario = null;
			if (result.next()) 
			{
				usuario = new Usuario();
				usuario.setId(result.getLong("id"));
				usuario.setNome(result.getString("nome"));
				usuario.setEmail(result.getString("email"));
				usuario.setSenha(result.getString("senha"));
				usuario.setDataNascimento(result.getDate("dataNascimento"));
			
				Sexo sexo = Sexo.valueOf(result.getString("sexo"));
				usuario.setSexo(sexo);
			}
			
			result.close();
			return usuario;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			connection.close();
		}
	}
	
	/**
	 * Método para autenticar a troca da senha confirmando outra a senha atual
	 */
	public Usuario buscarSenha(String senha) {
		String sql = "UPDATE tb_usuario SET senha = ? WHERE senha = ?";
		
		try {
			connection.open();
			
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			stmt.setString(1, senha);
			ResultSet result = stmt.executeQuery();
			
			Usuario usuario = null;
			if (result.next()) 
			{
				usuario = new Usuario();
				usuario.setId(result.getLong("id"));
				usuario.setNome(result.getString("nome"));
				usuario.setEmail(result.getString("email"));
				usuario.setSenha(result.getString("senha"));
				usuario.setDataNascimento(result.getDate("dataNascimento"));
			
				Sexo sexo = Sexo.valueOf(result.getString("sexo"));
				usuario.setSexo(sexo);
			}
			
			result.close();
			return usuario;
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
	public List<Usuario> listar() {
		return null;
	}

}
