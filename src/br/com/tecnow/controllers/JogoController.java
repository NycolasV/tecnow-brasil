package br.com.tecnow.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.tecnow.dao.jdbc.JogoDAO;
import br.com.tecnow.models.Jogo;
import br.com.tecnow.models.Usuario;
import br.com.tecnow.util.SessionUtils;

@Controller
public class JogoController {

	@Autowired
	private JogoDAO jogoDao;
	
	@Autowired
	private SessionUtils session;

	@GetMapping("/app/jogo") 
	public String abrirJogo(Model model, @RequestParam(name = "id", 
		required = false) Long id) {

		if (id != null)
		{
			Jogo jogo = jogoDao.buscar(id); 
			
			model.addAttribute("jogo", jogo);
		}
		
		Usuario usuarioLogado = session.getLogin();
		List<Jogo> jogos = jogoDao.listarJogoId(usuarioLogado);
		model.addAttribute("jogos", jogos);
		
		return "item/jogo";
	}
	
	@GetMapping("/app/jogo/excluir")
	public String excluirJogo(@RequestParam(name = "id", 
		required = true) Long id, Jogo jogo) {
		jogo.setId(id);
		jogoDao.excluir(jogo);
		
		return "redirect:/app/jogo";
	}
	
	@PostMapping("/app/jogo/alterar")
	public String alterarJogo(Jogo jogo){
		jogo.setUsuario(session.getLogin());
		
		if (jogo.getId() == null) {
			jogoDao.adicionar(jogo);
		} else {
			jogoDao.alterar(jogo);
		}
		
		return "redirect:/app/jogo";
	}
}
