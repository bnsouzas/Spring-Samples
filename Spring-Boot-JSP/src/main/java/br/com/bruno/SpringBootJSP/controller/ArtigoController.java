package br.com.bruno.SpringBootJSP.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.bruno.SpringBootJSP.dao.ArtigoDAO;
import br.com.bruno.SpringBootJSP.model.Artigo;
import br.com.bruno.SpringBootJSP.model.Usuario;

@Controller
@RequestMapping(value="/artigo")
public class ArtigoController {
	
	private ArtigoDAO artigoDAO;
	private MessageSource messageSource;
	
	@Autowired
	public ArtigoController(ArtigoDAO artigoDAO, MessageSource messageSource) {
		this.artigoDAO = artigoDAO;
		this.messageSource = messageSource;
	}
	
	@RequestMapping("/")
	public ModelAndView list(@AuthenticationPrincipal Usuario usuario, ModelMap modelMap){
		modelMap.addAttribute("artigos", artigoDAO.list(usuario));
		return new ModelAndView("artigo/list");
	}
	
	@RequestMapping(value="edit/{idParam}")
	public ModelAndView edit(@PathVariable int idParam, Artigo artigo, @AuthenticationPrincipal Usuario usuario){
		if (artigo.getId() == 0)
			artigo = artigoDAO.getArtigo(usuario, idParam);
		
		return new ModelAndView("artigo/edit", "artigo", artigo);
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public ModelAndView add(@AuthenticationPrincipal Usuario usuario, Locale loc){
		Artigo artigo = new Artigo();
		artigo.setUsuario(usuario);
		artigo.setTitulo(messageSource.getMessage("artigo.titulo.padrao", null, loc));
		artigo.setConteudo(messageSource.getMessage("artigo.conteudo.padrao", null, loc));
		
		artigoDAO.add(artigo);
		
		return new ModelAndView("redirect:/artigo/edit/" + artigo.getId());
	}
	
	@RequestMapping(value="edit/save", method=RequestMethod.POST)
	public ModelAndView save(@Valid Artigo artigo, BindingResult result, @AuthenticationPrincipal Usuario usuario){
		
		if (result.hasErrors() && !result.hasFieldErrors("usuario"))
			return edit(artigo.getId(), artigo, usuario);
		artigo.setUsuario(usuario);
		
		artigoDAO.put(artigo);
		
		return new ModelAndView("redirect:/artigo/");
	}
	
	@RequestMapping(value="delete/{idParam}", method=RequestMethod.POST)
	public ModelAndView delete(@PathVariable int idParam, @AuthenticationPrincipal Usuario usuario){
		artigoDAO.delete(idParam, usuario);
		return new ModelAndView("redirect:/artigo/");
	}
}
