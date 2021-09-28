package casa2.konferencija.converter;

import org.springframework.core.convert.converter.Converter;

import casa2.konferencija.entity.Osoba;
import casa2.konferencija.entity.Primanje;

public class OsobaToPrimanjeConverter implements Converter<Osoba, Primanje>{

	@Override
	public Primanje convert(Osoba source) {
		Primanje primanje = new Primanje();
		primanje.setOsoba(source);
		
		return primanje;
	}

}
