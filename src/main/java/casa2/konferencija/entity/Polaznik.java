package casa2.konferencija.entity;

import javax.persistence.Entity;

@Entity
public class Polaznik extends Osoba {

	public Polaznik() {};
	
	public Polaznik(String email, String ime, String prezime) {
		super(email,ime,prezime);
	}

	@Override
	public String toString() {
		return "Polaznik [getId()=" + getId() + ", getEmail()=" + getEmail() + ", getIme()=" + getIme()
				+ ", getPrezime()=" + getPrezime() + "]";
	}
	
	
}
