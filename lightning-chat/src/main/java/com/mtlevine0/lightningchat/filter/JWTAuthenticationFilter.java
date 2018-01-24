package com.mtlevine0.lightningchat.filter;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

public class JWTAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(
			ServletRequest req,
			ServletResponse res, 
			FilterChain chain)
			throws IOException, ServletException {
		
		Authentication auth = getAuthentication((HttpServletRequest) req);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		chain.doFilter(req, res);
		
	}
	
	@SuppressWarnings("unchecked")
	private Authentication getAuthentication(HttpServletRequest req) {		
		String jwt = req.getHeader("Authorization");
		
		if(jwt != null) {
			String user = null;
			Collection<GrantedAuthority> authorities = null;
			try{
				user = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).getBody().getSubject();
				authorities = (Collection<GrantedAuthority>) Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).getBody().get("roles");
			} catch(MalformedJwtException e) {
				System.err.println(e);
				return null;
			} catch(SignatureException e) {
				System.err.println(e);
				return null;
			}
			String roleList = "";
			for(Object authority: authorities) {
				roleList = roleList + authority.toString().substring(11).replace("}", "") + ',';
			}
			
			Collection<GrantedAuthority> authority = AuthorityUtils.commaSeparatedStringToAuthorityList(roleList);
			
			return user != null ? new UsernamePasswordAuthenticationToken(user, null, authority) : null;
		}
		return null;
	}
	
}
