package br.com.bruno.SpringBootJSP.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.bruno.SpringBootJSP.dao.RoleDAO;
import br.com.bruno.SpringBootJSP.dao.UsuarioDAO;
import br.com.bruno.SpringBootJSP.model.Role;
import br.com.bruno.SpringBootJSP.model.Usuario;


@Controller
@RequestMapping(value="/user")
public class UsuarioController {
	
	private UsuarioDAO usuarioDAO;
	private RoleDAO roleDAO;
	private MessageSource messageSource;
	
	@Autowired
	public UsuarioController(UsuarioDAO usuarioDAO, RoleDAO roleDAO, MessageSource messageSource){
		this.usuarioDAO = usuarioDAO;
		this.roleDAO = roleDAO;
		this.messageSource = messageSource;
	}
	
//	@InitBinder
//	public void initBinder(WebDataBinder dataBinder){
//		dataBinder.addValidators(new UsuarioValidation());
//	}
	
	@RequestMapping("")
	@Cacheable(value="usuarioList")
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView("user/list");
		modelAndView.addObject("users", usuarioDAO.list());
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="signin")
	public ModelAndView signIn(Usuario usuario){
		ModelAndView modelAndView = new ModelAndView("user/signin");
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="edit/{usernameParam}")
	@CacheEvict(value="usuarioList", allEntries=true)
	public ModelAndView edit(@PathVariable String usernameParam, Usuario usuario){
		ModelAndView modelAndView = new ModelAndView("user/edit");
		
		if(usuario.getUsername() == null)
			usuario = usuarioDAO.get(usernameParam);
		
		modelAndView.addObject("usuario", usuario);
		modelAndView.addObject("usernameOrigin", usernameParam);
		modelAndView.addObject("roleTypes", roleDAO.list());
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="signin")
	@CacheEvict(value="usuarioList", allEntries=true)
	public ModelAndView register(@Validated(Usuario.validateWithSenha.class) Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes, Locale loc){
		if (result.hasErrors())
			return signIn(usuario);
		
		usuario.getRoles().add(new Role("ROLE_USER"));
		usuarioDAO.add(usuario);
		redirectAttributes.addFlashAttribute("sucesso", messageSource.getMessage("redirect.message.insert"
																		       , new Object[] { messageSource.getMessage("palavra.usuario", null, loc) }
																		       , loc));
		
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(method=RequestMethod.POST,value="save/{username}")
	@CacheEvict(value="usuarioList", allEntries=true)
	public ModelAndView save(@PathVariable String username, @Validated(Usuario.validateWithoutSenha.class) Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes, Locale loc){
		if (result.hasErrors())
			return edit(username, usuario);
		usuarioDAO.put(username, usuario);
		redirectAttributes.addFlashAttribute("sucesso", messageSource.getMessage("redirect.message.update"
	    	      															   , new Object[] {messageSource.getMessage("palavra.usuario", null, loc), usuario.getUsername()}
																		       , loc));
		return new ModelAndView("redirect:/user");
	}
	
	@RequestMapping(method=RequestMethod.POST, value="delete/{username}")
	@CacheEvict(value="usuarioList", allEntries=true)
	public String delete(@PathVariable String username, RedirectAttributes redirectAttributes, Locale loc){
		usuarioDAO.delete(username);
		redirectAttributes.addFlashAttribute("sucesso", messageSource.getMessage("redirect.message.delete"
										    	      , new Object[] {messageSource.getMessage("palavra.usuario", null, loc), username}
											          , loc));
		return "redirect:/user";
	}
}
