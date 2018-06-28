package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SecurityProvider implements AuthenticationProvider {
	@Autowired
	private MyUserDetailService userDetailsService;
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		UserDetails userDetails = userDetailsService.loadUserByUsername(token.getName());
		if (userDetails == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		if(!userDetails.getPassword().equals(token.getCredentials().toString()))
		{
			  throw new BadCredentialsException("用户密码错误");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),userDetails.getAuthorities());
	}

	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

}