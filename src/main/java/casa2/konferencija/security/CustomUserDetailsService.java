package casa2.konferencija.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import casa2.konferencija.entity.Osoba;
import casa2.konferencija.repository.OsobaRepository;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
     
    @Autowired
    private OsobaRepository osobaRepository;  
    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Osoba user = osobaRepository.findByEmail(username);
		UserDetailsImpl userDetails =new UserDetailsImpl();
		userDetails.setUsername(user.getEmail());
		userDetails.setPassword(user.getPassword());		
		userDetails.setRole(user.getUloga());
		return userDetails;
		
    }
 
     
 


	
     
}
