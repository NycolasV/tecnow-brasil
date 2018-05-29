package br.com.tecnow.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.tecnow.dao.jdbc.UsuarioDAO;
import br.com.tecnow.models.Usuario;
import br.com.tecnow.util.SessionUtils;

/**
 * @author Nycolas de L. Vieira
 */

@Controller
public class UsuarioController {

	@Autowired
    private	UsuarioDAO usuarioDao;
	
	@Autowired
	private SessionUtils session;
		
	@RequestMapping("/cadastro")
	public String abrirCadastro() {
		return "usuario/cadastro";
	}
	
	@PostMapping("/cadastro/salvar")
	public String salvarCadastro(Usuario usuario, Model model) {
		List<String> erros = new ArrayList<>();
		
		if ( usuario.getNome() == null || 
			 usuario.getNome().length() < 2 ||
			 usuario.getNome().length() > 60) {
				erros.add("O nome é obrigatório e deve conter de 2 a 60 caracteres");
		}
		
		
		if(usuarioDao.buscarEmail(usuario.getEmail()) != null) {
			erros.add("O email inserido já está cadastrado");
		}

		if ( usuario.getEmail() == null || 
			 usuario.getEmail().length() < 2 ||
			 usuario.getEmail().length() > 120) {
				erros.add("O email é obrigatório e deve conter de 2 a 120 caracteres");
		}
		
		if (usuario.getSenha() == null ||
			usuario.getSenha().length() < 2 ||
			usuario.getSenha().length() > 20) {
				erros.add("A senha é obrigatória e deve conter de 2 a 20 caracteres");
		}
		
		Date date = new Date();	
		if ( usuario.getDataNascimento() == null || 
		     usuario.getDataNascimento().getTime() > date.getTime()) {
				erros.add("A data de nascimento é obrigatório e não pode ser maior que a data atual");
		}

		if (!erros.isEmpty()) 
		{
			model.addAttribute("erros",	erros);
			return "usuario/cadastro";
		}
		
		usuario.HashearSenha();
		
		usuarioDao.adicionar(usuario);
		
		return "redirect:/";
	}
	
	@PostMapping("/app/cadastro/editar")
	public String editarCadastro(Usuario usuario, Model model, 
			@RequestParam(name = "id", required = true) Long id) {
		usuario.setId(id);
				
		List<String> erros = new ArrayList<>();
		
		if ( usuario.getNome() == null || 
			 usuario.getNome().length() < 2 ||
			 usuario.getNome().length() > 60) {
				erros.add("O nome é obrigatório e deve conter de 2 a 60 caracteres");
		}
		
		
		if(usuarioDao.buscarEmail(usuario.getEmail()) != null) {
			erros.add("O email inserido já está cadastrado");
		}

		if ( usuario.getEmail() == null || 
			 usuario.getEmail().length() < 2 ||
			 usuario.getEmail().length() > 120) {
				erros.add("O email é obrigatório e deve conter de 2 a 120 caracteres");
		}
		
		if (usuario.getSenha() == null ||
			usuario.getSenha().length() < 2 ||
			usuario.getSenha().length() > 20) {
				erros.add("A senha é obrigatória e deve conter de 2 a 20 caracteres");
		}
		
		Date date = new Date();	
		if ( usuario.getDataNascimento() == null || 
		     usuario.getDataNascimento().getTime() > date.getTime()) {
				erros.add("A data de nascimento é obrigatório e não pode ser maior que a data atual");
		}

		if (!erros.isEmpty()) 
		{
			model.addAttribute("erros",	erros);
			return "usuario/cadastro-editar";
		}
		
		if (usuario.getId() != null) {
			usuarioDao.alterar(usuario);
		}
		
		return "redirect:/app";
	}
	
	@GetMapping("/cadastro/excluir")
	public String excluirCadastro(@RequestParam(name = "id", 
		required = true) Long id, Usuario usuario) {
		usuario.setId(id);
		usuarioDao.excluir(usuario);
	
		return "redirect:/";
	}
	
	@RequestMapping("/login")
	public String abrirLogin() {
		return "usuario/login";
	}
	
	@PostMapping("/login/entrar")
	public String entrarLogin(Usuario usuario, Model model) {	
		List<String> erros = new ArrayList<>();
		
		if ( usuario.getEmail() == null || 
			 usuario.getEmail().length() < 2 ||
			 usuario.getEmail().length() > 120) {
				erros.add("O email é obrigatório e deve conter de 2 a 120 caracteres");
		}
		
		if (usuario.getSenha() == null ||
			usuario.getSenha().length() < 2 ||
			usuario.getSenha().length() > 20) {
				erros.add("A senha é obrigatória e deve conter de 2 a 20 caracteres");
		}
		
		if (!erros.isEmpty()) 
		{
			model.addAttribute("erros",	erros);
			return "usuario/login";
		}
		
		usuario.HashearSenha();
		Usuario usuarioLogado = usuarioDao.logarUsuario(usuario);
		
		if (usuarioLogado == null) 
		{
			return "usuario/login";
		}
		
		session.setLogin(usuarioLogado);
		return "redirect:/app";
	}

	@GetMapping("/login/sair")
	public String sairLogin() {	
		session.closeSession();
		
		return  "redirect:/";
	}
}
