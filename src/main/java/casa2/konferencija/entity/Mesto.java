package casa2.konferencija.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Mesto {
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Izvodjenje izvodjenje;
	
	@ManyToOne
	private Karta karta;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Izvodjenje getIzvodjenje() {
		return izvodjenje;
	}

	public void setIzvodjenje(Izvodjenje izvodjenje) {
		this.izvodjenje = izvodjenje;
	}

	public Karta getKarta() {
		return karta;
	}

	public void setKarta(Karta karta) {
		this.karta = karta;
	}
	
	
	
}
