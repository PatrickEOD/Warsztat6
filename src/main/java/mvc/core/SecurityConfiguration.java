package mvc.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DBuserDetailsService dbUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		super.configure(http); -- to jest nie potrzebne
		http.authorizeRequests()
			.antMatchers("/login", "/register") //definiuje strony
				.permitAll() // które są dostepne do autoryzacji
			.anyRequest() // jake kolwiek żądanie po stronie login
				.authenticated() //musi byc autentykowane
			.and()
			.formLogin()//formatowanie loginu
				.loginPage("/login")//strona logowania
				.loginProcessingUrl("/login")//przekierowanie na stronę
				.usernameParameter("username")
				.passwordParameter("password")
			.and()
			.httpBasic();//reszta rzeczy domyślna
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//hardcoding
//	@Bean
//	public UserDetailsService userDetailsService() {
//		String encodedPassword = passwordEncoder().encode("bbb");
//		UserDetails user = User.builder()
//			.username("aaa")
//			.password(encodedPassword)
//			.roles()
//			.build();
//		
//		List<UserDetails> userDetails = Collections.singletonList(user);
//		
//		return new InMemoryUserDetailsManager(userDetails);
//	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(userDetailsService()); --- na potrzeby hardcodingu
		provider.setUserDetailsService(dbUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
}
