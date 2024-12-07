package net.codejava;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuration class for Spring Security.
 * This class defines the security settings for the application, including authentication and authorization.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// Injecting the DataSource bean to interact with the database for user authentication
	@Autowired
	private DataSource dataSource;

	/**
	 * Bean for UserDetailsService.
	 * This service provides user details from the database for authentication.
	 *
	 * @return CustomUserDetailsService implementation.
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	/**
	 * Bean for BCryptPasswordEncoder.
	 * This encoder is used to hash passwords for secure storage.
	 *
	 * @return BCryptPasswordEncoder instance.
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Bean for DaoAuthenticationProvider.
	 * This provider connects the UserDetailsService and PasswordEncoder to handle authentication logic.
	 *
	 * @return DaoAuthenticationProvider instance.
	 */
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	/**
	 * Configures the authentication manager with the custom authentication provider.
	 *
	 * @param auth AuthenticationManagerBuilder to set up authentication.
	 * @throws Exception if an error occurs while configuring authentication.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	/**
	 * Configures HTTP security for the application.
	 * Defines which endpoints require authentication and the behavior of the login/logout process.
	 *
	 * @param http HttpSecurity instance to configure web-based security.
	 * @throws Exception if an error occurs while configuring HTTP security.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// Restrict access to the "/users" endpoint to authenticated users only
				.antMatchers("/users").authenticated()
				// Allow all other endpoints to be accessed without authentication
				.anyRequest().permitAll()
				.and()
				// Configure form-based login
				.formLogin()
				.usernameParameter("email") // Use "email" as the username field
				.defaultSuccessUrl("/users") // Redirect to "/users" upon successful login
				.permitAll()
				.and()
				// Configure logout behavior
				.logout()
				.logoutSuccessUrl("/") // Redirect to "/" upon successful logout
				.permitAll();
	}
}
