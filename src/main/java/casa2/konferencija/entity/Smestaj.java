package casa2.konferencija.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Smestaj {

	@Id
	@GeneratedValue
	private long id;
	
	private String naziv;
	
    @OneToMany(
            mappedBy = "smestaj")
	private List<Soba> sobe = new ArrayList<Soba>();
	
	@OneToOne
	private PredstavnikSmestaja predstavnik;

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

	public List<Soba> getSobe() {
		return sobe;
	}

	public void setSobe(List<Soba> sobe) {
		this.sobe = sobe;
	}

	public PredstavnikSmestaja getPredstavnik() {
		return predstavnik;
	}

	public void setPredstavnik(PredstavnikSmestaja predstavnik) {
		this.predstavnik = predstavnik;
	}

	
}
