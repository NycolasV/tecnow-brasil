package br.com.tecnow.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.tecnow.util.SessionUtils;

/**
 * Filtra o usu�rio para selecionar quem passa e quais informa��es s�o acess�veis
 * 
 * @author Nycolas de L. Vieira
 */

public class Interceptor extends HandlerInterceptorAdapter{

	// Injeta a SessionUtils na classe Interceptor 
	@Autowired
	SessionUtils session;
	
	// M�todo para filtrar o acesso
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, 
			Object handler) throws Exception {
		boolean autenticacao = req.getRequestURI().contains("/app");
		
		// se o usu�rio for autenticado retorna true
		if (autenticacao && ! session.isLogin()) {
			resp.setStatus(401);
			return true;
		} else {
			return false;
		}
	}
	
}
