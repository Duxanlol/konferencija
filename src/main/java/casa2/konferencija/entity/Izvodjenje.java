package casa2.konferencija.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Izvodjenje {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Predavanje predavanje;
	
	@ManyToOne
	private Konferencija konferencija;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")	
	private LocalDateTime datum;
	
	private int brojMesta; 
	
	@OneToMany(mappedBy="izvodjenje")
	private List<Mesto> mesta = new ArrayList<Mesto>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Predavanje getPredavanje() {
		return predavanje;
	}

	public void setPredavanje(Predavanje predavanje) {
		this.predavanje = predavanje;
	}

	public Konferencija getKonferencija() {
		return konferencija;
	}

	public void setKonferencija(Konferencija konferencija) {
		this.konferencija = konferencija;
	}
	
	
	public LocalDateTime getDatum() {
		return datum;
	}

	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}

	public int getBrojMesta() {
		return brojMesta;
	}

	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
	}

	public List<Mesto> getMesta() {
		return mesta;
	}

	public void setMesta(List<Mesto> mesta) {
		this.mesta = mesta;
	}
	
	public LocalDate getDate() {
		return LocalDate.of(datum.getYear(), datum.getMonth(), datum.getDayOfMonth());
	}
	
	public LocalTime getTime() {
		return LocalTime.of(datum.getHour(), datum.getMinute());
	}
	
	
}
