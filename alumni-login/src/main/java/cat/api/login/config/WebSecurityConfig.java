package cat.api.login.config;

import cat.api.login.security.JwtAuthenticationFilter;
import cat.api.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	
	@Autowired
	private LoginService provider;
	
	@Autowired
	private SecurityConfig config;
	
	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.authenticationProvider(provider);
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		http.authorizeRequests().antMatchers(HttpMethod.POST, config.getSignUpUrl()).permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		http.exceptionHandling().authenticationEntryPoint(new BasicAuthenticationEntryPoint() {

			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().print(config.getErr());
			}
			
		});
	}

	@Override
	public void configure(WebSecurity web){
		web.ignoring()
				.antMatchers("/swagger-ui/**",
						"/swagger-ui-custom.html",
						"/api-docs/**")
				.anyRequest();
	}
	
}