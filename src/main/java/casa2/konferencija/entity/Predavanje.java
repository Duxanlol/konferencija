package casa2.konferencija.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Predavanje {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String naziv;
	private String opis;
	
	@ManyToOne
	private Predavac predavac;
	
	@OneToMany(mappedBy="predavanje")
	private List<Izvodjenje> izvodjenja = new ArrayList<Izvodjenje>();
	
	@ManyToOne
	private Konferencija konferencija;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Predavac getPredavac() {
		return predavac;
	}

	public void setPredavac(Predavac predavac) {
		this.predavac = predavac;
	}

	public List<Izvodjenje> getIzvodjenja() {
		return izvodjenja;
	}

	public void setIzvodjenja(List<Izvodjenje> izvodjenja) {
		this.izvodjenja = izvodjenja;
	}

	public Konferencija getKonferencija() {
		return konferencija;
	}

	public void setKonferencija(Konferencija konferencija) {
		this.konferencija = konferencija;
	}
	
	
}
