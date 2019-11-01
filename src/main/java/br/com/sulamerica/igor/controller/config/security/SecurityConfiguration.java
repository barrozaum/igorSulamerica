package br.com.sulamerica.igor.controller.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	// autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	}

	// Configuração de autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/usuario").permitAll()
		.antMatchers(HttpMethod.GET, "/usuario/*").permitAll()
		.antMatchers(HttpMethod.POST, "/usuario").permitAll()
		.antMatchers(HttpMethod.PUT, "/usuario").permitAll()
		.antMatchers(HttpMethod.DELETE, "/usuario").permitAll()
		.antMatchers(HttpMethod.GET , "/perfil").permitAll()
		.antMatchers(HttpMethod.POST , "/perfil").permitAll()
		.antMatchers(HttpMethod.GET , "/cargo").permitAll()
		.antMatchers(HttpMethod.POST , "/cargo").permitAll()
		.and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		
	}

	// configuração de recursos estáticos
	@Override
	public void configure(WebSecurity web) {
		// TODO Auto-generated method stub
		super.configure(web);
	}
	
}
