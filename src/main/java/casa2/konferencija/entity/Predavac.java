package casa2.konferencija.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Predavac extends Osoba {
   
	protected Predavac() {};
	
	public Predavac(String email, String ime, String prezime) {
		super(email,ime,prezime);
	}
	
	@OneToMany(
            mappedBy = "predavac")
	List<Predavanje> predavanja = new ArrayList<Predavanje>();

	public List<Predavanje> getPredavanja() {
		return predavanja;
	}

	public void setPredavanja(List<Predavanje> predavanja) {
		this.predavanja = predavanja;
	}

	@Override
	public String toString() {
		return "Predavac [getId()=" + getId() + ", getEmail()=" + getEmail()
				+ ", getIme()=" + getIme() + ", getPrezime()=" + getPrezime() +"]";
	}
	
}
