package net.codejava;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {
	private User user;

	public CustomUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null; // Currently, no roles or authorities are implemented
	}

	@Override
	public String getPassword() {
		return user.getPassword(); // Returns the user's hashed password
	}

	@Override
	public String getUsername() {
		return user.getEmail(); // Uses email as the username
	}

	@Override
	public boolean isAccountNonExpired() {
		return true; // Account expiration not implemented
	}

	@Override
	public boolean isAccountNonLocked() {
		return true; // Account locking not implemented
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true; // Credential expiration not implemented
	}

	@Override
	public boolean isEnabled() {
		return true; // User is always enabled
	}

	public String getFullName() {
		return user.getFirstName() + " " + user.getLastName();
	}
}
