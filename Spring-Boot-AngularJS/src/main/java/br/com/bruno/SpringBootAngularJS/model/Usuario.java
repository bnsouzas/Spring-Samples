package br.com.bruno.SpringBootAngularJS.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@JsonIgnoreProperties(value={"password", "authorities", "accountNonExpired"
		, "accountNonLocked", "credentialsNonExpired", "enabled"})
public class Usuario implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotEmpty(groups={ validateWithoutSenha.class, validateWithSenha.class })
	private String username;
	
	@NotEmpty(groups={ validateWithoutSenha.class, validateWithSenha.class })
	@Column(nullable=false)
	private String nome;
	
	@NotEmpty(groups={ validateWithoutSenha.class, validateWithSenha.class })
	@Column(unique=true)
	private String email;
	
	@Column(nullable=false)
	@NotEmpty(groups={ validateWithSenha.class })
	@JsonProperty(access=Access.WRITE_ONLY)
	private String senha;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Role> roles = new ArrayList<Role>();
	
	public interface validateWithSenha{}
	public interface validateWithoutSenha{}
	
	@Override
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}
	@Override
	public String getPassword() {
		return this.senha;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}
