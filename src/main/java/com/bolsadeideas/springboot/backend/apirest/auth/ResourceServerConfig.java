package com.bolsadeideas.springboot.backend.apirest.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/productos", "/api/productos/page/**", "/api/uploads/img/**", "/api/productos/{id}").permitAll()
		/*.antMatchers(HttpMethod.GET,).permitAll()
		.antMatchers(HttpMethod.POST,"/api/productos/upload").hasAnyRole("USER","ADMIN")
		.antMatchers(HttpMethod.POST,"/api/productos").hasRole("ADMIN")
		.antMatchers("/api/productos/**").hasRole("ADMIN")*/
		.anyRequest().authenticated();
		
		
	}
	
	

}
