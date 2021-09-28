package casa2.konferencija.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class PredstavnikSmestaja extends Osoba {

	protected PredstavnikSmestaja() {};
	
	public PredstavnikSmestaja(String email, String ime, String prezime) {
		super(email,ime,prezime);
	}
	
	@OneToOne
	private Smestaj smestaj;

	public Smestaj getSmestaj() {
		return smestaj;
	}

	public void setSmestaj(Smestaj smestaj) {
		this.smestaj = smestaj;
	}

	@Override
	public String toString() {
		return "PredstavnikSmestaja [smestaj=" + smestaj + ", getSmestaj()=" + getSmestaj() + ", getId()=" + getId()
				+ ", getEmail()=" + getEmail() + ", getIme()=" + getIme() + ", getPrezime()=" + getPrezime()
				+  "]";
	}
	
}
