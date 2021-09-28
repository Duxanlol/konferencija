package casa2.konferencija.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Karta {
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Konferencija konferencija;
	
	@OneToOne
	private Polaznik polaznik;
	
	@ManyToOne(optional = true)
	private Soba soba;
	
	@OneToMany(mappedBy="karta", fetch = FetchType.EAGER)
	private List<Mesto> mesta = new ArrayList<Mesto>();
	
	private double cena;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Konferencija getKonferencija() {
		return konferencija;
	}

	public void setKonferencija(Konferencija konferencija) {
		this.konferencija = konferencija;
	}

	public Polaznik getPolaznik() {
		return polaznik;
	}

	public void setPolaznik(Polaznik polaznik) {
		this.polaznik = polaznik;
	}

	public Soba getSoba() {
		return soba;
	}

	public void setSoba(Soba soba) {
		this.soba = soba;
	}

	public List<Mesto> getMesta() {
		return mesta;
	}

	public void setMesta(List<Mesto> mesta) {
		this.mesta = mesta;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	} 
	
	public void addMesto(Mesto mesto) {
		this.mesta.add(mesto);
	}
	
	
}
