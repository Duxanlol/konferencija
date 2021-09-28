package casa2.konferencija.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Uloga implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -341143635179298280L;

	@Id
	@GeneratedValue
	private int id;
	
	private String naziv;
	
	@OneToMany(mappedBy = "uloga")
	private Set<Osoba> osobe = new HashSet<Osoba>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Set<Osoba> getKorisnici() {
		return osobe;
	}

	public void setKorisnici(Set<Osoba> korisnici) {
		this.osobe = korisnici;
	}
	
	public void addOsoba(Osoba osoba) {
		this.osobe.add(osoba);
	}
	

}
