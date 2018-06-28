package security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import po.UserRole;

public class MyUserDetail implements UserDetails {
	private static final long serialVersionUID = -5619502406659516775L;
	private UserRole myUser;
	private Collection<? extends GrantedAuthority> authorities;

	public MyUserDetail(UserRole user,Collection<? extends GrantedAuthority> authorities) {
		this.myUser = user;
		this.authorities = authorities;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public UserRole getMyUser() {
		return myUser;
	}
	public String getPassword() {
		return myUser.getPassword();
	}

	public String getUsername() {
		return myUser.getUsername();
	}

	public boolean isAccountNonExpired() {
		return false;
	}

	public boolean isAccountNonLocked() {
		return false;
	}

	public boolean isCredentialsNonExpired() {
		return false;
	}

	public boolean isEnabled() {
		return false;
	}

}
