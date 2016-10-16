package br.com.bruno.SpringBootJSP.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorHandleController {
	
	@ExceptionHandler(Exception.class)
	public ModelAndView trataExceptionGenericas(Exception exception){
		System.out.println("Ocorreu um erro:");
		exception.printStackTrace();
		
		ModelAndView modelAndView = new ModelAndView("erro");
		modelAndView.addObject("exception", exception);
		return modelAndView;
	}
}