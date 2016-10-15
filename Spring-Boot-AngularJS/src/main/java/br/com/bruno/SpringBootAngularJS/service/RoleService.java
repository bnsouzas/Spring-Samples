package br.com.bruno.SpringBootAngularJS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bruno.SpringBootAngularJS.dao.RoleDAO;
import br.com.bruno.SpringBootAngularJS.model.Role;

@Service
public class RoleService {
	@Autowired
	private RoleDAO roleDAO;
	
	public List<Role> list(){
		return roleDAO.list();
	}
}
