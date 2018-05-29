package br.com.tecnow.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.tecnow.dao.jdbc.AnimeDAO;
import br.com.tecnow.dao.jdbc.JogoDAO;
import br.com.tecnow.models.Anime;
import br.com.tecnow.models.Jogo;
import br.com.tecnow.util.SessionUtils;

/**
 * 
 * @author Nycolas de L. Vieira
 */

@Controller
@RequestMapping("/app")
public class CadastroItensController {

	@Autowired
	private AnimeDAO animeDao;
	
	@Autowired
	private JogoDAO jogoDao;
	
	@Autowired
	private SessionUtils session;
	
	@GetMapping(value = {"", "/"})
	public String cadastrarItens() {
		return "item/form";
	}

	@PostMapping("/anime/salvar")
	public String salvarAnime(Anime anime, Model model){
		anime.setUsuario(session.getLogin());
	
		List<String> erros = new ArrayList<>();
		
		if (anime.getNome() == null || 
			anime.getNome().length() > 40) {
			erros.add("O nome é obrigatório e deve conter máximo de 40 caracteres");
		}
		
		if (!erros.isEmpty()) 
		{
			model.addAttribute("erros",	erros);
			return "item/form";
		}
		
		if (anime.getId() == null) {
			animeDao.adicionar(anime);
		} else {
			animeDao.alterar(anime);
		}
		
		return "redirect:/app";
	}
	
	@PostMapping("/jogo/salvar")
	public String salvarJogo(Jogo jogo, Model model){
		jogo.setUsuario(session.getLogin());
	
		List<String> erros = new ArrayList<>();
		
		if (jogo.getNome() == null || 
			jogo.getNome().length() > 40) {
			erros.add("O nome é obrigatório e deve conter máximo de 40 caracteres");
		}
		
		if (!erros.isEmpty()) 
		{
			model.addAttribute("erros",	erros);
			return "item/form";
		}
		
		if (jogo.getId() == null) {
			jogoDao.adicionar(jogo);
		} else {
			jogoDao.alterar(jogo);
		}
		
		return "redirect:/app";
	}
	
}
