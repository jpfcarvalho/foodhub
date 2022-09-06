package br.edu.unicesumar.foodhub.domain;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.edu.unicesumar.foodhub.config.auth.Roles;

public abstract class Users implements UserDetails {

	private static final long serialVersionUID = 1L;

	public abstract String getEmail();

	public abstract void setEmail(String email);

	@Override
	public abstract String getUsername();

	public abstract void setUsername(String username);

	@Override
	public abstract String getPassword();

	public abstract void setPassword(String password);

	public abstract Set<Roles> getRoles();

	public abstract void setRoles(Set<Roles> roles);

	public abstract void addRoles(Roles role);

	public abstract void removeRoles(Roles role);

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Stream
				.concat(getRoles().stream(), getRoles().stream().map(Roles::getCompositeRoles).flatMap(Set::stream))
				.collect(Collectors.toSet());
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}

}