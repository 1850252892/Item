package security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mapper.UserRoleMapper;
import po.UserRole;

@Service
public class MyUserDetailService implements UserDetailsService  {
	@Autowired
	UserRoleMapper userdao;
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserRole user =userdao.getUserByName(username);
		if(user==null)
		{
			throw new  UsernameNotFoundException("用户名不存在");
		}
		return new MyUserDetail(user, getAuthorities(user.getRole()));
	}

	private Collection<GrantedAuthority> getAuthorities(String role) {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
		grantedAuthorities.add(grantedAuthority);
		return grantedAuthorities;
	}

}
