package HoangLong.MyShoes;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import HoangLong.MyShoes.security.JwtTokenFilter;
import HoangLong.MyShoes.security.JwtTokenProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	// xac thuc(spring security can xac dinh hai bean de thiet lap va chay authentication 1. userDetailService 2. PasswordEncoder)
	//@Autowired tren ham la gan cac bean cho tham so dau vao cua ham
	@Autowired 
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder(12));
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
		
	}
	
	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
		    .antMatchers("/api/admin/**").hasAnyAuthority("ROLE_ADMIN")
		    
		    .antMatchers("/api/member/**").authenticated()
		    
		    .anyRequest().permitAll()
		    
		    .and().csrf().disable().cors()
		    
		    .configurationSource(new CorsConfigurationSource() {
				@Override
				public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
					// cho phep goi api tu client khac nhau
					CorsConfiguration configuration = new CorsConfiguration();
					configuration.setAllowedOrigins(Arrays.asList("*"));
					configuration.setAllowedMethods(Arrays.asList("*"));
					configuration.setAllowedHeaders(Arrays.asList("*"));
					return configuration;
				}
			})
		    .and().httpBasic().disable(); 
		
		// Apply JWT
		http.addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);	
	
	    return http.build();
	}
	 
}
