package br.com.bruno.SpringBootAngularJS.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bruno.SpringBootAngularJS.model.Role;
import br.com.bruno.SpringBootAngularJS.service.RoleService;

@RestController
@RequestMapping(value="/api/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="lista")
	public List<Role> list(){
		return roleService.list();
	}
}
