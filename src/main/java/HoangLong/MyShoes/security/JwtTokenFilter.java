package HoangLong.MyShoes.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtTokenFilter extends OncePerRequestFilter{
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	} 

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// doc token tu header
		String token = jwtTokenProvider.resolveToken(request);
		
		try {
			// verify token
			if(token != null && jwtTokenProvider.validateToken(token)) {
				// co token roi thi lay username, goi db len user
				Authentication authentication = jwtTokenProvider.getAuthentication(token);
				// set vao context de co dang nhap roi
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			// this is very important, since it guarantees the user is not authenticated at
			// all
			SecurityContextHolder.clearContext();
			response.sendError(401, e.getMessage());
			return;
		}
		filterChain.doFilter(request, response);
	}
}
