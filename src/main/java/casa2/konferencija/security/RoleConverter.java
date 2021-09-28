package casa2.konferencija.security;


import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import casa2.konferencija.entity.Uloga;
import casa2.konferencija.repository.UlogaRepository;


public class RoleConverter  implements Converter<String, Uloga> {
	
	UlogaRepository ulogaRepository;
	
	public RoleConverter(UlogaRepository ulogaRepository){
		this.ulogaRepository = ulogaRepository;
	}
	
	public Uloga convert(String source) {
			  int idRole=-1;
		       try{
		    	   idRole=Integer.parseInt(source);
		       }
		       catch(NumberFormatException e){
		    	   throw new ConversionFailedException(TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(Uloga.class),idRole, null);
		       }
		       Uloga role= ulogaRepository.findById(idRole);
		      return role;
		  }
}

