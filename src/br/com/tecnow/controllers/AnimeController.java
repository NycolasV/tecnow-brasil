package br.com.tecnow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import br.com.tecnow.dao.jdbc.AnimeDAO;
import br.com.tecnow.models.Anime;
import br.com.tecnow.models.Usuario;
import br.com.tecnow.util.SessionUtils;

@Controller
public class AnimeController {

	@Autowired
	private AnimeDAO animeDao;
	
	@Autowired
	private SessionUtils session;
	
	@GetMapping("/app/anime") 
	public String listarAnime(Model model, @RequestParam(name = "id", 
		required = false) Long id) {

		if (id != null)	
		{
			Anime anime = animeDao.buscar(id);
			
			model.addAttribute("anime", anime);
		}
		
		Usuario usuarioLogado = session.getLogin();
		List<Anime> animes = animeDao.listaAnimeId(usuarioLogado);
		model.addAttribute("animes", animes);
		
		return "item/anime";
	}
	
	@GetMapping("/app/anime/excluir")
	public String excluirAnime(@RequestParam(name = "id", 
		required = true) Long id, Anime anime) {
		anime.setId(id);
		animeDao.excluir(anime);
		
		return "redirect:/app/anime";
	}

	@PostMapping("/app/anime/alterar")
	public String alterarAnime(Anime anime, Model model) {
		anime.setUsuario(session.getLogin());
		
		List<String> erros = new ArrayList<>();
		
		if (anime.getNome() == null || 
			anime.getNome().length() > 40) {
			erros.add("O nome é obrigatório e deve conter máximo de 40 caracteres");
		}
		
		if (!erros.isEmpty()) 
		{
			model.addAttribute("erros",	erros);
			return "item/anime";
		}
			
		if (anime.getId() == null) {
			animeDao.adicionar(anime);
		} else {
			animeDao.alterar(anime);
		}
		
		return "redirect:/app/anime";
	}
}
