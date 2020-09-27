package com.craftsmengroup.paymentsystem.authserver.secure;

import com.craftsmengroup.paymentsystem.authserver.secure.details.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * For configuring the end users recognized by this Authorization Server
 */
@Configuration
@RequiredArgsConstructor
class UserConfig extends WebSecurityConfigurerAdapter {
	private final CustomUserDetailsService customUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/actuator/**").permitAll()
				.mvcMatchers("/.well-known/jwks.json").permitAll()
				.anyRequest().authenticated()
				.and()
			.httpBasic()
				.and()
			.csrf().ignoringRequestMatchers(request -> "/introspect".equals(request.getRequestURI()));
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}