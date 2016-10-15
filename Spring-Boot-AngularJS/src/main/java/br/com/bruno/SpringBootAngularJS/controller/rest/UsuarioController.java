package br.com.bruno.SpringBootAngularJS.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.bruno.SpringBootAngularJS.Validation.ValidationError;
import br.com.bruno.SpringBootAngularJS.Validation.ValidationErrorBuilder;
import br.com.bruno.SpringBootAngularJS.model.Usuario;
import br.com.bruno.SpringBootAngularJS.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value="logado", method=RequestMethod.GET)
	public Usuario logado(){
		return usuarioService.logado();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Usuario> list(){
		return usuarioService.list();
	}
	
	@RequestMapping(value="{username}", method=RequestMethod.PUT)
	public Object edit(@PathVariable String username, @RequestBody @Validated(Usuario.validateWithoutSenha.class) Usuario usuario){
		usuarioService.put(username, usuario);
		return usuario;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Object edit(@Validated(Usuario.validateWithSenha.class) @RequestBody Usuario usuario){
		usuarioService.add(usuario);
		return usuario;
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public void delete(@RequestBody String username){
		usuarioService.delete(username);
	}
	
	@ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError handleException(MethodArgumentNotValidException exception) {
        return createValidationError(exception);
    }

    private ValidationError createValidationError(MethodArgumentNotValidException exception) {
        return ValidationErrorBuilder.fromBindingErrors(exception.getBindingResult());
    }
}