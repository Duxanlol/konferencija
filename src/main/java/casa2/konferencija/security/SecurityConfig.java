package casa2.konferencija.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

    @Override
    public void configure(WebSecurity web) throws Exception { //Otvori in-memory-db (ima svoj auth protokol) 
        web
            .ignoring()
            .antMatchers("/h2-console/**", "/osobe/**");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/auth/**", "/osobe/prikaziFormuDodaj", "/konferencije/prikaziSve", "/smestaj/prikaziSve") 
				.permitAll()
				.antMatchers(
	            		"/izvodjenja/prikaziFormuDodaj","izvodjenja/dodaj",
	            		"/konferencije/prikaziFormuDodaj","konferencije/dodaj",
	            		"/osobe/prikaziPredavace","osobe/predavacRaspored",
	            		"/predavanja/prikaziFormuDodaj","predavanja/dodaj",
	            		"/smestaj/prikaziFormuDodaj","/smestaj/dodaj","smestaj/booking",
	            		"/soba/prikaziFormuDodaj","/soba/dodaj").hasRole("ADMIN")
				.antMatchers("/izvodjenja/**", "/konferencije/**", "/predavanja/**", "/smestaj/**").hasAnyRole("USER", "ADMIN")
				.and().formLogin().loginPage("/auth/login").permitAll().loginProcessingUrl("/login")
				.usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/karte/mojeKarte")
				.and()
				.logout().permitAll()
		        .logoutSuccessUrl("/auth/login")
		        .and()
	            .rememberMe().key("uniqueAndSecret");
		 
	}

}
