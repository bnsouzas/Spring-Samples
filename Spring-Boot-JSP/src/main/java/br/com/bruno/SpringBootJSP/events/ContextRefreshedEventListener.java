package br.com.bruno.SpringBootJSP.events;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.bruno.SpringBootJSP.dao.RoleDAO;
import br.com.bruno.SpringBootJSP.model.Role;

@Component
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private RoleDAO roleDAO;
    
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		System.out.println("ContextRefreshedEvent");
		List<Role> roles = new ArrayList<>();
		
		roles.add(new Role("ROLE_ADMIN"));
		roles.add(new Role("ROLE_USER"));
		
		for(Role role : roles){
			if (roleDAO.get(role.getNome()) == null){
				roleDAO.add(role);
			}
		}
	}
}
