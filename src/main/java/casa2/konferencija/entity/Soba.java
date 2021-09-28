package casa2.konferencija.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Soba {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Smestaj smestaj;
	
	@OneToMany(mappedBy="soba")
	private List<Karta> karte = new ArrayList<Karta>();
	
	private int brojKreveta;

	@GeneratedValue
	private String brojSobe;
	
	public String getBrojSobe() {
		return brojSobe;
	}

	public void setBrojSobe(String brojSobe) {
		this.brojSobe = brojSobe;
	}

	private double cena;

	public void setId(long id) {
		this.id = id;
	}

	public void setSmestaj(Smestaj smestaj) {
		this.smestaj = smestaj;
	}

	public void setKarte(List<Karta> karte) {
		this.karte = karte;
	}

	public void setBrojKreveta(int brojKreveta) {
		this.brojKreveta = brojKreveta;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public long getId() {
		return id;
	}

	public Smestaj getSmestaj() {
		return smestaj;
	}

	public List<Karta> getKarte() {
		return karte;
	}

	public int getBrojKreveta() {
		return brojKreveta;
	}

	public double getCena() {
		return cena;
	}
	
	
}
