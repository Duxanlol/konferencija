package casa2.konferencija.converter;

import org.springframework.core.convert.converter.Converter;

import casa2.konferencija.entity.Osoba;
import casa2.konferencija.entity.Placanje;

public class OsobaToPlacanjeConverter implements Converter<Osoba, Placanje>{

	@Override
	public Placanje convert(Osoba source) {
		Placanje placanje = new Placanje();
		placanje.setOsoba(source);
		
		return placanje;
	}

}
