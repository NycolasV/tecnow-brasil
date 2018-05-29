package br.com.tecnow.util;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tecnow.models.Usuario;

/**
 * Insere todos os comandos de SessionUtils facilitando seu uso em outras Classes
 * 
 * @author Nycolas de L. Vieira
 */

@Component
public class SessionUtils {

	// Insere a chave do usu�rio autenticado
	public static final String USUARIO = "usuario";
	
	// Injeta o HttpSession no SessionUtils
	@Autowired
	HttpSession session;
	
	// Retorna o usu�rio para sess�o
	public Usuario getLogin() {
		return (Usuario) session.getAttribute(USUARIO);
	}
	
	// Guarda o usu�rio autenticado na sess�o
	public void setLogin(Usuario usuario) {
		session.setAttribute(USUARIO, usuario);
	}
	
	// Confirma se a sess�o � nula
	public boolean isLogin() {
		return session.getAttribute(USUARIO) != null;
	}

	// Deleta o arquivo da sess�o
	public void closeSession() {
		session.invalidate();
	}
	
}
