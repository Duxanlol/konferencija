package casa2.konferencija.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Organizator extends Osoba {

	protected Organizator() {};
	
	public Organizator(String email, String ime, String prezime) {
		super(email,ime,prezime);
	}
	
	@OneToMany(mappedBy="organizator")
	private List<Konferencija> konferencije = new ArrayList<Konferencija>();

	public List<Konferencija> getKonferencije() {
		return konferencije;
	}

	public void setKonferencije(List<Konferencija> konferencije) {
		this.konferencije = konferencije;
	}

	@Override
	public String toString() {
		return "Organizator [getId()="
				+ getId() + ", getEmail()=" + getEmail() + ", getIme()=" + getIme() + ", getPrezime()=" + getPrezime()
				+  "]";
	}
	
	
}
