package br.com.tecnow.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Liga o projeto ao Banco de Dados do MySQL abrindo e fechando a conexão
 * 
 * @author Nycolas de L. Vieira
 */

public class ConnectionFactory {

	private Connection connection;

	// Abre a conexão com o banco de dados
	public void open() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		final String USUARIO = "root";
		final String SENHA = "root132";
		
		String sql = "jdbc:mysql://localhost:3306/db_tecnow?useTimezone=true&serverTimezone=UTC";
		connection = DriverManager.getConnection(sql, USUARIO, SENHA);
	}
	
	// Fecha a conexão com o banco de dados
	public void close() {
		if (connection != null) 
		{
			try {
				connection.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	// Pega a conexão com o banco para manipular as informações do banco
	public Connection getConnection() {
		return connection;
	}
}
